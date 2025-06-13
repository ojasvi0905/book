package com.example.bookinventory.repository;

import com.example.bookinventory.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByQuantityGreaterThan(int quantity);
}