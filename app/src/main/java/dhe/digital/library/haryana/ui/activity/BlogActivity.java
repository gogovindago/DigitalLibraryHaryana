package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.databinding.ActivityBlogBinding;
import dhe.digital.library.haryana.utility.BaseActivity;

public class BlogActivity extends BaseActivity {

    Integer typeId;
    String titleOfPage, liburl;
    ActivityBlogBinding binding;

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
                liburl=part2;


                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Blog for:-"+titleOfPage);
                // binding.txtmsgcontactus.setText("Contact us to:-"+titleOfPage);



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setAllCaps(false);
        binding.toolbar.tvToolbarTitle.setText("Blog for:-"+titleOfPage);
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
                CreateBlogintent.putExtra("itemid",typeId);
                CreateBlogintent.putExtra("liburl",liburl);
                startActivity(CreateBlogintent);
            }
        });


    }
}