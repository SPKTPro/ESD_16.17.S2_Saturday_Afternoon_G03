package com.example.rinnv.esd_g03.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.rinnv.esd_g03.R;

/**
 * Created by rinnv on 4/20/2017.
 */

public class TheoryFragment extends Fragment {
    public TheoryFragment()
    {

    }

    public Fragment createFragment()
    {
        TheoryFragment theoryFragment = new TheoryFragment();
        return theoryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.theory_layout, container, false);
    }
}
