package com.example.basketmenejeri.Adapters;
public class Calander_Team {
    private String team1;
    private String team2;
    private String ws ;

    public Calander_Team(String team1,  String ws, String team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.ws = ws;
    }

    public Calander_Team(String calenderteam, String calenderteam1) {

    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getWs() {
        return ws;
    }

    public void setWs(String ws) {
        this.ws = ws;
    }


}