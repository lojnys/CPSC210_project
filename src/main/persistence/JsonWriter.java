package persistence;

import model.BookListBorrowed;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes/store a list of book borrowed to JSON data(file)
public class JsonWriter {

    // This class is motivated from the demo provided in phase 2 page on edx,
    // but it is modified such that it fits to my application
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to designated file
    public JsonWriter(String filename) {
        this.destination = filename;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, throws FileNotFoundException if the given fill does not exist
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of book list to file
    public void write(BookListBorrowed bookList) {
        JSONObject json = bookList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
