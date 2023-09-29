package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents the set of books being borrowed
public class BookListBorrowed implements Writable {

    private final List<Book> bookList;

    // EFFECTS: constructs a list of book borrowed
    public BookListBorrowed() {
        this.bookList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given book to the list of book borrowed
    public void addBook(Book book) {
        if (!bookList.contains(book)) {
            bookList.add(book);
            EventLog.getInstance().logEvent(new Event(book.getTitle() + " is added to the book list"));
        }
    }

    // REQUIRES: the list bookList is non-empty list
    // MODIFIES: this
    // EFFECTS: removes the given book title from the list of book borrowed
    public void removeBook(Book book) {
        book.returnBook();
        bookList.remove(book);
        EventLog.getInstance().logEvent(new Event(book.getTitle() + " is removed from the book list"));
    }

    // EFFECTS: looks for the book with given book title in the list
    public Book selectBook(String bookTitle) {
        Book selection = null;

        for (Book book : bookList) {
            if (book.getTitle().equals(bookTitle)) {
                selection = book;
            }
        }
        return selection;
    }

    // EFFECTS: returns the current list of book borrowed
    public List<Book> getBookList() {
        return this.bookList;
    }

    // EFFECTS: returns the number of elements of the list
    public int getListSize() {
        return bookList.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Books", booksToJson());
        return json;
    }

    // EFFECTS: returns the list of books borrowed as a JSON array
    public JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book book: bookList) {
            jsonArray.put(book.toJson());
        }
        return jsonArray;
    }
}
