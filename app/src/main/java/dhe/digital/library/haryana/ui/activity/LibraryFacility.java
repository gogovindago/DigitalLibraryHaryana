package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.RvBookRecordLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryFacilitiesByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryFacilityBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.LibraryFacilitiesResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryFacility extends BaseActivity implements GetLibraryFacilitiesByLibIdData_interface {


    private ActivityLibraryFacilityBinding binding;
    String  titleOfPage;
 int LibId;
    private List<LibraryFacilitiesResponse.Datum> arrayList = new ArrayList<LibraryFacilitiesResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_library_facility);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                LibId = extras.getInt("itemid",0);
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("LibId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Facility Available in </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Facility Available in " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(LibraryFacility.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getLibraryFacilitydByLibIdDataMethod(LibraryFacility.this, LibraryFacility.this, String.valueOf(LibId), binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryFacility.this);

        } else {

            Toast.makeText(LibraryFacility.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryFacility.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getLibraryFacilitydByLibIdDataMethod(LibraryFacility.this, LibraryFacility.this, String.valueOf(LibId), binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryFacility.this);

                } else {

                    Toast.makeText(LibraryFacility.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void GetLibraryFacilitiesByLibIdData(List<LibraryFacilitiesResponse.Datum> list) {
        try {
            arrayList=list;
            if (list.isEmpty()){
                binding.txtdata.setVisibility(View.GONE);
                binding.txtdata.setVisibility(View.VISIBLE);
                binding.txtdata.setText("No Library Facility Data Availble");

            }else {
                binding.txtdata.setText(list.get(0).getWebData());

            }
        }catch (Exception e){
            binding.txtdata.setVisibility(View.VISIBLE);
            binding.txtdata.setText("No Library Facility Data Availble");
            e.getStackTrace();
        }




    }
}