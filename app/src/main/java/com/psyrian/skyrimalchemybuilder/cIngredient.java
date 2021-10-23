package com.psyrian.skyrimalchemybuilder;

import java.util.ArrayList;
import java.util.List;

public class cIngredient
{
    private String name;
    private List<cEffect> effects;

    /**
     * @param name = name of ingredient
     * @param effects = array of effects
     */
    public cIngredient(String name, List<cEffect> effects)
    {
        this.name = name;
        this.effects = effects;
    }

    // Getters for values; no setters as values should stay static
    public String getName() { return name; }
    public List<cEffect> getEffects() { return effects; }


    /**
     * @param compIngredient = ingredient to compare with this.ingredient
     * @return = list of index values in parameter ingredient's effect list which match with
     */
    public List<List<Integer>> compareEffects(cIngredient compIngredient)
    {
        List<List<Integer>> matches = new ArrayList<>();

        // Double for loop to compare each base ingredient's effect to each of target ingredient's effects
        for(int i = 0; i <= 3; i++)
        {
            List<Integer> curMatch = new ArrayList();
            for(int j = 0; j <=3; j++)
            {
                if (this.effects.get(i).getID() == compIngredient.effects.get(j).getID())
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