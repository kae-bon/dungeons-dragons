package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ClassDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcClassDao implements ClassDao {
    JdbcTemplate jdbc;

    public JdbcClassDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ClassDTO> getClassesAndSubclassesByCharacterId(int id) {
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

    @Override
    public void addNewClassAndSubclassByCharacterId(int id, ClassDTO classDTO) {
        String sql = "INSERT INTO character_classes(character_id, class_id, subclass_id, class_level)\n" +
                "VALUES (?, (SELECT class_id FROM classes WHERE class_name ILIKE ?), \n" +
                "(SELECT subclass_id FROM subclasses WHERE subclass_name ILIKE ?), ?);";
        try {
            int rowsAffected = jdbc.update(sql, id, classDTO.getCharacterClass(),
                                classDTO.getSubclass(), classDTO.getClassLevel());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not insert class for character because some of the provided data" +
                    "isn't correct.");
        }
    }

}
