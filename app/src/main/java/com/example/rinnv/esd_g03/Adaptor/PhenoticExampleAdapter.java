package com.example.rinnv.esd_g03.Adaptor;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Activity.TabActivity;
import com.example.rinnv.esd_g03.Models.Example;
import com.example.rinnv.esd_g03.R;

import java.util.List;

/**
 * Created by rinnv on 4/25/2017.
 */

public class PhenoticExampleAdapter extends ArrayAdapter<Example> {

    public PhenoticExampleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Example> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.example_item, null);
        }

        final Example p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.tv_pho1Ex1);
            tt1.setText(p.getExample()+" "+p.getPhonetic());
        }


        ImageButton speak = (ImageButton) v.findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TabActivity)getContext()).startTextToSpeech(p.getExample());
            }
        });
        return v;
    }
}
