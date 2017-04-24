package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class Word {
    private String Group;
    private String Word;
    private int Num_Check;
    private String Phonetic;

    public Word(String group, String word, int num_Check, String phonetic) {
        Group = group;
        Word = word;
        Num_Check = num_Check;
        Phonetic = phonetic;
    }

    public String getGroup() {
        return Group;
    }

    public String getWord() {
        return Word;
    }

    public int getNum_Check() {
        return Num_Check;
    }

    public String getPhonetic() {
        return Phonetic;
    }
}
