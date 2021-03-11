package com.caio.cervejeiros_sa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.caio.cervejeiros_sa.R;
import com.caio.cervejeiros_sa.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class AdapterBeerList extends RecyclerView.Adapter<AdapterBeerList.BeerListViewHolder>{

    private List<Beer> beerList;
    private List<Beer> filteredBeerList;
    private Context context;
    private OnBeerListener mOnBeerListener;

    public AdapterBeerList(List<Beer> beerList, Context context, OnBeerListener onBeerListener) {
        this.beerList = beerList;
        this.context = context;
        this.mOnBeerListener = onBeerListener;
    }



    @NonNull
    @Override
    public BeerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_beer_list,parent,false);
        return new AdapterBeerList.BeerListViewHolder(listItem,mOnBeerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerListViewHolder holder, int position) {
        Beer beer = beerList.get(position);

        holder.textBeerName.setText(beer.getName());
        holder.textBeerTagLine.setText(beer.getTagline());
        Glide.with(context).load(beer.getImage_url()).into(holder.imageBeer);


    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }



    public class BeerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageBeer;
        TextView textBeerName;
        TextView textBeerTagLine;
        OnBeerListener onBeerListener;

        public BeerListViewHolder(@NonNull View itemView,OnBeerListener onBeerListener) {
            super(itemView);

            textBeerName = itemView.findViewById(R.id.textBeerName);
            textBeerTagLine = itemView.findViewById(R.id.textBeerTagLine);
            imageBeer = itemView.findViewById(R.id.imageBeerList);
            this.onBeerListener = onBeerListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onBeerListener.onBeerListener(getAdapterPosition());

        }
    }

    public interface OnBeerListener{
        void onBeerListener(int position);
    }


}
