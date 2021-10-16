package com.psyrian.skyrimalchemybuilder.ui.byEffect;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ByEffectViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ByEffectViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}