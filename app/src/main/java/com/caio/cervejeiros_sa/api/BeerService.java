package com.caio.cervejeiros_sa.api;

import com.caio.cervejeiros_sa.model.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeerService {

    @GET("beers")
    Call<List<Beer>> getBeer();
}