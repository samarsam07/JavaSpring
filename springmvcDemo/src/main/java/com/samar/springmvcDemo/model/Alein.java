package com.samar.springmvcDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alein {
    @Id
    private int id;
    private String name;

    public Alein() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alein(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Alein{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
