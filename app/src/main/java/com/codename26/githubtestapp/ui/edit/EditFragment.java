package com.codename26.githubtestapp.ui.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.codename26.githubtestapp.R;
import com.codename26.githubtestapp.model.Repo;
import com.codename26.githubtestapp.ui.details.DetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codename26.githubtestapp.GitHubApp.REPO;

public class EditFragment extends MvpAppCompatFragment implements EditView  {

    @InjectPresenter
    EditPresenter editPresenter;

    @ProvidePresenter
    EditPresenter provideEditPresenter(){
        return new EditPresenter(getArguments().getParcelable(REPO));
    }

    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextDesc)
    EditText editTextDesc;
    @BindView(R.id.editTextFullName)
    EditText editTextFullName;
    @BindView(R.id.textViewId)
    TextView textViewId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void saveRepo(Repo repo) {
        getFragmentManager().popBackStack();
        repo.setName(editTextName.getText().toString());
        repo.setFullName(editTextFullName.getText().toString());
        repo.setDescription(editTextDesc.getText().toString());
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(REPO, repo);
        fragment.setArguments(args);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_fragment_container, fragment)
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.save_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                editPresenter.saveRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void initTextFields(Repo repo) {
        editTextName.setText(repo.getName());
        editTextDesc.setText(repo.getDescription());
        editTextFullName.setText(repo.getFullName());
        textViewId.setText(repo.getId());
    }

}
