package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.ViewAllItemsAdapter;
import dhe.digital.library.haryana.allinterface.GetAllData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityViewalldataBinding;
import dhe.digital.library.haryana.models.ViewAllResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class ViewAllDataActivity extends BaseActivity implements ViewAllItemsAdapter.ItemListener, GetAllData_interface {


    ActivityViewalldataBinding binding;
    boolean skiplogin;
    private List<ViewAllResponse.Datum> arrayList = new ArrayList<ViewAllResponse.Datum>();
    ViewAllItemsAdapter allItemsAdapter;
    String typeId, titleOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewalldata);

        allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this);
        binding.recyclerView.setAdapter(allItemsAdapter);
        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {
                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, ViewAllDataActivity.this, typeId);

                } else {

                    Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                shuffle();
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
        //  LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        //  binding.recyclerView.setLayoutManager(manager);
    }

    public void shuffle() {
        arrayList.clear();
        allItemsAdapter.notifyDataSetChanged();

        //  renewItems();

    }

    @Override
    public void onItemClick(ViewAllResponse.Datum item, int currposition) {

        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {

            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("bookurl", item.getUrl());
            certificate.putExtra("title", item.getDescription());
            startActivity(certificate);

        }
    }


    @Override
    public void initData() {


        skiplogin = CSPreferences.getBoolean(this, "skiplogin");
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getString("typeId");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, ViewAllDataActivity.this, typeId);

        } else {

            Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.toolbar.notifcation.setVisibility(View.GONE);

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
    public void GetAllData(List<ViewAllResponse.Datum> list) {

        arrayList.clear();
        arrayList.addAll(list);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this);
        binding.recyclerView.setAdapter(allItemsAdapter);
        allItemsAdapter.notifyDataSetChanged();


    }
}