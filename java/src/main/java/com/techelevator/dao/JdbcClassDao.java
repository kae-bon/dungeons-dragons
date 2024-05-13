package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.CharacterClassDTO;
import com.techelevator.model.ClassSubclassesDTO;
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

    public List<CharacterClassDTO> getClassesAndSubclassesByCharacterId(int id) {
        List<CharacterClassDTO> classesSubclasses = new ArrayList<>();
        CharacterClassDTO characterClassDTO = null;
        String sql = "SELECT class_name, subclass_name, class_level\n" +
                "FROM classes\n" +
                "JOIN character_classes AS cc ON cc.class_id = classes.class_id\n" +
                "JOIN subclasses AS s ON s.subclass_id = cc.subclass_id\n" +
                "JOIN characters ON cc.character_id = characters.character_id\n" +
                "WHERE characters.character_id = ?;";
        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, id);
            while (results.next()) {
                characterClassDTO = new CharacterClassDTO(results.getString("class_name"),
                        results.getString("subclass_name"),
                        results.getInt("class_level"));
                classesSubclasses.add(characterClassDTO);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Couldn't find any characters registered for that user id.");
        }
        return classesSubclasses;
    }




    @Override
    public void addNewClassAndSubclassByCharacterId(int id, CharacterClassDTO characterClassDTO) {
        String sql = "INSERT INTO character_classes(character_id, class_id, subclass_id, class_level)\n" +
                "VALUES (?, (SELECT class_id FROM classes WHERE class_name ILIKE ?), \n" +
                "(SELECT subclass_id FROM subclasses WHERE subclass_name ILIKE ?), ?);";
        try {
            int rowsAffected = jdbc.update(sql, id, characterClassDTO.getCharacterClass(),
                                characterClassDTO.getSubclass(), characterClassDTO.getClassLevel());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Could not insert class for character because some of the provided data" +
                    "isn't correct.");
        }
    }

    @Override
    public List<ClassSubclassesDTO> getAllClassesAndSubclasses() {
        List<ClassSubclassesDTO> classesAndSubclasses = new ArrayList<>();
        String sql = "SELECT class_name\n" +
                "FROM classes;";

        try {
            SqlRowSet results = jdbc.queryForRowSet(sql);
            while (results.next()) {
                ClassSubclassesDTO classSubclassesDTO = new ClassSubclassesDTO();
                classSubclassesDTO.setClassName(results.getString("class_name"));
                classSubclassesDTO.setSubclasses(getSubclassesForClass(classSubclassesDTO.getClassName()));
                classesAndSubclasses.add(classSubclassesDTO);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("That is not a valid class.");
        }

        return classesAndSubclasses;
    }

    public List<String> getSubclassesForClass(String className) {
        List<String> subclasses = new ArrayList<>();
        String sql = "SELECT subclass_name\n" +
                "FROM subclasses\n" +
                "JOIN classes ON subclasses.class_id = classes.class_id\n" +
                "WHERE class_name ILIKE ?;";
        try {
            SqlRowSet results = jdbc.queryForRowSet(sql, className);
            while (results.next()) {
                subclasses.add(results.getString("subclass_name"));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server - please try again later.");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("That is not a valid class.");
        }

        return subclasses;
    }

}
