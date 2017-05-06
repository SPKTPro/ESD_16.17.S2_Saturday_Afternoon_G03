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
import com.example.rinnv.esd_g03.Models.CWord;
import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.Ultility.Config;
import com.example.rinnv.esd_g03.Ultility.SQLiteDataController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rinnv on 4/20/2017.
 */

public class CWordFragment extends Fragment {

    TextView wordTextTV1;
    TextView wordScore;
    TextView wordPhoneticTV1;
    TextView wordTextTV2;
    TextView wordPhoneticTV2;
    ImageButton btnNextWord;
    ImageButton btnPreWord;
    static ImageButton btnRecord1;
    ImageButton btnSpeaker1;
    static ImageButton btnRecord2;
    ImageButton btnSpeaker2;
    static ImageButton result;
    private static ImageButton replay;
    String pheonicGrId;
    private String TAG = "Tag";
    private static int type = 1;
    ArrayList<CWord> words;
    public static ArrayList<Question> ques;
    private static ArrayList test;
    static int index;
    int count;
    ImageButton menu;

    public CWordFragment() {

    }

    public static Fragment createFragment() {
        CWordFragment wordFragment = new CWordFragment();
        return wordFragment;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.practice_layout2, container, false);
        SQLiteDataController db = new SQLiteDataController(container.getContext());
        pheonicGrId = Config.PHEONIC_GROUP_ID;
        Log.d(TAG, "onCreateView: new fragment");

        words = db.getListCWord(pheonicGrId);

        wordScore = (TextView) rootView.findViewById(R.id.wordText);
        wordTextTV1 = (TextView) rootView.findViewById(R.id.fword);
        wordPhoneticTV1 = (TextView) rootView.findViewById(R.id.fpho);
        wordTextTV2 = (TextView) rootView.findViewById(R.id.sword);
        wordPhoneticTV2 = (TextView) rootView.findViewById(R.id.spho);
        result = (ImageButton) rootView.findViewById(R.id.result);
        btnNextWord = (ImageButton) rootView.findViewById(R.id.btnnext);
        btnPreWord = (ImageButton) rootView.findViewById(R.id.btnprevious);
        replay = (ImageButton) rootView.findViewById(R.id.btnreplay);
        btnSpeaker1 = (ImageButton) rootView.findViewById(R.id.speaker1);
        btnRecord1 = (ImageButton) rootView.findViewById(R.id.record1);
        btnSpeaker2 = (ImageButton) rootView.findViewById(R.id.speaker2);
        btnRecord2 = (ImageButton) rootView.findViewById(R.id.record2);

