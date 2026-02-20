package com.example.demo.service;


import com.example.demo.dto.ItemRequest;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Item> getAll() {
        return repo.findAll();
    }

    public Item getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
    }

    public Item create(ItemRequest req) {
        Item item = new Item(null, req.getName(), req.getDescription(), Instant.now());
        return repo.save(item);
    }

    public Item update(Long id, ItemRequest req) {
        Item existing = getById(id);
        existing.setName(req.getName());
        existing.setDescription(req.getDescription());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Item not found: " + id);
        }
        repo.deleteById(id);
    }
}