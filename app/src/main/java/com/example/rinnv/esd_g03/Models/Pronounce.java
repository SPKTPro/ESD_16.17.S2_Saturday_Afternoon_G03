package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class Pronounce {
    //data
    private String Id_Pronounce;
    private String Pronounce;
    //contructor
    public Pronounce(String id_Pronounce, String pronounce) {
        this.Id_Pronounce = id_Pronounce;
        this.Pronounce = pronounce;
    }
    //get set method

    public String getId_Pronounce() {
        return Id_Pronounce;
    }

    public String getPronounce() {
        return Pronounce;
    }
}
