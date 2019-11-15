package com.example.basketmenejeri.Adapters;

import android.widget.Button;

public class PersonData {
    private String id;

    private String teamid;


    public PersonData(String id, String teamid) {
        this.id = id;
        this.teamid = teamid;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }
}