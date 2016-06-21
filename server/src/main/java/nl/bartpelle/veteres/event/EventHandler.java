package nl.bartpelle.veteres.event;

import nl.bartpelle.veteres.model.Entity;
import nl.bartpelle.veteres.model.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sky on 3-3-2016.
 */
public class EventHandler {

    /*private static EventHandler instance;

    public static EventHandler getSingleton() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }*/

    private List<EventContainer> events;

    public EventHandler() {
        this.events = new ArrayList<EventContainer>();
    }

    public void addEvent(Player player, Event event) {
        this.events.add(new EventContainer(player, 0, event));
    }

    public void addEvent(Player player, int ticks, Event event) {
        this.events.add(new EventContainer(player, ticks, event));
    }

    public void addEvent(EventContainer event) {
        events.add(event);
    }

    public void process() {
        List<EventContainer> eventsCopy = new ArrayList<EventContainer>(events);
        List<EventContainer> remove = new ArrayList<EventContainer>();
        for (EventContainer c : eventsCopy) {
            if (c != null) {
                if (c.needsExecution())

                    c.execute();
                if (!c.isRunning()) {
                    remove.add(c);
                }
            }
        }
        for (EventContainer c : remove) {
            events.remove(c);
        }
    }

    public int getEventsCount() {
        return this.events.size();
    }

    public void stopEvents(Entity entity) {
        for (EventContainer c : events) {
            if (c.getEntity() == entity) {
                c.stop();
            }
        }
    }
}
