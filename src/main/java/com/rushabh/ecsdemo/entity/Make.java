package com.rushabh.ecsdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Make {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(targetEntity = Model.class, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties("lstModel")
    @Transient
    private List<Model> lstModel;

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

    public List<Model> getLstModel() {
        return lstModel;
    }

    public void setLstModel(List<Model> lstModel) {
        this.lstModel = lstModel;
    }
}
