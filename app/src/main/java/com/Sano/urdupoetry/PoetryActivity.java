package com.Sano.urdupoetry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class PoetryActivity extends AppCompatActivity {

    public Integer[] images = {
            R.drawable.p25,R.drawable.may2,R.drawable.may3,R.drawable.may4,R.drawable.may4,R.drawable.may5,R.drawable.may6,R.drawable.may7,R.drawable.may8,R.drawable.may9,R.drawable.may10,R.drawable.may11
            ,R.drawable.may12,R.drawable.may13,R.drawable.may14,R.drawable.p183,R.drawable.p184,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10
            ,R.drawable.p11,R.drawable.p12,R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16,R.drawable.p17,R.drawable.p18,R.drawable.p19,R.drawable.p20,R.drawable.p21
            ,R.drawable.p22,R.drawable.p23,R.drawable.p24,R.drawable.p25,R.drawable.p26,R.drawable.p27,R.drawable.p28,R.drawable.p29,R.drawable.p30,R.drawable.p31,R.drawable.p32
            ,R.drawable.p33,R.drawable.p34,R.drawable.p35,R.drawable.p36,R.drawable.p37,R.drawable.p38,R.drawable.p39,R.drawable.p40,R.drawable.p181,R.drawable.p182
            ,R.drawable.p183,R.drawable.p184,R.drawable.p185,R.drawable.p16,R.drawable.p17,R.drawable.p18,R.drawable.p19,R.drawable.p20,R.drawable.p21
            ,R.drawable.p22,R.drawable.p23,R.drawable.p24,R.drawable.may1,R.drawable.p36,R.drawable.p37,R.drawable.p38,R.drawable.p39,R.drawable.p40,R.drawable.p181,R.drawable.p182
            ,R.drawable.p183,R.drawable.p184,R.drawable.p185,R.drawable.may7,R.drawable.may8,R.drawable.may9,R.drawable.may10,R.drawable.may11
            ,R.drawable.may12
    };
    int adCount = 0;
    ImageView imageview;
    int count=0;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    String INTERSTITIAL_AD_UNIT = "ca-app-pub-6165143774808397/1765824617";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry);


        imageview = findViewById(R.id.imageView);
        imageview.setImageResource(images[count]);

        //============== Code for Circle Image Shape ========================

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imageIcon);
//        RoundedBitmapDrawable mDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
//        mDrawable.setCircular(true);
//        imageview.setImageDrawable(mDrawable);

        //============== Code for Circle Image Shape ========================


        // ============================ Zoom ImageView With Dialog Box =====================================
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PoetryActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(images[count]);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        // ============================ Zoom ImageView With Dialog Box =====================================



//  =================================== Start Ads Code Here ===============================
        MobileAds.initialize(this, "ca-app-pub-6165143774808397/5349846401");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("7D963530F19D8B0E85984E22F5A22C78").build();
        mAdView.loadAd(adRequest);

        loadAd();
//  =================================== Start Ads Code Here ===============================

}



    private void loadAd() {

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

    public void saveImage(View view) {

        BitmapDrawable draw = (BitmapDrawable) imageview.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/Forza Wallpapers");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        try {
            outStream = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        try {
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),"Image Saved to Gallery", Toast.LENGTH_LONG).show();

        refreshGallery(outFile);

    }

    private void refreshGallery(File file){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    public void nextImage(View view) {
        if(adCount==11){
            adCount=2;
        }
        else{
            adCount++;
        }
        if(count<184){
            count++;
        }
        else{
            count=0;
        }
        logic();

    }

    public void backImage(View view) {
        if(adCount==11){
            adCount=2;
        }
        else{
            adCount++;
        }
        if(count>0){
            count--;
        }
        else{
            count=184;
        }
        logic();
    }

    public void logic(){
        imageview.setImageResource(images[count]);


        if(adCount==7){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }


    }

    public void shareImage(View view){

        Bitmap bitmap =getBitmapFromView(imageview);
        try {
            File file = new File(this.getExternalCacheDir(),"urdupoetry.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            //canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}
