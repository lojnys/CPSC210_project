package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EventLogTest {

    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void runBefore() {
        event1 = new Event("Harry Potter is added to the list");
        event2 = new Event("14 days is added");
        event3 = new Event("Harry Potter is removed from the list");
        EventLog eventLog = EventLog.getInstance();
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
        eventLog.logEvent(event3);
    }

    @Test
    public void testLogEvent() {
        List<Event> eventList = new ArrayList<Event>();

        EventLog eventLog = EventLog.getInstance();
        for (Event event: eventLog) {
            eventList.add(event);
        }

        assertTrue(eventList.contains(event1));
        assertTrue(eventList.contains(event2));
        assertTrue(eventList.contains(event3));
    }

    @Test
    public void testClear() {
        EventLog eventLog = EventLog.getInstance();
        eventLog.clear();
        Iterator<Event> itr = eventLog.iterator();
        assertTrue(itr.hasNext());
        assertEquals("Event log cleared", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }
}
