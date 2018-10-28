package com.codename26.githubtestapp.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codename26.githubtestapp.GitHubApp;
import com.codename26.githubtestapp.ui.list.ListActivity;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.data.remote.GitHubService;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

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

    }
}
