package com.techelevator.dao;

import com.techelevator.model.CharacterDTO;
import com.techelevator.model.ClassDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcCharacterDaoTests extends BaseDaoTests {
    JdbcCharacterDao sut;
    List<ClassDTO> CHARACTER_ONE_CLASSES = new ArrayList<>();
    List<ClassDTO> CHARACTER_TWO_CLASSES = new ArrayList<>();
    List<ClassDTO> CHARACTER_THREE_CLASSES = new ArrayList<>();
    CharacterDTO CHARACTER_ONE = new CharacterDTO(2, 1, "Neme", "Satyr", "Chaotic Neutral", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150573/x3hhbbomsbf1pa4ybw60.jpg", CHARACTER_ONE_CLASSES, 5);
    CharacterDTO CHARACTER_TWO = new CharacterDTO(2, 2, "Rhywyn", "Halfling", "None", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150698/qmzaf08mchmqsoeajnif.jpg", CHARACTER_TWO_CLASSES, 5);
    CharacterDTO CHARACTER_THREE = new CharacterDTO(2, 3, "Chicken", "Tiefling", "Chaotic Good", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714153760/dswrsvil5g9i65xhakof.jpg", CHARACTER_THREE_CLASSES, 3);

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCharacterDao(jdbcTemplate);
        CHARACTER_ONE_CLASSES.add(new ClassDTO("sorcerer", "Divine Soul", 5));
        CHARACTER_TWO_CLASSES.add(new ClassDTO("bard", "College of Swords", 3));
        CHARACTER_TWO_CLASSES.add(new ClassDTO("rogue", "Swashbuckler", 2));
        CHARACTER_THREE_CLASSES.add(new ClassDTO("druid", "Circle of Spores", 3));
    }

    @Test
    public void get_characters_by_user_id() {
        List<CharacterDTO> expected = new ArrayList<>();
        expected.add(CHARACTER_ONE);
        expected.add(CHARACTER_TWO);
        expected.add(CHARACTER_THREE);

        List<CharacterDTO> actual = sut.getCharactersByUserId(2);
        Assert.assertNotNull(actual);
        Assert.assertTrue(actual.size() == 3);

        for (int i = 0; i < expected.size(); i++) {
            assertCharactersMatch(expected.get(i), actual.get(i));
        }
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

    private void assertCharactersMatch(CharacterDTO expected, CharacterDTO actual) {
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRace(), actual.getRace());
        Assert.assertEquals(expected.getAlignment(), actual.getAlignment());
        Assert.assertEquals(expected.getProfilePic(), actual.getProfilePic());
        Assert.assertEquals(expected.getCurrentLevel(), actual.getCurrentLevel());

        for (int i = 0; i < expected.getClassesSubclasses().size(); i++) {
            assertClassSubclassMatch(expected.getClassesSubclasses().get(i), actual.getClassesSubclasses().get(i));
        }
    }

    private void assertClassSubclassMatch(ClassDTO expected, ClassDTO actual) {
        Assert.assertEquals(expected.getCharacterClass(), actual.getCharacterClass());
        Assert.assertEquals(expected.getSubclass(), actual.getSubclass());
        Assert.assertEquals(expected.getClassLevel(), actual.getClassLevel());
    }

}
