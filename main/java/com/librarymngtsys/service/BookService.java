package com.librarymngtsys.service;

import com.librarymngtsys.entity.Author;
import com.librarymngtsys.entity.Publisher;
import com.librarymngtsys.entity.Book;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

;
public class BookService {

    public void saveAll(String title, String genre, int year, Author author, Publisher publisher){

        EntityManager em = DatabaseManager.getEntityManager();
        em.getTransaction().begin();


        Author managedAuthor = em.merge(author);
        Publisher managedPublisher = em.merge(publisher);


        Book book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setYear(year);
        book.setAuthor(managedAuthor);
        book.setPublisher(managedPublisher);

        em.persist(book);

        em.getTransaction().commit();
        em.close();




    }





    public List<Book> findAllBooks() {
        EntityManager em = DatabaseManager.getEntityManager();
        return em.createQuery("SELECT b FROM Book b")
                .getResultList();

    }




    public ObservableList<Book> getAllBooks() {
        return FXCollections.observableArrayList(findAllBooks());
    }






    public void updateBookWithRelations(Integer id,
                                        String title, String genre, Integer year,
                                        String authorName, Integer authorBirthYear,
                                        String publisherName, String publisherCountry){


        EntityManager em = DatabaseManager.getEntityManager();
        em.getTransaction().begin();

        Book b = em.find(Book.class, id);

        b.setTitle(title);
        b.setGenre(genre);
        b.setYear(year);

        Author a= b.getAuthor();
        a.setName(authorName);
        a.setBirthYear(authorBirthYear);

       Publisher p = b.getPublisher();
       p.setName(publisherName);
       p.setCountry(publisherCountry);


       em.getTransaction().commit();
       em.close();


    }





    public void onDelete(Integer id) {
        EntityManager em = DatabaseManager.getEntityManager();

        Book b = em.find(Book.class, id);


        if (b != null) {

            em.getTransaction().begin();
            em.remove(b);
            em.getTransaction().commit();
            em.close();

        }

    }
    }


