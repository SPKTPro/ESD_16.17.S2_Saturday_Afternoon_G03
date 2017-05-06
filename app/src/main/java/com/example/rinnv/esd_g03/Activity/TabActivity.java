package com.example.rinnv.esd_g03.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rinnv.esd_g03.Fragment.CWordFragment;
import com.example.rinnv.esd_g03.Fragment.TheoryFragment;
import com.example.rinnv.esd_g03.Fragment.WordFragment;
import com.example.rinnv.esd_g03.Models.CWord;
import com.example.rinnv.esd_g03.Models.Word;
import com.example.rinnv.esd_g03.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static com.example.rinnv.esd_g03.R.id.container;

public class TabActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    public String your_word = "";
    public int index = 0;
    Word w;
    CWord cWord;
    int type=1;
    int type2=0;
    private final int SPEECH_RECOGNITION_CODE = 1001;
    private String TAG = "Tag";
    public static int Choice = 0;
    private static final int SPEECH_API_CHECK = 1234;
    private static TextToSpeech mTts;
    private static ViewPager mViewPager;
    // Tab titles
    private String[] tabs = {"THEORY", "PRACTICE"};
    private TabLayout tabLayout;
    //Layout
    public static int[] resourceIds = {
            R.layout.theory_layout
            , R.layout.practice_layout
    };

    private void CheckTTS() {

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, SPEECH_API_CHECK);
    }

    public void startTextToSpeech(String word) {
        mTts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void startSpeechToText(String word, int i, Word q, CWord cq,int t, int t2) {
        your_word = word;
        index = i;
        w = q;
        cWord = cq;
        type= t;
        type2= t2;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, word);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,
                new Long(1000));
        startActivityForResult(intent, SPEECH_RECOGNITION_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SPEECH_RECOGNITION_CODE && resultCode == RESULT_OK) {

            final ArrayList<String> matches_text = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(type==1)
            {
                if (matches_text.size() > 0) {
                    String[] matches_text2 = matches_text.toArray(new String[matches_text.size()]);
                    // chua bat truong hop matches_text2 khong có hoac chi co 1 2 từ
                    if (matches_text2[0].equals(your_word.toLowerCase()) ||
                            matches_text2[1].equals(your_word.toLowerCase())) {
                        // Toast.makeText(this,"dung"+" "+your_word.toLowerCase()+" "+matches_text2[0]+" "+matches_text2[1]+" "+matches_text2[2], Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "dung", Toast.LENGTH_SHORT).show();
                        WordFragment.Question q = new WordFragment.Question();
                        q.word = w;
                        q.kq = 1;
                        WordFragment.ques.set(index, q);
                        WordFragment.checkresult();
                    } else {
                        Toast.makeText(this, "sai", Toast.LENGTH_SHORT).show();
                        WordFragment.Question q = new WordFragment.Question();
                        q.word = w;
                        q.kq = 0;
                        WordFragment.ques.set(index, q);
                        WordFragment.checkresult();
                    }
                }
            }
            else
            {
                if (matches_text.size() > 0) {
                    String[] matches_text2 = matches_text.toArray(new String[matches_text.size()]);
                    // chua bat truong hop matches_text2 khong có hoac chi co 1 2 từ
                    if (matches_text2[0].equals(your_word.toLowerCase()) ||
                            matches_text2[1].equals(your_word.toLowerCase())) {
                        // Toast.makeText(this,"dung"+" "+your_word.toLowerCase()+" "+matches_text2[0]+" "+matches_text2[1]+" "+matches_text2[2], Toast.LENGTH_SHORT).show();
                        Toast.makeText(this, "dung "+matches_text2[0]+" "+matches_text2[1], Toast.LENGTH_SHORT).show();
                        if(type2==1)
                            CWordFragment.ques.get(index).kq1=1;
                        else
                            if(type2==2)
                                CWordFragment.ques.get(index).kq2=1;
                        CWordFragment.checkresult();
                        CWordFragment.checkresult2(index);
                    } else {
                        Toast.makeText(this, "sai"+matches_text2[0]+" "+matches_text2[1], Toast.LENGTH_SHORT).show();
                        if(type2==1)
                            CWordFragment.ques.get(index).kq1=0;
                        else
                        if(type2==2)
                            CWordFragment.ques.get(index).kq2=0;
                        CWordFragment.checkresult();
                        CWordFragment.checkresult2(index);
                    }
                }
            }


        }

        if (requestCode == SPEECH_API_CHECK) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i == TextToSpeech.SUCCESS) {

                            int result = mTts.setLanguage(Locale.getDefault());


                            if (result == TextToSpeech.LANG_MISSING_DATA
                                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "This Language is not supported");
                            } else {


                            }
                        } else {
                            // Initialization failed.
                            Log.e("app", "Could not initialize TextToSpeech.");
                        }
                    }
                });
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        CheckTTS();
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*
                final Fragment fragment = ((SectionsPagerAdapter) mViewPager.getAdapter()).getFragment(position);
                if (position==1 && fragment!=null)
                    new AlertDialog.Builder(TabActivity.this)
                            .setTitle("asdddddddddddddddddddddddddd")
                            .setMessage("ddddddddddddddddddddddddddd?")
                            .setPositiveButton("Word", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Choice=1;
                                    fragment.onResume();
                                }
                            })
                            .setNegativeButton("Sentence", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Choice=2;
                                    fragment.onResume();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private HashMap<Integer, String> mFragmentTags;
        private FragmentManager fragmentManager;
        private Context context;
        private Fragment mWordFragment;


        public SectionsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            fragmentManager = fm;
            mFragmentTags = new HashMap<Integer, String>();
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TheoryFragment().createFragment();
                case 1:
                    return new WordFragment().createFragment();
                default:
                    return new TheoryFragment().createFragment();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabs[position];

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                // record the fragment tag here.
                Fragment f = (Fragment) obj;
                String tag = f.getTag();
                mFragmentTags.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position) {
            String tag = mFragmentTags.get(position);
            if (tag == null)
                return null;
            return fragmentManager.findFragmentByTag(tag);
        }
    }


    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();
        if (net != null && net.isAvailable() && net.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
