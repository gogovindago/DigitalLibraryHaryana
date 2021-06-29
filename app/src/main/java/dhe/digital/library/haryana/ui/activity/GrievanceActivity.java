package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.ComplaintSpinnerAdapter;
import dhe.digital.library.haryana.allinterface.InsertionGrienvanceData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityGrievanceBinding;
import dhe.digital.library.haryana.models.ComplaintTypemodel;
import dhe.digital.library.haryana.models.InsertGrievanceRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class GrievanceActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, InsertionGrienvanceData_interface {

    private MyLoaders myLoaders;
    int spnComplaintTypeCurrentPosition = 0;
    ActivityGrievanceBinding binding;
    ArrayList<ComplaintTypemodel> list;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", userComplaintTypeId, LibtypeId, titleOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_grievance);
        myLoaders = new MyLoaders(getApplicationContext());


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                LibtypeId = String.valueOf(extras.getInt("itemid", 0));
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);

/*

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + titleOfPage + "</h6>"));
                }
*/


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        list = new ArrayList<ComplaintTypemodel>();

        for (int i = 0; i <= 3; i++) {
            ComplaintTypemodel dummyData = new ComplaintTypemodel();
            if (i == 0) {
                dummyData.setComplaintTypeName("Select Complaint Type");
                dummyData.setComplaintTypeId(0);
                list.add(dummyData);
            } else if (i == 1) {
                dummyData.setComplaintTypeName("Individual");
                dummyData.setComplaintTypeId(1);
                list.add(dummyData);
            } else if (i == 2) {

                dummyData.setComplaintTypeName("Group");
                dummyData.setComplaintTypeId(2);
                list.add(dummyData);
            } else if (i == 3) {
                dummyData.setComplaintTypeName("Union");
                dummyData.setComplaintTypeId(3);
                list.add(dummyData);
            } else {

            }
            ComplaintSpinnerAdapter adapter = new ComplaintSpinnerAdapter(getApplicationContext(), list);
            binding.spnComplaintType.setAdapter(adapter);
            binding.spnComplaintType.setOnItemSelectedListener(this);

        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btncomplaintsbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Data(view)) {


                    InsertGrievanceRequest request = new InsertGrievanceRequest();
                    request.setLibraryId(LibtypeId);
                    request.setFirstName(binding.txtlytusername.getText().toString().trim());
                    request.setLastName(binding.txtlytLastName.getText().toString().trim());
                    request.setEmail(binding.txtlytemail.getText().toString().trim());
                    request.setMiddleName(binding.txtlytmidname.getText().toString().trim());
                    request.setMobile(binding.txtlytmobile.getText().toString().trim());
                    request.setComplaint(binding.txtlytDetailedComplaint.getText().toString().trim());
                    request.setComplaintTypeId(userComplaintTypeId);

                    if (GlobalClass.isNetworkConnected(GrievanceActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.InsertGrievancePostDataMethod(GrievanceActivity.this, GrievanceActivity.this, GrievanceActivity.this, request);


                    } else {

                        Toast.makeText(GrievanceActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                } else {
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

        if (spnComplaintTypeCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select Complaint Type");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User first-Name");
            return false;

        } /*else if (TextUtils.isEmpty(binding.txtlytmidname.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Middle-Name");
            return false;

        }*/ else if (TextUtils.isEmpty(binding.txtlytLastName.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Last-Name");
            return false;

        } else if (!binding.txtlytemail.getText().toString().trim().matches(emailPattern)) {
            myLoaders.showSnackBar(view, "Please Enter Valid Email-Id");
            return false;
        } else if (TextUtils.isEmpty(binding.txtlytmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Mobile Number");
            return false;
        } else if (!isValidMobile(binding.txtlytmobile.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter 10 digits Mobile Number");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytDetailedComplaint.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Detailed Complaint");
            return false;

        }

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        // int id = adapterView.getId();
        // if (id == R.id.spnlibrarytype) {

        if (position != 0) {

            spnComplaintTypeCurrentPosition = position;
            userComplaintTypeId = String.valueOf(list.get(position).getComplaintTypeId());
            Toast.makeText(this, list.get(position).getComplaintTypeName(), Toast.LENGTH_SHORT).show();


        } else {
            spnComplaintTypeCurrentPosition = position;
        }


        // } else {
        // }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void allGrienvancedata(Integer data, String sysMessage) {
        if (data == 200) {
            binding.rrmain.setVisibility(View.GONE);
            binding.txtmsg.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.txtmsg.setText(Html.fromHtml("<h6> Registered Complaint Status:- <br> <br>" + sysMessage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                binding.txtmsg.setText(Html.fromHtml("<h6> Registered Complaint Status:- <br> <br>" + sysMessage + "</h6>"));
            }


        } else {

        }


    }
}