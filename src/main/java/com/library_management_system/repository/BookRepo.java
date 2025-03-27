package com.library_management_system.repository;

import com.library_management_system.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
}
