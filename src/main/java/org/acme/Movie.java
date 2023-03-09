package org.acme;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie{
    @Id
    @GeneratedValue
    public Long id;
    @Column(name = "name")
    public String name;


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
