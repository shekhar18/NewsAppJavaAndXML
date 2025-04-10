package com.mahadiks.newsappjavaandxml.data.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class News {
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public int totalResults;
    @SerializedName("articles")
    public ArrayList<Article> articles;
}


