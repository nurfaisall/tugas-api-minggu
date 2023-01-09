package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Platform extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "name")
    public String name;
}
