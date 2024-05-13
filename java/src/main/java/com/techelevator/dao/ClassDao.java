package com.techelevator.dao;

import com.techelevator.model.CharacterClassDTO;
import com.techelevator.model.ClassSubclassesDTO;

import java.util.List;

public interface ClassDao {
    /**
     * retrieves a List containing ClassDtos to represent
     * a character's classes, subclasses, and level in that class
     *
     * @param id character id
     * @return List of ClassDtos
     */
    public List<CharacterClassDTO> getClassesAndSubclassesByCharacterId(int id);

    /**
     * retrieves a map containing all of the classes & subclasses
     * of a particular character, with the class being the key
     * and the subclass being the value
     *
     * @param id character id
     * @return Map of class, subclass
     */
    public void addNewClassAndSubclassByCharacterId(int id, CharacterClassDTO characterClassDTO);

    /**
     * retrieves a List of all available classes, including an array of the subclasses
     * available for said class
     *
     * @return List of ClassSubclassesDTOs
     */
    public List<ClassSubclassesDTO> getAllClassesAndSubclasses();
}
