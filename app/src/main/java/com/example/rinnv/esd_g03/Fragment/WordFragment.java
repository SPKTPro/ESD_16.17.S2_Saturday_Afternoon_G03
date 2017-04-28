package com.example.rinnv.esd_g03.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Models.Word;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by rinnv on 4/20/2017.
 */

public class WordFragment extends Fragment {
   TextView textView;
    TextView wordTextTV;
    TextView wordPhoneticTV;
    ImageButton btnNextWord;
    ImageButton btnPreWord;
    ImageButton btnRecord;
    ImageButton btnSpeaker;
    ImageButton result;

    int count;
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.practice_layout, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        String pheonicGrId = Config.PHEONIC_GROUP_ID;
        //lay array word theo id
        final ArrayList<Word> words = db.getWordByPhoneticGrID(pheonicGrId);
     //   final ArrayList<Word> test = db.getWordByPhoneticGrID(pheonicGrId);;

        //lay ramdom 1 word trong array word
      //  int rnd = new Random().nextInt(wordSize-1);

        wordTextTV = (TextView) rootView.findViewById(R.id.wordText);
        wordPhoneticTV = (TextView) rootView.findViewById(R.id.wordPhonetic);
        result=(ImageButton)rootView.findViewById(R.id.result) ;
        btnNextWord = (ImageButton) rootView.findViewById(R.id.btnnext);
        btnPreWord = (ImageButton) rootView.findViewById(R.id.btnprevious);
        btnSpeaker = (ImageButton) rootView.findViewById(R.id.btnspeaker);
        btnRecord= (ImageButton) rootView.findViewById(R.id.btnrecord);
        final ArrayList<Word> test = Startgame(words);
        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay ramdom 1 word trong array word
                if(count>=0)
                {
                    btnPreWord.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnPreWord.setVisibility(View.INVISIBLE);
                }
                if(count<9) {
                    count = count + 1;
                    Word word = test.get(count);
                    String wordText = word.getWord();
                    String wordPhonetic = word.getPhonetic();
                    wordTextTV.setText(wordText);
                    wordPhoneticTV.setText(wordPhonetic);
                }
                else {
                    wordTextTV.setText("5/10");
                    EndGame();

                }
            }
        });
        btnPreWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //lay ramdom 1 word trong array word
             count = count - 1;
                if(count>0)
                {
                    btnPreWord.setVisibility(View.VISIBLE);
                    Word word = test.get(count);
                    String wordText = word.getWord();
                    String wordPhonetic = word.getPhonetic();
                    wordTextTV.setText(wordText);
                    wordPhoneticTV.setText(wordPhonetic);
                }
                else
                {
                    Word word = test.get(0);
                    String wordText = word.getWord();
                    String wordPhonetic = word.getPhonetic();
                    wordTextTV.setText(wordText);
                    wordPhoneticTV.setText(wordPhonetic);
                    btnPreWord.setVisibility(View.INVISIBLE);
                }

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Startgame(words);
               // onCreateView(inflater,container,savedInstanceState);

            }
        });
        menu =(ImageButton)rootView.findViewById(R.id.btnmenu);
        return rootView;
    }
    public void EndGame()
    {
        result.setBackgroundResource(R.drawable.repeat);
        btnNextWord.setVisibility(View.INVISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.INVISIBLE);
        btnRecord.setVisibility(View.INVISIBLE);
        wordPhoneticTV.setVisibility(View.INVISIBLE);
        result.setClickable(true);
    }
    public ArrayList<Word> Startgame(ArrayList<Word> words)
    {
        count=0;
        List<Word> test1 = new ArrayList<Word>();
        for (int i = 0; i < words.size(); i++)
        {
            test1.add(words.get(i));
        }
        Collections.shuffle(test1);
        ArrayList<Word> test = new ArrayList<Word>();
        for(int i=0;i<10;i++) {
            test.add(test1.get(i));
        }
        result.setBackgroundResource(R.drawable.true2);
        result.setClickable(false);
        btnNextWord.setVisibility(View.VISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.VISIBLE);
        btnRecord.setVisibility(View.VISIBLE);
        wordPhoneticTV.setVisibility(View.VISIBLE);
        Word word = test.get(0);
        String wordText = word.getWord();
        String wordPhonetic = word.getPhonetic();
        wordTextTV.setText(wordText);
        wordPhoneticTV.setText(wordPhonetic);
        return  test;
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
