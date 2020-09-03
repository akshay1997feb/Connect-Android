package com.connect.NewsFeed;

import com.connect.Auth.BasicAuthInterceptor;
import com.connect.Auth.Validate;
import com.connect.NewsFeed.model.Feed;

import java.io.File;
import java.util.List;
import java.util.Map;
import io.reactivex.Observable;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

import static com.connect.Auth.Validate.httpLoggingInterceptor;
import static com.connect.Auth.Validate.provideOfflineCacheInterceptor;

public interface NewsFeedApi {

    String BASE_URL = "http://192.168.42.206:8000/firstapp/";
//
//    int cacheSize = 10 * 1024 * 1024;
//    Cache cache = Validate.cache;
//    OkHttpClient client = new OkHttpClient.Builder()
//            .cache(cache)
//            .build();


    @Headers("Content-Type: application/json")
    @GET("newsfeed/")
    Call<List<Feed>> getData(
            @HeaderMap Map<String, String> headers
    );

    @Headers("Content-Type: application/json")
    @POST("post/likes/count/")
    Call<ResponseBody> countLikes(
            @Body Map<String, String> body,
            @HeaderMap Map<String, String> headers
    );

    @Headers("Content-Type: application/json")
    @GET("newsfeed/")
    Observable<List<Feed>> getObsData(
            @HeaderMap Map<String, String> headers
    );

    @Headers("Content-Type: application/json")
    @POST("user/feed/")
    Observable<List<Feed>> getUserFeed(
            @Body Map<String, String> body,
            @HeaderMap Map<String, String> headers
    );


    @Headers("Content-Type: application/json")
    @POST("post/likes/count/")
    Observable<ResponseBody> countLikesObs(
            @Body Map<String, String> body,
            @HeaderMap Map<String, String> headers
    );

    @Headers("Content-Type: application/json")
    @POST("post/comments/count/")
    Observable<ResponseBody> countCommentsObs(
            @Body Map<String, String> body,
            @HeaderMap Map<String, String> headers
    );

    @Headers("Content-Type: application/json")
    @POST("post/liked/")
    Observable<ResponseBody> getLiked(
            @Body Map<String, String> body,
            @HeaderMap Map<String, String> headers
    );

    public static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());

    static Retrofit retrofit = retrofitBuilder.build();

    static NewsFeedApi requestApi = retrofit.create(NewsFeedApi.class);

    public static NewsFeedApi getRequestApi(){
        return requestApi;
    }


}



