package com.codename26.githubtestapp.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;

public class ListRepositoryAdapter extends RecyclerView.Adapter<ListRepositoryAdapter.ListItemViewHolder> {

    private ListActivity.ItemClickListener clickListener;

    public ListRepositoryAdapter(ArrayList<Repo> repos, ListActivity.ItemClickListener clickListener) {
        this.repos = repos;
        this.clickListener = clickListener;
    }

    private ArrayList<Repo> repos;


    @NonNull
    @Override
    public ListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_repository, viewGroup, false);
        return new ListItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemViewHolder listItemViewHolder, int i) {
        listItemViewHolder.bind(repos.get(i));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {

        private TextView repositoryName;
        private TextView repositoryDesc;
        private CardView repoItemCard;


        public ListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            repositoryName = itemView.findViewById(R.id.textRepositoryName);
            repositoryDesc = itemView.findViewById(R.id.textRepositoryDesc);
            repoItemCard = itemView.findViewById(R.id.repoItemCard);
        }

        public void bind(Repo repo) {
            repositoryName.setText(repo.getName());
            repositoryDesc.setText(repo.getDescription());
            repoItemCard.setOnClickListener(v -> clickListener.onItemClick(repo));
        }
    }
}
