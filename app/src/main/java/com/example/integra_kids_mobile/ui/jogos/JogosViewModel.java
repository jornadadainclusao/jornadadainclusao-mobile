package com.example.integra_kids_mobile.ui.jogos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JogosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JogosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}