package ui;

import model.BookListBorrowed;
import model.Book;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

// Represents the book borrowing kiosk application
public class BookBorrowingKiosk extends JFrame implements ActionListener {

    private static final String JSON_PATH = "./data/books.json";
    private BookListBorrowed bookList;
    private Book book;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextArea display;
    private ImageIcon image1;
    private JScrollPane scrollPane;
    private JPanel screen;

    private EventLog events;

    // EFFECTS: construct and runs a Book Service Kiosk
    public BookBorrowingKiosk() {
        super("Book Borrowing Kiosk");
        runKiosk();
        printLog();
    }

    public void runKiosk() {
        this.bookList = new BookListBorrowed();
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);


//        JPanel displayPanel = new JPanel(new BorderLayout());
//        displayPanel.setLayout(new FlowLayout());

        screen = new JPanel(new FlowLayout());
        display = new JTextArea("Welcome to Book Borrowing Kiosk!",5, 60);
        display.setEditable(false);
        screen.add(display);
        scrollPane = new JScrollPane(screen);
//        displayPanel.add(scrollPane);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        frame.setContentPane(displayPanel);
//        setSize(600, 400);
        add(display, BorderLayout.NORTH);
        displayButtons();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    // MODIFIES: this
    // EFFECTS: puts buttons as they are required
    public void displayButtons() {
        JPanel buttonPane = new JPanel();
        JButton option1 = new JButton("Load the list");
//        JButton option2 = new JButton("Manage the list");
        JButton option3 = new JButton("Borrow a book");
        JButton option4 = new JButton("Save the list");
        JButton option5 = new JButton("Print current list");

        setButton(option1, "lobby_button1");
//        setButton(option2, "lobby_button2");
        setButton(option3, "lobby_button3");
        setButton(option4, "lobby_button4");
        setButton(option5, "lobby_button5");

        buttonPane.add(option1);
//        buttonPane.add(option2);
        buttonPane.add(option3);
        buttonPane.add(option4);
        buttonPane.add(option5);
        add(buttonPane, BorderLayout.SOUTH);
        pack();
    }

