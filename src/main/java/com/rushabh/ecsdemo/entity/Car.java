package com.rushabh.ecsdemo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String colour;
    private int year;

    @ManyToOne(targetEntity = Model.class, cascade = CascadeType.ALL)
    @JoinColumn(name="MODEL_ID", referencedColumnName = "id")
    private Model model;

    @Transient
    private List<String> matchingWords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getMatchingWords() {
        return matchingWords;
    }

    public void setMatchingWords(List<String> matchingWords) {
        this.matchingWords = matchingWords;
    }
}
