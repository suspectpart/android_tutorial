package com.example;

import java.util.ArrayList;
import java.util.Random;

public class JohnDeereDatabase {
    public static ArrayList<Item> getItems() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(1, "First Item", "This is the first item. It is always present in the list."));
        items.add(new Item(2, "Second Item", "This is the second item"));
        items.add(new Item(3, "Third Item", "And I am the third one."));

        int randomId = new Random().nextInt(1000);

        items.add(new Item(randomId , "Item # " + randomId, "desc"));


        return items;
    }
}
