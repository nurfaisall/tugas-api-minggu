package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
public class Game extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", length = 36, nullable = false)
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "rating")
    public String rating;

    @Column(name = "developer")
    public String developer;

    @Column(name = "status")
    public String status;

    @Column(name = "release_date")
    public Date releaseDate;

    @Column(name ="genre")
    public String[] genre;

    @Column(name = "genre_data")
    public String genreData;

    @Column(name = "platform")
    public String[] platform;

    @Column(name = "platform_data")
    public String platformData;

    @Column(name = "mode")
    public String[] mode;

    @Column(name = "mode_data")
    public String modeData;
}
