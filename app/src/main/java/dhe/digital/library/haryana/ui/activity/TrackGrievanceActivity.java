package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.TrackGrienvanceData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityTrackGrievanceBinding;
import dhe.digital.library.haryana.models.TrackGrievanceResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class TrackGrievanceActivity extends BaseActivity implements TrackGrienvanceData_interface {

    private MyLoaders myLoaders;

    ActivityTrackGrievanceBinding binding;
    ArrayList<TrackGrievanceResponse> list = new ArrayList<TrackGrievanceResponse>();

    String LibtypeId, titleOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_grievance);
        myLoaders = new MyLoaders(getApplicationContext());


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                LibtypeId = String.valueOf(extras.getInt("itemid", 0));


            }

        } catch (Exception e) {
            e.printStackTrace();
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


        binding.textSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Data(view)) {


                    if (GlobalClass.isNetworkConnected(TrackGrievanceActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.getLibraryTrackGrievanceMethod(TrackGrievanceActivity.this, TrackGrievanceActivity.this, binding.grievanceID.getText().toString().trim(), binding.ll, TrackGrievanceActivity.this);


                    } else {

                        Toast.makeText(TrackGrievanceActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                } else {
                }

            }
        });


    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.grievanceID.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Grievance ID ");
            return false;
        }
        return true;
    }


    @Override
    public void allTrackGrienvancedata(List<TrackGrievanceResponse.Datum> list) {

        try {
            binding.llcontent.setVisibility(View.VISIBLE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.textGrievance.setText(Html.fromHtml("<strong>Grievance ID:- <br> </strong> " + list.get(0).getGrievanceId() + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                binding.textGrievanceStatus.setText(Html.fromHtml("<strong>Grievance Status:-<br>  </strong>" + list.get(0).getStatus() + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                binding.txtStatusDate.setText(Html.fromHtml("<strong> Status Date:- </strong><br> " + list.get(0).getStatusDate() + "</h6>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                binding.textGrievance.setText(Html.fromHtml("<strong>Grievance ID:- <br> </strong>" + list.get(0).getGrievanceId() + "</h6>"));
                binding.textGrievanceStatus.setText(Html.fromHtml("<strong> Grievance Status:- <br> </strong> " + list.get(0).getStatus() + "</h6>"));
                binding.txtStatusDate.setText(Html.fromHtml("<strong> Status Date :- <br> </strong>" + list.get(0).getStatusDate() + "</h6>"));

            }



        }catch (Exception e){
            binding.llcontent.setVisibility(View.GONE);
            binding.textNodata.setVisibility(View.VISIBLE);

            e.printStackTrace();
        }




//
//        binding.textGrievance.setText(String.valueOf(list.get(0).getGrievanceId()));
//        binding.textGrievanceStatus.setText(String.valueOf(list.get(0).getStatus()));
//        binding.txtStatusDate.setText(String.valueOf(list.get(0).getStatusDate()));

    }
}