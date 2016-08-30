package net.appbank.testproject.todoapp.model;

import android.graphics.Color;

import java.util.Date;

/**
 * Created by tatsuya.sato on 2016/08/25.
 */
public class Item {
    private String text;
    private String date;
    private Boolean isCheck;

    public Item(String text, String date) {
        this.text = text;
        this.date = date;
        this.isCheck = false;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setIsCheck(Boolean isCheck){
        this.isCheck = isCheck;
    }

    public String getText() {return text;}
    public String getDate() {return  date;}
    public Boolean getIsCheck(){return  isCheck;}
}
