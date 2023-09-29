# My Personal Project

## User Stories

- As a user, I want to be able to add a book to a list of books borrowed
- As a user, I want to be able to remove a book from a list of books borrowed, for example, due to returning
- As a user, I want to be able to select a book and get its due date
- As a user, I want to be able to select a book and update the information, for example, postponing the due date
- As a user, I want to be able to save the list of books borrowed
- As a user, I want to be able to load and display the list of book borrowed 
- As a user, I want to be able to load the list and add more to it


## Description

This application is representation of Kiosk of library borrowing service. 
Users can use this application when they want to borrow several books from the library. 
The application will simply receive some information of the book and store down the book in a 
list of book borrowed. People who use library for books can use this application. 
This project interests me because I love reading and library was a place I frequently used.

## Instruction for Grader
- You can add a book to the book list by pressing the "Borrow a book" button and input book title and author name where
they are required and click "Done".
- You can continue to add a second book to the book list by erasing the content in the fields and put down the book 
title and author name then clicking "Done".
- You can load the list by clicking "Load the list" button.
- You can save the list by clicking "Save the list" button.
- You can access the books that are currently in the list by clicking "Print current list".
- You can locate my visual component by looking at the popup window when loading and saving list (checkmark) and 
on the book list: there are book icons on each buttons.
- After accessing the current book list, you can click one of the book buttons. Then, you can click Return button on the
new window. Then with a message "Returned!", you will be able to see that the book button is gone after reopening the
current book list. Now it is returned (removed from the list).

## Phase 4: Task2

'Fri Dec 02 19:57:09 PST 2022
Harry Potter is added to the book list
Fri Dec 02 19:57:21 PST 2022
The Lord of the Rings is added to the book list
Fri Dec 02 19:57:28 PST 2022
Harry Potter is removed from the book list
Fri Dec 02 19:57:31 PST 2022
14 was added to due date for The Lord of the Rings'


I believe this is happening when the button for borrowing books is clicked, BookBorrowingKiosk class refers to Book and
BookBorrowingList class and the book is added to the list, with being logged because of the dependency of EventLog class
in BookBorrowingList class. Similarly, EventLog is depended on BookBorrowingList class when the user is returning the 
book. EventLog captures the event when the user wishes to postpone the due date. The program is already set such that
the due date will be postponed by 14 days interval. Also, EventLog is depended on Book class for addDays() method. 
The two events that are relevant to the Xs and Ys are, borrowing (adding) books and returning (removing) books.

## Phase 4: Task3

If I had more time with this project, the one that I would preferably add try to add more object such as movies, 
magazine, etc. Also, I want to be adding more interface and use more hierarchy system (superclass and subclass) and more of
abstract classes. Design-wise, I want to make it more appealing and eye-catching to the users. 

## Citation
- I watched a video on scaling pictures so that it fits in buttons and JOptionPane.
- I referred to the code on the behaviour after the JFrame is closed. When I exit the window, I don't want to end the 
program, but to print event logs. 