package com.psyrian.skyrimalchemybuilder.ui.byIngredient;

import android.os.Bundle;
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

public class ByIngredientFragment extends Fragment implements AdapterView.OnItemLongClickListener
{

    private ByIngredientViewModel byIngredientViewModel;
    private FragmentByingredientBinding binding;
    private static cPotion potion;
    private List<cIngredient> filteredIngredients;
    private cIngredientAdapter adapter;
    ListView listView;
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
        listView.setOnItemLongClickListener(this);
        listView.setAdapter(adapter);

        return root;
    }

    public boolean addIngredient(cIngredient newIngredient)
    {
        boolean isAdded = false;
        isAdded = potion.addIngredient(newIngredient);

        if(isAdded)
        {
            LinearLayout curItemLayout = null;

            for(int i = 0; i < potion.getIngredients().size(); i++)
            {

                cIngredient curIngredient = potion.getIngredients().get(i);
                List<cEffect> effects = curIngredient.getEffects();

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

                curItemLayout.setBackground(getResources().getDrawable(R.mipmap.ingredient_background));
                TextView name = (TextView)curItemLayout.getChildAt(0);
                name.setText(curIngredient.getName());


                for(int j = 0; j < 4; j++)
                {
                    TextView effect = (TextView) curItemLayout.getChildAt(j+1);
                    effect.setText(effects.get(j).getName());
                    effect.setTextColor(getResources().getColor(getTextColor(effects.get(j))));
                }
            }

            refreshFilteredIngredients();
        }

        return isAdded;
    }

    private void refreshFilteredIngredients()
    {
        List<Integer> unusedEffects = potion.getUnusedEffects();
        List<cIngredient> wholeList = MainActivity.getIngredients();
        List<cIngredient> newList = new ArrayList();
        List<Integer> potionIDs = potion.getIngredientIDs();

        for(int k = 0; k < wholeList.size(); k++)
        {
            if(!potionIDs.contains(wholeList.get(k).getId()))
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
        listView.setAdapter(new cIngredientAdapter(this.getContext(), filteredIngredients, this));
        /*adapter.clear();
        adapter.addAll(newList);
        adapter.notifyDataSetChanged();*/

        //adapter.arrayList = newList;
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
        addIngredient(filteredIngredients.get(position));

        return false;
    }
}