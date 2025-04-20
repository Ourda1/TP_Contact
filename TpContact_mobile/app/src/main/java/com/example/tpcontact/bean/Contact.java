package com.example.tpcontact.bean;

import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private int number;

    // Constructeur par défaut requis pour Gson
    public Contact() {
    }

    // Constructeur pratique pour créer un nouveau contact
    public Contact(String name, int number) {
        this.name = name;
        this.number = number;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
