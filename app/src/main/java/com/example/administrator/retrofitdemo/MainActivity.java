package com.example.administrator.retrofitdemo;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public Retrofit retrofit;
    public ApiService apiService;
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNewtork();
        textView = (TextView) findViewById(R.id.view);
        getTest();
    }

    public void initNewtork(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://php.weather.sina.com.cn/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void getTest() {

            //Call<String> call = apiService.getWeather(strCity, "DJOYnieT8234jlsK", 0);
            Observable.just("1","2","3")
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String name) {
                           System.out.println(name);
                        }
                    });

            apiService.getObservableWeather().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.toString());
                        }

                        @Override
                        public void onNext(String s) {
                            textView.setText(s);
                        }
                    });
            //Call<String> call = apiService.get();

//            call.enqueue(new Callback<String>() {
//                @Override
//                public void onResponse(Call<String> call, Response<String> response) {
//                    String result = response.body();
//                    System.out.println("result = " + result);
//                }
//
//                @Override
//                public void onFailure(Call<String> call, Throwable t) {
//                    System.out.println("请求失败");
//                }
//            });
    }
}
