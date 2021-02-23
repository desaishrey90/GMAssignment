package com.example.gmassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button btnSearch, btnRepoSearch;
    EditText etUserSearchText, etRepoSearchText;
    LinearLayout llRepoSearch;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llRepoSearch = (LinearLayout)findViewById(R.id.llRepoSearch);
        btnSearch = (Button)findViewById(R.id.btnSearch);
        btnRepoSearch = (Button)findViewById(R.id.btnRepoSearch);
        etUserSearchText = (EditText)findViewById(R.id.etUserSearchText);
        etRepoSearchText = (EditText)findViewById(R.id.etRepoSearchText);

        dialog = new ProgressDialog(MainActivity.this);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard
                View view = MainActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //check if input string is empty
                String txt = etUserSearchText.getText().toString();
                if(txt.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter a user name.", Toast.LENGTH_LONG ).show();
                    return;
                }

                dialog.setMessage("Searching for repositories..");
                dialog.show();
                getRepos(etUserSearchText.getText().toString());
            }
        });
    }

    public void getRepos(String name) {
        try {
            final GitHubHelper githubHelper = new GitHubHelper();

            githubHelper.getUserRepos(name, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    dialog.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    dialog.dismiss();
                    if (response.code() == 200) {
                        llRepoSearch.setVisibility(View.VISIBLE);
                    }
                    else {
                        Log.v("GetRepoErrorCode", String.valueOf(response.code()));
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Please enter a valid user name.", Toast.LENGTH_LONG ).show();
                                return;
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}