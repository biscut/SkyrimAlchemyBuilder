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
                    setTextColor(effects.get(j), effect);
/*
                    TextView eff2 = (TextView) curItemLayout.getChildAt(2);
                    eff2.setText(effects.get(1).getName());

                    TextView eff3 = (TextView) curItemLayout.getChildAt(3);
                    eff3.setText(effects.get(2).getName());

                    TextView eff4 = (TextView) curItemLayout.getChildAt(4);
                    eff4.setText(effects.get(3).getName());*/
                }
            }
        }

        return isAdded;
    }

    private void setTextColor(cEffect effect, TextView view)
    {
        List<Integer> currentEffects = potion.getEffects();
        List<Integer> unusedEffects = potion.getUnusedEffects();

        if(currentEffects.contains(effect.getID()))
            view.setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
        else if(unusedEffects.contains(effect.getID()))
            view.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        else
            view.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
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