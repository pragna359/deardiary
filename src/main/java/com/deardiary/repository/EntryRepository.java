package com.deardiary.repository;

import com.deardiary.model.Entry;
import com.deardiary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByUserOrderByCreatedAtDesc(User user);
}