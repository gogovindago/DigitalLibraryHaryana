package dhe.digital.library.haryana.ui.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.DrawerItemCustomAdapter;
import dhe.digital.library.haryana.adapter.ImportantLinksAdapter;
import dhe.digital.library.haryana.adapter.OthesDigitalLibAdapter;
import dhe.digital.library.haryana.adapter.SliderAdapter;
import dhe.digital.library.haryana.adapter.TrendingUdaanVideosAdapter;
import dhe.digital.library.haryana.adapter.TrendingsEBooksAdapter;
import dhe.digital.library.haryana.adapter.TrendingsJournalsAdapter;
import dhe.digital.library.haryana.adapter.TrendingsVideosAdapter;
import dhe.digital.library.haryana.allinterface.GetbannersData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.models.DataModelLeftNew;
import dhe.digital.library.haryana.models.HomePageResponse;
import dhe.digital.library.haryana.ui.welcome.WelcomeActivity;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;


public class MainActivity extends BaseActivity implements OthesDigitalLibAdapter.ItemListener, GetbannersData_interface, TrendingsEBooksAdapter.ItemListener, TrendingsVideosAdapter.ItemListener, TrendingsJournalsAdapter.ItemListener, ImportantLinksAdapter.ItemListener, TrendingUdaanVideosAdapter.ItemListener {
    Boolean firstTime = true;

    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 11;
    private static final String TAG = "MainActivity";

    private static DrawerLayout mDrawerLayout;
    ImageView toggle, profile_image, goyesno, imgsearch;
    ImageButton mainNotification;
    LinearLayout llmain, llregUser;
    RelativeLayout uprofile;
    TextView toolbartxt, uname, txttotalcount, textView, txtrole, txtwelcome, rvViewAll, tebooksViewAll, JournalsViewAll, VideosViewAll, importantlinkViewAll, udaanlinkViewAll, btnlogin;
    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TextView mTextMessage;
    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    RecyclerView recyclerView, rvTrandingsEbooks, rvJournals, rvVideos, rvimportantlink, rvudaan;

    List<HomePageResponse.Banner> sliderItemList = new ArrayList<HomePageResponse.Banner>();
    private List<HomePageResponse.OtherDigitalTrendingLibrary> otherDigitalTrendingLibraries = new ArrayList<HomePageResponse.OtherDigitalTrendingLibrary>();
    private List<HomePageResponse.TrendingVideo> trendingVideos = new ArrayList<HomePageResponse.TrendingVideo>();
    private List<HomePageResponse.TrendingeBook> trendingeBooks = new ArrayList<HomePageResponse.TrendingeBook>();
    private List<HomePageResponse.TrendingJournal> trendingJournals = new ArrayList<HomePageResponse.TrendingJournal>();
    private List<HomePageResponse.ImportantLink> importantLinkList = new ArrayList<HomePageResponse.ImportantLink>();
    List<HomePageResponse.TrendingUdaanVideo> trendingUdaanVideos = new ArrayList<HomePageResponse.TrendingUdaanVideo>();
    List<DataModelLeftNew> dataModelLeftList;

    OthesDigitalLibAdapter adaptermain;
    TrendingsEBooksAdapter trendingsEBooksAdapter;
    TrendingsVideosAdapter trendingsVideosAdapter;
    TrendingsJournalsAdapter trendingsJournalsAdapter;
    ImportantLinksAdapter importantLinksAdapter;
    TrendingUdaanVideosAdapter udaanVideosAdapter;
    String role;
    boolean skiplogin;
    SliderView sliderView;
    SliderAdapter sliderAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    //  DataModelLeft[] drawerItem;


    public static void drawerCheck() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mTextMessage = findViewById(R.id.message);
        goyesno = findViewById(R.id.goyesno);
        mainNotification = findViewById(R.id.notifcationmain);
        btnlogin = findViewById(R.id.btnlogin);
        llregUser = findViewById(R.id.llregUser);
        imgsearch = findViewById(R.id.imgsearch);
        toggle = findViewById(R.id.toggle);
        toolbartxt = findViewById(R.id.toolbartxt);


