package com.library_management_system.services;

import com.library_management_system.Exception.ResourceNotFoundException;
import com.library_management_system.entities.Book;
import com.library_management_system.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    // Add Book
    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    // Get All Books
    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    // Get Book by id
    public Optional<Book> getBookById(Long id){
        return bookRepo.findById(id);
    }

    // Get Book by title
    public List<Book> searchBookByTitle(String title){
        return bookRepo.findByTitle(title);
    }

    // Update Book
    public Book updateBook(Book book, Long id){
        Book book1 = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Book Id", id));
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setGenre(book.getGenre());
        book1.setStatus(book.getStatus());
        return bookRepo.save(book1);
    }

    // Delete Book
    public void deleteBook(Long id){
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book", "Book Id", id));
        this.bookRepo.delete(book);
    }

}
