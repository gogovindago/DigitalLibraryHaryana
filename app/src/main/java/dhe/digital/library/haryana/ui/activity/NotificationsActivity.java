package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.RvNotificationAdapter;


import dhe.digital.library.haryana.databinding.ActivityNotificationsBinding;
import dhe.digital.library.haryana.models.DataModel;
import dhe.digital.library.haryana.utility.BaseActivity;

public class NotificationsActivity extends BaseActivity implements RvNotificationAdapter.ItemListener {

    ArrayList arrayList;
    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);


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

        RvNotificationAdapter adaptermain = new RvNotificationAdapter(this, arrayList, this);
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
    }

    @Override
    public void onItemClick(DataModel item, int currposition) {

        // Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
        Intent captureplant = new Intent(this, NotificationDetailActivity.class);
        captureplant.putExtra("titleOfPage", item.text);

        startActivity(captureplant);
    }


    @Override
    public void initData() {
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