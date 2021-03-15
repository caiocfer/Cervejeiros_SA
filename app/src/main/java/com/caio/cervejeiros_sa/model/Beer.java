package com.caio.cervejeiros_sa.model;

import java.io.Serializable;

public class Beer implements Serializable {
    private String id;
    private String name;
    private String tagline;
    private String description;
    private String image_url;
    private BeerIngredients ingredients;
    private boolean favorite=false;


    public Beer(){
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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
