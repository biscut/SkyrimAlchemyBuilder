package com.psyrian.skyrimalchemybuilder;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.psyrian.skyrimalchemybuilder.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class MainActivity extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public List<cIngredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ingredientList = new ArrayList<cIngredient>();
        this.initializeIngredients();
        this.testPotion(ingredientList);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void testPotion(List<cIngredient> ingredients)
    {
        cPotion test = new cPotion(ingredients);
        List<String> testEffects = test.getEffects();
        int a = 1;
    }

    public String loadJSONFromAsset(Context context)
    {
        String json = null;
        try {
            InputStream is = context.getAssets().open("ingredients.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void initializeIngredients()
    {
        //TypedArray test = getResources().obtainTypedArray(R.array.ingredients);
        //getResources();
        String jsonData = loadJSONFromAsset(this);

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject row = jsonArray.getJSONObject(i);

                List<cEffect> newEffects = new ArrayList();
                String ingName = row.getString("name");

                for(int j = 1; j <= 4; j++)
                {
                    JSONObject jsonEffect = row.getJSONObject("effect"+ j);

                    String effName = jsonEffect.getString("name");
                    float effVal = (float)jsonEffect.getDouble("modValue");
                    float effMag = (float)jsonEffect.getDouble("modMagnitude");
                    cEffect curEffect = new cEffect(effName, effVal, effMag);

                    newEffects.add(curEffect);
                }

                cIngredient curIngredient = new cIngredient(ingName, newEffects);
                ingredientList.add(curIngredient);
                int b = 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}