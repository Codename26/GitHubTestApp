package com.codename26.githubtestapp.ui.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;
import com.codename26.githubtestapp.ui.details.DetailsFragment;
import com.codename26.githubtestapp.ui.details.DetailsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codename26.githubtestapp.GitHubApp.REPO;
import static com.codename26.githubtestapp.GitHubApp.REPOS_LIST;

public class ListActivity extends MvpAppCompatActivity implements ListView {

    @BindView(R.id.reposListRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textNoRepos)
    TextView textNoRepos;

    private ItemClickListener itemClickListener;

    @InjectPresenter
    ListPresenter listPresenter;
    @ProvidePresenter
    ListPresenter provideListPresenter() {
        return new ListPresenter(getIntent().getParcelableArrayListExtra(REPOS_LIST));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(Repo repo) {
                listPresenter.showDetails(repo);
            }
        };
    }

    @Override
    public void initList(ArrayList<Repo> repos) {
        recyclerView.setAdapter(new ListRepositoryAdapter(repos, itemClickListener));
    }

    @Override
    public void showNoRepos() {
        textNoRepos.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showDetails(Repo repo) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(REPO, repo);
        fragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_fragment_container, fragment)
                .addToBackStack("DetailsFragment")
                .commit();
    }

    public interface ItemClickListener {
        void onItemClick(Repo repo);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
        else super.onBackPressed();
    }
}
