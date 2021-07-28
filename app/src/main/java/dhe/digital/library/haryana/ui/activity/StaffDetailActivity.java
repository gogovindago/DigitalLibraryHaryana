package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.StaffDetailAdapter;
import dhe.digital.library.haryana.allinterface.staffDetail_Data_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityStaffDetailBinding;
import dhe.digital.library.haryana.models.StaffDetailsResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class StaffDetailActivity extends BaseActivity implements staffDetail_Data_interface, StaffDetailAdapter.ItemListener {
    Integer typeId;
    String titleOfPage;
    StaffDetailAdapter adapter;
    ActivityStaffDetailBinding binding;
    private List<StaffDetailsResponse.Datum> arrayList = new ArrayList<StaffDetailsResponse.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staff_detail);


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getInt("itemid");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText("Staff Detail");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(StaffDetailActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getstaffDetailsMethod(StaffDetailActivity.this, StaffDetailActivity.this, String.valueOf(typeId), binding.simpleSwipeRefreshLayout, binding.rrmain, StaffDetailActivity.this);


        } else {

            Toast.makeText(StaffDetailActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                binding.recyclerView.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                if (GlobalClass.isNetworkConnected(StaffDetailActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getstaffDetailsMethod(StaffDetailActivity.this, StaffDetailActivity.this, String.valueOf(typeId), binding.simpleSwipeRefreshLayout, binding.rrmain, StaffDetailActivity.this);

                } else {

                    Toast.makeText(StaffDetailActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }


            }

        });


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

    }

    @Override
    public void allstaffDetaildata(List<StaffDetailsResponse.Datum> list) {
        try {
            binding.txtnodata.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            arrayList.clear();
            arrayList.addAll(list);

            // GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            binding.recyclerView.setLayoutManager(manager);
            adapter = new StaffDetailAdapter(this, (ArrayList) arrayList, this);
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        } catch (Exception e) {

             binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
            e.printStackTrace();
        }


    }

    @Override
    public void onItemClick(StaffDetailsResponse.Datum item, int currposition) {

    }


}