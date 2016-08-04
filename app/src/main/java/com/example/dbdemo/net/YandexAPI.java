package com.example.dbdemo.net;


import com.example.dbdemo.net.model.Artist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Student1 on 20.04.2016.
 */
public interface YandexAPI {
    @GET("artists.json")
    Call<List<Artist>> getArtists();
}
