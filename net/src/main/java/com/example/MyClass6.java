package com.example;

import com.example.model.Artist;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Student1 on 20.04.2016.
 */
public class MyClass6 {
    public static YandexAPI getYandexAPI() {
        String url = "http://cache-default03g.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

//        API api = retrofit.create(API.class);
        YandexAPI api = retrofit.create(YandexAPI.class);

        return api;

        // на этом boilerplate заканчивается

//        try {
//
//            list = api.getArtists().execute().body();
//            for (Artist a : list) {
//                System.out.println("id: " + a.id + " name: " + a.name);
//            }
//
////            OurIP ourIp = api.getOurIp().execute().body();
////            System.out.println(ourIp.origin);
////
////            DataFromServer someData = api.makeDummyPost("aaa", "bbb").execute().body();
////            System.out.print(someData.headers.contentType);
//
////            api.getOurIp().enqueue();
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
