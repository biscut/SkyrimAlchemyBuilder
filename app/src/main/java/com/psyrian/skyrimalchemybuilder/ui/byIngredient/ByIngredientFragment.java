package com.psyrian.skyrimalchemybuilder.ui.byIngredient;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.psyrian.skyrimalchemybuilder.MainActivity;
import com.psyrian.skyrimalchemybuilder.R;
import com.psyrian.skyrimalchemybuilder.cEffect;
import com.psyrian.skyrimalchemybuilder.cIngredient;
import com.psyrian.skyrimalchemybuilder.cPotion;
import com.psyrian.skyrimalchemybuilder.databinding.FragmentByingredientBinding;

import java.util.ArrayList;
import java.util.List;

public class ByIngredientFragment extends Fragment implements AdapterView.OnItemLongClickListener, View.OnLongClickListener
{

    private ByIngredientViewModel byIngredientViewModel;
    private FragmentByingredientBinding binding;
    private static cPotion potion;
    private List<cIngredient> filteredIngredients;
    private cIngredientAdapter adapter;
    ListView listView;
    LinearLayout potionView;
    TextView textView;
    String[] listItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        this.byIngredientViewModel = new ViewModelProvider(this).get(ByIngredientViewModel.class);
        MainActivity mainActivity = (MainActivity)getActivity();
        this.potion = new cPotion();
        this.filteredIngredients = mainActivity.getIngredients();
        this.binding = FragmentByingredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        adapter = new cIngredientAdapter(this.getContext(), filteredIngredients, this);

        listView = (ListView)root.findViewById(R.id.ingredientListView);
        potionView = (LinearLayout)root.findViewById(R.id.addedIngredients);
        listView.setOnItemLongClickListener(this);
        potionView.setOnLongClickListener(this);
        listView.setAdapter(adapter);

        return root;
    }

    public boolean addIngredient(cIngredient newIngredient)
    {
        boolean isAdded = false;
        isAdded = potion.addIngredient(newIngredient);

        if(isAdded)
        {
            updatePotionView();
            refreshFilteredIngredients();
        }

        return isAdded;
    }

    public boolean removeIngredient(cIngredient expelIngredient)
    {
        boolean removed = false;

        removed = potion.removeIngredient(expelIngredient);

        if(removed)
        {
            updatePotionView();
            refreshFilteredIngredients();
        }


        return removed;
    }

    private void updatePotionView()
    {
        LinearLayout curItemLayout = null;

        for(int i = 0; i < cPotion.MAX_INGREDIENTS; i++)
        {
            boolean emptyCell = false;
            cIngredient curIngredient = null;
            List<cEffect> effects = null;

            if(i >= potion.getIngredients().size())
                emptyCell = true;

            switch(i)
            {
                case 0:

                    curItemLayout = binding.ing1Layout;
                    break;
                case 1:

                    curItemLayout = binding.ing2Layout;
                    break;
                case 2:

                    curItemLayout = binding.ing3Layout;
                    break;
                default:
                    break;
            }

            if (!emptyCell)
            {
                curIngredient = potion.getIngredients().get(i);
                effects = curIngredient.getEffects();
            }

            if(emptyCell)
                curItemLayout.setBackground(getResources().getDrawable(R.mipmap.ingredient_placeholder));
            else
                curItemLayout.setBackground(getResources().getDrawable(R.mipmap.ingredient_background));
            TextView name = (TextView)curItemLayout.getChildAt(0);

            if(emptyCell)
                name.setText("");
            else
                name.setText(curIngredient.getName());


            for(int j = 0; j < 4; j++)
            {
                TextView effect = (TextView) curItemLayout.getChildAt(j+1);

                if(emptyCell)
                    effect.setText("");
                else
                    effect.setText(effects.get(j).getName());

                if(!emptyCell)
                    effect.setTextColor(getResources().getColor(getTextColor(effects.get(j))));
            }
        }

        refreshFilteredIngredients();
    }

    private void refreshFilteredIngredients()
    {
        List<Integer> unusedEffects = potion.getUnusedEffects();
        List<cIngredient> wholeList = MainActivity.getIngredients();
        List<cIngredient> newList = new ArrayList();
        List<Integer> potionIDs = potion.getIngredientIDs();

        if(potion.getIngredients().size() > 0)
        {
            for (int k = 0; k < wholeList.size(); k++)
            {
                if (!potionIDs.contains(wholeList.get(k).getId()))
                {
                    boolean found = false;
                    cIngredient curIngredient = wholeList.get(k);

                    for (int i = 0; found == false && i < 4; i++)
                    {
                        List<cEffect> curEffects = curIngredient.getEffects();

                        if (unusedEffects.contains(curEffects.get(i).getID()))
                        {
                            newList.add(curIngredient);
                            found = true;
                        }
                    }
                }
            }

            filteredIngredients = newList;
        }
        else
            filteredIngredients = wholeList;

        listView.setAdapter(new cIngredientAdapter(this.getContext(), filteredIngredients, this));
    }

    public static Integer getTextColor(cEffect effect)
    {
        List<Integer> currentEffects = potion.getEffects();
        List<Integer> unusedEffects = potion.getUnusedEffects();
        Integer color = R.color.effectBase;

        if (currentEffects.contains(effect.getID()))
            color = R.color.effectUsed;
        else if (unusedEffects.contains(effect.getID()))
            color = R.color.effectPotential;

        return color;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Log.i("longItemClick", String.valueOf(view.getId()));
        addIngredient(filteredIngredients.get(position));

        return false;
    }

    @Override
    public boolean onLongClick(View v)
    {
        removeIngredient(potion.getIngredients().get(potion.getIngredients().size()-1));
        return false;
    }
}