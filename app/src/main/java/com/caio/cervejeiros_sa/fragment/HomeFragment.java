package com.caio.cervejeiros_sa.fragment;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.caio.cervejeiros_sa.R;
import com.caio.cervejeiros_sa.activity.BeerDetailsActivity;
import com.caio.cervejeiros_sa.adapter.AdapterBeerList;
import com.caio.cervejeiros_sa.api.BeerService;
import com.caio.cervejeiros_sa.api.RetrofitClient;
import com.caio.cervejeiros_sa.model.Beer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements AdapterBeerList.OnBeerListener{

    private Retrofit retrofit;
    private List<Beer> beerList = new ArrayList<>();
    private AdapterBeerList adapterBeerList;
    private RecyclerView recyclerView;
    private Cache cache;




    public HomeFragment() {
        // Required empty public constructor
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerBeerList);

        retrofit = RetrofitClient.getRetrofit();
        getBeerList();


        return  view;
    }




    public void getBeerList(){
        BeerService beerService = retrofit.create(BeerService.class);
        Call<List<Beer>> call = beerService.getBeer();

        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if(response.isSuccessful()){
                    beerList = response.body();
                    getRecyclerView();
                }
                else{
                    Log.d("resultado", "onResponse: " + response.code());

                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {

            }
        });
    }

    public void getRecyclerView(){
        adapterBeerList = new AdapterBeerList(beerList,getActivity(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterBeerList);

    }


    @Override
    public void onStart() {
        super.onStart();
        getBeerList();
    }

    @Override
    public void onResume() {
        super.onResume();
        getBeerList();
    }


    @Override
    public void onBeerListener(int position) {
        Log.d("TAG", "onBeerListener: " + position);
        Beer beer = beerList.get(position);
        Intent intent = new Intent(getActivity(), BeerDetailsActivity.class);
        intent.putExtra("selectedBeer", beer);
        startActivity(intent);

    }
}