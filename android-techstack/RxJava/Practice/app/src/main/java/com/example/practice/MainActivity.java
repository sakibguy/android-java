package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public Subscription subscription;
    public GithubRepoAdapter githubRepoAdapter = new GithubRepoAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_view_repos = findViewById(R.id.list_view_repos);
        list_view_repos.setAdapter(githubRepoAdapter);
        EditText edit_text_username = findViewById(R.id.edit_text_username);
        Button button_search = findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edit_text_username.getText())) {
                    setStarredRepos(edit_text_username.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void setStarredRepos(String username) {
        subscription = GithubClient.getInstance()
                .getStarredRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubRepo>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(List<GithubRepo> githubRepos) {
                        githubRepoAdapter.setGithubRepos(githubRepos);
                    }
                });
    }
}