package com.example.x_mars;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.setStatusBarColor(getResources().getColor(R.color.my_statusbar_color_white));
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        relativeLayout = findViewById(R.id.pin_placeholder_contaniner);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                DisplayMetrics metrics = getResources().getDisplayMetrics();
                Log.e("Ceeyes",metrics.heightPixels+" "+imageView.getHeight()+" "+findViewById(R.id.constraint).getHeight());
                Integer height = findViewById(R.id.constraint).getHeight();
                ObjectAnimator translation = ObjectAnimator.ofFloat(imageView, "y",height  / 2 - imageView.getHeight()/2 - height/4 ); // metrics.heightPixels or root.getHeight()
                translation.setDuration(500);
                translation.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadFragment(new BlankFragment());
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                },500);


            }
        }, 2000);


    }
    private void loadFragment(final Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}
