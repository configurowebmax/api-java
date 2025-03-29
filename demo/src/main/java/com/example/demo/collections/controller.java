package com.example.demo.collections;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
public class controller {

    private List<Item> items = new ArrayList<>();

    @GetMapping
    public List<Item> getItems() {
        return items;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        items.add(item);
        return item;
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable int id) {
        return items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable int id, @RequestBody Item updatedItem) {
        Item item = getItem(id);
        if (item != null) {
            item.setName(updatedItem.getName());
            return item;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable int id) {
        items.removeIf(i -> i.getId() == id);
    }
}

class Item {
    private int id;
    private String name;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
