package com.example;

public class Item {
    private final int id;
    private final String title;
    private final String description;

    public Item(int ID, String title, String description) {

        id = ID;
        this.title = title;
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
