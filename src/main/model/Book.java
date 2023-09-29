package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a book with a title, author name
public class Book implements Writable {


    private BookListBorrowed bookList;
    private final String title;
    private final String author;
    private int dueDate;
    boolean returned;

    private static final int DUE_DATE = 14;

    // EFFECTS: constructs a book with given title and author and
    //          the due date for now is 0.
    public Book(String title, String author) {
        this.bookList = new BookListBorrowed();
        this.title = title;
        this.author = author;
        this.dueDate = DUE_DATE;
        this.returned = false;
    }

    // EFFECTS: constructs a book with given title, author and due date
    public Book(String title, String author, int dueDate) {
        this.title = title;
        this.author = author;
        this.dueDate = dueDate;
        this.returned = false;
    }

    // REQUIRES: dueDate must be positive
    // EFFECTS: sets the due date of the book or
    //          how long it can be borrowed it for
    //          and extends the due date by DUE_DATE (14 days)
    public void addDays() {
        this.dueDate += DUE_DATE;
        this.returned = false;
        EventLog.getInstance().logEvent(new Event("14 was added to due date for " + getTitle()));
    }

    // EFFECTS: set the status of the book returned by setting "returned" as true
    public void returnBook() {
        this.dueDate = 0;
        this.returned = true;
    }

    // EFFECTS: returns the title of the book
    public String getTitle() {
        return this.title;
    }

    // EFFECTS: returns the name of the author
    public String getAuthor() {
        return this.author;
    }

    // EFFECTS: returns the current due date of the book
    public int getDueDate() {
        return this.dueDate;
    }

    public Boolean getReturnedStatus() {
        return this.returned;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", title);
        json.put("Author", author);
        json.put("Days remained", dueDate);
        return json;
    }
}
