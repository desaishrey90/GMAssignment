package com.example.gmassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSearch;
    EditText etUserSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        etUserSearchText = (EditText)findViewById(R.id.etUserSearchText);

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
            }
        });
    }
}