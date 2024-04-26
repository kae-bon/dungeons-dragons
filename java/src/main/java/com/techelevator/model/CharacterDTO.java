package com.techelevator.model;

import java.util.Map;

public class CharacterDTO {
    private int userId;
    private int id;
    private String name;
    private String race;
    private int currentLevel;
    private String alignment;
    private String profilePic;
    private Map<String, String> classesSubclasses;

    public CharacterDTO() {}
    public CharacterDTO(int userId, int id, String name, String race, String alignment, String profilePic, Map<String, String> classesSubclasses, int currentLevel) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.race = race;
        this.alignment = alignment;
        this.profilePic = profilePic;
        this.classesSubclasses = classesSubclasses;
        this.currentLevel = currentLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Map<String, String> getClassesSubclasses() {
        return classesSubclasses;
    }

    public void setClassesSubclasses(Map<String, String> classesSubclasses) {
        this.classesSubclasses = classesSubclasses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
