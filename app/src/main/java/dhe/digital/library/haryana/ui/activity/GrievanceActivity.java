package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.ComplaintSpinnerAdapter;
import dhe.digital.library.haryana.databinding.ActivityGrievanceBinding;
import dhe.digital.library.haryana.models.ComplaintTypemodel;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.MyLoaders;

public class GrievanceActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private MyLoaders myLoaders;
    int spnLibraryTypeCurrentPosition;
    ActivityGrievanceBinding binding;
    ArrayList<ComplaintTypemodel> list;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", userLibraryTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_grievance);
        myLoaders = new MyLoaders(getApplicationContext());

        list = new ArrayList<ComplaintTypemodel>();
        for (int i = 0; i <= 3; i++) {
            ComplaintTypemodel dummyData = new ComplaintTypemodel();
            if (i == 0) {
                dummyData.setLibraryType("Select Complaint Type");
                dummyData.setLibraryTypeId(0);
                list.add(dummyData);
            } else if (i == 1) {
                dummyData.setLibraryType("Individual");
                dummyData.setLibraryTypeId(1);
                list.add(dummyData);
            } else if (i == 2) {

                dummyData.setLibraryType("Group");
                dummyData.setLibraryTypeId(2);
                list.add(dummyData);
            } else if (i == 3) {
                dummyData.setLibraryType("Union");
                dummyData.setLibraryTypeId(3);
                list.add(dummyData);
            } else {

            }
            ComplaintSpinnerAdapter adapter = new ComplaintSpinnerAdapter(getApplicationContext(), list);
            binding.spnComplaintType.setAdapter(adapter);


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


        binding.spnComplaintType.setOnItemSelectedListener(this);
        binding.btncomplaintsbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Data(view)) {


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

        if (spnLibraryTypeCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please Select Complaint Type");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User-Name");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytmidname.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Middle-Name");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytLastName.getText().toString().trim())) {
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

        int id = adapterView.getId();
        if (id == R.id.spnlibrarytype) {

            if (position != 0) {

                spnLibraryTypeCurrentPosition = position;
                userLibraryTypeId = String.valueOf(list.get(position).getLibraryTypeId());
                Toast.makeText(this, list.get(position).getLibraryType(), Toast.LENGTH_SHORT).show();


            } else {
            }


        } else {
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}