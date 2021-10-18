package com.psyrian.skyrimalchemybuilder;

import java.util.ArrayList;
import java.util.List;

public class cPotion
{

    private List<cIngredient> ingredients = new ArrayList();


    /**
     * Creates potion using list of ingredients
     * @param newIngredients = ingredients to populate potion with
     * @return
     */
    public cPotion(List<cIngredient> newIngredients)
    {
        boolean isSuccessful = true;

        for(int i = 0; isSuccessful && i < newIngredients.size(); i++)
            isSuccessful = this.addIngredient(newIngredients.get(i));

        if(!isSuccessful)
            this.ingredients = new ArrayList();
    }

    public List<cIngredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(List<cIngredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    /**
     * Receives ingredient and checks if already included, adding if not
     * @param newIngredient = ingredient to be added to the potion
     * @return boolean to indicate
     */
    public boolean addIngredient(cIngredient newIngredient)
    {
        boolean isExisting = false;

        // Check whether ingredient already exists in potion
        for(int i = 0; !isExisting && i < ingredients.size(); i++)
        {
            if(ingredients.get(i).getName().equals(newIngredient.getName()))
                isExisting = true;
        }

        // Adds ingredient if not already
        if(!isExisting)
            this.ingredients.add(newIngredient);

        return !isExisting;
    }

    /**
     * Crawls through ingredients looking for matches, one instance
     * of effect per potion
     * @return returns list of effect names created by this potion
     */
    public List<String> getEffects()
    {
        List<String> effects = new ArrayList<>(); // holds list of effect names to return

        // Loop all of the things to compare all of the things, progressing to not double compare
        for(int i = 0; i+1 < ingredients.size(); i++) //Loop through first ingredient for compare
        {
            for(int j = i+1; j+1 <= ingredients.size(); j++) // Loop through second ingredient
            {
                // Get ingredients for readability later
                List<cEffect> first = ingredients.get(i).getEffects();
                List<cEffect> second = ingredients.get(j).getEffects();
                for(int k = 0; k < 4; k++) // Loop through first ingredient's effects
                {
                    for(int l = 0; l < 4; l++) // Loop through second ingredients effects
                    {
                        if(first.get(k).getName().equals(second.get(l).getName())
                                && !effects.contains(first.get(k).getName()))
                        {
                            effects.add(first.get(k).getName());
                        }
                    }
                }
            }
        }

        return effects;
    }
}
