package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.ContactUsData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityContactUsBinding;
import dhe.digital.library.haryana.models.ContactUsRequest;
import dhe.digital.library.haryana.models.SignupRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class ContactUsActivity extends BaseActivity implements ContactUsData_interface {
    ActivityContactUsBinding binding;
    private MyLoaders myLoaders;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Integer typeId;
    String titleOfPage, liburl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        myLoaders = new MyLoaders(getApplicationContext());


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                typeId = extras.getInt("itemid");
                liburl = extras.getString("liburl");



                String string = liburl;
                String[] parts = string.split("//", 2);
                String part1 = parts[0]; // 004
                String part2 = parts[1]; // 034556-42
                liburl=part2;


                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Contact us to:-"+titleOfPage);
                binding.txtmsgcontactus.setText("Contact us to:-"+titleOfPage);



            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initData() {

       // binding.toolbar.tvToolbarTitle.setText("Contact Us");

    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Check_Data(v)) {

                    ContactUsRequest request = new ContactUsRequest();
                    request.setName(binding.name.getText().toString().trim());
                    request.setMessage(binding.edtMessage.getText().toString().trim());
                    request.setEmail(binding.edtemail.getText().toString().trim());
                    request.setSubject(binding.txtsubject.getText().toString().trim());
                    request.setLibraryUrl(liburl);


                    if (GlobalClass.isNetworkConnected(ContactUsActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.contactusDataMethod(ContactUsActivity.this, ContactUsActivity.this, ContactUsActivity.this, request);


                    } else {

                        Toast.makeText(ContactUsActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.name.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User-Name");
            return false;

        } else if (!binding.edtemail.getText().toString().trim().matches(emailPattern)) {
            myLoaders.showSnackBar(view, "Please Enter Valid Email-Id");
            return false;
        } else if (TextUtils.isEmpty(binding.txtsubject.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Subject");
            return false;
        } else if (TextUtils.isEmpty(binding.edtMessage.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Message");
            return false;
        }
        return true;
    }

    @Override
    public void allcontactusdata(int data) {



    }
}