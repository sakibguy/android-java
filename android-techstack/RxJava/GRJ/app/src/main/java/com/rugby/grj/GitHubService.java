package com.rugby.grj;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

// Create the Github Service interface.
public abstract class GitHubService {
    @GET("users/{user}/starred")
    abstract Observable<List<GitHubRepo>> getStarredRepositories(@Path("user") String userName);
}
