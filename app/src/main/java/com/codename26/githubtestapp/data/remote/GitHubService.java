package com.codename26.githubtestapp.data.remote;

import com.codename26.githubtestapp.model.Repo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("/user/repos")
    Observable<ArrayList<Repo>> getRepos();
}
