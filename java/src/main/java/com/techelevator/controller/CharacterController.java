package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.ClassDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CharacterDTO;
import com.techelevator.model.CharacterClassDTO;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class CharacterController {
    CharacterDao characterDao;
    UserDao userDao;
    ClassDao classDao;

    public CharacterController(CharacterDao characterDao, UserDao userDao, ClassDao classDao) {
        this.characterDao = characterDao;
        this.userDao = userDao;
        this.classDao = classDao;
    }

    @GetMapping(path="/users/characters")
    public List<CharacterDTO> getCharactersForUser(Principal principal) {
        try {
            User user = userDao.getUserByUsername(principal.getName());
            int userId = user.getId();
            return characterDao.getCharactersByUserId(userId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find characters registered for the currently logged in user.");
        }
    }

    @GetMapping(path="/characters/{id}")
    public CharacterDTO getCharacterById(@PathVariable int id) {
        try {
            return characterDao.getCharacterById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }

    @PutMapping(path="/characters/{id}")
    public CharacterDTO editCharacterInfo(@PathVariable int id, @RequestBody CharacterDTO character, Principal principal) {
        try {
            User user = userDao.getUserByUsername(principal.getName());
            character.setUserId(user.getId());
            character.setId(id);
            return characterDao.editCharacter(character);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not edit this character.");
        }
    }

    @PostMapping(path="/characters/{id}/classes")
    public CharacterDTO addClassSubclassToCharacter(@PathVariable int id, @RequestBody CharacterClassDTO characterClassDTO) {
        try {
            classDao.addNewClassAndSubclassByCharacterId(id, characterClassDTO);
            return characterDao.getCharacterById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find this character.");
        }
    }
}
