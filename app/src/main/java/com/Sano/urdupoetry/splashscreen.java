package com.Sano.urdupoetry;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class splashscreen extends AppCompatActivity {
    Animation topanim, bottomanim;
    ImageView image;
    TextView logo,slogan;
    private static int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        //animation
        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image= findViewById(R.id.image);
        logo= findViewById(R.id.textView1);
        slogan= findViewById(R.id.textView2);



        image.setAnimation(topanim);
        logo.setAnimation(topanim);
        slogan.setAnimation(bottomanim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splashscreen.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },SPLASH_SCREEN);


    }
}
