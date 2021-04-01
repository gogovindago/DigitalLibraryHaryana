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
import dhe.digital.library.haryana.databinding.ActivityCompetitiveexamBinding;
import dhe.digital.library.haryana.models.ViewAllResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class CompetitiveExamActivity extends BaseActivity implements GetAllData_interface, ViewAllItemsAdapter.ItemListener {
    private List<ViewAllResponse.Datum> arrayList = new ArrayList<ViewAllResponse.Datum>();
    private static int firstVisibleInListview;
    GridLayoutManager manager;
    ActivityCompetitiveexamBinding binding;
    String typeId, titleOfPage;
    boolean skiplogin;
    ViewAllItemsAdapter allItemsAdapter;
    Boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_divyaang);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_competitiveexam);


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


        if (firstTime) {

            if (GlobalClass.isNetworkConnected(CompetitiveExamActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();

                webapiCall.getAllDataMethod(CompetitiveExamActivity.this, CompetitiveExamActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, CompetitiveExamActivity.this, typeId);
                firstTime = false;

            } else {

                Toast.makeText(CompetitiveExamActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {


                binding.recyclerView.setVisibility(View.GONE);
                binding.recyclerView.setVisibility(View.VISIBLE);
                if (GlobalClass.isNetworkConnected(CompetitiveExamActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getAllDataMethod(CompetitiveExamActivity.this, CompetitiveExamActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, CompetitiveExamActivity.this, typeId);

                } else {

                    Toast.makeText(CompetitiveExamActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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

       /* binding.llvisually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rvViewAll = new Intent(CompetitiveExamActivity.this,  CompetitiveExamActivity.class);
                rvViewAll.putExtra("typeId", "8");
                rvViewAll.putExtra("itemType", "Book");
                rvViewAll.putExtra("titleOfPage", "Competitive Exam");
                startActivity(rvViewAll);
            }
        });

        binding.llhearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/

    }

    @Override
    public void GetAllData(List<ViewAllResponse.Datum> list) {
        arrayList.clear();
        arrayList.addAll(list);

//        if (typeId.equalsIgnoreCase("6")) {
        manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

      /*  } else {
            manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        }*/
        binding.recyclerView.setLayoutManager(manager);
        firstVisibleInListview = manager.findFirstVisibleItemPosition();

        allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this, typeId);
        binding.recyclerView.setAdapter(allItemsAdapter);
        allItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(ViewAllResponse.Datum item, int currposition) {
        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {


            if (item.getUrl() != null) {

                Intent certificate = new Intent(this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", item.getUrl());
                certificate.putExtra("title", item.getDescription());
                certificate.putExtra("typeId", typeId);
                certificate.putExtra("itemType", "CompetitiveExam");
                certificate.putExtra("itemid", item.getId());
                startActivity(certificate);
            } else {

                GlobalClass.dailogError(this, "No Url Found", "NO any url found to redirect to next page.");
            }


        }
    }
}