package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.charset.StandardCharsets;

import model.Book;
import model.BookListBorrowed;

import org.json.*;

// Represents a reader that reads a list of book borrowed from JSON data
public class JsonReader {

    // This class is motivated from the demo provided in phase 2 page on edx,
    // but it is modified such that it fits to my application

    private String source;

    // EFFECTS: constructs reader to read from the given file
    public JsonReader(String file) {
        this.source = file;
    }

    // EFFECTS: reads books from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookListBorrowed read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBooks(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String file) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses books from JSON object and returns it
    public BookListBorrowed parseBooks(JSONObject jsonObject) {
        BookListBorrowed books = new BookListBorrowed();
        addBooks(books, jsonObject);
        return books;
    }

//    public Account parseAccounts(JSONObject jsonObject) {
//        String name = jsonObject.getString("name");
//        Account account = new Account(name);
//        addBooks(account, jsonObject);
//        return account;
//    }


    // MODIFIES: books
    // EFFECTS: parses books from JSON object and adds them to books
    public void addBooks(BookListBorrowed books, JSONObject jsonObject) {
        JSONArray jsonBookList = jsonObject.getJSONArray("Books");
        for (Object json: jsonBookList) {
            JSONObject nextBook = (JSONObject) json;
            addBook(books, nextBook);
        }
    }

    // MODIFIES: books
    // EFFECTS: parses book from JSON object and adds it to book
    public void addBook(BookListBorrowed books, JSONObject nextBook) {
        String title = nextBook.getString("Title");
        String author = nextBook.getString("Author");
        int dueDate = nextBook.getInt("Days remained");

        Book book = new Book(title, author, dueDate);
        books.addBook(book);
    }

}
