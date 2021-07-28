package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.CommitteeDetailAdapter;
import dhe.digital.library.haryana.allinterface.CommitteeDetail_Data_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityCommitteeDetailBinding;
import dhe.digital.library.haryana.models.CommitteeDetailsResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class CommitteeDetailActivity extends BaseActivity implements CommitteeDetail_Data_interface, CommitteeDetailAdapter.ItemListener {
    Integer typeId;
    String titleOfPage;
    CommitteeDetailAdapter adapter;
    ActivityCommitteeDetailBinding binding;
    private List<CommitteeDetailsResponse.Datum> arrayList = new ArrayList<CommitteeDetailsResponse.Datum>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_committee_detail);


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getInt("itemid");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText("Committee Detail");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(CommitteeDetailActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getCommitteeDetailsMethod(CommitteeDetailActivity.this, CommitteeDetailActivity.this, String.valueOf(typeId),binding.simpleSwipeRefreshLayout, binding.rrmain, CommitteeDetailActivity.this);


        } else {

            Toast.makeText(CommitteeDetailActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                binding.recyclerView.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                if (GlobalClass.isNetworkConnected(CommitteeDetailActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getCommitteeDetailsMethod(CommitteeDetailActivity.this, CommitteeDetailActivity.this, String.valueOf(typeId),binding.simpleSwipeRefreshLayout, binding.rrmain, CommitteeDetailActivity.this);

                } else {

                    Toast.makeText(CommitteeDetailActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void allCommitteeDetaildata(List<CommitteeDetailsResponse.Datum> list) {
        try {
            binding.txtnodata.setVisibility(View.GONE);

            binding.recyclerView.setVisibility(View.VISIBLE);
            arrayList.clear();
            arrayList.addAll(list);

           // GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
         LinearLayoutManager   manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            binding.recyclerView.setLayoutManager(manager);
            adapter = new CommitteeDetailAdapter(this, (ArrayList) arrayList, this);
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        } catch (Exception e) {

            binding.txtnodata.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.GONE);
            e.printStackTrace();
        }


    }

    @Override
    public void onItemClick(CommitteeDetailsResponse.Datum item, int currposition) {

    }


}