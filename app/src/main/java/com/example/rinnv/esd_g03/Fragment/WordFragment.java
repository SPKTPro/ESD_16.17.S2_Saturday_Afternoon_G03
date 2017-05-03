package com.example.rinnv.esd_g03.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rinnv.esd_g03.Activity.TabActivity;
import com.example.rinnv.esd_g03.Models.Sentence;
import com.example.rinnv.esd_g03.Models.Word;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    ImageButton replay;
    String pheonicGrId;
    private String TAG = "Tag";
    private static int type = 1;
    ArrayList<Word> words;
    ArrayList<Sentence> sentence;
    private static ArrayList test;

    int count;
    ImageButton menu;

    public WordFragment() {

    }

    public Fragment createFragment() {
        WordFragment wordFragment = new WordFragment();
        return wordFragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.practice_layout, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        pheonicGrId = Config.PHEONIC_GROUP_ID;
        //lay array word theo id
        words = db.getWordByPhoneticGrID(pheonicGrId);
        sentence = db.getSentenceByPhoneticGrID(pheonicGrId);
        //   final ArrayList<Word> test = db.getWordByPhoneticGrID(pheonicGrId);;

        //lay ramdom 1 word trong array word
        //  int rnd = new Random().nextInt(wordSize-1);

        wordTextTV = (TextView) rootView.findViewById(R.id.wordText);
        wordPhoneticTV = (TextView) rootView.findViewById(R.id.wordPhonetic);
        result = (ImageButton) rootView.findViewById(R.id.result);
        btnNextWord = (ImageButton) rootView.findViewById(R.id.btnnext);
        btnPreWord = (ImageButton) rootView.findViewById(R.id.btnprevious);
        replay = (ImageButton) rootView.findViewById(R.id.btnreplay);
        btnSpeaker = (ImageButton) rootView.findViewById(R.id.btnspeaker);
        btnRecord = (ImageButton) rootView.findViewById(R.id.btnrecord);

        btnSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TabActivity) container.getContext()).startTextToSpeech(wordTextTV.getText().toString());
            }
        });
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((TabActivity) container.getContext()).isConnected()) {
                    ((TabActivity) container.getContext()).startSpeechToText(wordTextTV.getText().toString());
                } else {
                    Toast.makeText(container.getContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
        test = Startgame(words, sentence);

        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay ramdom 1 word trong array word
                if (count >= 0) {
                    btnPreWord.setVisibility(View.VISIBLE);
                } else {
                    btnPreWord.setVisibility(View.INVISIBLE);
                }
                if (count < 9) {
                    count = count + 1;
                    if (type == 1) {
                        setWord(count);
                    } else {
                        setSentence(count);
                    }
                } else {
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
                if (count > 0) {
                    btnPreWord.setVisibility(View.VISIBLE);
                    if (type == 1) {
                        setWord(count);
                    } else {
                        setSentence(count);
                    }
                } else {
                    if (type == 1) {
                        setWord(0);
                    } else {
                        setSentence(0);
                    }
                    btnPreWord.setVisibility(View.INVISIBLE);
                }

            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Startgame(words, sentence);
                // onCreateView(inflater,container,savedInstanceState);

            }
        });
        menu = (ImageButton) rootView.findViewById(R.id.btnmenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Choose test:")
                        .setMessage("")
                        .setPositiveButton("Word", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                type = 1;
                                Startgame(words, sentence);

                            }
                        })
                        .setNegativeButton("Sentence", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                type = 2;
                                Startgame(words, sentence);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        return rootView;
    }

    public void setWord(int count) {
        Word word = (Word) test.get(count);
        String wordText = word.getWord();
        String wordPhonetic = word.getPhonetic();
        wordTextTV.setText(wordText);
        wordPhoneticTV.setText(wordPhonetic);
    }

    public void setSentence(int count) {
        try {
            Sentence word = (Sentence) test.get(count);
            String wordText = word.getSentence();
            String wordPhonetic = word.getPhonetic_Word1();
            wordTextTV.setText(wordText);
            wordPhoneticTV.setText(wordPhonetic);
            Log.d(TAG, "setSentence: " + wordText);

        } catch (Exception ex) {
            Log.d("Tag", "setSentence: " + ex.getLocalizedMessage());
        }
    }

    public void EndGame() {
        result.setVisibility(View.INVISIBLE);
        btnNextWord.setVisibility(View.INVISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.INVISIBLE);
        btnRecord.setVisibility(View.INVISIBLE);
        wordPhoneticTV.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.VISIBLE);
    }

    public ArrayList Startgame(ArrayList<Word> words, ArrayList<Sentence> sentence) {
        count = 0;
        test = new ArrayList();
        if (type == 1) {
            List<Word> test1 = new ArrayList<Word>();
            for (int i = 0; i < words.size(); i++) {
                test1.add(words.get(i));
            }
            Collections.shuffle(test1);
            Collections.shuffle(test1);
            test = new ArrayList<Word>();
            for (int i = 0; i < 10; i++) {
                test.add(test1.get(i));
            }
            Word word = (Word) test.get(0);
            String wordText = word.getWord();
            String wordPhonetic = word.getPhonetic();
            wordTextTV.setText(wordText);
            wordPhoneticTV.setText(wordPhonetic);
        } else {
            List<Sentence> test1 = new ArrayList<Sentence>();
            for (int i = 0; i < sentence.size(); i++) {
                test1.add(sentence.get(i));
            }
            Collections.shuffle(test1);
            Collections.shuffle(test1);
            test = new ArrayList<Sentence>();
            for (int i = 0; i < 10; i++) {
                test.add(test1.get(i));
            }
            Sentence word = (Sentence) test.get(0);
            String wordText = word.getSentence();
            String wordPhonetic = word.getPhonetic_Word1();
            wordTextTV.setText(wordText);
            wordPhoneticTV.setText(wordPhonetic);

        }
        replay.setVisibility(View.INVISIBLE);
        result.setVisibility(View.VISIBLE);
        btnNextWord.setVisibility(View.VISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.VISIBLE);
        btnRecord.setVisibility(View.VISIBLE);
        wordPhoneticTV.setVisibility(View.VISIBLE);
        Log.d(TAG, "Startgame: " + type);
        Log.d("Tag", "Startgame: " + test.size());
        return test;

    }
}
