package com.codename26.githubtestapp.ui.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.codename26.githubtestapp.model.Repo;

public interface DetailsView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void initDetails(Repo repo);
    @StateStrategyType(AddToEndSingleStrategy.class)
    void openEditFragment(Repo repo);
}
