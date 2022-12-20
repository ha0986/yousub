package com.hanif.tikfollow;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONObject;


public class autoLoad {
    public static String userName;
    private static RewardedAd mRewardedAd;
    private static AdView adView;
    private static InterstitialAd mInterstitialAd;
    public  static boolean connection = false;
    public static String points = "500";
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();






    //for https
    static RequestQueue queue;

    static String url = "https://www.google.com/";















   public static void isAppInstalled(Context context, String packageName){
       try{
           context.getPackageManager().getApplicationInfo(packageName,0);

       }catch (PackageManager.NameNotFoundException e){

           AlertDialog.Builder builder = new AlertDialog.Builder(context);
           builder.setTitle(R.string.app_name);
           builder.setMessage("You Don't have Tiktok install");
           AlertDialog alert = builder.create();
           alert.show();
       }
   }

   public static void alart(Context context, String text){
       AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setTitle("Tikfollow");
       builder.setMessage(text);
       AlertDialog alert = builder.create();
       alert.show();
   }


    public static void Exit(Context context,Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> activity.finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static void taskComplated(Context context,Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Task complated");
        builder.setMessage("You have completed all task of the day. Now we recommend you to read some jokes.. Will you read jokes?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    context.startActivity(new Intent(context, jokes.class));
                    activity.finish();
                })
                .setNegativeButton("No", (dialog, id) -> {
                    context.startActivity(new Intent(context, profile.class));
                    activity.finish();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }




    public static void checkNetwork(Context context){
        queue =  Volley.newRequestQueue(context);
        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> connection = true,
                error -> {connection = false; alart(context,"Please turn on your data connection");});

        queue.add(stringRequest);
   }




















    public static void loadBanner(Context context, String gravity){
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.TOP);
        adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        layout.addView(adView);
        adView.loadAd(adRequestBuilder.build());
    }





    public static  void loadAdd(Context context){
        MobileAds.initialize(context, initializationStatus -> {
        });
        loadInter(context);
        loadReward(context);


    }

    public static void loadInter(Context context){

        AdRequest loadInter = new AdRequest.Builder().build();

        InterstitialAd.load(context,"ca-app-pub-9422110628550448/7543745921", loadInter,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }

    public static void loadReward(Context context){
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, "ca-app-pub-9422110628550448/3388878497",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                    }
                });

    }

    public static void showInter(Activity activity){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    public static void showReward(Activity activity){
        if (mRewardedAd != null) {
            mRewardedAd.show(activity, rewardItem -> {
                // Handle the reward.
                int rewardAmount = rewardItem.getAmount();
                String rewardType = rewardItem.getType();
            });
        }
    }







    public static void getdata(){

        DatabaseReference myRef = database.getReference("tikfan");


        myRef.child(userName).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                points = String.valueOf(task.getResult().getValue());
                profile.points.setText(points);
            }
        });
    }


    public static void savedata(String userName){
        DatabaseReference myRef = database.getReference("tikfan");
        myRef.child(userName).setValue(Integer.valueOf(points));
    }


    public static void removedata(String userName){
        DatabaseReference myRef = database.getReference("tikfan");
        myRef.child(userName).removeValue();

    }

    public static void getkeys(){

        DatabaseReference myRef = database.getReference("tikfan");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}

