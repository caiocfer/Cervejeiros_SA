package com.caio.cervejeiros_sa.api;

import com.caio.cervejeiros_sa.model.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerService {

    @GET("beers")
    Call<List<Beer>> getBeer(
            @Query("page") int pageNumber
    );



    @GET("beers")
    Call<List<Beer>> getBeerSearch(
            @Query("beer_name") String beerName
    );

}
