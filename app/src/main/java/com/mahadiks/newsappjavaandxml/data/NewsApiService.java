package com.mahadiks.newsappjavaandxml.data;

import com.mahadiks.newsappjavaandxml.data.remote.models.News;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("everything")
    Observable<News> getNews(@Query("q") String queryTopic,
                             @Query("from") String date,
                             @Query("sortBy") String sortBy,
                             @Query("apiKey") String apiKey);

}
