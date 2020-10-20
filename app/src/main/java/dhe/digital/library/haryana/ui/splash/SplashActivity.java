package dhe.digital.library.haryana.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.ui.activity.MainActivity;
import dhe.digital.library.haryana.utility.CSPreferences;

public class SplashActivity extends AppCompatActivity {

    private static int splashTimeOut = 5000;
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);

    /*    try {

            if (intent.extras != null) {

                Log.e("splash moredata", getIntent().getExtras().getString("moredata"));
                moredata = intent.extras!!.getString("moredata")
                title = intent.extras!!.getString("name")
                Log.e("firebase data moredata", getIntent().getExtras().getString("moredata"));

//                itemid = intent.extras!!.getString("itemid")
//                itemname = intent.extras!!.getString("itemName")
//                userid = intent.extras!!.getString("desiredid")
//                username = intent.extras!!.getString("sendername")
                *//*  for (String key : getIntent().getExtras().keySet()) {

            if (key.equals("type")){
                String value = getIntent().getExtras().getString(key);
                type=  value;

            }
        }*//*
            }
CSPreferences.putString(this, "token", data.getToken());CSPreferences.putString(this, "token", data.getToken());
        } catch (e: Exception) {
            e.printStackTrace()
        }*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String auth_key = auth_key = CSPreferences.readString(SplashActivity.this, "token");
                if (!auth_key.isEmpty()) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                   /// Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        }, splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mysplashanimation);
        logo.startAnimation(myanim);
    }
}
