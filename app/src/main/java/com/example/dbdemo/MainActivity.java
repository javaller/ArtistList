package com.example.dbdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.dbdemo.net.MyClass6;
import com.example.dbdemo.net.YandexAPI;
import com.example.dbdemo.net.model.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Db db;
    private View button;
    private EditText input;
    private ListView list;
    private MyAdapter adapter;
    private View empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        input = (EditText)findViewById(R.id.input);
        list = (ListView)findViewById(R.id.list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input.getText().toString();
                if (!name.isEmpty()) {
                    db.addName(name);
                    adapter.setCursor(db.getNames());
                }
            }
        });

        db = Db.getInstance(getApplicationContext());

        adapter = new MyAdapter(this);
        adapter.setCursor(db.getNames());
        list.setAdapter(adapter);

        empty = findViewById(R.id.empty);
        list.setEmptyView(empty);

        loadDataFromServer();

    }

    private void loadDataFromServer() {
        YandexAPI api = MyClass6.getYandexAPI();
        Call<List<Artist>> call = api.getArtists();
        call.enqueue(new Callback<List<Artist>>() {
            @Override
            public void onResponse(Call<List<Artist>> call, Response<List<Artist>> response) {
                if (response.isSuccessful()) {
                    db.saveArtistList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Artist>> call, Throwable t) {
                Log.d("happy", t.getMessage());
            }
        });
    }
}
