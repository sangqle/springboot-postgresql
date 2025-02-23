package com.cabin.example.repository;

import com.cabin.example.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    // Custom query
    @Query("SELECT b FROM Book b WHERE b.publishDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") LocalDate date);

    Page<Book> findByIsDeletedFalse(@Param(("pageable")) Pageable pageable);
}
