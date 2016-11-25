package com.example.administrator.retrofitdemo;

import retrofit2.*;
import retrofit2.http.*;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ApiService {
    @GET("xml.php")
    Call<String> getWeather(@Query("city") String city, @Query("password") String password, @Query("day") int day);
    @GET("http://php.weather.sina.com.cn/xml.php?city=%B1%B1%BE%A9&password=DJOYnieT8234jlsK&day=0")
    Call<String> get();

    @GET("http://php.weather.sina.com.cn/xml.php?city=%B1%B1%BE%A9&password=DJOYnieT8234jlsK&day=0")
    Observable<String> getObservableWeather();
}
