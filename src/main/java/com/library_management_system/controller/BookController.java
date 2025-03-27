package com.library_management_system.controller;

import com.library_management_system.entities.Book;
import com.library_management_system.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @PostMapping("/new")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.addBook(book);
        redirectAttributes.addFlashAttribute("success", "Book added successfully!");
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "books/details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        model.addAttribute("book", book);
        return "books/form";
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.updateBook(book, id);
        redirectAttributes.addFlashAttribute("success", "Book updated successfully!");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("success", "Book deleted successfully!");
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String title, Model model) {
        List<Book> books = title != null ? bookService.searchBookByTitle(title) : bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("title", title);
        return "books/list";
    }
}