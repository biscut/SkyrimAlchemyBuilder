package com.psyrian.skyrimalchemybuilder.ui.byEffect;

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
import com.psyrian.skyrimalchemybuilder.databinding.FragmentByeffectBinding;
import com.psyrian.skyrimalchemybuilder.databinding.FragmentByingredientBinding;
import com.psyrian.skyrimalchemybuilder.ui.byIngredient.ByIngredientViewModel;

public class ByEffectFragment extends Fragment {

    private ByEffectViewModel byEffectViewModel;
    private FragmentByeffectBinding binding;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        byEffectViewModel =
                new ViewModelProvider(this).get(ByEffectViewModel.class);

        binding = FragmentByeffectBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //String[] effectNameList = getResources().getStringArray(R.array.effectNames);
        String[] effectNameList = {"test", "test1", "test2"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                effectNameList);
        listView = (ListView)root.findViewById(R.id.effectListView);
        listView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}