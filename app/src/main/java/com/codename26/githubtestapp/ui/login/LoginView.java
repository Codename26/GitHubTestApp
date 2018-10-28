package com.codename26.githubtestapp.ui.login;

import android.os.Bundle;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface LoginView extends MvpView {
    void showProgress();
    void hideProgress();
    void onSignIn(Bundle bundle);
    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(String errorMessage);

}
