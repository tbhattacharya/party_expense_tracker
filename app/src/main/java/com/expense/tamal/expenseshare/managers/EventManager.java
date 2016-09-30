package com.expense.tamal.expenseshare.managers;

import com.expense.tamal.expenseshare.pojo.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tamal on 29/09/2016.
 */
public class EventManager {

    private static final EventManager INSTANCE = new EventManager();
    private List<Event> allEvents = new ArrayList<>();

    private EventManager() {
        super();
        testMethod();
    }

    public static EventManager getSingleton() {
        return INSTANCE;
    }

    public List<Event> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(List<Event> allEvents) {
        this.allEvents = allEvents;
    }

    public void addEvents(Event event) {
        allEvents.add(event);
    }

    public List<String> getAllEventNames() {
        List<String> eventNames = new ArrayList<>();
        if (allEvents != null && allEvents.size() > 0) {
            for (Event event : allEvents) {
                eventNames.add(event.getEventName());
            }
        }
        return eventNames;
    }

    public List<String> getAllEventsImages() {
        List<String> eventUrls = new ArrayList<>();
        if (allEvents != null && allEvents.size() > 0) {
            for (Event event : allEvents) {
                eventUrls.add(event.getImageUrl());
            }
        }
        return eventUrls;
    }

    private void testMethod() {
        Event event = new Event();
        event.setEventName("My First Event");
        event.setImageUrl("file:///android_asset/step_1.png");
        allEvents.add(event);
        Event event1 = new Event();
        event1.setEventName("My Second Event");
        event1.setImageUrl("file:///android_asset/step_2.png");
        allEvents.add(event1);
    }
}
