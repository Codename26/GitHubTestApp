package com.codename26.githubtestapp.ui.edit;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.codename26.githubtestapp.model.Repo;

@InjectViewState
public class EditPresenter extends MvpPresenter<EditView> {

    private Repo repo;

    public EditPresenter(Repo repo) {
        this.repo = repo;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().initTextFields(repo);
    }

    public void saveRepo() {
        getViewState().saveRepo(repo);
    }
}
