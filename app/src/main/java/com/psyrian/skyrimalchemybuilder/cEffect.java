package com.psyrian.skyrimalchemybuilder;

import android.content.Context;

import com.psyrian.skyrimalchemybuilder.MainActivity;

public class cEffect
{
    private Integer id;
    private float modValue;
    private float modMagnitude;

    public cEffect(int id, float modValue, float modMagnitude)
    {
        this.id = id;
        this.modValue = modValue;
        this.modMagnitude = modMagnitude;
    }

    public int getID() {
        return id;
    }
    public String getName() { return MainActivity.getContext().getResources().getString(id); }
    public float getModValue() { return modValue; }
    public float getModMagnitude() {
        return modMagnitude;
    }
}
