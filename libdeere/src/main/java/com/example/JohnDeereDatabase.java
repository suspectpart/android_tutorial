package com.example;

import java.util.ArrayList;

public class JohnDeereDatabase {
    public static ArrayList<Item> getItems() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "First Item", "desc"));
        items.add(new Item(2, "Second Item", "desc"));
        items.add(new Item(3, "Third Item", "desc"));
        return items;
    }
}
