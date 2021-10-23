package com.psyrian.skyrimalchemybuilder;

public class cEffect
{
    private int id;
    private String name;
    private float modValue;
    private float modMagnitude;

    public cEffect(int id, String name, float modValue, float modMagnitude)
    {
        this.id = id;
        this.name = name;
        this.modValue = modValue;
        this.modMagnitude = modMagnitude;
    }

    public int getID() {
        return id;
    }
    public String getName() { return name; }
    public float getModValue() { return modValue; }
    public float getModMagnitude() {
        return modMagnitude;
    }
}
