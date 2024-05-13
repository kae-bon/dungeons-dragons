package com.techelevator.model;

public class CharacterClassDTO {
    private String characterClass;
    private String subclass;
    private int classLevel;

    public CharacterClassDTO(){}
    public CharacterClassDTO(String characterClass, String subclass, int classLevel) {
        this.characterClass = characterClass;
        this.subclass = subclass;
        this.classLevel = classLevel;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }
}
