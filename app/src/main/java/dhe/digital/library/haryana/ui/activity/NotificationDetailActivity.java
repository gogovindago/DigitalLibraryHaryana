package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.databinding.ActivityNotificationDetailBinding;
import dhe.digital.library.haryana.utility.BaseActivity;

public class NotificationDetailActivity extends BaseActivity {
ActivityNotificationDetailBinding binding;

String titleOfPage,
    detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notification_detail);
    }

    @Override
    public void initData() {

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                detail = extras.getString("detail");
                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        binding.toolbar.notifcation.setVisibility(View.GONE);
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