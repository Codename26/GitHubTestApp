package com.codename26.githubtestapp.ui.list;

import android.os.Parcelable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {
    private ArrayList<Repo> repos;

    public ListPresenter(ArrayList<Repo> repos) {
        this.repos = repos;
        getViewState().initList(repos);
    }
}
