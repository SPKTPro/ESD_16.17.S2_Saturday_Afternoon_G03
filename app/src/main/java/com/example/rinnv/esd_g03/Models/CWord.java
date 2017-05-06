package com.example.rinnv.esd_g03.Models;

/**
 * Created by thaihuynh on 4/24/2017.
 */

public class CWord {
    private String Group;
    private String f_Word;
    private String s_Word;
    private int Num_Check;
    private String f_Phonetic;
    private String s_Phonetic;

    public CWord(String group, String f_Word, String f_Phonetic, String s_Word, String s_Phonetic, int num_Check) {
        this.Group = group;
        this.f_Word = f_Word;
        this.s_Word = s_Word;
        Num_Check = num_Check;
        this.f_Phonetic = f_Phonetic;
        this.s_Phonetic = s_Phonetic;
    }

    public String getGroup() {
        return Group;
    }

    public String getfWord() {
        return f_Word;
    }
    public String getsWord() {
        return s_Word;
    }

    public int getNum_Check() {
        return Num_Check;
    }

    public String getfPhonetic() {
        return f_Phonetic;
    }

    public String getsPhonetic() {
        return s_Phonetic;
    }
}
