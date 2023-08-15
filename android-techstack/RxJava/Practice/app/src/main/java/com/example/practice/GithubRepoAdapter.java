package com.example.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoAdapter extends BaseAdapter {

    private List<GithubRepo> githubRepos = new ArrayList<>();

    @Override
    public int getCount() {
        return githubRepos.size();
    }

    @Override
    public GithubRepo getItem(int position) {
        if (position < 0 || position > githubRepos.size()) {
            return null;
        } else {
            return githubRepos.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null) ? convertView : createView(parent);
        final GithubRepoViewHolder viewHolder = (GithubRepoViewHolder) view.getTag();
        viewHolder.setGithubRepo(getItem(position));
        return view;
    }

    public void setGithubRepos(List<GithubRepo> repos) {
        if (repos == null) {
            return;
        }
        githubRepos.clear();
        githubRepos.addAll(repos);
        notifyDataSetChanged();
    }

    private View createView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_github_repo, parent, false);
        final GithubRepoViewHolder viewHolder = new GithubRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public static class GithubRepoViewHolder {
        TextView text_repo_name;
        TextView text_repo_description;
        TextView text_language;
        TextView text_stars;

        public GithubRepoViewHolder(View view) {
            text_repo_name = view.findViewById(R.id.text_repo_name);
            text_repo_description = view.findViewById(R.id.text_repo_description);
            text_language = view.findViewById(R.id.text_language);
            text_stars = view.findViewById(R.id.text_stars);
        }

        public void setGithubRepo(GithubRepo githubRepo) {
            text_repo_name.setText(githubRepo.name);
            text_repo_description.setText(githubRepo.description);
            text_language.setText("Language: " + githubRepo.language);
            text_stars.setText("Stars: " + githubRepo.stargazersCount);
        }
    }
}
