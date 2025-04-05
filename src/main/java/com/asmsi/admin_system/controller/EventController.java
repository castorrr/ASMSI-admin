package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Event;
import com.asmsi.admin_system.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String showEvents(
            @RequestParam(required = false) Integer schoolYear,
            Model model) {

        List<Event> events;
        if (schoolYear != null) {
            events = eventService.getEventsByYear(schoolYear);
        } else {
            events = eventService.getAllEvents();
        }
        model.addAttribute("events", events);
        return "events";
    }

    @PostMapping("/create")
    public String createEvent(
            @RequestParam String name,
            @RequestParam String speaker,
            @RequestParam LocalDateTime dateTime,
            @RequestParam String venue,
            @RequestParam(name = "audience", required = false) String[] audiences,
            RedirectAttributes redirectAttributes) {

        String audienceStr;
        if (audiences == null || audiences.length == 0) {
            audienceStr = "None";
        } else if (Arrays.asList(audiences).contains("all")) {
            audienceStr = "All Levels"; // Store as exact string "All Levels"
        } else {
            audienceStr = String.join(",", audiences);
        }

        eventService.createEvent(name, speaker, dateTime, venue, audienceStr);
        redirectAttributes.addFlashAttribute("success", "Event created successfully!");
        return "redirect:/events";
    }
}