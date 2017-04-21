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

import com.example.rinnv.esd_g03.R;
import com.example.rinnv.esd_g03.TabActivity;

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
        textView = (TextView) rootView.findViewById(R.id.word1);
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
