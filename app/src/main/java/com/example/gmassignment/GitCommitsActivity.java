package com.example.gmassignment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmassignment.Adapters.GitCommitAdapter;
import com.example.gmassignment.Model.Commit;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GitCommitsActivity extends AppCompatActivity {
    RecyclerView rvCommits;
    //ListView lvCommits;
    String userName, repoName;
    ArrayList<Commit> commits;
    private ProgressDialog dialog;
    private GitCommitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gitcommits);

        rvCommits = (RecyclerView)findViewById(R.id.rvCommits);
        //lvCommits = (ListView)findViewById(R.id.lvCommits);
        userName = getIntent().getStringExtra("userName");
        repoName = getIntent().getStringExtra("repoName");
        dialog = new ProgressDialog(GitCommitsActivity.this);
        getCommits(repoName);
    }

    public void getCommits(String repoName){
        try{
            dialog.setMessage("Searching for commits..");
            dialog.show();
            final GitHubHelper githubHelper = new GitHubHelper();
            githubHelper.getCommits("https://api.github.com/repos/" + userName + "/" + repoName + "/commits", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() == 200) {
                        commits = githubHelper.processCommitResult(response);
                        GitCommitsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new GitCommitAdapter(commits);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                rvCommits.setLayoutManager(layoutManager);
                                rvCommits.setHasFixedSize(true);
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCommits.getContext(), DividerItemDecoration.VERTICAL);
                                rvCommits.addItemDecoration(dividerItemDecoration);
                                rvCommits.setAdapter(adapter);
                                //lvCommits.setAdapter(adapter);

                                dialog.hide();
                            }
                        });
                    }
                    else {
                        Log.i("ResponseCode", String.valueOf(response.code()));
                        GitCommitsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.hide();
                                Toast.makeText(GitCommitsActivity.this, "There was an error while fetching commits.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
