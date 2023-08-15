package com.example.practice;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class GithubClient {
    public static GithubClient instance;
    public static GithubClient getInstance() {
        if (instance == null) {
            instance = new GithubClient();
        }
        return instance;
    }

    GithubService githubService;
    String GITHUB_BASE_URL = "https://api.github.com";
    private GithubClient() {
        final Gson gson =
                new GsonBuilder()
                        .setFieldNamingPolicy(
                                FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        githubService = retrofit.create(GithubService.class);
    }

    public Observable<List<GithubRepo>> getStarredRepos(@NonNull String username) {
        return githubService.getStarredRepositories(username);
    }
}
