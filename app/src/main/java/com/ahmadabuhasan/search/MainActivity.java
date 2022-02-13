package com.ahmadabuhasan.search;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class MainActivity extends AppCompatActivity {

    EditText etSearch;
    MainAdapter adapter;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideKeyboard();
        etSearch = findViewById(R.id.etSearch);

        recyclerView = findViewById(R.id.data_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        DBAccess dbAccess = DBAccess.getInstance(this);
        dbAccess.open();

        List<HashMap<String, String>> dataProduct = dbAccess.getData();
        if (dataProduct.size() <= 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        } else {
            MainAdapter mainAdapter = new MainAdapter(this, dataProduct);
            this.adapter = mainAdapter;
            this.recyclerView.setAdapter(mainAdapter);
        }

        this.etSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DBAccess databaseAccess = DBAccess.getInstance(MainActivity.this);
                databaseAccess.open();
                List<HashMap<String, String>> searchData = databaseAccess.getSearchData(s.toString());
                if (searchData.size() <= 0) {
                    MainActivity.this.recyclerView.setVisibility(View.GONE);
                    return;
                }
                MainActivity.this.recyclerView.setVisibility(View.VISIBLE);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.adapter = new MainAdapter(mainActivity, searchData);
                MainActivity.this.recyclerView.setAdapter(MainActivity.this.adapter);
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}