        /// mTitle = mDrawerTitle = getTitle();
        // mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        llmain = findViewById(R.id.llmain);
        mDrawerList = findViewById(R.id.left_drawer);
        uprofile = findViewById(R.id.uprofile);
        uname = findViewById(R.id.uname);
        textView = findViewById(R.id.textView);
        txttotalcount = findViewById(R.id.txttotalcount);

        rvViewAll = findViewById(R.id.rvViewAll);
        tebooksViewAll = findViewById(R.id.tebooksViewAll);
        JournalsViewAll = findViewById(R.id.JournalsViewAll);
        VideosViewAll = findViewById(R.id.VideosViewAll);
        importantlinkViewAll = findViewById(R.id.importantlinkViewAll);
        udaanlinkViewAll = findViewById(R.id.udaanlinkViewAll);

        txtrole = findViewById(R.id.txtrole);
        txtwelcome = findViewById(R.id.txtwelcome);
        profile_image = findViewById(R.id.profile_image);


        setupToolbar();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(MainActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getHomePageDataMethod(MainActivity.this, MainActivity.this, llmain, mSwipeRefreshLayout, MainActivity.this);

                } else {

                    Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                shuffle();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

       // calling api


            if (GlobalClass.isNetworkConnected(MainActivity.this)) {
                WebAPiCall webapiCall = new WebAPiCall();
                webapiCall.getHomePageDataMethod(MainActivity.this, MainActivity.this, llmain, mSwipeRefreshLayout, this);

            } else {

                Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
            }




        sliderView = findViewById(R.id.imageSlider);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        rvTrandingsEbooks = (RecyclerView) findViewById(R.id.rvTrandingsEbooks);
        rvJournals = (RecyclerView) findViewById(R.id.rvJournals);
        rvVideos = (RecyclerView) findViewById(R.id.rvVideos);
        rvimportantlink = (RecyclerView) findViewById(R.id.rvimportantlink);
        rvudaan = (RecyclerView) findViewById(R.id.rvudaan);


        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(videocall,
                new IntentFilter("videocall"));

    }


    public void shuffle() {
        sliderItemList.clear();
        sliderAdapter.notifyDataSetChanged();

        //  renewItems();

    }

