package com.example.apiejemplo.ui.gallery;

public class Personajes {
    public String name;
    public String status;
    public String specie;
    public String gender;
    public String origin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Personajes(String name, String status, String specie, String gender, String origin) {
        this.name = name;
        this.status = status;
        this.specie = specie;
        this.gender = gender;
        this.origin = origin;
    }
}
