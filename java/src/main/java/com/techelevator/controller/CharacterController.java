package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.CharacterDTO;
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

    public CharacterController(CharacterDao characterDao, UserDao userDao) {
        this.characterDao = characterDao;
        this.userDao = userDao;
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

    @PutMapping(path="/characters/{id}")
    public CharacterDTO editCharacterInfo(@PathVariable int id, @RequestBody CharacterDTO character) {
        try {
            character.setId(id);
            return characterDao.editCharacter(character);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not edit this character - try again later.");
        }
    }
}
