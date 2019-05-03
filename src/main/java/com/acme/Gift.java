package com.acme;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "Gift.findAll", query = "SELECT g FROM Gift g")
, @NamedQuery(name = "Gift.findById", query = "SELECT g FROM Gift g WHERE g.id = :id")
})

public class Gift implements Serializable {
    private Long id;
    private String name;

    public Gift() { }

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="giftSeq")
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
}