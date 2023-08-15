package com.example.practice;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GithubService {
    @GET("users/{user}/starred")
    Observable<List<GithubRepo>> getStarredRepositories(@Path("user") String username);
}