package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre extends PanacheEntityBase {
    @Id
    @Column(name = "id")
    public String id;

    @Column(name = "genre_name")
    public String name;
}
