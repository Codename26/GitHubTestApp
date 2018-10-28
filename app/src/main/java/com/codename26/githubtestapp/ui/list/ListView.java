package com.codename26.githubtestapp.ui.list;

import com.arellomobile.mvp.MvpView;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

public interface ListView extends MvpView {
    void setFragment();
    void initList(ArrayList<Repo> repos);
}
