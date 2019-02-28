package com.project.howtoplaysearchapp.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.project.howtoplaysearchapp.R;


public class YoutubeListActiviy extends AppCompatActivity{


    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    public String KEY_WORD = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_list_activiy);
        Log.d("YoutubeListActiviy", "onCreate: Starting.");



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            KEY_WORD = getIntent().getExtras().getString("KEY_WORD","");
        }

        // tab setting
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("WikiPedia"));
        tabLayout.addTab(tabLayout.newTab().setText("Youtube"));


        // pager setting
        final PagerAdapter adapter = new com.project.howtoplaysearchapp.adapters.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
