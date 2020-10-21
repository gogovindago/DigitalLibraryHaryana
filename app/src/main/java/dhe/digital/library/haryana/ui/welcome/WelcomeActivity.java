package dhe.digital.library.haryana.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;

import dhe.digital.library.haryana.ui.activity.LoginActivity;
import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.SpinnerLanguageAdapter;

import dhe.digital.library.haryana.databinding.ActivityWelcomeBinding;

import dhe.digital.library.haryana.models.LanguageModelData;
import dhe.digital.library.haryana.ui.activity.MainActivity;
import dhe.digital.library.haryana.ui.activity.SignupActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;


public class WelcomeActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ActivityWelcomeBinding binding;
    ArrayList<LanguageModelData> languagelist;
    String SelectedLanguage = "en";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        languagelist = new ArrayList<LanguageModelData>();
        for (int i = 0; i <= 3; i++) {
            LanguageModelData dummyData = new LanguageModelData();
            if (i == 0) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName(getString(R.string.selectlanguage));
                dummyData.setLanCode("en");
                languagelist.add(dummyData);
            } else if (i == 1) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName(getString(R.string.english));
                dummyData.setLanCode("en");
                languagelist.add(dummyData);
            } else if (i == 2) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName("Hindi");
                dummyData.setLanCode("hi");
                languagelist.add(dummyData);
            }/* else if (i == 3) {
                dummyData.setImage(R.drawable.ic_baseline_language_24);
                dummyData.setName("Haryanvi");
                languagelist.add(dummyData);
            } */ else {

            }
            SpinnerLanguageAdapter spnGenderAdapter = new SpinnerLanguageAdapter(getApplicationContext(), languagelist);
            binding.languageSpinner.setAdapter(spnGenderAdapter);

        }
    }


   /* fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }*/


    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        binding.languageSpinner.setOnItemSelectedListener(this);

        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intentlogin);
                finish();

            }
        });

        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivityold.class);
                Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivity.class);
                startActivity(intentsignup);
                finish();

            }
        });


        binding.btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivityold.class);

                try {
                    CSPreferences.putString(WelcomeActivity.this, "User_Name", "Digital Library Haryana");
//                    CSPreferences.putString(WelcomeActivity.this, "Purpose", data2.getPurpose());
//                    CSPreferences.putString(WelcomeActivity.this, "Status", data2.getStatus());
//                    CSPreferences.putString(WelcomeActivity.this, "LibraryId", String.valueOf(data2.getLibraryId()));
//                    CSPreferences.putString(WelcomeActivity.this, "otp", String.valueOf(data2.getOtp()));
//                    CSPreferences.putString(WelcomeActivity.this, "PhoneNo", data2.getPhoneNo());
//                    CSPreferences.putString(WelcomeActivity.this, "Email", data2.getEmail());
//                    CSPreferences.putString(WelcomeActivity.this, "token", data2.getToken());
                  //  CSPreferences.putBolean(WelcomeActivity.this, "firstTimelogin", firstTimelogin);
                    CSPreferences.putBolean(WelcomeActivity.this, "skiplogin", true);


//                    if (data2.getPic() == null) {
//                        data2.setPic(imageurl);
//                    }
//
//                    CSPreferences.putString(WelcomeActivity.this, "pic", data2.getPic());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("skiplogin", "skiplogin");
                CSPreferences.putBolean(WelcomeActivity.this, "skiplogin", true);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
