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
    private int color;

    public Item(String text, String date, int color) {
        this.text = text;
        this.date = date;
        this.isCheck = false;
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setIsCheck(Boolean isCheck){
        this.isCheck = isCheck;
    }
    public void setColor(int color) {this.color = color; }

    public String getText() {return text;}
    public String getDate() {return  date;}
    public Boolean getIsCheck(){return  isCheck;}
    public int getColor() {return  color;}
}
