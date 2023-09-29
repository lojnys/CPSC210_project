package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// Represents the event log with multiple events
public class EventLog implements Iterable<Event> {

    private static EventLog theLog;
    private Collection<Event> events;

    //EFFECTS: constructs an event log with empty list
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns the EventLog class
    //          if theLog is null then assign EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECTS: clears the event log
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared"));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
