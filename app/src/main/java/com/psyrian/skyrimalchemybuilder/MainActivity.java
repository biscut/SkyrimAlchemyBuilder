package com.psyrian.skyrimalchemybuilder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

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
    private static Context mContext;
    private static List<cIngredient> ingredientList;

    public static Context getContext()
    {
        return mContext;
    }
    public static List<cIngredient> getIngredients() { return ingredientList; }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        ingredientList = new ArrayList<cIngredient>();
        this.initializeIngredients();

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
        String jsonData = loadJSONFromAsset(this);

        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject row = jsonArray.getJSONObject(i);

                List<cEffect> newEffects = new ArrayList();
                int ingNameID = getResources().getIdentifier(row.getString("name"), "string", getPackageName());
                String ingName = getString(ingNameID);
                int a = 0;

                for(int j = 1; j <= 4; j++)
                {
                    JSONObject jsonEffect = row.getJSONObject("effect"+ j);


                    //String effName = jsonEffect.getString("name");
                    int effID = getResources().getIdentifier(jsonEffect.getString("name"), "string", getPackageName());
                    float effVal = (float)jsonEffect.getDouble("modValue");
                    float effMag = (float)jsonEffect.getDouble("modMagnitude");
                    cEffect curEffect = new cEffect(effID, effVal, effMag);

                    newEffects.add(curEffect);
                }

                cIngredient curIngredient = new cIngredient(ingNameID, ingName, newEffects);
                ingredientList.add(curIngredient);
                int b = 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onIngredientClick(View v)
    {
        cIngredient toAdd = ingredientList.get(v.getId());
        Log.i("test", toAdd.getName());


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