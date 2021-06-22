package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
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
import dhe.digital.library.haryana.adapter.LibraryEventsandActivitiesLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryEventsandActivitiesByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryeventsandactivitiesBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.LibraryEventsActivitieResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryEventsandActivities extends BaseActivity implements GetLibraryEventsandActivitiesByLibIdData_interface, LibraryEventsandActivitiesLibIdAdapter.ItemListener {
    LinearLayoutManager manager;


    private ActivityLibraryeventsandactivitiesBinding binding;
    String typeId, itemType, titleOfPage;

    LibraryEventsandActivitiesLibIdAdapter adaptermain;
    private List<LibraryEventsActivitieResponse.Datum> arrayList = new ArrayList<LibraryEventsActivitieResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_libraryeventsandactivities);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                typeId = extras.getString("typeId");
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(LibraryEventsandActivities.this)) {

            BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
            record.setLibraryId("1");

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getLibraryEventsActivitiesLibIdDataMethod(LibraryEventsandActivities.this, LibraryEventsandActivities.this, "8", binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivities.this);

        } else {

            Toast.makeText(LibraryEventsandActivities.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryEventsandActivities.this)) {
                    BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
                    record.setLibraryId("1");

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getLibraryEventsActivitiesLibIdDataMethod(LibraryEventsandActivities.this, LibraryEventsandActivities.this, "8", binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivities.this);

                } else {

                    Toast.makeText(LibraryEventsandActivities.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void GetLibraryEventsandActivitiesByLibIdData(List<LibraryEventsActivitieResponse.Datum> list) {

/* LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvimportantlink.setLayoutManager(manager);
        importantLinksAdapter = new ImportantLinksAdapter(this, importantLinkList, this);
        rvimportantlink.setAdapter(importantLinksAdapter);
        importantLinksAdapter.notifyDataSetChanged();*/

        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new LibraryEventsandActivitiesLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);

        // adaptermain.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(LibraryEventsActivitieResponse.Datum item, int currposition, String type) {


        if (type.equalsIgnoreCase("bookdetail")) {

            Intent intent = new Intent(this, BooksDeatilActivity.class);
            intent.putExtra("bookserial_Id", item.getEventTitle());
            startActivity(intent);


        } else {
        }

    }
}