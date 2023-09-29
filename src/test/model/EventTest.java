package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

public class EventTest {

    private Event event;
    private Date date;

    @BeforeEach
    public void runBefore() {
        event = new Event("Harry Potter is added to the book list");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Harry Potter is added to the book list", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Harry Potter is added to the book list",
                event.toString());
    }
}
