package com.codename26.githubtestapp.ui.login;

import com.arellomobile.mvp.MvpView;

public interface LoginView extends MvpView {
    void showProgress();
    void hideProgress();
    void showErrorMessage(String errorMessage);
    void onSignIn();
}
