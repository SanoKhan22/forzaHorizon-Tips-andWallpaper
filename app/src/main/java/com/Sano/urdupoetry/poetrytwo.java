package com.Sano.urdupoetry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class poetrytwo extends AppCompatActivity {

    private AdView mAdView,mAdView2,mAdView3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetrytwo);
        MobileAds.initialize(this, "ca-app-pub-6165143774808397/5349846401");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("7D963530F19D8B0E85984E22F5A22C78").build();
        mAdView.loadAd(adRequest);






    }
}
