package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CharacterDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCharacterDao implements CharacterDao {
    JdbcTemplate jdbc;
    JdbcClassDao classDao;
    private final String sqlLevel = "(SELECT SUM(class_level) AS total_level\n" +
            "  FROM character_classes\n" +
            "  WHERE character_id = characters.character_id)\n";

    public JdbcCharacterDao(JdbcTemplate jdbc, JdbcClassDao classDao) {
        this.jdbc = jdbc;
        this.classDao = classDao;
    }

    @Override
    public CharacterDTO getCharacterById(int id) {
        CharacterDTO character = null;
        String sql = "SELECT character_id, user_id, character_name, character_race, (SELECT SUM(class_level) AS total_level\n" +
                "FROM character_classes\n" +
                "WHERE character_id = characters.character_id) AS char_level, alignment, profile_pic\n" +
                "FROM characters\n" +
                "WHERE character_id = ?;";

        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, id);
            if (results.next()) {
                character = mapRowToCharacter(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered under that id.");
        }

        return character;
    }

    @Override
    public List<CharacterDTO> getCharactersByUserId(int id) {
        List<CharacterDTO> characters = new ArrayList<>();
        String sql = "SELECT characters.character_id, user_id, character_name, character_race, \n" +
                "(SELECT SUM(class_level) AS total_level\n" +
                "FROM character_classes\n" +
                "WHERE character_id = characters.character_id) AS char_level\n" +
                ", alignment, profile_pic\n" +
                "FROM characters\n" +
                "JOIN character_classes AS cc ON cc.character_id = characters.character_id\n" +
                "JOIN classes AS c ON c.class_id = cc.class_id\n" +
                "JOIN subclasses AS s ON s.class_id = cc.class_id\n" +
                "WHERE user_id = ? AND s.subclass_id = cc.subclass_id\n" +
                "GROUP BY characters.character_id;";
        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, id);

            while (results.next()) {
                CharacterDTO character = mapRowToCharacter(results);
                characters.add(character);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered for that user id.");
        }

        return characters;
    }

    @Override
    public CharacterDTO editCharacter(CharacterDTO character) {
        CharacterDTO updatedCharacter = null;
        String sql = "UPDATE characters\n" +
                "SET character_id=?, user_id=?, character_name=?, character_race=?, current_level=?, alignment=?, profile_pic=?\n" +
                "WHERE character_id=?;";
        try {
            int numberOfRows = jdbc.update(sql, character.getId(), character.getUserId(),
                                            character.getName(),character.getRace(), character.getCurrentLevel(),
                                            character.getAlignment(), character.getProfilePic(), character.getId());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one.");
            } else {
                updatedCharacter = this.getCharacterById(character.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered for that user id.");
        }
        return updatedCharacter;
    }


    private CharacterDTO mapRowToCharacter(SqlRowSet results) {
        CharacterDTO character = new CharacterDTO(results.getInt("user_id"),
                                                results.getInt("character_id"),
                                                results.getString("character_name"),
                                                results.getString("character_race"),
                                                results.getString("alignment"),
                                                results.getString("profile_pic"),
                                                classDao.getClassesAndSubclassesByCharacterId(results.getInt("character_id")),
                                                results.getInt("char_level"));
        return character;
    }


}
