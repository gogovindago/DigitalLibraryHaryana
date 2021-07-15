package dhe.digital.library.haryana.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.databinding.ActivityContactUsBinding;
import dhe.digital.library.haryana.utility.BaseActivity;

public class ContactUsActivity extends BaseActivity {
ActivityContactUsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);



    }

    @Override
    public void initData() {

        binding.toolbar.tvToolbarTitle.setText("Contact Us");

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