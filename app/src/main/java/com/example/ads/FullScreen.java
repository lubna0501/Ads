package com.example.ads;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class FullScreen extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "FullScreen";
    AdRequest adRequest;
    boolean res = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(getApplicationContext(), "Device is ready for ads", Toast.LENGTH_SHORT).show();
                adRequest = new AdRequest.Builder().build();
                loadads();
            }
        });





    }

    private void loadads() {
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                        if (mInterstitialAd !=null){
                            mInterstitialAd.show(FullScreen.this);
                            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                    super.onAdFailedToShowFullScreenContent(adError);
                                    Log.e(TAG, "onAdFailedToShowFullScreenContent: " );
                                }

                                @Override
                                public void onAdShowedFullScreenContent() {
                                    super.onAdShowedFullScreenContent();
                                    Log.e(TAG, "onAdShowedFullScreenContent: " );
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    super.onAdDismissedFullScreenContent();
                                    Log.e(TAG, "onAdDismissedFullScreenContent: " );
                                }

                                @Override
                                public void onAdImpression() {
                                    super.onAdImpression();
                                    Log.e(TAG, "onAdImpression: " );
                                }

                                @Override
                                public void onAdClicked() {
                                    super.onAdClicked();
                                    Log.e(TAG, "onAdClicked: " );
                                }
                            });

                        }
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.e(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }
}