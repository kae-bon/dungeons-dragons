package com.techelevator.dao;

import com.techelevator.model.ClassDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcClassDaoTests extends BaseDaoTests {

    JdbcClassDao sut;
    List<ClassDTO> CHARACTER_ONE_CLASSES = new ArrayList<>();
    List<ClassDTO> CHARACTER_TWO_CLASSES = new ArrayList<>();
    List<ClassDTO> CHARACTER_THREE_CLASSES = new ArrayList<>();
    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcClassDao(jdbcTemplate);
        CHARACTER_ONE_CLASSES.add(new ClassDTO("sorcerer", "Divine Soul", 5));
        CHARACTER_TWO_CLASSES.add(new ClassDTO("bard", "College of Swords", 3));
        CHARACTER_TWO_CLASSES.add(new ClassDTO("rogue", "Swashbuckler", 2));
        CHARACTER_THREE_CLASSES.add(new ClassDTO("druid", "Circle of Spores", 3));
    }
    @Test
    public void get_classes_and_subclasses_returns_correct() {
        List<ClassDTO> expected = new ArrayList<>();
        expected.add(new ClassDTO("sorcerer", "Divine Soul", 5));
        List<ClassDTO> actual = sut.getClassesAndSubclassesByCharacterId(1);
        assertClassSubclassMatch(expected.get(0), actual.get(0));

        expected.clear();
        expected.add(new ClassDTO("bard", "College of Swords", 3));
        expected.add(new ClassDTO("rogue", "Swashbuckler", 2));
        Assert.assertTrue(expected.size() == 2);
        actual = sut.getClassesAndSubclassesByCharacterId(2);
        assertClassSubclassMatch(expected.get(0), actual.get(0));
    }

    @Test
    public void add_class_subclass_to_character() {
        ClassDTO expected = new ClassDTO("paladin", "Oath of Devotion", 3);

        sut.addNewClassAndSubclassByCharacterId(1, expected);

        List<ClassDTO> actual = sut.getClassesAndSubclassesByCharacterId(1);
        Assert.assertTrue(actual.size() == 2);

        assertClassSubclassMatch(actual.get(0), expected);
    }

    private void assertClassSubclassMatch(ClassDTO expected, ClassDTO actual) {
        Assert.assertEquals(expected.getCharacterClass(), actual.getCharacterClass());
        Assert.assertEquals(expected.getSubclass(), actual.getSubclass());
        Assert.assertEquals(expected.getClassLevel(), actual.getClassLevel());
    }
}
