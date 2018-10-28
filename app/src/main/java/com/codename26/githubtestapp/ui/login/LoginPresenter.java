package com.codename26.githubtestapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.codename26.githubtestapp.GitHubApp;
import com.codename26.githubtestapp.data.remote.GitHubService;
import com.codename26.githubtestapp.model.Repo;
import com.codename26.githubtestapp.ui.list.ListActivity;
import com.codename26.githubtestapp.util.ValidationUtil;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.codename26.githubtestapp.GitHubApp.REPOS_LIST;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public void signIn(String login, String password) {
        if (isValid(login, password)) {
            performLoginAttempt(login, password);
        }
    }

    private void performLoginAttempt(String login, String password) {

        getViewState().showProgress();
        GitHubService gitHubService = GitHubApp.getGitHubService();

        gitHubService.getRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(repo -> Timber.d("repos: " + repo))
                .doOnError(e -> Timber.d("error: " + e))
                .subscribe(new Observer<ArrayList<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Repo> repos) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(REPOS_LIST ,repos);
                        startActivity(new Intent(LoginActivity.this, ListActivity.class));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(LoginActivity.this, "Login failed. Please, check your credentials", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private boolean isValid(String login, String password) {
        if (!ValidationUtil.isLoginValid(login)) {
            getViewState().showErrorMessage("Login is invalid");
            return false;
        }
        if (!ValidationUtil.isPasswordValid(password)) {
            getViewState().showErrorMessage("Password is invalid");
            return false;
        }
        return true;
    }
}
