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
import dhe.digital.library.haryana.adapter.RvBookRecordLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetBookRecordByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBooksAvailableinLibraryBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.BookRecordByLibIdResponse;
import dhe.digital.library.haryana.models.DataModel;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class BooksAvailableinLibrary extends BaseActivity implements GetBookRecordByLibIdData_interface, RvBookRecordLibIdAdapter.ItemListener {

    ArrayList arrayList;
    private ActivityBooksAvailableinLibraryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_books_availablein_library);


        arrayList = new ArrayList();
        //arrayList.add(new DataModel("Plantation", R.drawable.notifications, "#4CAF50"));
        arrayList.add(new DataModel("admission and counselling", R.drawable.notifications, "#FF9800"));
        arrayList.add(new DataModel("admission", R.drawable.notifications, "#FF9800"));
//        arrayList.add(new DataModel("Result Declare", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("New Events", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Result Declare", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("New Events", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#4CAF50"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 1", R.drawable.notifications, "#09A9FF"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("Item 2", R.drawable.notifications, "#3E51B1"));
//        arrayList.add(new DataModel("Item 3", R.drawable.notifications, "#673BB7"));
//        arrayList.add(new DataModel("Item 4", R.drawable.notifications, "#4BAA50"));
//        arrayList.add(new DataModel("Item 5", R.drawable.notifications, "#F94336"));
//        arrayList.add(new DataModel("Item 6", R.drawable.notifications, "#0A9B88"));

        RvBookRecordLibIdAdapter adaptermain = new RvBookRecordLibIdAdapter(this, arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        /*AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(layoutManager);*/


        /**
         Simple GridLayoutManager that spans two columns
         **/
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);


        if (GlobalClass.isNetworkConnected(BooksAvailableinLibrary.this)) {

            BookRecordByLibIdRequest record = new BookRecordByLibIdRequest();
            record.setLibraryId("1");

            WebAPiCall webapiCall = new WebAPiCall();

          //  webapiCall.getBookRecordByLibIdDataMethod(BooksAvailableinLibrary.this, BooksAvailableinLibrary.this, "1", binding.rrmain, binding.simpleSwipeRefreshLayout, BooksAvailableinLibrary.this);

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

                  //  webapiCall.getBookRecordByLibIdDataMethod(BooksAvailableinLibrary.this, BooksAvailableinLibrary.this, "1", binding.rrmain, binding.simpleSwipeRefreshLayout, BooksAvailableinLibrary.this);

                } else {

                    Toast.makeText(BooksAvailableinLibrary.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText("Books Available");
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

    }

    @Override
    public void onItemClick(DataModel item, int currposition) {

    }
}