package com.deardiary.controller;

import com.deardiary.dto.EntryRequest;
import com.deardiary.model.Entry;
import com.deardiary.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EntryController {

    private final EntryService entryService;

    @PostMapping
    public ResponseEntity<Entry> create(@RequestBody EntryRequest request, Authentication auth) {
        return ResponseEntity.ok(entryService.createEntry(request, auth.getName()));
    }

    @GetMapping
    public ResponseEntity<List<Entry>> getAll(Authentication auth) {
        return ResponseEntity.ok(entryService.getAllEntries(auth.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> update(@PathVariable Long id, @RequestBody EntryRequest request, Authentication auth) {
        return ResponseEntity.ok(entryService.updateEntry(id, request, auth.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication auth) {
        entryService.deleteEntry(id, auth.getName());
        return ResponseEntity.ok("Deleted");
    }
}