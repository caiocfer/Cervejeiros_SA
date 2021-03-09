package com.caio.cervejeiros_sa.model;

import java.io.Serializable;
import java.util.ArrayList;

public class BeerIngredients implements Serializable {

    private ArrayList<Malt> malt;
    private ArrayList<Hops> hops;
    private String yeast;

    public BeerIngredients() {

    }

    public class Malt implements Serializable{
        private String name;
        private Amount amount;

        public Malt(){
        }

        public String getName() {
            return name;
        }

        public Amount getAmount() {
            return amount;
        }

        public Double getAmountValue(){
            return amount.value;
        }

        public String getAmountUnit(){
            return amount.unit;
        }




    }

    public class Hops implements  Serializable{
        private String name;
        private Amount amount;
        private String add;
        private String attribute;

        public Hops(){}

        public String getName() {
            return name;
        }

        public Amount getAmount() {
            return amount;
        }

        public String getAdd() {
            return add;
        }

        public String getAttribute() {
            return attribute;
        }

        public Double getAmountValue(){
            return amount.value;
        }

        public String getAmountUnit(){
            return amount.unit;
        }

    }

    private class Amount implements Serializable{
        private Double value;
        private String unit;

        public Amount(){}

        public Double getValue() {
            return value;
        }

        public String getUnit() {
            return unit;
        }


    }


    public ArrayList<Malt> getMalt() {
        return malt;
    }

    public void setMalt(ArrayList<Malt> malt) {
        this.malt = malt;
    }

    public ArrayList<Hops> getHops() {
        return hops;
    }

    public void setHops(ArrayList<Hops> hops) {
        this.hops = hops;
    }

    public String getYeast() {
        return yeast;
    }

    public void setYeast(String yeast) {
        this.yeast = yeast;
    }
}
