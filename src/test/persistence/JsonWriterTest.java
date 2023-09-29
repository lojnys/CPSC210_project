package persistence;

import model.Book;
import model.BookListBorrowed;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/h\\ello.json");
            writer.open();
        } catch (IOException e) {
            fail("IOException was expected");
        }
    }

    @Test
    void testWriterEmptyBookList() {
        try {
            String file = "./data/testWriterEmptyBookList.json";
            BookListBorrowed bookList = new BookListBorrowed();
            JsonWriter writer = new JsonWriter(file);
            writer.open();
            writer.write(bookList);
            writer.close();

            JsonReader reader = new JsonReader(file);
            bookList = reader.read();
            assertEquals(0, bookList.getListSize());
            assertFalse(bookList.getBookList().contains(new Book("Harry Potter", "J.K. Rowling")));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookList() {
        try {
            String file = "./data/testWriterGeneralBookList.json";
            BookListBorrowed bookList = new BookListBorrowed();
            bookList.addBook(new Book("Python Crash Course", "Yushin Nam"));
            bookList.addBook(new Book("Grade 8 Mathematics", "BC edition"));
            bookList.addBook(new Book("Breaking Dawn", "Stephenie Meyer"));
            JsonWriter writer = new JsonWriter(file);
            writer.open();
            writer.write(bookList);
            writer.close();

            JsonReader reader = new JsonReader(file);
            bookList = reader.read();
            assertEquals(3, bookList.getListSize());
            assertEquals("Python Crash Course", bookList.getBookList().get(0).getTitle());
            assertEquals("Yushin Nam", bookList.getBookList().get(0).getAuthor());
            assertEquals("Breaking Dawn", bookList.getBookList().get(2).getTitle());
            assertEquals("BC edition", bookList.getBookList().get(1).getAuthor());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
