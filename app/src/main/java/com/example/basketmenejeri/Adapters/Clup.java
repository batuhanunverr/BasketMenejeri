package com.example.basketmenejeri.Adapters;

import android.content.Context;

public class Clup {
    private Context context;
    private String clupname ,clupwin,cluplose,cluppoint;
public Clup(String clupname,String clupwin ,String cluplose,String cluppoint){

    this.clupname=clupname;
    this.clupwin=clupwin;
    this.cluplose=cluplose;
    this.cluppoint=cluppoint;

}

    public String getClupname() {
        return clupname;
    }

    public void setClupname(String clupname) {
        this.clupname = clupname;
    }

    public String getClupwin() {
        return clupwin;
    }

    public void setClupwin(String clupwin) {
        this.clupwin = clupwin;
    }

    public String getCluplose() {
        return cluplose;
    }

    public void setCluplose(String cluplose) {
        this.cluplose = cluplose;
    }

    public String getCluppoint() {
        return cluppoint;
    }

    public void setCluppoint(String cluppoint) {
        this.cluppoint = cluppoint;
    }
}
