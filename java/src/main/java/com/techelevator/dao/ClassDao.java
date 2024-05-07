package com.techelevator.dao;

import com.techelevator.model.ClassDTO;

import java.util.List;

public interface ClassDao {
    /**
     * retrieves a List containing ClassDtos to represent
     * a character's classes, subclasses, and level in that class
     *
     * @param id character id
     * @return List of ClassDtos
     */
    public List<ClassDTO> getClassesAndSubclassesByCharacterId(int id);

    /**
     * retrieves a map containing all of the classes & subclasses
     * of a particular character, with the class being the key
     * and the subclass being the value
     *
     * @param id character id
     * @return Map of class, subclass
     */
    public void addNewClassAndSubclassByCharacterId(int id, ClassDTO classDTO);
}
