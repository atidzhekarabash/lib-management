package com.librarymngtsys.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String country;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCountry(){
         return  country;
    }
    public  void setCountry(String country){
        this.country = country;
    }

    public List<Book> getBooks() {
        return books;
    }
}
