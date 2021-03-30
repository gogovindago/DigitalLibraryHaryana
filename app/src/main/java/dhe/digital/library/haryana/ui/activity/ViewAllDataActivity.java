package dhe.digital.library.haryana.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.SpinnerLibraryTypeAdapter;
import dhe.digital.library.haryana.adapter.ViewAllItemsAdapter;
import dhe.digital.library.haryana.adapter.ViewLibByIdlItemsAdapter;
import dhe.digital.library.haryana.allinterface.GetAllData_interface;
import dhe.digital.library.haryana.allinterface.GetAllLibraryTypesData_interface;
import dhe.digital.library.haryana.allinterface.GetLibTypeByIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityViewalldataBinding;
import dhe.digital.library.haryana.models.LibraryTypeAndCoutResponse;
import dhe.digital.library.haryana.models.LibraryTypeByIdResponse;
import dhe.digital.library.haryana.models.ViewAllResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class ViewAllDataActivity extends BaseActivity implements ViewAllItemsAdapter.ItemListener, GetAllData_interface, GetAllLibraryTypesData_interface, AdapterView.OnItemSelectedListener, GetLibTypeByIdData_interface, ViewLibByIdlItemsAdapter.ItemListener {

    private static int firstVisibleInListview;
    GridLayoutManager manager;
    ActivityViewalldataBinding binding;
    boolean skiplogin;
    private List<ViewAllResponse.Datum> arrayList = new ArrayList<ViewAllResponse.Datum>();

    private List<LibraryTypeAndCoutResponse.Datum> librarydataArrayList = new ArrayList<LibraryTypeAndCoutResponse.Datum>();

    private List<LibraryTypeByIdResponse.Datum> libdataByIDlist = new ArrayList<LibraryTypeByIdResponse.Datum>();

    ViewAllItemsAdapter allItemsAdapter;
    ViewLibByIdlItemsAdapter viewLibByIdlItemsAdapter;
    String typeId, itemType, titleOfPage,
            userLibSelectedId;
    int spnLibCurrentPosition;
    SpinnerLibraryTypeAdapter spinnerLibraryTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewalldata);


        binding.simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        // allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this, 6);
        /// binding.recyclerView.setAdapter(allItemsAdapter);


        skiplogin = CSPreferences.getBoolean(this, "skiplogin");
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getString("typeId");
                itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (typeId.equalsIgnoreCase("1")) {

            binding.llstate.setVisibility(View.VISIBLE);

            if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();
                webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                //webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                // webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,ViewAllDataActivity.this, typeId);

            } else {

                Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }


        } else if (typeId.equalsIgnoreCase("6")) {
            {

                binding.llstate.setVisibility(View.GONE);
            }
        } else {

            binding.llstate.setVisibility(View.GONE);
            if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();
                // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, typeId);

            } else {

                Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }

        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (typeId.equalsIgnoreCase("1")) {

                    binding.llstate.setVisibility(View.VISIBLE);

                    if (spnLibCurrentPosition != 0) {

                        if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);

                        } else {

                            Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }

                    } else {


                        if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                            //webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                            // webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,ViewAllDataActivity.this, typeId);

                        } else {

                            Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }
                    }


                } else if (typeId.equalsIgnoreCase("6")) {


                    binding.llstate.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();

                        webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, typeId);

                    } else {

                        Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                } else {

                    binding.llstate.setVisibility(View.GONE);
                    if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                        // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                        webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, typeId);

                    } else {

                        Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                }

