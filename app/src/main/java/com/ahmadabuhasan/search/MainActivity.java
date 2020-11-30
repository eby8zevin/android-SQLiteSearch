package com.ahmadabuhasan.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ahmad Abu Hasan on 30/11/2020
 */

public class MainActivity extends AppCompatActivity {

    EditText etSearch;
    BarangAdapter barangAdapter;
    public RecyclerView recyclerView;

    private SQLiteDatabase db = null;
    private DBHelper data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);

        data = new DBHelper(this);
        db = data.getWritableDatabase();
        data.add(db);

        recyclerView = findViewById(R.id.data_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        DBAccess dbAccess = DBAccess.getInstance(this);
        dbAccess.open();

        this.etSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DBAccess databaseAccess = DBAccess.getInstance(MainActivity.this);
                databaseAccess.open();
                List<HashMap<String, String>> searchBarangList = databaseAccess.getSearchBarang(s.toString());
                if (searchBarangList.size() <= 0) {
                    MainActivity.this.recyclerView.setVisibility(View.GONE);
                    return;
                }
                MainActivity.this.recyclerView.setVisibility(View.VISIBLE);
                MainActivity barangActivity = MainActivity.this;
                barangActivity.barangAdapter = new BarangAdapter(barangActivity, searchBarangList);
                MainActivity.this.recyclerView.setAdapter(MainActivity.this.barangAdapter);
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }
}