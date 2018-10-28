package com.codename26.githubtestapp.ui.list;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codename26.githubtestapp.GitHubApp.REPOS_LIST;

public class ListActivity extends MvpAppCompatActivity implements ListView {

    @BindView(R.id.reposListRecyclerView)
    RecyclerView recyclerView;

    @InjectPresenter
    ListPresenter listPresenter;
    @ProvidePresenter
    ListPresenter provideListPresenter(){
        return new ListPresenter(getIntent().getParcelableArrayListExtra(REPOS_LIST));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
    }



    @Override
    public void setFragment() {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.list_fragment_container, ListFragment.getInstance(repos));
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }

    @Override
    public void initList(ArrayList<Repo> repos) {
        recyclerView.setAdapter(new ListRepositoryAdapter(repos));
    }
}
