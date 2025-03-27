package com.library_management_system;

import com.library_management_system.entities.Book;
import com.library_management_system.entities.BookStatus;
import org.junit.jupiter.api.Test;

public class BookTest {


    // Test For Book Add
    @Test
    public void testBookAdd(){
        Book book = new Book(3L, "Thinking in java", "Bruce Eckel", "Programming", BookStatus.AVAILABLE );
        System.out.println(book);
    }




}
