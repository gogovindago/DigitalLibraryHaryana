package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.BlogListBYLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetBLogsByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBlogBinding;
import dhe.digital.library.haryana.models.BlogListResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class BlogActivity extends BaseActivity implements GetBLogsByLibIdData_interface, BlogListBYLibIdAdapter.ItemListener {
    private List<BlogListResponse.Datum> arrayList = new ArrayList<BlogListResponse.Datum>();
    Integer typeId;
    String titleOfPage, liburl;
    ActivityBlogBinding binding;
    LinearLayoutManager manager;
    BlogListBYLibIdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blog);
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                titleOfPage = extras.getString("title");
                typeId = extras.getInt("itemid");
                liburl = extras.getString("liburl");


                String string = liburl;
                String[] parts = string.split("//", 2);
                String part1 = parts[0]; // 004
                String part2 = parts[1]; // 034556-42
                liburl = part2;


                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Blog for:-" + titleOfPage);
                // binding.txtmsgcontactus.setText("Contact us to:-"+titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(BlogActivity.this)) {


            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getBlogListByLibIdDataMethod(BlogActivity.this, BlogActivity.this, String.valueOf(typeId), binding.rvblog, binding.txtnodatamsg, BlogActivity.this);

        } else {

            Toast.makeText(BlogActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


       /* binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(BlogActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getBookRecordByLibIdDataMethod(BlogActivity.this, BlogActivity.this, typeId, binding.rrmain,binding.txtnodatamsg, binding.simpleSwipeRefreshLayout, BlogActivity.this);

                } else {

                    Toast.makeText(BlogActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });
        */


    }


    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setAllCaps(false);
        binding.toolbar.tvToolbarTitle.setText("Blog for:-" + titleOfPage);
    }

    @Override
    public void initListeners() {


        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.txtCreateBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent CreateBlogintent = new Intent(BlogActivity.this, CreateBlogActivity.class);
                CreateBlogintent.putExtra("title", titleOfPage);
                CreateBlogintent.putExtra("itemid", typeId);
                CreateBlogintent.putExtra("liburl", liburl);
                startActivity(CreateBlogintent);
            }
        });


    }

    @Override
    public void GetBlogsByLibIdData(List<BlogListResponse.Datum> list) {

        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvblog.setLayoutManager(manager);
        adapter = new BlogListBYLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.rvblog.setAdapter(adapter);

    }

    @Override
    public void onItemClick(BlogListResponse.Datum item, int currposition, String type) {



    }
}