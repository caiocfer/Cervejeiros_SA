package com.caio.cervejeiros_sa.fragment;


import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.caio.cervejeiros_sa.R;
import com.caio.cervejeiros_sa.activity.BeerDetailsActivity;
import com.caio.cervejeiros_sa.adapter.AdapterBeerList;
import com.caio.cervejeiros_sa.api.BeerService;
import com.caio.cervejeiros_sa.api.RetrofitClient;
import com.caio.cervejeiros_sa.model.Beer;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment implements AdapterBeerList.OnBeerListener{

    private Retrofit retrofit;
    private List<Beer> beerList = new ArrayList<>();
    private List<Beer> beerListAux = new ArrayList<>();
    private AdapterBeerList adapterBeerList;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private int page = 1;
    private ProgressBar progressBar;
    private boolean loading;
    private int pastVisibleItems, visibleItemCount,totalItemCount;
    private LinearLayoutManager linearLayoutManager;





    public HomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerBeerList);
        searchView = view.findViewById(R.id.searchBeer);
        progressBar = view.findViewById(R.id.progressBar);

        retrofit = RetrofitClient.getRetrofit();
        getBeerList(page);
        getRecyclerView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty() || newText.equals("")){
                    beerList.clear();
                    beerListAux.clear();
                    getBeerList(1);
                    adapterBeerList.notifyDataSetChanged();
                    getRecyclerView();

                }else{
                    beerList.clear();
                    beerListAux.clear();
                    getBeerList(newText);
                    adapterBeerList.notifyDataSetChanged();
                }
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.clearFocus();
                beerList.clear();
                beerListAux.clear();
                getBeerList(1);
                adapterBeerList.notifyDataSetChanged();
                getRecyclerView();
                return true;
            }
        });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if((visibleItemCount+pastVisibleItems) >= totalItemCount && page<=13){
                        loadMoreItems();
                        adapterBeerList.notifyDataSetChanged();
                    }


                }

            }
        });




        return  view;
    }

    private void loadMoreItems(){
        page +=1;
        getBeerList(page);
    }




    public void getBeerList(int page){
        isLoading(true);
        BeerService beerService = retrofit.create(BeerService.class);
        Call<List<Beer>> call = beerService.getBeer(page);

        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if(response.isSuccessful()){

                    beerList = response.body();
                    adapterBeerList.addAll(beerList);
                    beerListAux.addAll(beerList);
                    isLoading(false);
                }


            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getBeerList(String search){
        isLoading(true);
        BeerService beerService = retrofit.create(BeerService.class);
        Call<List<Beer>> call = beerService.getBeerSearch(search);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                beerList = response.body();
                beerListAux.clear();
                beerListAux.addAll(beerList);

                getRecyclerView();
                isLoading(false);

            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                t.printStackTrace();
            }
        });



    }

    public void getRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapterBeerList = new AdapterBeerList(beerList,getActivity(),this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBeerList);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBeerListener(int position) {
        Beer beer = beerListAux.get(position);
        Intent intent = new Intent(getActivity(), BeerDetailsActivity.class);
        intent.putExtra("selectedBeer", beer);
        searchView.clearFocus();
        startActivity(intent);
    }

    private boolean isLoading(boolean loading){
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
            return true;
        }
        else{
            progressBar.setVisibility(View.GONE);
            return false;
        }
    }
}