package com.codename26.githubtestapp.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;
import com.codename26.githubtestapp.ui.edit.EditFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codename26.githubtestapp.GitHubApp.REPO;

public class DetailsFragment extends MvpAppCompatFragment implements DetailsView {

    @InjectPresenter
    DetailsPresenter detailsPresenter;

    @ProvidePresenter
    DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter(getArguments().getParcelable(REPO));
    }


    @BindView(R.id.textDetailedName)
    TextView textDetailedName;
    @BindView(R.id.textDetailedDesc)
    TextView textDetailedDesc;
    @BindView(R.id.textDetailedId)
    TextView textDetailedId;
    @BindView(R.id.textDetailedUrl)
    TextView textDetailedUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initDetails(Repo repo) {
        textDetailedName.setText(repo.getName());
        textDetailedDesc.setText(repo.getDescription());
        textDetailedId.setText(repo.getId());
        textDetailedUrl.setText(repo.getFullName());
    }

    @Override
    public void openEditFragment(Repo repo) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putParcelable(REPO, repo);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.details_fragment_container, fragment)
                .addToBackStack("EditFragment")
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                detailsPresenter.openEditScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
