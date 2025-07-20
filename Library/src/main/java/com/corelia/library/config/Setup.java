package com.corelia.library.config;

import com.corelia.library.entity.Author;
import com.corelia.library.entity.Book;
import com.corelia.library.repository.AuthorRepository;
import com.corelia.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Setup implements CommandLineRunner{


    @Autowired
    private  AuthorRepository authorRepository;
    @Autowired
    private  BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {

        if (authorRepository.count() == 0 && bookRepository.count() == 0) {

            Author author1 = new Author(null, "George Orwell", "orwell@example.com", new ArrayList<>());
            Author author2 = new Author(null, "J.K. Rowling", "jk@example.com", new ArrayList<>());

            authorRepository.saveAll(List.of(author1, author2));

            Book book1 = new Book(null, "1984", "Dystopian", author1);
            Book book2 = new Book(null, "Animal Farm", "Political Satire", author1);
            Book book3 = new Book(null, "Harry Potter", "Fantasy", author2);

            bookRepository.saveAll(List.of(book1, book2, book3));
            author1.setBooks(List.of(book1, book2));
            author2.setBooks(List.of(book3));

            System.out.println("Dummy data inserted.");
        } else {
            System.out.println("Dummy data already exists. Skipping insertion.");
        }

    }


}
