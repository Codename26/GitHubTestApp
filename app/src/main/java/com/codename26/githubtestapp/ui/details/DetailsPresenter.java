package com.codename26.githubtestapp.ui.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.codename26.githubtestapp.model.Repo;

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    private Repo repo;

    public DetailsPresenter(Repo repo) {
        this.repo = repo;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().initDetails(repo);
    }

    public void openEditScreen(){
        getViewState().openEditFragment(repo);
    }
}
