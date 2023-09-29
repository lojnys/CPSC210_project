package persistence;

import model.BookListBorrowed;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        String file = "./data/nonExistingFile.json";
        JsonReader reader = new JsonReader(file);
        try {
            BookListBorrowed bookList = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookList.json");
        try {
            BookListBorrowed bookList = reader.read();
            assertEquals(0, bookList.getListSize());
        } catch (IOException e) {
            fail("Unable to read from the file");
        }
    }

    @Test
    void testReaderGeneralBookList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookList.json");
        try {
            BookListBorrowed bookList = reader.read();
            assertEquals(4, bookList.getListSize());
            assertEquals("Harry Potter", bookList.getBookList().get(0).getTitle());
            assertEquals("J.K. Rowling", bookList.getBookList().get(0).getAuthor());
            assertEquals("Exhalation", bookList.getBookList().get(1).getTitle());
            assertEquals("The Lord of the Rings", bookList.getBookList().get(2).getTitle());
            assertEquals("Yushin Nam", bookList.getBookList().get(3).getAuthor());
            assertEquals(14, bookList.getBookList().get(2).getDueDate());
        } catch (IOException e) {
            fail("Unable to read from the file");
        }
    }
}
