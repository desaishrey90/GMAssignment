package com.example.gmassignment.Model;

public class Commit {
    private String authorName, authorEmail, commitMessage, commitHash;

    public Commit(String authorName, String authorEmail, String commitMessage, String commitHash){
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.commitMessage = commitMessage;
        this.commitHash = commitHash;
    }

    public String getAuthorName(){
        return authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public String getauthorEmail(){
        return authorEmail;
    }

    public void setauthorEmail(String authorEmail){
        this.authorEmail = authorEmail;
    }

    public String getcommitMessage(){
        return commitMessage;
    }

    public void setcommitMessage(String commitMessage){
        this.commitMessage = commitMessage;
    }

    public String getcommitHash(){
        return commitHash;
    }

    public void setcommitHash(String commitHash){
        this.commitHash = commitHash;
    }
}
