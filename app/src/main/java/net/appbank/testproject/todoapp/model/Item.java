package net.appbank.testproject.todoapp.model;

import java.util.Date;

/**
 * Created by tatsuya.sato on 2016/08/25.
 */
public class Item {
    private String name;
    private String text;
    private String date;
    private Boolean isCheck;

    public Item(String name, String text, String date) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.isCheck = false;
    }

    public void setIsCheck(Boolean isCheck){
        this.isCheck = isCheck;
    }

    public String getName() {return name;}
    public String getText() {return text;}
    public String getDate() {return  date;}
    public Boolean getIsCheck(){return  isCheck;}
}
