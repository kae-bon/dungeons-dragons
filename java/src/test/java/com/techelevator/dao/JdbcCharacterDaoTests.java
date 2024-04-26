package com.techelevator.dao;

import com.techelevator.model.CharacterDTO;
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
    Map<String, String> CHARACTER_ONE_CLASSES = new HashMap<>();
    Map<String, String> CHARACTER_TWO_CLASSES = new HashMap<>();
    Map<String, String> CHARACTER_THREE_CLASSES = new HashMap<>();
    CharacterDTO CHARACTER_ONE = new CharacterDTO(2, 1, "Neme", "Satyr", "Chaotic Neutral", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150573/x3hhbbomsbf1pa4ybw60.jpg", CHARACTER_ONE_CLASSES, 5);
    CharacterDTO CHARACTER_TWO = new CharacterDTO(2, 2, "Rhywyn", "Halfling", "None", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150698/qmzaf08mchmqsoeajnif.jpg", CHARACTER_TWO_CLASSES, 5);
    CharacterDTO CHARACTER_THREE = new CharacterDTO(2, 3, "Chicken", "Tiefling", "Chaotic Good", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714153760/dswrsvil5g9i65xhakof.jpg", CHARACTER_THREE_CLASSES, 3);

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCharacterDao(jdbcTemplate);
        CHARACTER_ONE_CLASSES.put("sorcerer", "Divine Soul");
        CHARACTER_TWO_CLASSES.put("bard", "College of Swords");
        CHARACTER_TWO_CLASSES.put("rogue", "Swashbuckler");
        CHARACTER_THREE_CLASSES.put("druid", "Circle of Spores");
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
        Map<String, String> expected = new HashMap<>();
        expected.put("sorcerer", "Divine Soul");
        Map<String, String> actual = sut.getClassesAndSubclassesByCharacterId(1);
        Assert.assertEquals(expected, actual);

        expected.clear();
        expected.put("rogue", "Swashbuckler");
        expected.put("bard", "College of Swords");
        Assert.assertTrue(expected.size() == 2);
        actual = sut.getClassesAndSubclassesByCharacterId(2);
        Assert.assertEquals(expected, actual);
    }

    private void assertCharactersMatch(CharacterDTO expected, CharacterDTO actual) {
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getRace(), actual.getRace());
        Assert.assertEquals(expected.getAlignment(), actual.getAlignment());
        Assert.assertEquals(expected.getProfilePic(), actual.getProfilePic());
        Assert.assertEquals(expected.getCurrentLevel(), actual.getCurrentLevel());
        Assert.assertEquals(expected.getClassesSubclasses(), actual.getClassesSubclasses());
    }

}
