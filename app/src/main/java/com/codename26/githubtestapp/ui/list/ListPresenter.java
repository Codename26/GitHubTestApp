package com.codename26.githubtestapp.ui.list;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {
    private ArrayList<Repo> repos;

    public ListPresenter(ArrayList<Repo> repos) {
        if (repos.size() > 0)
        getViewState().initList(repos);
        else
            getViewState().showNoRepos();
    }

    public void showDetails(Repo repo){
        getViewState().showDetails(repo);
    }

}
