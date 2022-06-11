package com.hit.product.adapter.web.v1.controllers;

import com.hit.product.adapter.web.base.VsResponseUtil;
import com.hit.product.applications.services.EventService;
import com.hit.product.domains.dtos.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping("")
    public ResponseEntity<?> getEvents() {
        return VsResponseUtil.ok(eventService.getEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(eventService.getEventById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {
        return VsResponseUtil.ok(eventService.createEvent(eventDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") Long id, @RequestBody EventDto eventDto) {
        return VsResponseUtil.ok(eventService.updateEvent(id, eventDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        return VsResponseUtil.ok(eventService.deleteEvent(id));
    }

}
