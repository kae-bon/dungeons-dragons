package com.techelevator.dao;

import com.techelevator.model.CharacterDTO;
import com.techelevator.model.CharacterClassDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class JdbcCharacterDaoTests extends BaseDaoTests {
    JdbcCharacterDao sut;
    JdbcClassDao classDao;
    List<CharacterClassDTO> CHARACTER_ONE_CLASSES = new ArrayList<>();
    List<CharacterClassDTO> CHARACTER_TWO_CLASSES = new ArrayList<>();
    List<CharacterClassDTO> CHARACTER_THREE_CLASSES = new ArrayList<>();
    CharacterDTO CHARACTER_ONE = new CharacterDTO(2, 1, "Neme", "Satyr", "Chaotic Neutral", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150573/x3hhbbomsbf1pa4ybw60.jpg", CHARACTER_ONE_CLASSES, 5);
    CharacterDTO CHARACTER_TWO = new CharacterDTO(2, 2, "Rhywyn", "Halfling", "None", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714150698/qmzaf08mchmqsoeajnif.jpg", CHARACTER_TWO_CLASSES, 5);
    CharacterDTO CHARACTER_THREE = new CharacterDTO(2, 3, "Chicken", "Tiefling", "Chaotic Good", "https://res.cloudinary.com/dccsx3iht/image/upload/v1714153760/dswrsvil5g9i65xhakof.jpg", CHARACTER_THREE_CLASSES, 3);

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        classDao = new JdbcClassDao(jdbcTemplate);
        sut = new JdbcCharacterDao(jdbcTemplate, classDao);
        CHARACTER_ONE_CLASSES.add(new CharacterClassDTO("sorcerer", "Divine Soul", 5));
        CHARACTER_TWO_CLASSES.add(new CharacterClassDTO("bard", "College of Swords", 3));
        CHARACTER_TWO_CLASSES.add(new CharacterClassDTO("rogue", "Swashbuckler", 2));
        CHARACTER_THREE_CLASSES.add(new CharacterClassDTO("druid", "Circle of Spores", 3));
    }

    @Test
    public void get_character_gets_character() {
        CharacterDTO actual = sut.getCharacterById(1);
        assertCharactersMatch(CHARACTER_ONE, actual);

        actual = sut.getCharacterById(2);
        assertCharactersMatch(CHARACTER_TWO, actual);
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
    public void edit_character_edits_character() {
        CharacterDTO characterToUpdate = sut.getCharacterById(1);

        characterToUpdate.setAlignment("Chaotic Evil");
        characterToUpdate.setCurrentLevel(6);

        CharacterDTO updatedCharacter = sut.editCharacter(characterToUpdate);
        CharacterDTO retrievedCharacter = sut.getCharacterById(updatedCharacter.getId());

        assertCharactersMatch(characterToUpdate, retrievedCharacter);
        assertCharactersMatch(updatedCharacter, retrievedCharacter);
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

    private void assertClassSubclassMatch(CharacterClassDTO expected, CharacterClassDTO actual) {
        Assert.assertEquals(expected.getCharacterClass(), actual.getCharacterClass());
        Assert.assertEquals(expected.getSubclass(), actual.getSubclass());
        Assert.assertEquals(expected.getClassLevel(), actual.getClassLevel());
    }

}
