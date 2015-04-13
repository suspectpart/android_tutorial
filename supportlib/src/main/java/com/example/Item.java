package com.example;

public class Item {
    private final int id;
    private final String title;
    private final String description;
    private final String html;

    public Item(int ID, String title, String description, String html) {
        id = ID;
        this.title = title;
        this.description = description;
        this.html = html;
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

    public String getHtml() {return html;}
}
