package com.example.eva.domain.model;

public class Equipo {
    private String teamName;
    private String teamColour;

    public Equipo(String teamName, String teamColour) {
        this.teamName = teamName;
        this.teamColour = teamColour;
    }

    public Equipo(String teamName) {
        this(teamName, null);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(String teamColour) {
        this.teamColour = teamColour;
    }

}
