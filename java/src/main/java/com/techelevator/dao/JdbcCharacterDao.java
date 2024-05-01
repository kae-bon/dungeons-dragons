package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ClassDTO;
import com.techelevator.model.CharacterDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JdbcCharacterDao implements CharacterDao {
    JdbcTemplate jdbc;

    public JdbcCharacterDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public CharacterDTO getCharacterById(int id) {
        return null;
    }

    @Override
    public List<CharacterDTO> getCharactersByUserId(int id) {
        List<CharacterDTO> characters = new ArrayList<>();
        String sql = "SELECT characters.character_id, user_id, character_name, character_race, current_level, alignment, profile_pic\n" +
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

    public List<ClassDTO> getClassesAndSubclassesByCharacterId(int id) {
//        Map<String, String> classesSubclasses = new HashMap<>();
        List<ClassDTO> classesSubclasses = new ArrayList<>();
        ClassDTO classDTO = null;
        String sql = "SELECT class_name, subclass_name, class_level\n" +
                "FROM classes\n" +
                "JOIN character_classes AS cc ON cc.class_id = classes.class_id\n" +
                "JOIN subclasses AS s ON s.subclass_id = cc.subclass_id\n" +
                "JOIN characters ON cc.character_id = characters.character_id\n" +
                "WHERE characters.character_id = ?;";
        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, id);
            while (results.next()) {
                classDTO = new ClassDTO(results.getString("class_name"),
                                        results.getString("subclass_name"),
                                        results.getInt("class_level"));
                classesSubclasses.add(classDTO);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered for that user id.");
        }
        return classesSubclasses;
    }

    private CharacterDTO mapRowToCharacter(SqlRowSet results) {
        CharacterDTO character = new CharacterDTO(results.getInt("user_id"),
                                                results.getInt("character_id"),
                                                results.getString("character_name"),
                                                results.getString("character_race"),
                                                results.getString("alignment"),
                                                results.getString("profile_pic"),
                                                this.getClassesAndSubclassesByCharacterId(results.getInt("character_id")),
                                                results.getInt("current_level"));
        return character;
    }


}
