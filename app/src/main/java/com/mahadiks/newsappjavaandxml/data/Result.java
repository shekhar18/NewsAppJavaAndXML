package com.mahadiks.newsappjavaandxml.data;

public abstract class Result<T> {

    private Result(){}

    static final class Success<T> extends Result<T>{
        final T data;
        Success(T data){this.data = data;}
    }

    static final class Error<T> extends Result<T>{
        final T message;

        Error(T message){this.message = message;}

    }

    static final class Loading<T> extends Result<T>{}
}