/*
                if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {
                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, typeId);

                } else {

                    Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                shuffle();
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
                */
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


            if (item.getUrl() != null) {

                Intent certificate = new Intent(this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", item.getUrl());
                certificate.putExtra("title", item.getDescription());
                certificate.putExtra("typeId", typeId);
                certificate.putExtra("itemType", itemType);
                certificate.putExtra("itemid", item.getId());
                startActivity(certificate);
            } else {

                GlobalClass.dailogError(this, "No Url Found", "NO any url found to redirect to next page.");
            }


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
                itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (typeId.equalsIgnoreCase("1")) {

            // binding.llstate.setVisibility(View.VISIBLE);

//            if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {
//
//                WebAPiCall webapiCall = new WebAPiCall();
//                webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);
//
//                //webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
//                // webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,ViewAllDataActivity.this, typeId);
//
//            } else {
//
//                Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
//            }


        } else if (typeId.equalsIgnoreCase("6")) {

            binding.llstate.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            if (GlobalClass.isNetworkConnected(ViewAllDataActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();

                webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, typeId);

            } else {

                Toast.makeText(ViewAllDataActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }
        } else {
            binding.llstate.setVisibility(View.GONE);

        }


        binding.toolbar.notifcation.setVisibility(View.GONE);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                onScrolledd(recyclerView, dx, dy);
                binding.gototop.setVisibility(View.VISIBLE);

                if (dy > 0) {
                    // Scrolling up
                } else {


                }
            }
        });

    }


    public void onScrolledd(RecyclerView recyclerView, int dx, int dy) {

        int currentFirstVisible = manager.findFirstVisibleItemPosition();

        if (currentFirstVisible > firstVisibleInListview)
            Log.i("RecyclerView scrolled: ", "scroll up!");
        else

            Log.i("RecyclerView scrolled: ", "scroll down!");

        firstVisibleInListview = currentFirstVisible;

    }

    @Override
    public void initListeners() {


        binding.gototop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recyclerView.smoothScrollToPosition(0);
            }
        });

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.spnlibrarytype.setOnItemSelectedListener(this);

    }

    @Override
    public void GetAllData(List<ViewAllResponse.Datum> list) {

        arrayList.clear();
        arrayList.addAll(list);

        if (typeId.equalsIgnoreCase("6")) {
            manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        } else {
            manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        }
        binding.recyclerView.setLayoutManager(manager);
        firstVisibleInListview = manager.findFirstVisibleItemPosition();

        allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this, typeId);
        binding.recyclerView.setAdapter(allItemsAdapter);
        allItemsAdapter.notifyDataSetChanged();


    }

    @Override
    public void GetLibrarytypesData(List<LibraryTypeAndCoutResponse.Datum> list) {


        librarydataArrayList.clear();
        librarydataArrayList.addAll(list);
        LibraryTypeAndCoutResponse.Datum allLib = new LibraryTypeAndCoutResponse.Datum();
        allLib.setLibraryType("Select your Library Type");
        allLib.setLibraryTypeId(0);
        librarydataArrayList.add(0, allLib);

        spinnerLibraryTypeAdapter = new SpinnerLibraryTypeAdapter(getApplicationContext(), librarydataArrayList);
        binding.spnlibrarytype.setAdapter(spinnerLibraryTypeAdapter);
        spinnerLibraryTypeAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        // int id = adapterView.getId();

        //  if (id == binding.spnlibrarytype) {


        //  if (AccountType.equalsIgnoreCase("1")) {


        if (position != 0) {
            spnLibCurrentPosition = position;
            userLibSelectedId = String.valueOf(librarydataArrayList.get(position).getLibraryTypeId());

            // Toast.makeText(getApplicationContext(), allDistrict.get(position).getDistrict(), Toast.LENGTH_LONG).show();
            // Toast.makeText(getApplicationContext(), String.valueOf(allDistrict.get(position).getDistrictId()), Toast.LENGTH_LONG).show();

            if (GlobalClass.isNetworkConnected(this)) {

                WebAPiCall webapiCall = new WebAPiCall();
                webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);


            } else {

                Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }

        } else {

            spnLibCurrentPosition = position;
            binding.recyclerView.setVisibility(View.GONE);


        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void GetAllLibTypeByIdData(List<LibraryTypeByIdResponse.Datum> list) {

        libdataByIDlist.clear();
        libdataByIDlist.addAll(list);

        if (typeId.equalsIgnoreCase("6")) {
            manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
            binding.llstate.setVisibility(View.VISIBLE);


        } else {
            manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        }
        binding.recyclerView.setLayoutManager(manager);
        firstVisibleInListview = manager.findFirstVisibleItemPosition();

        viewLibByIdlItemsAdapter = new ViewLibByIdlItemsAdapter(this, libdataByIDlist, this, typeId);
        binding.recyclerView.setAdapter(viewLibByIdlItemsAdapter);
        viewLibByIdlItemsAdapter.notifyDataSetChanged();


    }

    @Override
    public void onItemClick(LibraryTypeByIdResponse.Datum item, int currposition) {

        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {


            if (item.getUrl() != null) {

                Intent certificate = new Intent(this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", item.getUrl());
                certificate.putExtra("title", item.getDescription());
                certificate.putExtra("typeId", typeId);
                certificate.putExtra("itemType", "importantlink");
                certificate.putExtra("itemid", item.getId());
                startActivity(certificate);
            } else {

                GlobalClass.dailogError(this, "No Url Found", "NO any url found to redirect to next page.");
            }


        }

    }
}