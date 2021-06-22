package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.GetLibraryAlumniAchievementsByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityAlumniachievementsBinding;
import dhe.digital.library.haryana.models.AlumniAchievementsResponse;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class AlumniAchievements extends BaseActivity implements GetLibraryAlumniAchievementsByLibIdData_interface {


    private ActivityAlumniachievementsBinding binding;
    String titleOfPage;
    int LibId;
    private List<AlumniAchievementsResponse.Datum> arrayList = new ArrayList<AlumniAchievementsResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alumniachievements);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                LibId = extras.getInt("itemid", 0);
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("LibId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Alumni Achievements </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Alumni Achievements " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(AlumniAchievements.this)) {

            BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
            record.setLibraryId("1");

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getLibraryAlumniAchievementsByLibIdDataMethod(AlumniAchievements.this, AlumniAchievements.this, String.valueOf(LibId), binding.rrmain, binding.simpleSwipeRefreshLayout, AlumniAchievements.this);

        } else {

            Toast.makeText(AlumniAchievements.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(AlumniAchievements.this)) {
                    BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
                    record.setLibraryId("1");

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getLibraryAlumniAchievementsByLibIdDataMethod(AlumniAchievements.this, AlumniAchievements.this, String.valueOf(LibId), binding.rrmain, binding.simpleSwipeRefreshLayout, AlumniAchievements.this);

                } else {

                    Toast.makeText(AlumniAchievements.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public void initData() {
        // binding.toolbar.tvToolbarTitle.setText("Books Available");
    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    public void GetLibraryAlumniAchievementsByLibIdData(List<AlumniAchievementsResponse.Datum> list) {
        try {
            arrayList = list;
            if (list.isEmpty()) {
                binding.txtdata.setText("No Alumni Achievements Data Availble");

            } else {
                binding.txtdata.setText(list.get(0).getWebData());

            }
        } catch (Exception e) {
            binding.txtdata.setText("No Alumni Achievements Data Availble");
            e.getStackTrace();
        }


    }
}