        btnSpeaker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TabActivity) container.getContext()).startTextToSpeech(wordTextTV1.getText().toString());

            }
        });
        btnSpeaker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TabActivity) container.getContext()).startTextToSpeech(wordTextTV2.getText().toString());

            }
        });
        btnRecord1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((TabActivity) container.getContext()).isConnected()) {
                    CWord w = (CWord) test.get(index);
                    ((TabActivity) container.getContext()).startSpeechToText(wordTextTV1.getText().toString(), index, null, w,2,1);
                } else {
                    Toast.makeText(container.getContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnRecord2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TabActivity) container.getContext()).isConnected()) {
                    CWord cWord = (CWord) test.get(index);
                    ((TabActivity) container.getContext()).startSpeechToText(wordTextTV2.getText().toString(), index, null, cWord,2,2);
                } else {
                    Toast.makeText(container.getContext(), "Please Connect to Internet", Toast.LENGTH_LONG).show();
                }

            }
        });
        test = Startgame(words);
        ques = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Question q = new Question();
            q.word = (CWord) test.get(i);
            q.kq1 = -1;
            q.kq2 = -1;
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
                    wordScore.setVisibility(View.VISIBLE);
                    wordScore.setText("5/10");
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
                    q.word = (CWord) test.get(i);
                    q.kq1 = -1;
                    q.kq2 = -1;
                    q.kq = -1;
                    ques.add(i, q);
                }

            }
        });
        menu = (ImageButton) rootView.findViewById(R.id.btnmenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.wordFragment, WordFragment.createFragment()).commit();

            }
        });

        return rootView;
    }

    public void setWord(int count) {
        CWord word = (CWord) test.get(count);
        String wordText = word.getfWord();
        String wordPhonetic = word.getfPhonetic();
        wordTextTV1.setText(wordText);
        wordPhoneticTV1.setText(wordPhonetic);
        wordText = word.getsWord();
        wordPhonetic = word.getsPhonetic();
        wordTextTV2.setText(wordText);
        wordPhoneticTV2.setText(wordPhonetic);
        index = count;
        checkresult();
        checkresult2(index);
    }

    public static void checkresult() {
        if (ques.get(index).kq1 == -1) {
            btnRecord1.setBackgroundResource(R.drawable.record2);
            btnRecord1.setClickable(true);
        } else if (ques.get(index).kq1 == 1) {
            btnRecord1.setBackgroundResource(R.drawable.true2);
           // btnRecord1.setClickable(false);
        } else {
            btnRecord1.setBackgroundResource(R.drawable.false2);
          //  btnRecord1.setClickable(false);
        }

        if (ques.get(index).kq2 == -1) {
            btnRecord2.setBackgroundResource(R.drawable.record2);
            btnRecord2.setClickable(true);
        } else if (ques.get(index).kq2 == 1) {
            btnRecord2.setBackgroundResource(R.drawable.true2);
           // btnRecord2.setClickable(false);
        } else {
            btnRecord2.setBackgroundResource(R.drawable.false2);
           // btnRecord2.setClickable(false);
        }

    }
    public static void checkresult2(int i)
    {
        if (ques.get(i).kq1==1&&ques.get(i).kq2==1)
        {
            ques.get(i).kq=1;
             result.setVisibility(View.VISIBLE);
             result.setBackgroundResource(R.drawable.true2);

        }
        else
            if((ques.get(i).kq1==1&&ques.get(i).kq2==0)||(ques.get(i).kq1==0&&ques.get(i).kq2==1)||(ques.get(i).kq1==0&&ques.get(i).kq2==0))
        {
            ques.get(i).kq=0;
            result.setVisibility(View.VISIBLE);
            result.setBackgroundResource(R.drawable.false2);
        }
        else
                result.setVisibility(View.INVISIBLE);

    }


    public void EndGame() {
        result.setVisibility(View.INVISIBLE);
        btnNextWord.setVisibility(View.INVISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker1.setVisibility(View.INVISIBLE);
        btnRecord1.setVisibility(View.INVISIBLE);
        btnSpeaker2.setVisibility(View.INVISIBLE);
        btnRecord2.setVisibility(View.INVISIBLE);
        wordPhoneticTV1.setVisibility(View.INVISIBLE);
        wordPhoneticTV2.setVisibility(View.INVISIBLE);
        wordTextTV1.setVisibility(View.INVISIBLE);
        wordTextTV2.setVisibility(View.INVISIBLE);
        replay.setVisibility(View.VISIBLE);
    }

    public ArrayList Startgame(ArrayList<CWord> words) {
        count = 0;
        test = new ArrayList();

        List<CWord> test1 = new ArrayList<CWord>();
        for (int i = 0; i < words.size(); i++) {
            CWord w = words.get(i);
            Log.d(TAG, "Startgame: tittle" + w.getfWord());
            test1.add(words.get(i));
        }
        Collections.shuffle(test1);
        Log.d(TAG, "Startgame: test sieze" + test1.size());

        test = new ArrayList<CWord>();
        for (int i = 0; i < 10; i++) {
            test.add(test1.get(i));
        }
        CWord word = (CWord) test.get(0);
        index = 0;
        String wordText = word.getfWord();
        String wordPhonetic = word.getfPhonetic();
        wordTextTV1.setText(wordText);
        wordPhoneticTV1.setText(wordPhonetic);
        wordText = word.getsWord();
        wordPhonetic = word.getsPhonetic();
        wordTextTV2.setText(wordText);
        wordPhoneticTV2.setText(wordPhonetic);

        wordScore.setVisibility(View.INVISIBLE);
        wordTextTV1.setVisibility(View.VISIBLE);
        wordTextTV2.setVisibility(View.VISIBLE);
        replay.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        wordPhoneticTV1.setVisibility(View.VISIBLE);
        wordPhoneticTV2.setVisibility(View.VISIBLE);
        wordPhoneticTV1.setVisibility(View.VISIBLE);
        wordPhoneticTV2.setVisibility(View.VISIBLE);
        btnNextWord.setVisibility(View.VISIBLE);
        btnPreWord.setVisibility(View.INVISIBLE);
        btnSpeaker1.setVisibility(View.VISIBLE);
        btnRecord1.setVisibility(View.VISIBLE);
        btnRecord1.setBackgroundResource(R.drawable.record2);
        btnRecord1.setClickable(true);
        btnSpeaker2.setVisibility(View.VISIBLE);
        btnRecord2.setVisibility(View.VISIBLE);
        btnRecord2.setBackgroundResource(R.drawable.record2);
        btnRecord2.setClickable(true);
        return test;

    }

    public static class Question {
        public CWord word;
        public int kq1;
        public int kq2;
        public int kq;
    }
}
