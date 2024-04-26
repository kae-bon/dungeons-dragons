package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CharacterDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
    }

    public Map<String, String> getClassesAndSubclassesByCharacterId(int id) {
        Map<String, String> classesSubclasses = new HashMap<>();
        String sql = "SELECT class_name, subclass_name\n" +
                "FROM classes\n" +
                "JOIN character_classes AS cc ON cc.class_id = classes.class_id\n" +
                "JOIN subclasses AS s ON s.subclass_id = cc.subclass_id\n" +
                "JOIN characters ON cc.character_id = characters.character_id\n" +
                "WHERE characters.character_id = ?;";
        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, id);
            while (results.next()) {
                classesSubclasses.put(results.getString("class_name"),
                                    results.getString("subclass_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered for that user id.");
        }
        return classesSubclasses;
    }

    private CharacterDTO mapRowToCharacter(SqlRowSet results) {
        CharacterDTO character = new CharacterDTO();

        return character;
    }
}