    @Override
    public void initData() {
        role = CSPreferences.readString(this, "AccountType");


        try {
            skiplogin = CSPreferences.getBoolean(this, "skiplogin");
            if (skiplogin) {

                uprofile.setClickable(false);
                goyesno.setVisibility(View.GONE);
                btnlogin.setVisibility(View.VISIBLE);
                mainNotification.setVisibility(View.INVISIBLE);

            } else {
                uprofile.setClickable(true);
                goyesno.setVisibility(View.VISIBLE);
                btnlogin.setVisibility(View.INVISIBLE);
                mainNotification.setVisibility(View.VISIBLE);
            }

            dataModelLeftList = new ArrayList<DataModelLeftNew>();
            dataModelLeftList.clear();

            DataModelLeftNew publiclib = new DataModelLeftNew(R.drawable.ic_baseline_library_books_24, "Public Library", 0);
            dataModelLeftList.add(publiclib);

            DataModelLeftNew ebooks = new DataModelLeftNew(R.drawable.ic_baseline_book_online_24, "E-Books", 1);
            dataModelLeftList.add(ebooks);

            DataModelLeftNew journals = new DataModelLeftNew(R.drawable.ic_baseline_account_balance_wallet_24, "journals", 2);
            dataModelLeftList.add(journals);

            DataModelLeftNew Videos = new DataModelLeftNew(R.drawable.ic_baseline_video_library_24, "Videos", 3);
            dataModelLeftList.add(Videos);
//Competitive Exam
//People's Collection

            DataModelLeftNew DivyaangCorner = new DataModelLeftNew(R.mipmap.divyandcorner, "Divyaang Corner", 31);
            dataModelLeftList.add(DivyaangCorner);

            DataModelLeftNew Competitive = new DataModelLeftNew(R.drawable.ic_baseline_library_books_24, "Competitive Exam", 32);
            dataModelLeftList.add(Competitive);


            if (skiplogin) {


            } else {

//                DataModelLeftNew PeopleCollection = new DataModelLeftNew(R.drawable.ic_baseline_library_books_24, "People's Collection", 33);
//                dataModelLeftList.add(PeopleCollection);

            }


            DataModelLeftNew UdaanUrl = new DataModelLeftNew(R.drawable.ic_baseline_library_books_24, "Udaan Mental Health Counseling", 30);
            dataModelLeftList.add(UdaanUrl);

            if (skiplogin) {


            } else {

                DataModelLeftNew Notification = new DataModelLeftNew(R.drawable.notifications, "Notification", 7);
                dataModelLeftList.add(Notification);

            }

            DataModelLeftNew OurOthersApps = new DataModelLeftNew(R.drawable.ic_baseline_apps_24, "Our Others Apps", 4);
            dataModelLeftList.add(OurOthersApps);

            DataModelLeftNew RateApp = new DataModelLeftNew(R.drawable.rate_review, "Rate App", 5);
            dataModelLeftList.add(RateApp);

            DataModelLeftNew ShareApp = new DataModelLeftNew(R.drawable.share, "Share App", 6);
            dataModelLeftList.add(ShareApp);


            if (skiplogin) {


                DataModelLeftNew LoginSignup = new DataModelLeftNew(R.drawable.personwhite, "Login/Signup", 8);
                dataModelLeftList.add(LoginSignup);


            } else {

                DataModelLeftNew Logout = new DataModelLeftNew(R.drawable.ic_baseline_exit_to_app_24, "Logout", 8);
                dataModelLeftList.add(Logout);


            }

/*
            drawerItem = new DataModelLeft[9];


            drawerItem[0] = new DataModelLeft(R.drawable.ic_baseline_library_books_24, "Public Library");
            drawerItem[1] = new DataModelLeft(R.drawable.ic_baseline_book_online_24, "E-Books");
            drawerItem[2] = new DataModelLeft(R.drawable.ic_baseline_account_balance_wallet_24, "journals");
            drawerItem[3] = new DataModelLeft(R.drawable.ic_baseline_video_library_24, "Videos");
            drawerItem[4] = new DataModelLeft(R.drawable.ic_baseline_apps_24, "Our Others Apps");
            drawerItem[5] = new DataModelLeft(R.drawable.rate_review, "Rate App");
            drawerItem[6] = new DataModelLeft(R.drawable.share, "Share App");
            drawerItem[7] = new DataModelLeft(R.drawable.notifications, "Notification");
            if (skiplogin) {
                drawerItem[8] = new DataModelLeft(R.drawable.ic_baseline_exit_to_app_24, "Login/Signup");

            } else {
                drawerItem[8] = new DataModelLeft(R.drawable.ic_baseline_exit_to_app_24, "Logout");

            }*/


            DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, dataModelLeftList);
            mDrawerList.setAdapter(adapter);
            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
            mDrawerLayout = findViewById(R.id.drawer_layout);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            setupDrawerToggle();


            if (CSPreferences.getBoolean(this, "firstTimelogin")) {

                GlobalClass.dailogsuccess(this, "Login Successfull.", "Welcome to Digital Library,Haryana.");
                CSPreferences.putBolean(this, "firstTimelogin", false);
                mDrawerLayout.openDrawer(GravityCompat.START);


            } else {

                mDrawerLayout.closeDrawers();

            }


            uname.setText(CSPreferences.readString(this, "PhoneNo"));
            // textView.setText(CSPreferences.readString(this, "User_Email"));
            textView.setText(CSPreferences.readString(this, "Email"));
            toolbartxt.setText(CSPreferences.readString(this, "User_Name"));
            txtrole.setText(CSPreferences.readString(this, "AccountType"));
            role = CSPreferences.readString(this, "AccountType");
            txtwelcome.setText("Welcome, " + CSPreferences.readString(this, "User_Name"));


            Glide.with(MainActivity.this)
                    .load(CSPreferences.readString(MainActivity.this, "pic").trim()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(140, 140) // resizing
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);


        } catch (Exception e) {

        }


