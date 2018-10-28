package com.codename26.githubtestapp.ui.edit;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.codename26.githubtestapp.model.Repo;

public interface EditView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void saveRepo(Repo repo);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void initTextFields(Repo repo);
}
