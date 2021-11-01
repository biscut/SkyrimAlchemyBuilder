package com.psyrian.skyrimalchemybuilder.ui.byIngredient;
import com.psyrian.skyrimalchemybuilder.MainActivity;
import com.psyrian.skyrimalchemybuilder.R;
import com.psyrian.skyrimalchemybuilder.cEffect;
import com.psyrian.skyrimalchemybuilder.cIngredient;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class cIngredientAdapter implements ListAdapter
{
	List<cIngredient> arrayList;
	Context context;
	ByIngredientFragment caller;

	public cIngredientAdapter(Context context, List<cIngredient> arrayList, ByIngredientFragment caller) {
		this.caller = caller;
		this.arrayList=arrayList;
		this.context=context;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}
	@Override
	public boolean isEnabled(int position) {
		return true;
	}
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}
	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
	}
	@Override
	public int getCount() {
		return arrayList.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public boolean hasStableIds() {
		return false;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		cIngredient ingredientData=arrayList.get(position);
		if(convertView==null) {
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			convertView=layoutInflater.inflate(R.layout.ingredient_item, null);

			/*convertView.setOnLongClickListener(new View.OnLongClickListener(){
					@Override
					public boolean onLongClick(View v) {
						Log.i(String.valueOf(v.getId()),ingredientData.getName()
						);

						return false;
					}
				});
*/
			convertView.setId(ingredientData.getId());
			Resources res = context.getResources();
			TextView curText=convertView.findViewById(R.id.item_name);
			List<cEffect> effects = ingredientData.getEffects();
			curText.setText(ingredientData.getName());
			curText = convertView.findViewById(R.id.effect1);
			curText.setText(effects.get(0).getName());
			curText = convertView.findViewById(R.id.effect2);
			curText.setText(effects.get(1).getName());
			curText = convertView.findViewById(R.id.effect3);
			curText.setText(effects.get(2).getName());
			curText = convertView.findViewById(R.id.effect4);
			curText.setText(effects.get(3).getName());

		}

		return convertView;
	}
	@Override
	public int getItemViewType(int position) {
		return position;
	}
	@Override
	public int getViewTypeCount() {
		return arrayList.size();
	}
	@Override
	public boolean isEmpty() {
		return false;
	}
}
