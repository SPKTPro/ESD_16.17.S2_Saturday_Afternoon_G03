package com.example.rinnv.esd_g03.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Activity.YouTubeActivity;
import com.example.rinnv.esd_g03.Adaptor.PhenoticExampleAdapter;
import com.example.rinnv.esd_g03.Adaptor.PronounceAdapter;
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
        String phonetic1Title = phonetic1.getPhonetic();
        final String linkUtube_Pho1 = phonetic1.getLink();
        ArrayList<Pronounce> pronouncesPhonetic1 = db.getProunceByPhonetic(phonetic1.getId_Pronounce());
        List<Example> examples1 = db.getListExample(phonetic1.getId_Example());


        //lay phonetic va pronounce cua phonetic thu hai
        Phonetic phonetic2 = phonetics.get(1);
        String phonetic2Title = phonetic2.getPhonetic();
        final String linkUtube_Pho2 = phonetic2.getLink();
        ArrayList<Pronounce> pronouncesPhonetic2 = db.getProunceByPhonetic(phonetic2.getId_Pronounce());
        List<Example> examples2 = db.getListExample(phonetic2.getId_Example());


        //lấy tên các phonetic
        TextView pho1_Title = (TextView) rootView.findViewById(R.id.tv_pho1Title);
        TextView pho2_Title = (TextView) rootView.findViewById(R.id.tv_pho2Title);
        TextView pho1_TitleEX = (TextView) rootView.findViewById(R.id.tv_pho1TitleEx);
        TextView pho2_TitleEX = (TextView) rootView.findViewById(R.id.tv_pho2TitleEx);

        pho1_Title.setText("/"+phonetic1Title+"/");
        pho1_TitleEX.setText("/"+phonetic1Title+"/");
        pho2_Title.setText("/"+phonetic2Title+"/");
        pho2_TitleEX.setText("/"+phonetic2Title+"/");

        //lấy nội dung pronounce
        /*
        ExpandableHeightListView listView_pho1Guide = (ExpandableHeightListView) rootView.findViewById(R.id.list_pho1Guide);
        listView_pho1Guide.setAdapter(new ArrayAdapter<>(container.getContext(), R.layout.pronouce_example_item,
                R.id.tv_phoWord1, pronounces1));
        listView_pho1Guide.setExpanded(true);


        ExpandableHeightListView listView_pho2Guide = (ExpandableHeightListView) rootView.findViewById(R.id.list_pho2Guide);
        listView_pho2Guide.setAdapter(new ArrayAdapter<>(container.getContext(), R.layout.pronouce_example_item,
                R.id.tv_phoWord1, pronounces2));
        listView_pho2Guide.setExpanded(true);*/


        ExpandableHeightListView listView_pho1Guide = (ExpandableHeightListView) rootView.findViewById(R.id.list_pho1Guide);
       listView_pho1Guide.setAdapter(new PronounceAdapter(container.getContext(),
                R.layout.pronouce_example_item,pronouncesPhonetic1,pronouncesPhonetic2));
        listView_pho1Guide.setExpanded(true);

        ExpandableHeightListView listView_phonetic1_Example = (ExpandableHeightListView) rootView.findViewById(R.id.listView_phonetic1_Example);
        listView_phonetic1_Example.setAdapter(new PhenoticExampleAdapter(container.getContext(), R.layout.example_item, examples1));
        listView_phonetic1_Example.setExpanded(true);


        ExpandableHeightListView listView_phonetic2_Example = (ExpandableHeightListView) rootView.findViewById(R.id.listView_phonetic2_Example);
        listView_phonetic2_Example.setAdapter(new PhenoticExampleAdapter(container.getContext(),R.layout.example_item,examples2));
        listView_phonetic2_Example.setExpanded(true);

        //btn Utube
        ImageButton btnUtube_Pho1 = (ImageButton) rootView.findViewById(R.id.btnvideo_Pho1);
        ImageButton btnUtube_Pho2 = (ImageButton) rootView.findViewById(R.id.btnvideo_Pho2);

        btnUtube_Pho1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),YouTubeActivity.class);
                intent.putExtra(Config.YOUTUBE_LINK, linkUtube_Pho1);
                startActivity(intent);
            }
        });

        btnUtube_Pho2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),YouTubeActivity.class);
                intent.putExtra(Config.YOUTUBE_LINK, linkUtube_Pho2);
                startActivity(intent);
            }
        });

        return rootView;
    }


}
