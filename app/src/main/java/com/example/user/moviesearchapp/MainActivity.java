package com.example.user.moviesearchapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.user.moviesearchapp.api.ApiService;
import com.example.user.moviesearchapp.util.InternetConnetion;
import com.example.user.moviesearchapp.adapter.MovieAdapter;
import com.example.user.moviesearchapp.model.MovieList;
import com.example.user.moviesearchapp.api.RetroClient;
import com.example.user.moviesearchapp.model.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

     List<Search> search;
     MovieAdapter adapter;
     RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = new ArrayList<>();

        final RecyclerView recyclerView = findViewById(R.id.movies);

       //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Checking internet Conntction
                if (InternetConnetion.checkConection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    // Process Dialog for user Interaction
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    ApiService apiService = RetroClient.getApiService();
                    // Calling JSON
                    Call<MovieList> call = apiService.getMyJSON();

                    //Callback wiil be call when responce...
                    call.enqueue(new Callback<MovieList>() {
                        @Override
                        public void onResponse(@NonNull Call<MovieList> call, @NonNull Response<MovieList> response) { // onResponce если успешно
                            dialog.dismiss();
                            if (response.isSuccessful()) {


                                search = response.body().getSearch();

                                adapter = new MovieAdapter(MainActivity.this, search);
                                recyclerView.setAdapter(adapter);
                            } else {
                                Snackbar.make(view, R.string.string_some_thing_vrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<MovieList> call, @NonNull Throwable t) {   // onResponce если не успешно
                            dialog.dismiss();
                        }
                    });
                } else {
                    Snackbar.make(view, R.string.internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });
        }
    }
