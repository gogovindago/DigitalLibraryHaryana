package dhe.digital.library.haryana.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityForgetPasswordBinding;
import dhe.digital.library.haryana.models.ForgotPasswordRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class ForgetPasswordActivity extends BaseActivity {
    ActivityForgetPasswordBinding binding;
    String selectedDate, username;
    private MyLoaders myLoaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);


    }

    @Override
    public void initData() {
        myLoaders = new MyLoaders(getApplicationContext());


    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.forgotpswdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Check_Data(view)) {

                    // Intent i = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
                    // startActivity(i);


                    username = binding.edtUserMno.getText().toString().trim();

                    if (GlobalClass.isNetworkConnected(ForgetPasswordActivity.this)) {

                        ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
                        //forgotPasswordRequest.setPhoneNo(binding.edtUserId.getText().toString().trim());
                        forgotPasswordRequest.setPhoneNo(username);
                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.forgotPasswordPostDataMethod(ForgetPasswordActivity.this, ForgetPasswordActivity.this, forgotPasswordRequest);


                    } else {

                        Toast.makeText(ForgetPasswordActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        binding.edtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ForgetPasswordActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);

                                selectedDate = dateFormat.format(calendar.getTime());


                                binding.edtdob.setText(selectedDate);


                                Toast.makeText(ForgetPasswordActivity.this, selectedDate, Toast.LENGTH_SHORT).show();

                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog.show();

            }


        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtUserMno.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your 10 digits Registered Mobile Number");
            return false;

        } else if (binding.edtUserMno.getText().toString().trim().length() != 10) {
            myLoaders.showSnackBar(view, "Please Enter your 10 digits Registered Mobile Number");
            return false;
        }
        return true;
    }
}