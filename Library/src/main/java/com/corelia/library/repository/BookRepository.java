package com.corelia.library.repository;


import com.corelia.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>{
    Optional<Book> findByTitle(String title);
    @Query("SELECT b FROM Book b WHERE b.author.name = :name")
    List<Book> findBooksByAuthorName(@Param("name") String name);

}
