package com.example.rinnv.esd_g03.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rinnv.esd_g03.Activity.TabActivity;
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
    static ImageButton btnRecord;
    ImageButton btnSpeaker;
    static ImageButton result;
    ImageButton replay;
    String pheonicGrId;
    private String TAG = "Tag";
    private static int type = 1;
    ArrayList<Word> words;
    public static ArrayList<Question> ques;
    private static ArrayList test;
    static int index;
    int score=0;

    int count;
    ImageButton menu;

    public WordFragment() {

    }

    public static Fragment createFragment() {
        WordFragment wordFragment = new WordFragment();
        return wordFragment;
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.practice_layout, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        pheonicGrId = Config.PHEONIC_GROUP_ID;

        words = db.getWordByPhoneticGrID(pheonicGrId);

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

                    Word w = (Word) test.get(index);

                    ((TabActivity) container.getContext()).startSpeechToText(wordTextTV.getText().toString(), index, w,null);


                } else {
                    Toast.makeText(container.getContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
                }

            }
        });
        test = Startgame(words);
        ques = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Question q = new Question();
            q.word = (Word) test.get(i);
            q.kq = -1;
            ques.add(i, q);
        }

        btnNextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count >= 0) {
                    btnPreWord.setVisibility(View.VISIBLE);
                } else {
                    btnPreWord.setVisibility(View.INVISIBLE);
                }
                if (count < 9) {
                    count = count + 1;
                   setWord(count);

                } else {
                    wordTextTV.setText("5/10");
                    EndGame();

                }
            }
        });
        btnPreWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count = count - 1;
                if (count > 0) {
                    btnPreWord.setVisibility(View.VISIBLE);
                    setWord(count);

                } else {
                    setWord(0);
                    btnPreWord.setVisibility(View.INVISIBLE);
                }

            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test = Startgame(words);
                ques = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Question q = new Question();
                    q.word = (Word) test.get(i);
                    q.kq = -1;
                    ques.add(i, q);
                }

            }
        });
        menu = (ImageButton) rootView.findViewById(R.id.btnmenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* new AlertDialog.Builder(getContext())
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
                        .show();*/

               getFragmentManager().beginTransaction().replace(R.id.wordFragment,CWordFragment.createFragment()).commit();

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
        index = count;
        checkresult();
    }

    public static void checkresult() {
        if (ques.get(index).kq == -1) {
            result.setVisibility(View.INVISIBLE);
            btnRecord.setVisibility(View.VISIBLE);
        } else if (ques.get(index).kq == 1) {
            result.setVisibility(View.VISIBLE);
            result.setBackgroundResource(R.drawable.true2);
            btnRecord.setVisibility(View.INVISIBLE);
        } else {
            result.setVisibility(View.VISIBLE);
            result.setBackgroundResource(R.drawable.false2);
            btnRecord.setVisibility(View.INVISIBLE);
        }

    }


    public void EndGame() {
        result.setVisibility(View.INVISIBLE);
        for(int i=0;i<10;i++)
        {
            if(ques.get(i).kq==1)
                score++;
        }
        wordTextTV.setText(score+"/10");
        wordTextTV.setTextSize(60);
        btnNextWord.setVisibility(View.INVISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.INVISIBLE);
        btnRecord.setVisibility(View.INVISIBLE);
        wordPhoneticTV.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.VISIBLE);
    }

    public ArrayList Startgame(ArrayList<Word> words) {
        count = 0;
        test = new ArrayList();

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
            index = 0;
            String wordText = word.getWord();
            String wordPhonetic = word.getPhonetic();
            wordTextTV.setText(wordText);
            wordPhoneticTV.setText(wordPhonetic);

        wordTextTV.setTextSize(40);
        replay.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        btnNextWord.setVisibility(View.VISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker.setVisibility(View.VISIBLE);
        btnRecord.setVisibility(View.VISIBLE);
        wordPhoneticTV.setVisibility(View.VISIBLE);
        Log.d(TAG, "Startgame: " + type);
        Log.d("Tag", "Startgame: " + test.size());
        return test;

    }

    public static class Question {
        public Word word;
        public int kq;
    }
}
