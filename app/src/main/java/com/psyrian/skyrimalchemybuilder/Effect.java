package com.psyrian.skyrimalchemybuilder;

public class Effect {
    private String name;
    private float modValue;
    private float modMagnitude;

    public Effect(String name, float modValue, float modMagnitude) {
        this.name = name;
        this.modValue = modValue;
        this.modMagnitude = modMagnitude;
    }

    public String getName() {
        return name;
    }
    public float getModValue() { return modValue; }
    public float getModMagnitude() {
        return modMagnitude;
    }
}
