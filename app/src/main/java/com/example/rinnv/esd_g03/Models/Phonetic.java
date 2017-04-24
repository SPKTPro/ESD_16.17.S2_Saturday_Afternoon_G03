package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class Phonetic {
    //data
    private int Group;
    private  int Id_Phonetic;
    private String Phonetic;
    private String Id_Example;
    private String Id_Pronounce;
    private String Link;

    public Phonetic(int group, int id_Phonetic, String phonetic, String id_Example, String id_Pronounce, String link) {
        this.Group = group;
        this.Id_Phonetic = id_Phonetic;
        this.Phonetic = phonetic;
        this.Id_Example = id_Example;
        this.Id_Pronounce = id_Pronounce;
        this.Link = link;
    }

    public int getGroup() {
        return Group;
    }

    public int getId_Phonetic() {
        return Id_Phonetic;
    }

    public String getPhonetic() {
        return Phonetic;
    }

    public String getId_Example() {
        return Id_Example;
    }

    public String getId_Pronounce() {
        return Id_Pronounce;
    }

    public String getLink() {
        return Link;
    }
}
