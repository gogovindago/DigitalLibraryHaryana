package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.OtpVerifyData_interface;
import dhe.digital.library.haryana.allinterface.SignupData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivitySignupBinding;
import dhe.digital.library.haryana.models.SignupRequest;
import dhe.digital.library.haryana.models.SignupResponse;
import dhe.digital.library.haryana.models.VerifyOtpRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class SignupActivity extends BaseActivity implements SignupData_interface, OtpVerifyData_interface {
    ActivitySignupBinding binding;
    private MyLoaders myLoaders;
    private static final String TAG = "LoginActivity";
    private String refreshedToken, adminotp, userMobileno;
    Boolean firstTimelogin = true;
    String imageurl = "https://upload.wikimedia.org/wikipedia/commons/8/85/Library_book_shelves.jpg";
    SignupResponse.Data data2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        myLoaders = new MyLoaders(getApplicationContext());

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //  Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        refreshedToken = task.getResult();
                        // Log.d("fcm",refreshedToken);

                    }
                });
        CSPreferences.putBolean(SignupActivity.this, "skiplogin", false);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {

        binding.txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        binding.btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intentsignup = new Intent(WelcomeActivity.this, SignupActivityold.class);

                try {
                    CSPreferences.putString(SignupActivity.this, "User_Name", "Digital Library Haryana");
//                    CSPreferences.putString(WelcomeActivity.this, "Purpose", data2.getPurpose());
//                    CSPreferences.putString(WelcomeActivity.this, "Status", data2.getStatus());
//                    CSPreferences.putString(WelcomeActivity.this, "LibraryId", String.valueOf(data2.getLibraryId()));
//                    CSPreferences.putString(WelcomeActivity.this, "otp", String.valueOf(data2.getOtp()));
//                    CSPreferences.putString(WelcomeActivity.this, "PhoneNo", data2.getPhoneNo());
//                    CSPreferences.putString(WelcomeActivity.this, "Email", data2.getEmail());
//                    CSPreferences.putString(WelcomeActivity.this, "token", data2.getToken());
                    //  CSPreferences.putBolean(WelcomeActivity.this, "firstTimelogin", firstTimelogin);
                    CSPreferences.putBolean(SignupActivity.this, "skiplogin", true);


//                    if (data2.getPic() == null) {
//                        data2.setPic(imageurl);
//                    }
//
//                    CSPreferences.putString(WelcomeActivity.this, "pic", data2.getPic());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                intent.putExtra("skiplogin", "skiplogin");
                CSPreferences.putBolean(SignupActivity.this, "skiplogin", true);
                startActivity(intent);
                finish();

            }
        });


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

                    // if (adminotp.equalsIgnoreCase(userEnteredOTP)) {

                    VerifyOtpRequest otpRequest = new VerifyOtpRequest();
                    otpRequest.setOtp(userEnteredOTP);
                    otpRequest.setPhoneNo(userMobileno);
                    if (GlobalClass.isNetworkConnected(SignupActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.VerifyOtpPostDataMethod(SignupActivity.this, SignupActivity.this, SignupActivity.this, otpRequest);


                    } else {

                        Toast.makeText(SignupActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


//                    } else {
//                        GlobalClass.dailogError(SignupActivity.this, "Wrong OTP", "Please Enter valid  OTP");
//
//
//                    }


                }

            }
        });

    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User-Name");
            return false;

        } else if (TextUtils.isEmpty(binding.edtmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Mobile Number");
            return false;
        } else if (!isValidMobile(binding.edtmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter 10 digits Mobile Number");
            return false;

        } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
            myLoaders.showSnackBar(view, "Please Enter Valid Email-Id");
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
            //adminotp = String.valueOf(data.getOtp());
            binding.txtmsg.setVisibility(View.VISIBLE);

            userMobileno = binding.edtmobile.getText().toString().trim();

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

    @Override
    public void userOtpVerifydata(Integer data) {


        if (data == 200) {


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
                CSPreferences.putString(this, "AccountType", data2.getUserType());
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


        }

    }
}