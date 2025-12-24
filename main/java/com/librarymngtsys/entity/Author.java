package com.librarymngtsys.entity;

import jakarta.persistence.*;

import javax.naming.Name;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer id;
     String name;
     Integer birthYear;

     @OneToMany(mappedBy = "author")
     private List<Book> books;



     public Integer getAuthorID(){return  id;}

     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }


     public int getBirthYear() {
          return birthYear;
     }
     public void setBirthYear(Integer birthYear) {
          this.birthYear = birthYear;
     }


     public List<Book> getBooks() {
          return books;
     }
}