package com.techelevator.dao;

import com.techelevator.model.CharacterDTO;
import com.techelevator.model.ClassDTO;

import java.util.List;
import java.util.Map;

public interface CharacterDao {
    /**
     * retrieves a character by its unique id from the database
     *
     * @param id character id
     * @return retrieved character
     */
    public CharacterDTO getCharacterById(int id);
    /**
     * retrieves a list of all characters registered to a specific
     * user from the database
     *
     * @param id user id
     * @return List of retrieved characters
     */
    public List<CharacterDTO> getCharactersByUserId(int id);
    /**
     * edits the specified character in the database
     *
     * @param id character id
     * @return edited character
     */
    public CharacterDTO editCharacter(CharacterDTO updatedCharacter);
}
