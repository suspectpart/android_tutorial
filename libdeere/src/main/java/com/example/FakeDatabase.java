package com.example;

import java.util.ArrayList;
import java.util.Random;

public class FakeDatabase {
    public static ArrayList<Item> getItems() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(1, "First Item", "This is the first item. It is always present in the list."));
        items.add(new Item(2, "Second Item", "This is the second item"));
        items.add(new Item(3, "Third Item", "This is the third one."));

        int randomId = new Random().nextInt(1000);

        items.add(new Item(randomId, "Item # " + randomId, getDescription()));

        return items;
    }

    private static String getDescription() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    }
}
