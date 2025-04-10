package com.mahadiks.newsappjavaandxml.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mahadiks.newsappjavaandxml.data.remote.models.Article;
import com.mahadiks.newsappjavaandxml.databinding.RvNewsItemBinding;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    List<Article> articleList;
    Context context;

    public NewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RvNewsItemBinding rvNewsItemBinding = RvNewsItemBinding.inflate(inflater,parent,false);
        return new MyViewHolder(rvNewsItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.binding(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

  class MyViewHolder extends RecyclerView.ViewHolder {
        RvNewsItemBinding rvNewsItemBinding;
        public MyViewHolder(@NonNull RvNewsItemBinding rvNewsItemBinding) {
            super(rvNewsItemBinding.getRoot());
            this.rvNewsItemBinding = rvNewsItemBinding;
        }

        void binding(Article article){
           rvNewsItemBinding.setArticle(article);
        }
    }
}
