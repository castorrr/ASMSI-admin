package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Event;
import com.asmsi.admin_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public String createEvent(
            @RequestParam String name,
            @RequestParam String speaker,
            @RequestParam LocalDateTime dateTime,
            @RequestParam String venue,
            RedirectAttributes redirectAttributes) {

        // Create and save the event (the returned Event object is used)
        Event savedEvent = eventService.createEvent(name, speaker, dateTime, venue);

        // Add the saved event details to flash attributes if you want to display them
        redirectAttributes.addFlashAttribute("success", "Event created successfully!");
        redirectAttributes.addFlashAttribute("eventName", savedEvent.getName());
        redirectAttributes.addFlashAttribute("eventSpeaker", savedEvent.getSpeaker());

        return "redirect:/events"; // Redirect to events listing page
    }
}