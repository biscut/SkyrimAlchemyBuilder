package com.psyrian.skyrimalchemybuilder.ui.byIngredient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ByIngredientViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ByIngredientViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}