        mAppUpdateManager = AppUpdateManagerFactory.create(this);

        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/)) {

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.IMMEDIATE /*AppUpdateType.IMMEDIATE*/, MainActivity.this, RC_APP_UPDATE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                popupSnackbarForCompleteUpdate();
            } else {
                Log.e(TAG, "checkForAppUpdateAvailability: something else");
            }
        });


    }

    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED) {
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED) {
                        if (mAppUpdateManager != null) {
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        Log.i(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_APP_UPDATE) {
            if (resultCode != RESULT_OK) {
                Log.e(TAG, "onActivityResult: app download failed");
            }
        }
    }


    private void popupSnackbarForCompleteUpdate() {

        Snackbar snackbar =
                Snackbar.make(
                        mDrawerLayout,
                        "New app is ready!",
                        Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("Install", view -> {
            if (mAppUpdateManager != null) {
                mAppUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void initListeners() {


        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchintent = new Intent(MainActivity.this, SearchActivity.class);
                searchintent.putExtra("typeId", "2");
                searchintent.putExtra("itemType", "Book");
                searchintent.putExtra("titleOfPage", "Search ");
                startActivity(searchintent);

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drawerCheck();
            }
        });

        if (skiplogin) {
            uprofile.setClickable(false);
            uprofile.setEnabled(false);
            goyesno.setVisibility(View.GONE);

        } else {
            uprofile.setClickable(true);
            uprofile.setEnabled(true);
            goyesno.setVisibility(View.VISIBLE);
        }

        uprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mDrawerLayout.closeDrawers();
                Intent notification = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(notification);


            }
        });

        //   }


        mainNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDrawerLayout.closeDrawers();
                Intent notification = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(notification);

            }
        });


        /*rvViewAll tebooksViewAll JournalsViewAll VideosViewAll*/
        rvViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "1");
                rvViewAll.putExtra("itemType", "Book");
                rvViewAll.putExtra("titleOfPage", "All Digital Libraries");
                startActivity(rvViewAll);
            }
        });

        tebooksViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "2");
                rvViewAll.putExtra("itemType", "Book");
                rvViewAll.putExtra("titleOfPage", "All Ebooks");
                startActivity(rvViewAll);
            }
        });

        JournalsViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "3");
                rvViewAll.putExtra("itemType", "Journal");
                rvViewAll.putExtra("titleOfPage", "All Journals");
                startActivity(rvViewAll);
            }
        });

        VideosViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "4");
                rvViewAll.putExtra("itemType", "Video");
                rvViewAll.putExtra("titleOfPage", "All Videos");
                startActivity(rvViewAll);
            }
        });

        udaanlinkViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "5");
                rvViewAll.putExtra("itemType", "udaan");
                // rvViewAll.putExtra("titleOfPage", "Udaan Career counseling");
                rvViewAll.putExtra("titleOfPage", "Udaan Mental Health Counseling");
                startActivity(rvViewAll);
            }
        });

        importantlinkViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rvViewAll = new Intent(MainActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "6");
                rvViewAll.putExtra("itemType", "importantlink");
                rvViewAll.putExtra("titleOfPage", "Important Links");
                startActivity(rvViewAll);
            }
        });


    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (dataModelLeftList.get(position).id) {
            case 0:
                mDrawerLayout.closeDrawers();
                Intent AllothersDlib = new Intent(MainActivity.this, ViewAllDataActivity.class);
                AllothersDlib.putExtra("typeId", "1");
                AllothersDlib.putExtra("titleOfPage", "All Digital Libraries");
                startActivity(AllothersDlib);
                break;

            case 1:
                mDrawerLayout.closeDrawers();
                Intent Allebooks = new Intent(MainActivity.this, ViewAllDataActivity.class);
                Allebooks.putExtra("typeId", "2");
                Allebooks.putExtra("itemType", "Book");
                Allebooks.putExtra("titleOfPage", "All E-books");
                startActivity(Allebooks);
                break;

            case 2:
                mDrawerLayout.closeDrawers();
                Intent AllJournals = new Intent(MainActivity.this, ViewAllDataActivity.class);
                AllJournals.putExtra("typeId", "3");
                AllJournals.putExtra("itemType", "Journal");
                AllJournals.putExtra("titleOfPage", "All Journals");
                startActivity(AllJournals);
                break;

            case 3:
                mDrawerLayout.closeDrawers();
                Intent AllVideos = new Intent(MainActivity.this, ViewAllDataActivity.class);
                AllVideos.putExtra("typeId", "4");
                AllVideos.putExtra("itemType", "Video");
                AllVideos.putExtra("titleOfPage", "All Videos");
                startActivity(AllVideos);
                break;

            case 30:
                mDrawerLayout.closeDrawers();

                /*  String result = extras.getString("title");
                webViewUrl = extras.getString("bookurl");
*/
                Intent touchbase = new Intent(MainActivity.this, OpenBooksActivity.class);
                touchbase.putExtra("title", "Udaan Mental Health Counseling");
                touchbase.putExtra("bookurl", "https://touchbase.live/");
                startActivity(touchbase);
                break;


            case 31:
                mDrawerLayout.closeDrawers();

                /*  String result = extras.getString("title");
                webViewUrl = extras.getString("bookurl");
*/
                Intent DivyaangCorner = new Intent(MainActivity.this, DivyaangActivity.class);
                DivyaangCorner.putExtra("titleOfPage", "Divyaang Corner");
                DivyaangCorner.putExtra("typeId", "9");

                DivyaangCorner.putExtra("bookurl", "https://touchbase.live/");

                startActivity(DivyaangCorner);
                break;

            case 32:
                mDrawerLayout.closeDrawers();

                /*  String result = extras.getString("title");
                webViewUrl = extras.getString("bookurl");
*/
                Intent CompetitiveExam = new Intent(MainActivity.this, CompetitiveExamActivity.class);
                CompetitiveExam.putExtra("typeId", "8");
                CompetitiveExam.putExtra("itemType", "CompetitiveExam");
                CompetitiveExam.putExtra("titleOfPage", "Competitive Exam");
                startActivity(CompetitiveExam);
                break;

            case 33:
                mDrawerLayout.closeDrawers();

                /*  String result = extras.getString("title");
                webViewUrl = extras.getString("bookurl");
*/
                GlobalClass.showtost(MainActivity.this, "Coming Soon...");
                break;
               /* Intent PeopleCorner = new Intent(MainActivity.this, PeopleCornerActivity.class);
                PeopleCorner.putExtra("typeId", "10");
                PeopleCorner.putExtra("titleOfPage", "People Corner");
                startActivity(PeopleCorner);
                break;
*/

            case 4:

                mDrawerLayout.closeDrawers();

                /*  String result = extras.getString("title");
                webViewUrl = extras.getString("bookurl");
*/
                Intent othersApps = new Intent(MainActivity.this, OpenBooksActivity.class);
                othersApps.putExtra("title", "Our Other Apps");
                othersApps.putExtra("bookurl", "https://play.google.com/store/apps/details?id=shikshasahyog.hry.edu.govt");
                startActivity(othersApps);
                break;

            case 5:
                mDrawerLayout.closeDrawers();
                // Intent intent = new Intent(this, RoomDBMainActivity.class);
                // startActivity(intent);
                // fragment = new My_chanlel();

                rateApp();
                break;
            case 6:

                mDrawerLayout.closeDrawers();

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Reach to more user");
                String app_url = "https://play.google.com/store/apps/details?id=shikshasahyog.hry.edu.govt";
                shareIntent.putExtra(Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));

                break;
            case 7:

                mDrawerLayout.closeDrawers();
                Intent notification = new Intent(this, NotificationsActivity.class);
                startActivity(notification);
                break;
            case 8:


                if (skiplogin) {
                    mDrawerLayout.closeDrawers();
                    Intent welcomeintent = new Intent(this, WelcomeActivity.class);
                    startActivity(welcomeintent);
                    finish();

                } else {
                    mDrawerLayout.closeDrawers();
                    logout();

                }

                break;


            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            // setTitle(mNavigationDrawerItemTitles[position]);
            // mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.closeDrawers();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void rateApp() {
        try {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        } catch (ActivityNotFoundException e) {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21) {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        } else {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        //toolbar.setTitle(mTitle);
        //toolbartxt.setText(mTitle.toString());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        // hide the title bar  0
        setSupportActionBar(toolbar);
        (getSupportActionBar()).setDisplayShowHomeEnabled(false);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void GetbannersData(List<HomePageResponse.Banner> list) {


        sliderItemList.clear();
        sliderAdapter = new SliderAdapter(this);
        sliderItemList.addAll((Collection<? extends HomePageResponse.Banner>) list);
        sliderAdapter.renewItems(list);


        //renewItems();

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        // sliderView.setIndicatorSelectedColor(Color.WHITE);
        // sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public void GetOtherDigitalTrendingLibraryData(List<HomePageResponse.OtherDigitalTrendingLibrary> list) {

        otherDigitalTrendingLibraries.clear();
        otherDigitalTrendingLibraries.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        adaptermain = new OthesDigitalLibAdapter(this, otherDigitalTrendingLibraries, this);
        recyclerView.setAdapter(adaptermain);
        adaptermain.notifyDataSetChanged();

    }

    @Override
    public void GetTrendingVideosData(List<HomePageResponse.TrendingVideo> list) {
        trendingVideos.clear();
        trendingVideos.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvVideos.setLayoutManager(manager);
        trendingsVideosAdapter = new TrendingsVideosAdapter(this, trendingVideos, this);
        rvVideos.setAdapter(trendingsVideosAdapter);
        trendingsVideosAdapter.notifyDataSetChanged();

    }

    @Override
    public void GetTrendingeBookData(List<HomePageResponse.TrendingeBook> list) {

        trendingeBooks.clear();
        trendingeBooks.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvTrandingsEbooks.setLayoutManager(manager);
        trendingsEBooksAdapter = new TrendingsEBooksAdapter(this, trendingeBooks, this);
        rvTrandingsEbooks.setAdapter(trendingsEBooksAdapter);
        adaptermain.notifyDataSetChanged();

    }

    @Override
    public void GetTrendingJournalData(List<HomePageResponse.TrendingJournal> list) {
        trendingJournals.clear();
        trendingJournals.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvJournals.setLayoutManager(manager);
        trendingsJournalsAdapter = new TrendingsJournalsAdapter(this, trendingJournals, this);
        rvJournals.setAdapter(trendingsJournalsAdapter);
        trendingsJournalsAdapter.notifyDataSetChanged();
    }

    @Override
    public void GetImportantsLinkData(List<HomePageResponse.ImportantLink> list) {
        importantLinkList.clear();
        importantLinkList.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvimportantlink.setLayoutManager(manager);
        importantLinksAdapter = new ImportantLinksAdapter(this, importantLinkList, this);
        rvimportantlink.setAdapter(importantLinksAdapter);
        importantLinksAdapter.notifyDataSetChanged();

    }

    @Override
    public void GetUdaanVideosData(List<HomePageResponse.TrendingUdaanVideo> list) {
        trendingUdaanVideos.clear();
        trendingUdaanVideos.addAll(list);
        LinearLayoutManager manager = new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false);
        rvudaan.setLayoutManager(manager);
        udaanVideosAdapter = new TrendingUdaanVideosAdapter(this, trendingUdaanVideos, this);
        rvudaan.setAdapter(udaanVideosAdapter);
        udaanVideosAdapter.notifyDataSetChanged();
    }

    @Override
    public void GetRegisteredUserData(List<HomePageResponse.RegisteredUser> list) {
        if (role.equalsIgnoreCase("Admin")) {

            llregUser.setVisibility(View.VISIBLE);

            txttotalcount.setText(list.get(0).getTotalCount());
        }


    }

    @Override
    public void onItemClick(HomePageResponse.TrendingeBook item, int currposition) {

//           itemType = extras.getString("itemType");
//                itemid = extras.getString("itemid");

        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {

            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "2");
            certificate.putExtra("bookurl", item.getBookIframeUrl());
            certificate.putExtra("title", item.getBookTitle());
            certificate.putExtra("itemType", "Book");
            certificate.putExtra("itemid", item.getBookId());
            startActivity(certificate);

        }


    }

    @Override
    public void onItemClick(HomePageResponse.TrendingVideo item, int currposition) {

        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {

            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "4");
            certificate.putExtra("bookurl", item.getVideoIframeUrl());
            certificate.putExtra("title", item.getVideoTitle());
            certificate.putExtra("itemType", "Video");
            certificate.putExtra("itemid", item.getVideoId());
            startActivity(certificate);

        }

    }

    @Override
    public void onItemClick(HomePageResponse.TrendingJournal item, int currposition) {


        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {
            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "3");
            certificate.putExtra("bookurl", item.getBookIframeUrl());
            certificate.putExtra("title", item.getBookTitle());
            certificate.putExtra("itemType", "Journal");
            certificate.putExtra("itemid", item.getBookId());
            startActivity(certificate);

        }


    }


    @Override
    public void onItemClick(HomePageResponse.OtherDigitalTrendingLibrary item, int currposition) {


        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {
            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "1");
            certificate.putExtra("bookurl", item.getLibraryUrl());
            certificate.putExtra("title", item.getLibraryName());
            certificate.putExtra("itemType", "Book");
            certificate.putExtra("itemid", item.getLibraryId());
            startActivity(certificate);

        }


    }

    @Override
    public void onItemClick(HomePageResponse.ImportantLink item, int currposition) {
        if (item.getUrl() != null) {

            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "6");
            certificate.putExtra("itemType", "importantlink");
            certificate.putExtra("bookurl", item.getUrl());
            certificate.putExtra("title", item.getTitle());
            certificate.putExtra("itemid", item.getId());
            startActivity(certificate);
        } else {

            GlobalClass.dailogError(MainActivity.this, "No Url Found", "NO any url found to redirect to next page.");
        }
    }

    @Override
    public void onItemClick(HomePageResponse.TrendingUdaanVideo item, int currposition) {
        if (skiplogin) {
            Intent welcomeintent = new Intent(this, WelcomeActivity.class);
            startActivity(welcomeintent);

        } else {

            Intent certificate = new Intent(this, OpenBooksActivity.class);
            certificate.putExtra("typeId", "5");
            certificate.putExtra("bookurl", item.getVideoIframeUrl());
            certificate.putExtra("title", item.getVideoTitle());
            certificate.putExtra("itemType", "udaan");
            certificate.putExtra("itemid", item.getVideoId());
            startActivity(certificate);

        }
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

    }

    void logout() {
        mDrawerLayout.closeDrawers();
        new FancyGifDialog.Builder(this)
                .setTitle("Log out")
                .setMessage("Are you sure logout from Digital Library haryana?")
                .setNegativeBtnText("No")
                .setPositiveBtnBackground("#4CAF50")
                .setPositiveBtnText("YES")
                .setNegativeBtnBackground("#FF3D00")
                .setGifResource(R.drawable.logoutgif)   //Pass your Gif here
                .isCancellable(false)
                .OnPositiveClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {

                      /*  if (GlobalClass.isNetworkConnected(MainActivity.this)) {
                            logout(MainActivity.this, CSPreferences.readString(MainActivity.this, "tooken"), CSPreferences.readString(MainActivity.this, "tooken"));

                        } else {

                            Toast.makeText(MainActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }*/

                        Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                        mDrawerLayout.closeDrawers();
                        CSPreferences.clearPref(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        //onBackPressed();
                    }
                })
                .OnNegativeClicked(new FancyGifDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
    }

    // LogOut APi................

  /*  public void logout(final Context context, String auth, String tooken) {
        dailogshow(context);
        Call<Logout_model> userpost_responseCall = ApiClient.getClient().app_logout(GlobalClass.apikey, "Bearer " + auth, tooken);
        userpost_responseCall.enqueue(new Callback<Logout_model>() {
            @Override
            public void onResponse(Call<Logout_model> call, Response<Logout_model> response) {
                dailoghide(context);

                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                    CSPreferences.clearPref(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Logout_model> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }*/

    ProgressDialog pd;

    public void dailogshow(Context context) {
        pd = new ProgressDialog(context);
        pd.setMessage("loading...");
        pd.setCancelable(false);
        pd.show();
    }

    public void dailoghide(Context context) {
        pd.dismiss();
    }

    private final BroadcastReceiver videocall = new BroadcastReceiver() {


        @Override
        public void onReceive(Context context, Intent intent) {


            Toast.makeText(context, "Sorry For Inconvenience! All Driver is Busy Right Now. Please Book After a few moment. ", Toast.LENGTH_SHORT).show();

            try {


            } catch (Exception e) {

            }


        }
    };


}
