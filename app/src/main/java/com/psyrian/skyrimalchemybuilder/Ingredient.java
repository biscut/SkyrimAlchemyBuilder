package com.psyrian.skyrimalchemybuilder;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {

    private String name;
    private int id;
    private Effect[] effects;

    /**
     * @param name = name of ingredient
     * @param id = id of ingredient
     * @param effects = array of effects
     */
    public Ingredient(String name, int id, Effect[] effects) {
        this.name = name;
        this.id = id;
        this.effects = effects;
    }

    // Getters for values; no setters as values should stay static
    public String getName() { return name; }
    public int getId() { return id; }
    public Effect[] getEffects() { return effects; }


    /**
     * @param compIngredient = ingredient to compare with this.ingredient
     * @return = list of index values in parameter ingredient's effect list which match with
     */
    public List<List<Integer>> compareEffects(Ingredient compIngredient){
        List<List<Integer>> matches = new ArrayList();

        // Double for loop to compare each base ingredient's effect to each of target ingredient's effects
        for(int i = 0; i <= 3; i++){
            List<Integer> curMatch = new ArrayList();
            for(int j = 0; j <=3; j++) {
                if (this.effects[i].getName() == compIngredient.effects[j].getName())
                {
                    curMatch.add(i);
                    curMatch.add(j);
                    matches.add(curMatch);
                }
            }
        }

        return matches;
    }
}
