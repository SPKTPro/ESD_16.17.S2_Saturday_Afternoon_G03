package com.example.rinnv.esd_g03;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.rinnv.esd_g03.Fragment.TheoryFragment;
import com.example.rinnv.esd_g03.Fragment.WordFragment;

import java.util.HashMap;

import static com.example.rinnv.esd_g03.R.id.container;

public class TabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String TAG ="Tag";
    public static int Choice =0;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;
    // Tab titles
    private String[] tabs = {"THEORY", "PRACTICE"};
    private TabLayout tabLayout;
    //Layout
    public static int[] resourceIds = {
            R.layout.theory_layout
            , R.layout.practice_layout
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),getApplicationContext());

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

        public SectionsPagerAdapter(FragmentManager fm,Context context) {
            super(fm);
            fragmentManager=fm;
            mFragmentTags = new HashMap<Integer, String>();
            this.context =context;
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


}
