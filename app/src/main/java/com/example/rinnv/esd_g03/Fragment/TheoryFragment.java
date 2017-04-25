package com.example.rinnv.esd_g03.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.rinnv.esd_g03.Models.Example;
import com.example.rinnv.esd_g03.Models.Phonetic;
import com.example.rinnv.esd_g03.Models.Pronounce;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.ExpandableHeightListView;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rinnv on 4/20/2017.
 */

public class TheoryFragment extends Fragment {


    public TheoryFragment() {
    }

    public Fragment createFragment() {
        TheoryFragment theoryFragment = new TheoryFragment();
        return theoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.theory_layout, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        String pheonicGrId = Config.PHEONIC_GROUP_ID;
        // lấy array 2 phonetic theo group id
        ArrayList<Phonetic> phonetics = db.getListPhonetic(pheonicGrId);

        //lay phonetic va pronounce cua phonetic thu nhat
        Phonetic phonetic1 = phonetics.get(0);
        ArrayList<Pronounce> pronouncesPhonetic1 = db.getProunceByPhonetic(phonetic1.getId_Pronounce());
        List<String> pronounces1 = new ArrayList<>();
        List<Example> examples1 = db.getListExample(phonetic1.getId_Example());
        for (Pronounce pronounce : pronouncesPhonetic1) {
            pronounces1.add(pronounce.getPronounce());
        }

        //lay phonetic va pronounce cua phonetic thu hai
        Phonetic phonetic2 = phonetics.get(1);
        ArrayList<Pronounce> pronouncesPhonetic2 = db.getProunceByPhonetic(phonetic2.getId_Pronounce());
        List<String> pronounces2 = new ArrayList<>();
        List<Example> examples2 = db.getListExample(phonetic2.getId_Example());
        for (Pronounce pronounce : pronouncesPhonetic2) {
            pronounces2.add(pronounce.getPronounce());
        }

        ExpandableHeightListView listView_pho1Guide = (ExpandableHeightListView) rootView.findViewById(R.id.list_pho1Guide);
        listView_pho1Guide.setAdapter(new ArrayAdapter<>(container.getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, pronounces1));
        listView_pho1Guide.setExpanded(true);


        ExpandableHeightListView listView_pho2Guide = (ExpandableHeightListView) rootView.findViewById(R.id.list_pho2Guide);
        listView_pho2Guide.setAdapter(new ArrayAdapter<>(container.getContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, pronounces2));
        listView_pho2Guide.setExpanded(true);


        ExpandableHeightListView listView_phonetic1_Example = (ExpandableHeightListView) rootView.findViewById(R.id.listView_phonetic1_Example);
        listView_phonetic1_Example.setAdapter(new PhenoticExampleAdapter(container.getContext(), R.layout.example_item, examples1));
        listView_phonetic1_Example.setExpanded(true);


        ExpandableHeightListView listView_phonetic2_Example = (ExpandableHeightListView) rootView.findViewById(R.id.listView_phonetic2_Example);
        listView_phonetic2_Example.setAdapter(new PhenoticExampleAdapter(container.getContext(),R.layout.example_item,examples2));
        listView_phonetic2_Example.setExpanded(true);

        return rootView;
    }


}
