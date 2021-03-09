package com.caio.cervejeiros_sa.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Beer implements Serializable {
    private String id;
    private String name;
    private String tagline;
    private String first_brewed;
    private String description;
    private String image_url;
    private BeerIngredients ingredients;


    public Beer(){
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getFirst_brewed() {
        return first_brewed;
    }

    public void setFirst_brewed(String first_brewed) {
        this.first_brewed = first_brewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public BeerIngredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(BeerIngredients ingredients) {
        this.ingredients = ingredients;
    }

}
