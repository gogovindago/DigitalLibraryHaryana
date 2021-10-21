package dhe.digital.library.haryana.ui.helpdesk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.databinding.ActivityHelpdeskBinding;
import dhe.digital.library.haryana.ui.activity.OpenBooksActivity;
import dhe.digital.library.haryana.utility.BaseActivity;

public class HelpDeskActivity extends BaseActivity implements View.OnClickListener {
    ActivityHelpdeskBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_helpdesk);



        binding.toolbartxt2.setSelected(true);

        binding.fb.setOnClickListener(this);
        binding.twitter.setOnClickListener(this);
        binding.youtube.setOnClickListener(this);
        binding.instagram.setOnClickListener(this);


    }

    @Override
    public void initData() {

        binding.toolbartxt2.setText("                                       Help Desk                                      ");

    }

    @Override
    public void initListeners() {

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.fb:

                Intent fb = new Intent(this, OpenBooksActivity.class);
                fb.putExtra("title", "Dhe,haryana ");
                fb.putExtra("bookurl", "https://www.facebook.com/pg/highereduhry/posts/?ref=page_internal");
                startActivity(fb);

                break;
            case R.id.twitter:
                Intent twitter = new Intent(this, OpenBooksActivity.class);
                twitter.putExtra("title", "Dhe,haryana ");
                twitter.putExtra("bookurl", "https://twitter.com/highereduHRY");
                startActivity(twitter);

                break;
            case R.id.instagram:
                Intent instagram = new Intent(this, OpenBooksActivity.class);
                instagram.putExtra("title", "Dhe,haryana ");
                instagram.putExtra("bookurl", "https://www.instagram.com/accounts/login/");
                startActivity(instagram);

                break;

            case R.id.youtube:
                Intent youtube = new Intent(this, OpenBooksActivity.class);
                youtube.putExtra("title", "Dhe,haryana ");
                youtube.putExtra("bookurl", "https://www.youtube.com/channel/UClnGVAjgb084SMNIYD1NTxA/featured");
                startActivity(youtube);

                break;


        }


    }
}