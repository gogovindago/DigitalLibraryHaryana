package dhe.digital.library.haryana.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.SearchAllItemsAdapter;
import dhe.digital.library.haryana.adapter.SpinnerLibraryTypeAdapter;
import dhe.digital.library.haryana.adapter.ViewLibByIdlItemsAdapter;
import dhe.digital.library.haryana.allinterface.GetAllData_interface;
import dhe.digital.library.haryana.allinterface.GetAllLibraryTypesData_interface;
import dhe.digital.library.haryana.allinterface.GetLibTypeByIdData_interface;
import dhe.digital.library.haryana.allinterface.SearchingData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivitySearchBinding;
import dhe.digital.library.haryana.models.LibraryTypeAndCoutResponse;
import dhe.digital.library.haryana.models.LibraryTypeByIdResponse;
import dhe.digital.library.haryana.models.SearchResponse;
import dhe.digital.library.haryana.models.ViewAllResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class SearchActivity extends BaseActivity implements SearchAllItemsAdapter.ItemListener, GetAllData_interface, GetAllLibraryTypesData_interface, AdapterView.OnItemSelectedListener, GetLibTypeByIdData_interface, ViewLibByIdlItemsAdapter.ItemListener, SearchingData_interface {


    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private static int firstVisibleInListview;
    GridLayoutManager manager;
    ActivitySearchBinding binding;
    boolean skiplogin;
    private List<SearchResponse.Datum> arrayList = new ArrayList<SearchResponse.Datum>();
    private List<LibraryTypeAndCoutResponse.Datum> librarydataArrayList = new ArrayList<LibraryTypeAndCoutResponse.Datum>();
    private List<LibraryTypeByIdResponse.Datum> libdataByIDlist = new ArrayList<LibraryTypeByIdResponse.Datum>();
    SearchAllItemsAdapter searchallItemsAdapter;
    ViewLibByIdlItemsAdapter viewLibByIdlItemsAdapter;
    String typeId, titleOfPage,
            userLibSelectedId;
    int spnLibCurrentPosition;
    SpinnerLibraryTypeAdapter spinnerLibraryTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.simpleSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        // allItemsAdapter = new ViewAllItemsAdapter(this, arrayList, this, 6);
        /// binding.recyclerView.setAdapter(allItemsAdapter);


        skiplogin = CSPreferences.getBoolean(this, "skiplogin");
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getString("typeId");
                typeId = "2";
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (typeId.equalsIgnoreCase("1")) {

            binding.llstate.setVisibility(View.VISIBLE);

            if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();
                webapiCall.GetAllLibraryTypesDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this);

                //webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                // webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,ViewAllDataActivity.this, typeId);

            } else {

                Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }


        } else if (typeId.equalsIgnoreCase("6")) {
            {

                binding.llstate.setVisibility(View.GONE);
            }
        } else {

            binding.llstate.setVisibility(View.GONE);
            if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();
                // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                // webapiCall.SearchDataMethod(SearchActivity.this, SearchActivity.this, binding.simpleSwipeRefreshLayout, binding.recyclerView, SearchActivity.this, "Rohtak");

            } else {

                Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }

        }
 

        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {
                hideKeybaord( binding.simpleSwipeRefreshLayout);

                if (typeId.equalsIgnoreCase("1")) {

                    binding.llstate.setVisibility(View.VISIBLE);

                    if (spnLibCurrentPosition != 0) {

                        if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.getLibraryTypeByIdDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, userLibSelectedId);

                        } else {

                            Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }

                    } else {


                        if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.GetAllLibraryTypesDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this);

                            //webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                            // webapiCall.getAllDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView,  binding.simpleSwipeRefreshLayout,ViewAllDataActivity.this, typeId);

                        } else {

                            Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }
                    }


                } else if (typeId.equalsIgnoreCase("6")) {


                    binding.llstate.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                    if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();

                        webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);

                    } else {

                        Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }

                } else {

                    binding.llstate.setVisibility(View.GONE);
                    if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                        // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);

                        //  webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);

                        if (binding.txtserach.getText().toString().trim().isEmpty()) {
                            binding.simpleSwipeRefreshLayout.setRefreshing(false);
                            GlobalClass.dailogError(SearchActivity.this, "Missing search Data", "Nothing to search, use Mic or type for search anything.");

                        } else {
                            webapiCall.SearchDataMethod(SearchActivity.this, SearchActivity.this, binding.simpleSwipeRefreshLayout, binding.recyclerView, SearchActivity.this, binding.txtserach.getText().toString().trim());

                        }


                    } else {

                        Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                //editText.setText("");
                // binding.serachView.setQueryHint().setHint("Listening...");
                //GlobalClass.showtost(SearchActivity.this, "Listening...");
                binding.txtserach.setText("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                binding.micButton.setImageResource(R.drawable.ic_mic_black_off);
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                /* editText.setText(data.get(0)); */
                // binding.serachView.setQuery(data.get(0));
                GlobalClass.showtost(SearchActivity.this, data.get(0));
                String searchData = data.get(0);
                binding.txtserach.setText(searchData);
hideKeybaord( binding.simpleSwipeRefreshLayout);
                if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                    // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                    //  webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);
                    webapiCall.SearchDataMethod(SearchActivity.this, SearchActivity.this, binding.simpleSwipeRefreshLayout, binding.recyclerView, SearchActivity.this, searchData);


                } else {

                    Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


        binding.micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.micButton.setImageResource(R.drawable.ic_mic_black_24dp);
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });


        binding.txtserach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (i2 > 0) {
                    binding.cleartxt.setVisibility(View.VISIBLE);
                    binding.btnsearch.setVisibility(View.VISIBLE);

                } else {
                    binding.cleartxt.setVisibility(View.GONE);
                    binding.btnsearch.setVisibility(View.GONE);


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (binding.txtserach.getText().toString().trim().length() > 0) {
                    binding.cleartxt.setVisibility(View.VISIBLE);
                    binding.btnsearch.setVisibility(View.VISIBLE);


                } else {

                    binding.cleartxt.setVisibility(View.GONE);
                    binding.btnsearch.setVisibility(View.GONE);
                }


            }
        });

    }

    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void shuffle() {
        arrayList.clear();
        searchallItemsAdapter.notifyDataSetChanged();

        //  renewItems();

    }

    @Override
    public void onItemClick(SearchResponse.Datum item, int currposition) {


        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {


            if (item.getUrl() != null) {

                Intent certificate = new Intent(this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", item.getUrl());
                certificate.putExtra("title", item.getDescription());
                certificate.putExtra("typeId", item.getType());
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
            if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                WebAPiCall webapiCall = new WebAPiCall();

                webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);

            } else {

                Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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

    private void performSearch() {
        binding.txtserach.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(binding.txtserach.getWindowToken(), 0);
        if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();
            // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

            // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
            //  webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);
            webapiCall.SearchDataMethod(SearchActivity.this, SearchActivity.this, binding.simpleSwipeRefreshLayout, binding.recyclerView, SearchActivity.this, binding.txtserach.getText().toString().trim());


        } else {

            Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void initListeners() {

        binding.txtserach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });


        binding.cleartxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeybaord(view);
                binding.txtserach.setText("");

            }
        });

        binding.btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeybaord(view);

                if (GlobalClass.isNetworkConnected(SearchActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    // webapiCall.GetAllLibraryTypesDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this);

                    // webapiCall.getLibraryTypeByIdDataMethod(ViewAllDataActivity.this, ViewAllDataActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, ViewAllDataActivity.this, userLibSelectedId);
                    //  webapiCall.getAllDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, typeId);
                    webapiCall.SearchDataMethod(SearchActivity.this, SearchActivity.this, binding.simpleSwipeRefreshLayout, binding.recyclerView, SearchActivity.this, binding.txtserach.getText().toString().trim());


                } else {

                    Toast.makeText(SearchActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            }
        });


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
    public void userSearchingdata(List<SearchResponse.Datum> list) {

        arrayList.clear();
        arrayList.addAll(list);
        if (list.isEmpty()) {
            binding.txtmsg.setVisibility(View.VISIBLE);

        }else {
            binding.txtmsg.setVisibility(View.GONE);
        }

        if (typeId.equalsIgnoreCase("6")) {
            manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        } else {
            manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);


        }
        binding.recyclerView.setLayoutManager(manager);
        firstVisibleInListview = manager.findFirstVisibleItemPosition();

        searchallItemsAdapter = new SearchAllItemsAdapter(this, arrayList, this, typeId);
        binding.recyclerView.setAdapter(searchallItemsAdapter);
        searchallItemsAdapter.notifyDataSetChanged();


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
                webapiCall.getLibraryTypeByIdDataMethod(SearchActivity.this, SearchActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, SearchActivity.this, userLibSelectedId);


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
                certificate.putExtra("itemid", item.getId());
                startActivity(certificate);
            } else {

                GlobalClass.dailogError(this, "No Url Found", "NO any url found to redirect to next page.");
            }


        }

    }

    @Override
    public void GetAllData(List<ViewAllResponse.Datum> list) {

    }


}