package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class Example {
    private String Id_Example;
    private String Example;
    private String Phonetic;

    public Example(String id_Example, String example, String phonetic) {
        Id_Example = id_Example;
        Example = example;
        Phonetic = phonetic;
    }

    public String getId_Example() {
        return Id_Example;
    }

    public String getExample() {
        return Example;
    }

    public String getPhonetic() {
        return Phonetic;
    }
}
