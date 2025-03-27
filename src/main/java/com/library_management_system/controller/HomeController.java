package com.library_management_system.controller;

import com.library_management_system.entities.Book;
import com.library_management_system.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Book> allBooks = bookService.getAllBooks();
        long totalBooks = allBooks.size();
        long availableBooks = allBooks.stream().filter(b -> b.getStatus().equals("AVAILABLE")).count();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("availableBooks", availableBooks);
        model.addAttribute("checkedOutBooks", totalBooks - availableBooks);

        return "home";
    }
}