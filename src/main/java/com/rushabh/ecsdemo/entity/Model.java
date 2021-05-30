package com.rushabh.ecsdemo.entity;

import javax.persistence.*;

@Entity
public class Model {
    @Id
    @GeneratedValue
    private int id;
    private String model;
    @ManyToOne(targetEntity = Make.class, cascade = CascadeType.ALL)
    @JoinColumn(name="MAKE_ID", referencedColumnName = "id")
    private Make make;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }
}
