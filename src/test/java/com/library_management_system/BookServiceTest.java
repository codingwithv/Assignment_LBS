package com.library_management_system;

import com.library_management_system.entities.Book;
import com.library_management_system.entities.BookStatus;
import com.library_management_system.repository.BookRepo;
import com.library_management_system.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    // Test For Book Add
    @Test
    public void testBookAdd(){
        Book book = new Book(3L, "Thinking in java", "Bruce Eckel", "Programming", BookStatus.AVAILABLE );
        when(bookRepo.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals(003L, savedBook.getId());
        verify(bookRepo, times(1)).save(book);
    }

    // Test For Get All Books
    @Test
    public void testGetAllBooks(){
        Book book1 = new Book(1L, "Book1", "Author1", "Genre1", BookStatus.AVAILABLE);
        Book book2 = new Book(2L, "Book2", "Author2", "Genre2", BookStatus.AVAILABLE);

        when(bookRepo.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();
        assertEquals(2, books.size());
        verify(bookRepo, times(1)).findAll();
    }

    // Test Fot Get Book By id
    @Test
    public void testGetBookById(){
        Book book1 = new Book(1L, "Book1", "Author1", "Genre1", BookStatus.AVAILABLE);

        when(bookRepo.findById(1L)).thenReturn(Optional.of(book1));

        Optional<Book> foundBook = bookService.getBookById(1L);

        assertTrue(foundBook.isPresent());
        verify(bookRepo, times(1)).findById(1L);
    }

    // Test For Get BookBy Title
    @Test
    public void testGetBookByTitle(){
        Book book1 = new Book(1L, "Book1", "Author1", "Genre1", BookStatus.AVAILABLE);

        when(bookRepo.findByTitle("Book1")).thenReturn(Arrays.asList(book1));
        List<Book> books = bookService.searchBookByTitle("Book1");
        assertEquals(1, books.size());
        assertEquals("Book1", books.get(0).getTitle());
        verify(bookRepo, times(1)).findByTitle("Book1");
    }

    // Test For Update Book
    @Test
    public void testUpdateBook(){
        Book existingBook = new Book(1L, "Old Title", "Author1", "Old Genre", BookStatus.AVAILABLE);
        Book updatedBook = new Book(1L, "New Title", "New Author", "New Genre", BookStatus.AVAILABLE);

        when(bookRepo.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepo.save(any(Book.class))).thenReturn(updatedBook);

        Book result = bookService.updateBook(updatedBook, 1L);

        verify(bookRepo, times(1)).findById(1L);
        verify(bookRepo, times(1)).save(updatedBook);
    }

    // Test For Delete Book
    @Test
    public void testDeleteBook(){
        Book book = new Book(1L, "Book1", "Author1", "Genre1", BookStatus.AVAILABLE);
        when(bookRepo.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepo).delete(book);

        bookService.deleteBook(1L);

        verify(bookRepo, times(1)).findById(1L);
        verify(bookRepo, times(1)).delete(book);
    }

}
