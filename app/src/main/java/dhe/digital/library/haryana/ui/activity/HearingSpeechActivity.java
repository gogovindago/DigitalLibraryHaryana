package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.HearingSpeechAllItemsAdapter;
import dhe.digital.library.haryana.allinterface.GetAllHearingSpeechData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityHearingSpeechBinding;
import dhe.digital.library.haryana.models.HearingSpeechimpairedDataResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class HearingSpeechActivity extends BaseActivity implements GetAllHearingSpeechData_interface, HearingSpeechAllItemsAdapter.ItemListener {
    private static int firstVisibleInListview;
    GridLayoutManager manager;
    ActivityHearingSpeechBinding binding;
    boolean skiplogin;
    private List<HearingSpeechimpairedDataResponse.Datum> arrayList = new ArrayList<HearingSpeechimpairedDataResponse.Datum>();


    HearingSpeechAllItemsAdapter allItemsAdapter;
    String typeId, titleOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_hearing_speech);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hearing_speech);

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


        if (GlobalClass.isNetworkConnected(HearingSpeechActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.GetAllHearingSpeechDataMethod(HearingSpeechActivity.this, HearingSpeechActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, HearingSpeechActivity.this);

            //webapiCall.getLibraryTypeByIdDataMethod(HearingSpeechActivity.this, HearingSpeechActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, HearingSpeechActivity.this, userLibSelectedId);
            // webapiCall.getAllDataMethod(HearingSpeechActivity.this, HearingSpeechActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,HearingSpeechActivity.this, typeId);

        } else {

            Toast.makeText(HearingSpeechActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void GetHearingSpeechData(List<HearingSpeechimpairedDataResponse.Datum> list) {


        arrayList.clear();
        arrayList.addAll(list);

        if (typeId.equalsIgnoreCase("9")) {
            manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        } else {
            manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        }
        binding.recyclerView.setLayoutManager(manager);
        firstVisibleInListview = manager.findFirstVisibleItemPosition();

        allItemsAdapter = new HearingSpeechAllItemsAdapter(this, arrayList, this, typeId);
        binding.recyclerView.setAdapter(allItemsAdapter);
        allItemsAdapter.notifyDataSetChanged();


    }


    @Override
    public void onItemClick(HearingSpeechimpairedDataResponse.Datum item, int currposition) {

        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {


            if (item.getYoutubeLink() != null) {

                Intent certificate = new Intent(this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", item.getYoutubeLink());
                certificate.putExtra("title", item.getTopic());
                certificate.putExtra("typeId", typeId);
                certificate.putExtra("itemid", item.getId());
                startActivity(certificate);
            } else {

                GlobalClass.dailogError(this, "No Url Found", "NO any url found to redirect to next page.");
            }


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
}