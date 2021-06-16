package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.RvBookRecordLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetBookRecordByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBooksAvailableinLibraryBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.BookRecordByLibIdResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class BooksAvailableinLibrary extends BaseActivity implements GetBookRecordByLibIdData_interface, RvBookRecordLibIdAdapter.ItemListener {
    LinearLayoutManager manager;


    private ActivityBooksAvailableinLibraryBinding binding;
    String typeId, itemType, titleOfPage;

    RvBookRecordLibIdAdapter adaptermain;
    private List<BookRecordByLibIdResponse.Datum> arrayList = new ArrayList<BookRecordByLibIdResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_books_availablein_library);

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


        if (GlobalClass.isNetworkConnected(BooksAvailableinLibrary.this)) {

            BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
            record.setLibraryId("1");

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getBookRecordByLibIdDataMethod(BooksAvailableinLibrary.this, BooksAvailableinLibrary.this, "1", binding.rrmain, binding.simpleSwipeRefreshLayout, BooksAvailableinLibrary.this);

        } else {

            Toast.makeText(BooksAvailableinLibrary.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(BooksAvailableinLibrary.this)) {
                    BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
                    record.setLibraryId("1");

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getBookRecordByLibIdDataMethod(BooksAvailableinLibrary.this, BooksAvailableinLibrary.this, "1", binding.rrmain, binding.simpleSwipeRefreshLayout, BooksAvailableinLibrary.this);

                } else {

                    Toast.makeText(BooksAvailableinLibrary.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void GetBookRecordByLibIdData(List<BookRecordByLibIdResponse.Datum> list) {

/* LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvimportantlink.setLayoutManager(manager);
        importantLinksAdapter = new ImportantLinksAdapter(this, importantLinkList, this);
        rvimportantlink.setAdapter(importantLinksAdapter);
        importantLinksAdapter.notifyDataSetChanged();*/

        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new RvBookRecordLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);

        // adaptermain.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(BookRecordByLibIdResponse.Datum item, int currposition) {

    }
}