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
import dhe.digital.library.haryana.adapter.LibraryGalleryLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryGalleryData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryGalleryBinding;
import dhe.digital.library.haryana.models.LibraryGalleryResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryGalleryActivity extends BaseActivity implements GetLibraryGalleryData_interface, LibraryGalleryLibIdAdapter.ItemListener {


    ActivityLibraryGalleryBinding binding;
    LinearLayoutManager manager;
    String typeId,  titleOfPage;

    LibraryGalleryLibIdAdapter adaptermain;
    private List<LibraryGalleryResponse.Datum> arrayList = new ArrayList<LibraryGalleryResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_library_gallery);

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




        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryGalleryActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getLibraryGalleryDataMethod(LibraryGalleryActivity.this, LibraryGalleryActivity.this, binding.rrmain,"8",  binding.simpleSwipeRefreshLayout, LibraryGalleryActivity.this);

                } else {

                    Toast.makeText(LibraryGalleryActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

        // calling api


        if (GlobalClass.isNetworkConnected(LibraryGalleryActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.getLibraryGalleryDataMethod(LibraryGalleryActivity.this, LibraryGalleryActivity.this, binding.rrmain,"8",  binding.simpleSwipeRefreshLayout, LibraryGalleryActivity.this);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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

    }

    @Override
    public void GetLibraryGalleryData(List<LibraryGalleryResponse.Datum> list) {
        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new LibraryGalleryLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);

    }

    @Override
    public void onItemClick(LibraryGalleryResponse.Datum item, int currposition, String type) {

    }
}