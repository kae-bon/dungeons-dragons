package com.techelevator.dao;

import com.techelevator.model.CharacterClassDTO;
import com.techelevator.model.ClassSubclassesDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;

import java.util.ArrayList;
import java.util.List;

public class JdbcClassDaoTests extends BaseDaoTests {

   private JdbcClassDao sut;
    private List<CharacterClassDTO> CHARACTER_ONE_CLASSES = new ArrayList<>();
    private List<CharacterClassDTO> CHARACTER_TWO_CLASSES = new ArrayList<>();
    private List<CharacterClassDTO> CHARACTER_THREE_CLASSES = new ArrayList<>();
    private List<String> SUBCLASSES = new ArrayList<>();
    private List<ClassSubclassesDTO> CLASSES_SUBCLASSES = new ArrayList<>();

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcClassDao(jdbcTemplate);
        CHARACTER_ONE_CLASSES.add(new CharacterClassDTO("sorcerer", "Divine Soul", 5));
        CHARACTER_TWO_CLASSES.add(new CharacterClassDTO("bard", "College of Swords", 3));
        CHARACTER_TWO_CLASSES.add(new CharacterClassDTO("rogue", "Swashbuckler", 2));
        CHARACTER_THREE_CLASSES.add(new CharacterClassDTO("druid", "Circle of Spores", 3));
    }
    @Test
    public void get_classes_and_subclasses_returns_correct() {
        List<CharacterClassDTO> expected = new ArrayList<>();
        expected.add(new CharacterClassDTO("sorcerer", "Divine Soul", 5));
        List<CharacterClassDTO> actual = sut.getClassesAndSubclassesByCharacterId(1);
        assertClassSubclassMatch(expected.get(0), actual.get(0));

        expected.clear();
        expected.add(new CharacterClassDTO("bard", "College of Swords", 3));
        expected.add(new CharacterClassDTO("rogue", "Swashbuckler", 2));
        Assert.assertTrue(expected.size() == 2);
        actual = sut.getClassesAndSubclassesByCharacterId(2);
        assertClassSubclassMatch(expected.get(0), actual.get(0));
    }

    @Test
    public void add_class_subclass_to_character() {
        CharacterClassDTO expected = new CharacterClassDTO("paladin", "Oath of Devotion", 3);

        sut.addNewClassAndSubclassByCharacterId(1, expected);

        List<CharacterClassDTO> actual = sut.getClassesAndSubclassesByCharacterId(1);
        Assert.assertTrue(actual.size() == 2);

        assertClassSubclassMatch(actual.get(0), expected);
    }

    @Test
    public void get_all_classes_and_subclasses() {
        List<ClassSubclassesDTO> classSubclassesDTOS = sut.getAllClassesAndSubclasses();
        Assert.assertTrue(classSubclassesDTOS.size() == 13);

        Assert.assertEquals(classSubclassesDTOS.get(0).getClassName(), "wizard");
    }

    @Test
    public void get_subclasses_for_class() {
        SUBCLASSES.add("Alchemist");
        SUBCLASSES.add("Armorer");
        SUBCLASSES.add("Artillerist");
        SUBCLASSES.add("Battle Smith");

        List<String> subclasses = sut.getSubclassesForClass("Artificer");
        Assert.assertTrue(SUBCLASSES.size() == subclasses.size());
        Assert.assertEquals(SUBCLASSES, subclasses);
    }

    private void assertClassSubclassMatch(CharacterClassDTO expected, CharacterClassDTO actual) {
        Assert.assertEquals(expected.getCharacterClass(), actual.getCharacterClass());
        Assert.assertEquals(expected.getSubclass(), actual.getSubclass());
        Assert.assertEquals(expected.getClassLevel(), actual.getClassLevel());
    }
}
