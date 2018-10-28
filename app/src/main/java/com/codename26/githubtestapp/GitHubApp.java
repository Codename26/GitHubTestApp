package com.codename26.githubtestapp;

import android.app.Application;

import com.codename26.githubtestapp.data.remote.GitHubService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ConnectionSpec;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GitHubApp extends Application {

    public static final String REPOS_LIST = "REPOS_LIST";
    private static final String BASE_URL = "https://api.github.com";

    private String authToken;
    private final String username = "codename26";
    private final String password = "pbrv8S04215";
    private Retrofit retrofit;
    private static GitHubService gitHubService;


    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(logging);
        clientBuilder.connectionSpecs(Collections.singletonList(ConnectionSpec.MODERN_TLS));
        authToken = Credentials.basic(username, password);

        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();
                Headers headers = request.headers().newBuilder().add("Authorization", authToken).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };

        clientBuilder.addInterceptor(headerAuthorizationInterceptor);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubService getGitHubService(){
        return gitHubService;
    }


}
