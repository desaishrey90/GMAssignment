package com.example.gmassignment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
