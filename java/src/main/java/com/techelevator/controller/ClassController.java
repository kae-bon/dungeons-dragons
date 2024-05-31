package com.techelevator.controller;

import com.techelevator.dao.ClassDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.ClassSubclassesDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ClassController {
    ClassDao classDao;

    public ClassController(ClassDao classDao) {
        this.classDao = classDao;
    }

    @GetMapping(path="/classes")
    public List<ClassSubclassesDTO> getAllClasses() {
        try {
            return classDao.getAllClassesAndSubclasses();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not access database. Please try again later.");
        }
    }

    @GetMapping(path="/subclasses")
    public List<String> getSubclassesPerClass(@RequestParam String className) {
        try {
            List<String> subclasses = new ArrayList<>();
            List<ClassSubclassesDTO> classSubclassesDTOS = classDao.getAllClassesAndSubclasses();
            for (ClassSubclassesDTO classSubclassesDTO : classSubclassesDTOS) {
                if (classSubclassesDTO.getClassName().equalsIgnoreCase(className)) {
                    subclasses = classSubclassesDTO.getSubclasses();
                    break;
                }
            }
            return subclasses;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not access database. Please try again later.");
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found.");
        }
    }

}
