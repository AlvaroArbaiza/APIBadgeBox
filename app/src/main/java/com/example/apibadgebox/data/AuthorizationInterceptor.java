package com.example.apibadgebox.data;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {

    private String apiKey = "5277ff227ef6b66797d4c934025a2f8a";
    private String apiSecret = "02a7cef518d041d9732e05fc0b5e4430";
    private int limit = 10;
    private int page = 0;


    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request requestAuthorization =
                request.newBuilder()
                .addHeader("api_key", getApiKey())
                .addHeader("api_secret", getApiSecret())
                .addHeader("limit", Integer.toString(getLimit()))
                .addHeader("page", Integer.toString(getPage()))
                .build();
        return chain.proceed(requestAuthorization);

    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }
    public int getLimit() {
        return limit;
    }
    public int getPage() {
        return page;
    }



}
