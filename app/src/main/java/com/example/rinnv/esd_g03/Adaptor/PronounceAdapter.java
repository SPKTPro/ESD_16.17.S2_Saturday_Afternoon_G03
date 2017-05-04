package com.example.rinnv.esd_g03.Adaptor;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rinnv.esd_g03.Models.Pronounce;
import com.example.rinnv.esd_g03.R;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by rinnv on 4/25/2017.
 */

public class PronounceAdapter extends ArrayAdapter<Pronounce> {

    private List<Pronounce> pronounces1, pronounces2;

    public PronounceAdapter(@NonNull Context context, @LayoutRes int resource,
                            @NonNull List<Pronounce> objects1, List<Pronounce> objects2) {
        super(context, resource,objects1);
        pronounces1 = objects1;
        pronounces2 = objects2;      
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: ");
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.pronouce_example_item, null);
        }

        Pronounce p1 = pronounces1.get(position);
        Pronounce p2 = pronounces2.get(position);
        Log.d(TAG, "getView: "+p1.getPronounce());
        Log.d(TAG, "getView: "+p2.getPronounce());

        if (p1 != null && p2 != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.tv_phoWord1);
            TextView tt2 = (TextView) v.findViewById(R.id.tv_phoWord2);
            tt1.setText(p1.getPronounce());
            tt2.setText(p2.getPronounce());
        }

        return v;
    }
}
