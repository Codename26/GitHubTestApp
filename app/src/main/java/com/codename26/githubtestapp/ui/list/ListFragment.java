package com.codename26.githubtestapp.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.MvpFragment;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codename26.githubtestapp.GitHubApp.REPOS_LIST;

public class ListFragment extends MvpAppCompatFragment {

    public static ListFragment getInstance(ArrayList<Repo> repos) {
        ListFragment listFragment = new ListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(REPOS_LIST, repos);
        listFragment.setArguments(args);
        return listFragment;
    }

    @BindView(R.id.reposListRecyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getView());
    }
}
