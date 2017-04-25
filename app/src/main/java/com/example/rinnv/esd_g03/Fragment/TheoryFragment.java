package com.example.rinnv.esd_g03.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Models.Phonetic;
import com.example.rinnv.esd_g03.Models.Pronounce;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;

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
        ArrayList<Phonetic> phonetics = db.getListPhonetic(pheonicGrId);


        Phonetic phonetic1 = phonetics.get(0);
        ArrayList<Pronounce> pronouncesPhonetic1 = db.getProunceByPhonetic(phonetic1.getId_Pronounce());

        Phonetic phonetic2 = phonetics.get(1);
        ArrayList<Pronounce> pronouncesPhonetic2 = db.getProunceByPhonetic(phonetic2.getId_Pronounce());

        TextView tv_pho1Title = (TextView) rootView.findViewById(R.id.tv_pho1Title);
        TextView tv_pho1Guide1 = (TextView) rootView.findViewById(R.id.tv_pho1Guide1);
        TextView tv_pho1Guide2 = (TextView) rootView.findViewById(R.id.tv_pho1Guide2);
        TextView tv_pho1Guide3 = (TextView) rootView.findViewById(R.id.tv_pho1Guide3);

        TextView tv_pho2Title = (TextView) rootView.findViewById(R.id.tv_pho2Title);
        TextView tv_pho2Guide1 = (TextView) rootView.findViewById(R.id.tv_pho2Guide1);
        TextView tv_pho2Guide2 = (TextView) rootView.findViewById(R.id.tv_pho2Guide2);
        TextView tv_pho2Guide3 = (TextView) rootView.findViewById(R.id.tv_pho2Guide3);

        TextView tv_pho1TitleEx = (TextView) rootView.findViewById(R.id.tv_pho1TitleEx);
        TextView tv_pho1Ex1 = (TextView) rootView.findViewById(R.id.tv_pho1Ex1);
        TextView tv_pho1Ex2 = (TextView) rootView.findViewById(R.id.tv_pho1Ex2);

        TextView tv_pho2TitleEx = (TextView) rootView.findViewById(R.id.tv_pho2TitleEx);
        TextView tv_pho2Ex1 = (TextView) rootView.findViewById(R.id.tv_pho2Ex1);
        TextView tv_pho2Ex2 = (TextView) rootView.findViewById(R.id.tv_pho2Ex2);


        tv_pho1Title.setText(phonetic1.getPhonetic());
        tv_pho1Guide1.setText(pronouncesPhonetic1.get(0).getPronounce()+"Rin");
        tv_pho1Guide2.setText(pronouncesPhonetic1.get(1).getPronounce()+"Rin");
        tv_pho1Guide3.setText(pronouncesPhonetic1.get(2).getPronounce()+"Rin");

        tv_pho2Title.setText(phonetic2.getPhonetic());
        tv_pho2Guide1.setText(pronouncesPhonetic2.get(0).getPronounce()+"Rin");
        tv_pho2Guide2.setText(pronouncesPhonetic2.get(1).getPronounce()+"Rin");
        tv_pho2Guide3.setText(pronouncesPhonetic2.get(2).getPronounce()+"Rin");

        return rootView;
    }


}
