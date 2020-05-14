package com.Sano.urdupoetry;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private Button btnPoetry2,thirdbutton;
    //   testad>>.  ca-app-pub-3940256099942544/1033173712
    String INTERSTITIAL_AD_UNIT = "ca-app-pub-6165143774808397/1765824617";

    Permission permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPoetry2=(Button) findViewById(R.id.btnPoetry2);

        btnPoetry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movetoActivityTwo();
            }
        });


        //thirdbutton or goldenbtn
        thirdbutton=(Button) findViewById(R.id.golden_btn);
        thirdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gintent =new Intent(getApplicationContext(),Goldtips.class);
                startActivity(gintent);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

            }
        });





        permission.checkStorageWritPermission(this);
        MobileAds.initialize(this, "ca-app-pub-6165143774808397/5349846401");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("7D963530F19D8B0E85984E22F5A22C78").build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(INTERSTITIAL_AD_UNIT);
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("7D963530F19D8B0E85984E22F5A22C78").build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

 
    public void btnUrduPoetry(View view) {
        Intent intent =new Intent(getApplicationContext(),PoetryActivity.class);
        startActivity(intent);



        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }



    public void rateThisApp(View view) {
        final String appPackageName = getApplicationContext().getPackageName();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));

    }

    public void moreApps(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Sano.k")));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://search?q=pub:Sano.k"));
        MainActivity.this.startActivity(intent);
    }

    public void shareWithFriends(View view) {
        final String appPackageName = getApplicationContext().getPackageName();
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Wallpapers Images");
            String sAux = "*Forza Horizon Wallpapers with Tips and Tricks* \n\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + appPackageName + "\n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    }

    private void movetoActivityTwo(){
        Intent intent = new Intent(MainActivity.this,poetrytwo.class);
        startActivity(intent);






    }
}
