package com.deardiary.service;

import com.deardiary.dto.EntryRequest;
import com.deardiary.model.Entry;
import com.deardiary.model.User;
import com.deardiary.repository.EntryRepository;
import com.deardiary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final UserRepository userRepository;

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Entry createEntry(EntryRequest request, String email) {
        User user = getUser(email);
        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setContent(request.getContent());
        entry.setMood(request.getMood());
        entry.setCreatedAt(LocalDateTime.now());
        entry.setUser(user);
        return entryRepository.save(entry);
    }

    public List<Entry> getAllEntries(String email) {
        User user = getUser(email);
        return entryRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Entry updateEntry(Long id, EntryRequest request, String email) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        if (!entry.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }
        entry.setTitle(request.getTitle());
        entry.setContent(request.getContent());
        entry.setMood(request.getMood());
        return entryRepository.save(entry);
    }

    public void deleteEntry(Long id, String email) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        if (!entry.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }
        entryRepository.delete(entry);
    }
}