package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookListBorrowedTest {

    private BookListBorrowed bookList;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setup() {
        this.bookList = new BookListBorrowed();
        this.book1 = new Book("Harry Potter", "J.K. Rowling");
        this.book2 = new Book("The Lord of the Rings", "J.R.R Tolkien");
        this.book3 = new Book("Sherlock Holmes", "Arthur Conan Doyle");
    }

    @Test
    public void addBookEmptyTest() {
        assertEquals(0, bookList.getBookList().size());
    }

    @Test
    public void addBookOneTest() {
        bookList.addBook(book1);

        assertEquals(1, bookList.getBookList().size());
        assertEquals("Harry Potter", book1.getTitle());
        assertEquals("J.K. Rowling", book1.getAuthor());
        assertEquals(14, book1.getDueDate());
    }

    @Test
    public void addBookMultipleTest() {
        bookList.addBook(book1);
        bookList.addBook(book2);

        assertEquals(book1, bookList.getBookList().get(0));
        assertEquals(book2, bookList.getBookList().get(1));
        assertEquals(2, bookList.getBookList().size());

        bookList.addBook(book3);

        assertEquals(book3, bookList.getBookList().get(2));
        assertEquals(3, bookList.getBookList().size());
    }

    @Test
    public void addBookDuplicateTest() {
        bookList.addBook(book3);
        bookList.addBook(book3);

        assertEquals(1, bookList.getBookList().size());
        assertEquals(book3, bookList.getBookList().get(0));

        bookList.addBook(book1);

        assertEquals(2, bookList.getBookList().size());
        assertEquals(book1, bookList.getBookList().get(1));
    }

    @Test
    public void removeBookOneTest() {
        bookList.addBook(book3);
        bookList.addBook(book1);
        bookList.removeBook(book3);

        assertEquals(1, bookList.getBookList().size());
    }

    @Test
    public void removeBookMultipleTest() {
        bookList.addBook(book1);
        bookList.addBook(book2);
        bookList.addBook(book3);

        bookList.removeBook(book1);
        assertEquals(2, bookList.getBookList().size());

        bookList.removeBook(book2);
        assertEquals(1, bookList.getBookList().size());
        assertEquals(book3, bookList.getBookList().get(0));

    }

    @Test
    public void selectBookEmptyListTest() {
        assertEquals(0, bookList.getBookList().size());
        assertEquals(null, bookList.selectBook("Harry Potter"));
    }

    @Test
    public void selectBookOneAndOneNotInList() {
        bookList.addBook(book1);

        assertEquals(book1, bookList.selectBook("Harry Potter"));
        assertEquals(null, bookList.selectBook("Java Crash Course"));
    }

}
