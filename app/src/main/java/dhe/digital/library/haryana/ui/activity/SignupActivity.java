package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.SignupData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;


import dhe.digital.library.haryana.databinding.ActivitySignupBinding;
import dhe.digital.library.haryana.models.SignupRequest;
import dhe.digital.library.haryana.models.SignupResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class SignupActivity extends BaseActivity implements SignupData_interface {
    ActivitySignupBinding binding;
    private MyLoaders myLoaders;
    private static final String TAG = "LoginActivity";
    private String refreshedToken, adminotp;
    Boolean firstTimelogin = true;
    String imageurl = "https://i.picsum.photos/id/599/200/200.jpg?hmac=2WLKs3sxIsaEQ-6WZaa6YMxgl6ZC4cNnid0aqupm2is";
    SignupResponse.Data data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        myLoaders = new MyLoaders(getApplicationContext());
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // Log.d(TAG, "Refreshed token: " + refreshedToken);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        refreshedToken = task.getResult().getToken();

                        // Log and toast
                        String msg = token;
                        // Log.d(TAG, msg);
                        //Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        CSPreferences.putBolean(SignupActivity.this, "skiplogin", false);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        binding.btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Check_Data(v)) {
                    SignupRequest signupRequest = new SignupRequest();
                    signupRequest.setFCMToken(refreshedToken);
                    signupRequest.setPassword(binding.edtconfirmpass.getText().toString().trim());
                    signupRequest.setEmail(binding.edtemail.getText().toString().trim());
                    signupRequest.setFullName(binding.edtusername.getText().toString().trim());
                    signupRequest.setPhoneNo(binding.edtmobile.getText().toString().trim());
                    signupRequest.setLibraryId("1");

                    if (GlobalClass.isNetworkConnected(SignupActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.signupPostDataMethod(SignupActivity.this, SignupActivity.this, SignupActivity.this, signupRequest);


                    } else {

                        Toast.makeText(SignupActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Check_Dataotp(v)) {
                    String userEnteredOTP = binding.edtotp.getText().toString().trim();

                    if (adminotp.equalsIgnoreCase(userEnteredOTP)) {

                        try {
                            CSPreferences.putString(SignupActivity.this, "User_Name", data2.getFullName());
                            CSPreferences.putString(SignupActivity.this, "Purpose", data2.getPurpose());
                            CSPreferences.putString(SignupActivity.this, "Status", data2.getStatus());
                            CSPreferences.putString(SignupActivity.this, "LibraryId", String.valueOf(data2.getLibraryId()));
                            CSPreferences.putString(SignupActivity.this, "otp", String.valueOf(data2.getOtp()));
                            CSPreferences.putString(SignupActivity.this, "PhoneNo", data2.getPhoneNo());
                            CSPreferences.putString(SignupActivity.this, "Email", data2.getEmail());
                            CSPreferences.putString(SignupActivity.this, "token", data2.getToken());
                            CSPreferences.putBolean(SignupActivity.this, "firstTimelogin", firstTimelogin);
                            CSPreferences.putBolean(SignupActivity.this, "skiplogin", false);


                            if (data2.getPic() == null) {
                                data2.setPic(imageurl);
                            }

                            CSPreferences.putString(SignupActivity.this, "pic", data2.getPic());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intentlogin = new Intent(SignupActivity.this, MainActivity.class);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentlogin);
                        finish();

                    } else {
                        GlobalClass.dailogError(SignupActivity.this, "Wrong OTP", "Please Enter valid  OTP");


                    }


                }

            }
        });

    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User-Name");
            return false;

        } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter 10-digits Mobile");
            return false;
        } else if (TextUtils.isEmpty(binding.edtemail.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Email-Id");
            return false;
        } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        } else if (TextUtils.isEmpty(binding.edtpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        } else if (TextUtils.isEmpty(binding.edtconfirmpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Confirm-Password");
            return false;
        } else if (!binding.edtpass.getText().toString().trim().equalsIgnoreCase(binding.edtconfirmpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Password and  Confirm-Password should be same.");
            return false;
        }
        return true;
    }

    public boolean Check_Dataotp(View view) {

        if (TextUtils.isEmpty(binding.edtotp.getText().toString().trim())) {
            GlobalClass.dailogError(SignupActivity.this, "Missing OTP", "Please Enter OTP");
            return false;

        }
        return true;
    }

    @Override
    public void alluserdata(SignupResponse.Data data) {

        try {
            data2 = data;
            adminotp = String.valueOf(data.getOtp());
            binding.txtmsg.setVisibility(View.VISIBLE);

            String userMobileno = binding.edtmobile.getText().toString().trim();

            String mask = userMobileno.replaceAll("\\w(?=\\w{4})", "*");
            binding.txtmsg.setText(getString(R.string.digitsoptmsg) + mask);

            binding.regmainlayout.setVisibility(View.GONE);
            binding.btnotp.setVisibility(View.GONE);
            binding.btnotp.setVisibility(View.GONE);
            binding.btnRegister.setVisibility(View.VISIBLE);
            binding.otplayout.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}