package net.appbank.testproject.todoapp.model;

import java.util.Date;

/**
 * Created by tatsuya.sato on 2016/08/25.
 */
public class Item {
    private String name;
    private String text;
    private String date;

    public Item(String name, String text, String date) {
        this.name = name;
        this.text = text;
        this.date = date;
    }
    public String getName() {return name;}
    public String getText() {return text;}
    public String getDate() {return  date;}
}
