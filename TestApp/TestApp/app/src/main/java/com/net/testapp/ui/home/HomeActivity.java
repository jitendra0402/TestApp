package com.net.testapp.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.tabs.TabLayout;
import com.net.testapp.R;
import com.net.testapp.ui.home.fragments.ImagesFragment;
import com.net.testapp.ui.home.fragments.QuotesFragment;

public class HomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preWork();
        getIDs();
        setEvents();
        postWork();

    }

    public void preWork() { }

    public void getIDs() {
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.viewpager);
    }

    public void setEvents() {
    }

    public void postWork() {

        pagerAdapter=new PagerAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;
        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    QuotesFragment quotesFragment = new QuotesFragment();
                    return quotesFragment;
                case 1:
                    ImagesFragment imagesFragment = new ImagesFragment();
                    return imagesFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Quotes";
                case 1:
                    return "Images";
                default:
                    return null;
            }
        }
    }

}