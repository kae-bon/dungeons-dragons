package com.techelevator.model;

import java.util.List;
import java.util.Map;

public class CharacterDTO {
    private int userId;
    private int id;
    private String name;
    private String race;
    private String alignment;
    private String profile_pic;
    private Map<String, String> classesSubclasses;

    public CharacterDTO() {}
    public CharacterDTO(int userId, int id, String name, String race, String alignment, String profile_pic, Map<String, String> classesSubclasses) {
        this.userId = userId;
        this.id = id;
        this.name = name;
        this.race = race;
        this.alignment = alignment;
        this.profile_pic = profile_pic;
        this.classesSubclasses = classesSubclasses;
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

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
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
}
