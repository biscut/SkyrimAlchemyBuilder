package com.psyrian.skyrimalchemybuilder.ui.byIngredient;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.psyrian.skyrimalchemybuilder.R;
import com.psyrian.skyrimalchemybuilder.databinding.FragmentByingredientBinding;

public class ByIngredientFragment extends Fragment {

    private ByIngredientViewModel byIngredientViewModel;
    private FragmentByingredientBinding binding;
    ListView listView;
    TextView textView;
    String[] listItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        byIngredientViewModel =
                new ViewModelProvider(this).get(ByIngredientViewModel.class);

        binding = FragmentByingredientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String[] effectNameList = getResources().getStringArray(R.array.effectNames);
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                effectNameList);
        listView = (ListView)root.findViewById(R.id.ingredientListView);
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}