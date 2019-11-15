package com.example.basketmenejeri.Adapters;

import android.content.Context;
import android.widget.Button;

public class Person {
    private Context context;
       private String name;

        private String age;
        private String overall;
        private String position;
        private String value;

    public Person(String name, String age, String overall, String position, String value) {
        this.age = age;
        this.name = name;
        this.overall = overall;
        this.position = position;
        this.value = value;
    }
    public static class ViewHolder{
        Button teklifbutton;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}