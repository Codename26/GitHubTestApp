package com.codename26.githubtestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codename26.githubtestapp.data.remote.GitHubService;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.codename26.githubtestapp.GitHubApp.REPOS_LIST;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
}
