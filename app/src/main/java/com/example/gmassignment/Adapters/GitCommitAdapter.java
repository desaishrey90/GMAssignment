package com.example.gmassignment.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gmassignment.Model.Commit;
import com.example.gmassignment.R;

import java.util.List;

public class GitCommitAdapter extends RecyclerView.Adapter<GitCommitAdapter.ViewHolder> {
    List<Commit> commits;

    public GitCommitAdapter(List<Commit> commits){
        this.commits = commits;
    }

    @NonNull
    @Override
    public GitCommitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.commits_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitCommitAdapter.ViewHolder holder, int position) {
        holder.bind(commits.get(position));
    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCommit, txtAuthor, txtCommitHash;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
            //bind();
        }

        public void init(View itemView){
            txtCommit = (TextView)itemView.findViewById(R.id.txtCommit);
            txtAuthor = (TextView)itemView.findViewById(R.id.txtAuthor);
            txtCommitHash = (TextView)itemView.findViewById(R.id.txtCommitHash);
        }

        public void bind(Commit commit){
            txtCommit.setText(commit.getcommitMessage());
            txtAuthor.setText(commit.getAuthorName() + " (" + commit.getauthorEmail() + ")");
            txtCommitHash.setText(commit.getcommitHash());
        }
    }
}
