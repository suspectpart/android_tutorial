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

        items.add(new Item(1, "First Item", "This is the first item. It is always present in the list.", getHtml()));
        items.add(new Item(2, "Second Item", "This is the second item", getHtml()));
        items.add(new Item(3, "Third Item", "This is the third one.", getHtml()));

        int randomId = new Random().nextInt(1000);

        items.add(new Item(randomId, "Item # " + randomId, getDescription(), getHtml()));

        return items;
    }

    private static String getHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "}\n" +
                "th {\n" +
                "    text-align: left;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "<table style=\"width:100%\">\n" +
                "  <tr>\n" +
                "    <th>Firstname</th>\n" +
                "    <th>Lastname</th>\t\t\n" +
                "    <th>Points</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Jill</td>\n" +
                "    <td>Smith</td>\t\t\n" +
                "    <td>50</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Eve</td>\n" +
                "    <td>Jackson</td>\t\t\n" +
                "    <td>94</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>John</td>\n" +
                "    <td>Doe</td>\t\t\n" +
                "    <td>80</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }

    private static String getDescription() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    }
}
