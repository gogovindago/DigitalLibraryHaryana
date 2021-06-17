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
import dhe.digital.library.haryana.adapter.BookDetailAdapter;
import dhe.digital.library.haryana.allinterface.GetBookDetailData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBooksDetailBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.BooksDetailResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class BooksDeatilActivity extends BaseActivity implements GetBookDetailData_interface, BookDetailAdapter.ItemListener {
    LinearLayoutManager manager;


    private ActivityBooksDetailBinding binding;
    String  titleOfPage;
int bookserial_Id;
    BookDetailAdapter adaptermain;
    private List<BooksDetailResponse.Datum> arrayList = new ArrayList<BooksDetailResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_books_detail);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


              //  titleOfPage = extras.getString("title");
                bookserial_Id = extras.getInt("bookserial_Id");
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);





            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(BooksDeatilActivity.this)) {

            BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
            record.setLibraryId("1");

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getBookDetailByLibIdDataMethod(BooksDeatilActivity.this, BooksDeatilActivity.this, String.valueOf(bookserial_Id), binding.rrmain, binding.simpleSwipeRefreshLayout, BooksDeatilActivity.this);

        } else {

            Toast.makeText(BooksDeatilActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(BooksDeatilActivity.this)) {
                    BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
                    record.setLibraryId("1");

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getBookDetailByLibIdDataMethod(BooksDeatilActivity.this, BooksDeatilActivity.this, String.valueOf(bookserial_Id), binding.rrmain, binding.simpleSwipeRefreshLayout, BooksDeatilActivity.this);

                } else {

                    Toast.makeText(BooksDeatilActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void GetBookDetailbyIdData(List<BooksDetailResponse.Datum> list) {

/* LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvimportantlink.setLayoutManager(manager);
        importantLinksAdapter = new ImportantLinksAdapter(this, importantLinkList, this);
        rvimportantlink.setAdapter(importantLinksAdapter);
        importantLinksAdapter.notifyDataSetChanged();*/

        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new BookDetailAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in </h6>" + arrayList.get(0).getLibraryName() + "</h6>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + arrayList.get(0).getLibraryName() + "</h6>"));
        }

        // adaptermain.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(BooksDetailResponse.Datum item, int currposition) {

    }
}