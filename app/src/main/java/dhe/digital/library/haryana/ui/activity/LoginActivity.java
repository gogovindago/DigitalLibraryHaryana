package dhe.digital.library.haryana.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.LoginData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.models.LoginRequest;
import dhe.digital.library.haryana.models.LoginResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginData_interface, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    @TargetApi(Build.VERSION_CODES.O)

    GoogleApiClient mGoogleApiClient;
    private int RESOLVE_HINT = 2;
    TextView login, createaccount, txtforget;
    private TextInputEditText edtemail;
    private TextInputEditText edtpass;
    Boolean firstTimelogin = true;
    private String refreshedToken;
    private static final String TAG = "LoginActivity";
    private MyLoaders myLoaders;
    String imageurl = "https://i.picsum.photos/id/599/200/200.jpg?hmac=2WLKs3sxIsaEQ-6WZaa6YMxgl6ZC4cNnid0aqupm2is";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        myLoaders = new MyLoaders(getApplicationContext());
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // Log.d(TAG, "Refreshed token: " + refreshedToken);
        findViews();

        //set google api client for hint request
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
        getHintPhoneNumber();


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


        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                finish();

            }
        });
        txtforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                   startActivity(i);


            }
        });
    }

    public void getHintPhoneNumber() {
        HintRequest hintRequest =
                new HintRequest.Builder()
                        .setPhoneNumberIdentifierSupported(true)
                        .build();
        PendingIntent mIntent = Auth.CredentialsApi.getHintPickerIntent(mGoogleApiClient, hintRequest);
        try {
            startIntentSenderForResult(mIntent.getIntentSender(), RESOLVE_HINT, null, 0, 0, 0);

        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result if we want hint number
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    // credential.getId();  <-- will need to process phone number string
                    String mobilenumberwithcountrycode = credential.getId().substring(3);
                    edtemail.setText(mobilenumberwithcountrycode);

                }
            }
        }
    }


    private void findViews() {
        login = (TextView) findViewById(R.id.button);
        txtforget = (TextView) findViewById(R.id.txtforget);
        createaccount = (TextView) findViewById(R.id.button2);
        edtemail = (TextInputEditText) findViewById(R.id.textInputEditText);
        edtpass = (TextInputEditText) findViewById(R.id.textInputEditText2);
        login.setOnClickListener(this);
    }

    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(edtemail.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Correct User ID");
            return false;

        } else if (TextUtils.isEmpty(edtpass.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Password");
            return false;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v == login) {
            if (Check_Data(v)) {
//{
//"PhoneNo":"7018401817",
//"Password":"1234"
//}

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setFCMToken(refreshedToken);
                loginRequest.setPassword(edtpass.getText().toString().trim());
                loginRequest.setPhoneNo(edtemail.getText().toString().trim());


                if (GlobalClass.isNetworkConnected(LoginActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    //  webapiCall.loginPostDataMethod(this, this, LoginActivity.this, edtemail.getText().toString().trim(), edtpass.getText().toString().trim(), refreshedToken);
                    webapiCall.loginPostDataMethod(this, this, LoginActivity.this, loginRequest);


                } else {

                    Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }


        }
    }


    @Override
    public void alluserdata(LoginResponse.Data data) {
        try {
/*"userid": "48907",
        "empName": "Rakesh Kumar",
        "firstLoginFlag": null,
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOiI0ODkwNyIsImVtcG5hbWUiOiJSYWtlc2ggS3VtYXIiLCJFTVBfSUQiOiI0NzY5MiIsIlJvbGUiOiJFbXBsb3llZSIsIlVzZXJOYW1lIjoiMTczNDMiLCJDb2xsZWdlIjoiR0MgTWF0YW5oYWlsIiwiQ29sbGVnZUlEIjoiNjAiLCJuYmYiOjE1OTg4NjE2ODksImV4cCI6MTU5ODg2MzQ4OSwiaWF0IjoxNTk4ODYxNjg5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjQ5NTM5In0.hqcViJxFsvCbigxFCISTgt8oG7r2bk-HadqIf_9BWbA",
        "emP_ID": "47692",
        "role": "Employee",
        "userName": "17343",
        "college": "GC Matanhail",
        "collegeID": "60",
        "expiration": "2020-08-31T08:44:49Z"
                //"principalName":"DINESH KUMAR","principalNo":"8288005428"
*/
            CSPreferences.putString(this, "User_Name", data.getFullName());
            CSPreferences.putString(this, "Purpose", data.getPurpose());
            CSPreferences.putString(this, "Status", data.getStatus());
            CSPreferences.putString(this, "LibraryId", String.valueOf(data.getLibraryId()));
            CSPreferences.putString(this, "otp", String.valueOf(data.getOtp()));
            CSPreferences.putString(this, "PhoneNo", data.getPhoneNo());
            CSPreferences.putString(this, "Email", data.getEmail());
            CSPreferences.putString(this, "token", data.getToken());
            CSPreferences.putBolean(this, "firstTimelogin", firstTimelogin);
            CSPreferences.putBolean(this, "skiplogin", false);


            if (data.getPic() == null) {
                data.setPic(imageurl);
            }

            CSPreferences.putString(this, "pic", data.getPic());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}