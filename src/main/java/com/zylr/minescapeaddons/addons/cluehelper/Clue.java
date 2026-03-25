package com.zylr.minescapeaddons.addons.cluehelper;

public class Clue {

    private int id;
    private String clueStep;
    private String answer;
    private String coords;

    public Clue(int id, String clueStep, String answer, String coords) {
        this.id = id;
        this.clueStep= clueStep;
        this.answer = answer;
        this.coords = coords;
    }

    public int getId() {
        return this.id;
    }

    public String getClueStep() {
        return this.clueStep;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getCoords() {
        return this.coords;
    }
}
