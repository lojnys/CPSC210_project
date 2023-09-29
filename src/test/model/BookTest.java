package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setup() {
        this.book = new Book("Harry Potter", "J.K. Rowling");
    }

    @Test
    public void constructorTest() {
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("J.K. Rowling", book.getAuthor());
        assertEquals(14, book.getDueDate());
    }

    @Test
    public void setDueDateOnceTest() {
        book.addDays();
        assertEquals(28, book.getDueDate());
        assertFalse(book.getReturnedStatus());
    }

    @Test
    public void setDueDateMultipleTest() {
        book.addDays();
        book.addDays();

        assertEquals(42, book.getDueDate());

        book.addDays();

        assertEquals(56, book.getDueDate());
        assertFalse(book.getReturnedStatus());
    }

    @Test
    public void returnBookTest() {
        book.returnBook();

        assertEquals(0, book.getDueDate());
        assertTrue(book.returned);
    }
}