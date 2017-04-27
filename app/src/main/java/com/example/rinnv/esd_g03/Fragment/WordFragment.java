package com.example.rinnv.esd_g03.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Models.Word;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rinnv on 4/20/2017.
 */

public class WordFragment extends Fragment {
   TextView textView;
    ImageButton menu;
    public WordFragment()
    {

    }

    public Fragment createFragment()
    {
        WordFragment wordFragment = new WordFragment();
        return wordFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.practice_layout, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        String pheonicGrId = Config.PHEONIC_GROUP_ID;
        //lay array word theo id
        final ArrayList<Word> words = db.getWordByPhoneticGrID(pheonicGrId);
        final int wordSize = words.size();

        //lay ramdom 1 word trong array word
        int rnd = new Random().nextInt(wordSize-1);
        Word word = words.get(rnd);

        //
        String wordText = word.getWord();
        String wordPhonetic = word.getPhonetic();

        final TextView wordTextTV = (TextView) rootView.findViewById(R.id.wordText);
        final TextView wordPhoneticTV = (TextView) rootView.findViewById(R.id.wordPhonetic);

        wordTextTV.setText(wordText);
        wordPhoneticTV.setText(wordPhonetic);

        //
        ImageButton btnNextWord = (ImageButton) rootView.findViewById(R.id.btnnext);

        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay ramdom 1 word trong array word
                int rnd = new Random().nextInt(wordSize-1);
                Word word = words.get(rnd);
                //
                String wordText = word.getWord();
                String wordPhonetic = word.getPhonetic();

                wordTextTV.setText(wordText);
                wordPhoneticTV.setText(wordPhonetic);
            }
        });

        menu =(ImageButton)rootView.findViewById(R.id.btnmenu);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("asdddddddddddddddddddddddddd")
                        .setMessage("ddddddddddddddddddddddddddd?")
                        .setPositiveButton("Word", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText("WORD");
                            }
                        })
                        .setNegativeButton("Sentence", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText("SENTENCE");
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
