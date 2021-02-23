package com.example.gmassignment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubHelper {
    public static void getUserRepos(String name, Callback callback) {
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.com/users/"+name+"/repos").newBuilder();
            urlBuilder.addQueryParameter("access_token",Constants.GITHUB_TOKEN);
            String url = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> processUserRepos(Response response) {
        ArrayList<String> repoNames = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONArray reposJsonArray = new JSONArray(jsonData);

            for (int i = 0; i < reposJsonArray.length(); i++) {
                JSONObject repo = reposJsonArray.getJSONObject(i);
                repoNames.add(repo.getString("name"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repoNames;
    }

    public void getCommits(String commitUrl, Callback callback){
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            HttpUrl.Builder urlBuilder = HttpUrl.parse(commitUrl).newBuilder();
            urlBuilder.addQueryParameter("access_token",Constants.GITHUB_TOKEN);
            String url = urlBuilder.build().toString();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
