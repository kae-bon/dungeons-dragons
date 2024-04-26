package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CharacterController {
    CharacterDao characterDao;

    public CharacterController(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
}
