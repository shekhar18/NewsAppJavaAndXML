package com.mahadiks.newsappjavaandxml.data.remote;

import com.mahadiks.newsappjavaandxml.data.NewsApiService;
import com.mahadiks.newsappjavaandxml.data.NewsRepository;
import com.mahadiks.newsappjavaandxml.data.remote.models.News;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class RemoteRepository implements NewsRepository {

   private  NewsApiService apiService = null;
   @Inject
   public RemoteRepository(NewsApiService newsApiService){
       this.apiService = newsApiService;
   }



   public Observable<News> getNews(){
       return apiService.getNews("tesla","2025-03-10","publishedAt","44be455366de4215a249cddc2b0f269c");
   }

}
