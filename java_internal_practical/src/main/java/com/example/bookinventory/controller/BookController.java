package com.example.bookinventory.controller;

import com.example.bookinventory.model.Book;
import com.example.bookinventory.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // optional prefix
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @GetMapping("/books/author/{name}")
    public List<Book> getBooksByAuthor(@PathVariable String name) {
        return bookRepository.findByAuthorIgnoreCase(name);
    }

    @GetMapping("/books/available")
    public List<Book> getAvailableBooks() {
        return bookRepository.findByQuantityGreaterThan(0);
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setQuantity(updatedBook.getQuantity());
            book.setPrice(updatedBook.getPrice());
            return bookRepository.save(book);
        }).orElse(null);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
