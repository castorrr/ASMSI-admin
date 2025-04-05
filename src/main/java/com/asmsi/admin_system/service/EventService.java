package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.Event;
import com.asmsi.admin_system.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    // Change parameter from String[] to String
    public Event createEvent(String name, String speaker, LocalDateTime dateTime,
            String venue, String audience) {
        Event event = new Event(name, speaker, dateTime, venue, audience);
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByYear(int year) {
        return eventRepository.findByYear(year);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public void updateEvent(Long id, String name, String speaker,
            LocalDateTime dateTime, String venue,
            String audience) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        event.setName(name);
        event.setSpeaker(speaker);
        event.setDateTime(dateTime);
        event.setVenue(venue);
        event.setAudience(audience);

        eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

}