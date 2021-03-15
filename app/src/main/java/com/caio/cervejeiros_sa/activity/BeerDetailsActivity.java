package com.caio.cervejeiros_sa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caio.cervejeiros_sa.R;
import com.caio.cervejeiros_sa.model.Beer;
import com.caio.cervejeiros_sa.model.BeerIngredients;

import java.util.ArrayList;

public class BeerDetailsActivity extends AppCompatActivity {

    private Beer selectedBeer;
    private ImageView imageSelectedBeer;
    private TextView textSelectedBeerName;
    private TextView textSelectedBeerDescription;
    private TextView textSelectedBeerYeast;
    private TextView textSelectedBeerMaltList;
    private TextView textSelectedBeerHopList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_24);

        imageSelectedBeer = findViewById(R.id.imageSelectedBeer);
        textSelectedBeerName = findViewById(R.id.textSelectedBeerName);
        textSelectedBeerDescription = findViewById(R.id.textSelectedBeerDescription);
        textSelectedBeerMaltList = findViewById(R.id.textSelectedBeerMaltList);
        textSelectedBeerYeast = findViewById(R.id.textSelectedBeerYeast);
        textSelectedBeerHopList = findViewById(R.id.textSelectedBeerHopList);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            selectedBeer = (Beer) bundle.getSerializable("selectedBeer");
            String maltList = recoverMaltList(selectedBeer);
            String hopList = recoverHopList(selectedBeer);
//            Glide.with(BeerDetailsActivity.this).load(url).into(imageSelectedBeer);
            if(selectedBeer.getImage_url() != null) {
                Uri url = Uri.parse(selectedBeer.getImage_url());
                Glide.with(BeerDetailsActivity.this).load(url).into(imageSelectedBeer);
            }

            textSelectedBeerName.setText(selectedBeer.getName());
            getSupportActionBar().setTitle(selectedBeer.getName());
            textSelectedBeerDescription.setText(selectedBeer.getDescription());
            textSelectedBeerMaltList.setText(maltList);
            textSelectedBeerHopList.setText(hopList);
            textSelectedBeerYeast.setText(selectedBeer.getIngredients().getYeast());




        }
    }

    private String recoverMaltList(Beer beer){
        ArrayList<BeerIngredients.Malt> malt = beer.getIngredients().getMalt();
        String maltList = "";
        for(int i=0;i<malt.size();i++){
            maltList = maltList + malt.get(i).getName() + "\n";
            maltList = maltList + "Amount: " + malt.get(i).getAmountValue();
            maltList = maltList +" " +malt.get(i).getAmountUnit() + "\n";
        }

        return maltList;

    }

    private String recoverHopList(Beer beer){
        ArrayList<BeerIngredients.Hops> hops = beer.getIngredients().getHops();
        String hopList = "";
        for(int i=0;i<hops.size();i++){
            hopList = hopList + hops.get(i).getName() + "\n";
            hopList = hopList + "Amount: " + hops.get(i).getAmountValue();
            hopList = hopList +" " +hops.get(i).getAmountUnit() + "\n";
            hopList = hopList +"Add: " +hops.get(i).getAdd() + "\n";
            hopList = hopList +"Attribute: " +hops.get(i).getAttribute() + "\n\n";
        }
        return hopList;

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }



}