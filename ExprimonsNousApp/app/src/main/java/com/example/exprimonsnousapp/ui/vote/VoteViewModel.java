package com.example.exprimonsnousapp.ui.vote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VoteViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VoteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}