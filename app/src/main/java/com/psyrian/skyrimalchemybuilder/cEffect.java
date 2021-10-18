package com.psyrian.skyrimalchemybuilder;

public class cEffect
{
    private String name;
    private float modValue;
    private float modMagnitude;

    public cEffect(String name, float modValue, float modMagnitude)
    {
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
