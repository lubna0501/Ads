package com.example.ads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

class VideoAds extends AppCompatActivity {
    private static final String TAG = "VideoAds";
    private RewardedAd mRewardedAd;
    AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_add);
        Button button = findViewById(R.id.videoload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adRequest = new AdRequest.Builder().build();
                loadVideoads();
            }
        });
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(getApplicationContext(), "Device is ready for ads", Toast.LENGTH_SHORT).show();

            }
        });




    }

    private void loadVideoads() {
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, "onAds failed "+loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        if (mRewardedAd !=null){
                            Activity activityContext = VideoAds.this;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    Log.e(TAG, "onUserEarnedReward: " );
                                    int rewardamount  = rewardItem.getAmount();
                                    String rewardtype = rewardItem.getType();
                                    Log.e(TAG, "onUserEarnedReward: "+rewardamount +"and type is "+rewardtype );
                                }
                            });
                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when ad is shown.
                                    Log.d(TAG, "Ad was shown.");
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when ad fails to show.
                                    Log.d(TAG, "Ad failed to show.");
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    // Called when ad is dismissed.
                                    // Set the ad reference to null so you don't show the ad a second time.
                                    Log.d(TAG, "Ad was dismissed.");
                                    mRewardedAd = null;
                                }
                            });
                        }
                        Log.d(TAG, "Ad was loaded.");
                    }
                });

    }
}