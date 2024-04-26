package com.techelevator.dao;

import com.techelevator.model.CharacterDTO;

import java.util.List;

public interface CharacterDao {
    public CharacterDTO getCharacterById(int id);
    public List<CharacterDTO> getCharactersByUserId(int id);
}
