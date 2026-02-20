package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    private final Map<Long, Item> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(0);

    public List<Item> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Item::getId))
                .toList();
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Item save(Item item) {
        if (item.getId() == null) item.setId(idGen.incrementAndGet());
        store.put(item.getId(), item);
        return item;
    }

    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }
}