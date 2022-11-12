package com.example.ads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdView adView;
    Button fullscreen,videoads;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView= findViewById(R.id.adViewsss);
        fullscreen = findViewById(R.id.fulladsmovebtn);
        videoads =findViewById(R.id.videoadsmovebtn);

        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,FullScreen.class));
            }
        });
        videoads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoAds.class));
            }
        });

        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                Toast.makeText(getApplicationContext(), "device is ready for load ads", Toast.LENGTH_SHORT).show();


            }
        });
        List<String> testDeviceIds = Arrays.asList("E88640FF60E18FE15526B4C5072F79B7");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e(TAG, "onAdClosed: ");
                // Toast.makeText(getApplicationContext(), "ad closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e(TAG, "onAdFailedToLoad: "+loadAdError);
                //Toast.makeText(getApplicationContext(), "error "+loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e(TAG, "onAdOpened: ");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "onAdLoaded: " );
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.e(TAG, "onAdClicked: " );
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.e(TAG, "onAdImpression: " );
            }
        });
    }
}