package com.psyrian.skyrimalchemybuilder;

import java.util.ArrayList;
import java.util.List;

public class cPotion {
    List<cIngredient> ingredients;

    public cPotion(List<cIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<cIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<cIngredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<cEffect> getEffects(){
        List<cEffect> effects = new ArrayList<cEffect>();

        return effects;
    }
}
