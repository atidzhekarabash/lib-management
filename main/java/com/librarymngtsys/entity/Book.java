package com.librarymngtsys.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String title;
    Integer year;
    String genre;



    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    Author author;


    @JoinColumn(name = "publisher_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    Publisher publisher;

    public Integer getBookID() {return id;}

    public String getTitle(){return  title;}
    public void setTitle(String title) {this.title = title;}


    public String getGenre(){return  genre;}
    public void setGenre(String genre) {this.genre = genre;}


    public Integer getYear(){return year;}
    public void setYear(int year) {this.year = year;}

    public Author getAuthor(){return author;}
    public void setAuthor(Author author) {this.author = author;}

    public Publisher getPublisher(){return publisher;}
    public void setPublisher(Publisher publisher) {this.publisher = publisher;}




}