    // EFFECTS: let the buttons to be more dynamic
    public void setButton(JButton button, String command) {
        button.setActionCommand(command);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("lobby_button1")) {
            loadBooks();
//        } else if (e.getActionCommand().equals("lobby_button2")) {
//            operationOnBook();
        } else if (e.getActionCommand().equals("lobby_button3")) {
            borrowBook();
        } else if (e.getActionCommand().equals("lobby_button4")) {
            saveBooks();
        } else if (e.getActionCommand().equals("lobby_button5")) {
            printBookList();
        }
    }

    // MODIFIES: this
    // EFFECTS: a window where the user can input the book title and the author name
    //          borrowing a book by taking the title and author name and
    //          adding to a list of book borrowed
    public void borrowBook() {

        JFrame borrowFrame = new JFrame("Borrow books");
        borrowFrame.setPreferredSize(new Dimension(400, 90));
        borrowFrame.setLayout(new FlowLayout());

        JLabel titleQuestion = new JLabel("What is the title of the book?");
        JLabel authorQuestion = new JLabel("What is the name of the author?");
        JTextField title = new JTextField(5);
        JTextField author = new JTextField(5);
        JButton btn = new JButton("Done");

        doneButtonFunction(borrowFrame, btn, title, author);

        borrowFrame.add(titleQuestion);
        borrowFrame.add(title);
        borrowFrame.add(authorQuestion);
        borrowFrame.add(author);
        borrowFrame.add(btn);
        borrowFrame.pack();
        borrowFrame.setLocationRelativeTo(null);
        borrowFrame.setVisible(true);
        borrowFrame.setResizable(false);
    }

    public void doneButtonFunction(JFrame borrowFrame, JButton btn, JTextField title, JTextField author) {
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                book = new Book(title.getText(), author.getText());
                bookList.addBook(book);
                JOptionPane.showMessageDialog(borrowFrame,
                        book.getTitle() + " by " + book.getAuthor() + " has been added to the list");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: reads designated JSON file and load on bookList
    //          if it is successful, then it will give out a popup window saying it has been loaded
    public void loadBooks() {
        String message;
        String filename = "/Users/yushinnam/desktop/CPSC210/project_o5u2r/src/main/ui/images/checkmark.png";
        image1 = new ImageIcon(filename);
        Image image = image1.getImage();
        Image modifiedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        image1 = new ImageIcon(modifiedImage);

        try {
            bookList = jsonReader.read();
            message = "Loaded!";

        } catch (IOException e) {
            message = "Unable to read from the file: " + JSON_PATH;
        }

        JOptionPane.showMessageDialog(this, message, "Notification",
                JOptionPane.INFORMATION_MESSAGE, image1);


    }

    // EFFECTS: saves the current list of books borrowed
    //          if it is successful, then it will give out a pop-up window saying it has been saved
    public void saveBooks() {
        String message;
        String filename = "/Users/yushinnam/desktop/CPSC210/project_o5u2r/src/main/ui/images/checkmark.png";
        image1 = new ImageIcon(filename);
        Image image = image1.getImage();
        Image modifiedImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        image1 = new ImageIcon(modifiedImage);

        try {
            jsonWriter.open();
            jsonWriter.write(bookList);
            jsonWriter.close();
            message = "Saved!";
        } catch (FileNotFoundException e) {
            message = "Unable to write to file: " + JSON_PATH;
        }
        JOptionPane.showMessageDialog(this, message, "Notification",
                JOptionPane.INFORMATION_MESSAGE, image1);
    }

    // MODIFIES: this
    // EFFECTS: takes in users command and do the following operations:
    //          "1": prints out the title of selected book
    //          "2": prints out the author name of selected book
    //          "3": returns the due date of the selected book
    //          "4": extend the due date by DUE_DATE
    //          "5": return the book by setting the status as returned
    public void operationOnBook(Book book) {

        JFrame manageBookWindow = new JFrame("Manage Book List");
        JPanel buttonPanel = new JPanel();
        JPanel dispayPanel = new JPanel(new BorderLayout());
        JTextArea displayBoard = new JTextArea(5, 10);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        displayBoard.setEditable(false);
        dispayPanel.add(displayBoard);
        manageBookWindow.setSize(200, 300);

        manageBookWindow.add(dispayPanel, BorderLayout.NORTH);
        manageBookWindow.add(buttonPanel, BorderLayout.SOUTH);

        putButton(buttonPanel, "Get Book Info", displayBoard,book);
        putButton(buttonPanel, "Postpone Due Date", displayBoard, book);
        putButton(buttonPanel, "Return", displayBoard, book);
        manageBookWindow.pack();

        manageBookWindow.setVisible(true);
        manageBookWindow.setLocationRelativeTo(null);
        manageBookWindow.setResizable(false);

    }

    // EFFECTS: puts button on the Manage the Book Window
    public void putButton(JPanel buttonPanel, String text, JTextArea displayBoard, Book book) {
        JButton button = new JButton(text);
        buttonPanel.add(button);

        if (text.equals("Get Book Info")) {
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    displayOnBoard("Get Book Info", displayBoard, book);
                }
            });
        } else if (text.equals("Postpone Due Date")) {
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    displayOnBoard("Postpone Due Date", displayBoard, book);
                }
            });
        } else {
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    displayOnBoard("Return", displayBoard, book);
                }
            });
        }
    }

    // EFFECTS: displays message depending on the button
    public void displayOnBoard(String text, JTextArea displayBoard, Book book) {
        if (text.equals("Get Book Info")) {
            displayBoard.setEditable(true);
            displayBoard.setText("Title: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nDue date: "
                    + book.getDueDate());
            displayBoard.setEditable(false);
        } else if (text.equals("Postpone Due Date")) {
            book.addDays();
            displayBoard.setEditable(true);
            displayBoard.setText("Postponed!");
            displayBoard.setEditable(false);
        } else {
//            book.returnBook();
            bookList.removeBook(book);
            displayBoard.setEditable(true);
            displayBoard.setText("Returned!");
            displayBoard.setEditable(false);
        }
    }

    // EFFECTS: creates a new window with buttons of the current books
    public void printBookList() {

        JFrame currentBookWindow = new JFrame("Current Book List");
        currentBookWindow.setSize(400, 300);

        ImageIcon bookIcon = scaleImage();

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.PAGE_AXIS));
        JScrollPane newScrollPane = new JScrollPane(bookPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        for (Book book: bookList.getBookList()) {
            if (!book.getReturnedStatus()) {
                JButton bookButton = new JButton("Title: " + book.getTitle() + ", Author: " + book.getAuthor()
                        + ", Days left: " + book.getDueDate(), bookIcon);

                bookPanel.add(bookButton, BorderLayout.SOUTH);
                bookButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        operationOnBook(book);
                    }
                });
            }
        }
        currentBookWindow.add(newScrollPane);
        currentBookWindow.setVisible(true);
        currentBookWindow.setLocationRelativeTo(null);

    }

    // EFFECTS: scales the image so that it fits in the window
    public ImageIcon scaleImage() {
        String filename = "/Users/yushinnam/desktop/CPSC210/project_o5u2r/src/main/ui/images/book.png";
        ImageIcon bookIcon = new ImageIcon(filename);
        Image bookImage = bookIcon.getImage();
        Image modifiedImage = bookImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        return new ImageIcon(modifiedImage);
    }

    public void printLog() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                EventLog eventLog = EventLog.getInstance();
                for (Event event: eventLog) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }

}
