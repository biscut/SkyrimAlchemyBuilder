package com.psyrian.skyrimalchemybuilder;

import java.util.ArrayList;
import java.util.List;

public class cPotion {

    private List<cIngredient> ingredients = new ArrayList();


    /**
     * Creates potion using list of ingredients
     * @param newIngredients = ingredients to populate potion with
     * @return
     */
    public cPotion(List<cIngredient> newIngredients){
        boolean isSuccessful = true;

        for(int i = 0; isSuccessful && i < newIngredients.size(); i++)
            isSuccessful = this.addIngredient(newIngredients.get(i));

        if(!isSuccessful)
            this.ingredients = new ArrayList();
    }

    public List<cIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<cIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Receives ingredient and checks if already included, adding if not
     * @param newIngredient = ingredient to be added to the potion
     * @return boolean to indicate
     */
    public boolean addIngredient(cIngredient newIngredient){
        boolean isExisting = false;

        for(int i = 0; !isExisting && i < ingredients.size(); i++){
            if(ingredients.get(i).getId() == newIngredient.getId())
                isExisting = true;
        }

        if(!isExisting)
            this.ingredients.add(newIngredient);

        return !isExisting;
    }

    /**
     * Crawls through ingredients looking for matches, one instance
     * of effect per potion
     * @return returns list of effects created by this potion
     */
    public List<String> getEffects(){
        List<String> effects = new ArrayList<>();

        for(int i = 0; i+1 < ingredients.size(); i++)
        {
            for(int j = i+1; j+1 <= ingredients.size(); j++)
            {
                cEffect[] first = ingredients.get(i).getEffects();
                cEffect[] second = ingredients.get(j).getEffects();
                for(int k = 0; k < 4; k++)
                {
                    for(int l = 0; l < 4; l++)
                    {
                        if(first[k].getName().equals(second[l].getName()) && !effects.contains(first[k].getName()))
                        {
                            effects.add(first[k].getName());
                        }
                    }
                }
            }
        }

        return effects;
    }
}
