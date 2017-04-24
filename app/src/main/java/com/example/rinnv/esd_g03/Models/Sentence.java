package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class Sentence {
    private String Group;
    private String Sentence;
    private int Num_Check;
    private String Phonetic_Word1;
    private String Phonetic_Word2;

    public Sentence(String group, String sentence, int num_Check, String phonetic_Word1, String phonetic_Word2) {
        Group = group;
        Sentence = sentence;
        Num_Check = num_Check;
        Phonetic_Word1 = phonetic_Word1;
        Phonetic_Word2 = phonetic_Word2;
    }

    public String getGroup() {
        return Group;
    }

    public String getSentence() {
        return Sentence;
    }

    public int getNum_Check() {
        return Num_Check;
    }

    public String getPhonetic_Word1() {
        return Phonetic_Word1;
    }

    public String getPhonetic_Word2() {
        return Phonetic_Word2;
    }
}
