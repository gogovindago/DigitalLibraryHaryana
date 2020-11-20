package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.databinding.ActivityDivyaangBinding;
import dhe.digital.library.haryana.utility.BaseActivity;

public class DivyaangActivity extends BaseActivity {

    ActivityDivyaangBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_divyaang);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_divyaang);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListeners() {
        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.llvisually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rvViewAll = new Intent(DivyaangActivity.this, ViewAllDataActivity.class);
                rvViewAll.putExtra("typeId", "7");
                rvViewAll.putExtra("itemType", "Book");
                rvViewAll.putExtra("titleOfPage", "Divyaan Corner");
                startActivity(rvViewAll);
            }
        });

        binding.llhearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}