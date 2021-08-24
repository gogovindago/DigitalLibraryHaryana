package dhe.digital.library.haryana.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.LibraryEventsandActivitiesLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryEventsandActivitiesByLibIdData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryeventsandactivitiesBinding;
import dhe.digital.library.haryana.models.BookRecordByLibIdRequest;
import dhe.digital.library.haryana.models.LibraryEventsActivitieAlbumResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryEventsandActivities extends BaseActivity implements GetLibraryEventsandActivitiesByLibIdData_interface, LibraryEventsandActivitiesLibIdAdapter.ItemListener {
    LinearLayoutManager manager;


    private ActivityLibraryeventsandactivitiesBinding binding;
    String typeId, itemType, titleOfPage;

    LibraryEventsandActivitiesLibIdAdapter adaptermain;
    private List<LibraryEventsActivitieAlbumResponse.Datum> arrayList = new ArrayList<LibraryEventsActivitieAlbumResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_libraryeventsandactivities);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                typeId = String.valueOf(extras.getInt("itemid"));
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Events & Activities in </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Events & Activities in " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(LibraryEventsandActivities.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getLibraryEventsActivitiesLibIdDataMethod(LibraryEventsandActivities.this, LibraryEventsandActivities.this, typeId, binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivities.this);

        } else {

            Toast.makeText(LibraryEventsandActivities.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryEventsandActivities.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getLibraryEventsActivitiesLibIdDataMethod(LibraryEventsandActivities.this, LibraryEventsandActivities.this, typeId, binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivities.this);

                } else {

                    Toast.makeText(LibraryEventsandActivities.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public void initData() {
        // binding.toolbar.tvToolbarTitle.setText("Books Available");
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

    @Override
    public void GetLibraryEventsandActivitiesByLibIdData(List<LibraryEventsActivitieAlbumResponse.Datum> list) {


        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new LibraryEventsandActivitiesLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);


    }

    @Override
    public void onItemClick(LibraryEventsActivitieAlbumResponse.Datum item, int currposition) {




            Intent intent = new Intent(this, BooksDeatilActivity.class);
            intent.putExtra("bookserial_Id", item.getEventTitle());
            startActivity(intent);







    }


    @Override
    public void onImageItemClick(LibraryEventsActivitieAlbumResponse.Datum item, int currposition, String type) {


      /*  if (type.equalsIgnoreCase("bookdetail")) {

            Intent intent = new Intent(this, BooksDeatilActivity.class);
            intent.putExtra("bookserial_Id", item.getEventTitle());
            startActivity(intent);


        } else {
        }*/


        openDialog(item);

    }

    public void openDialog(LibraryEventsActivitieAlbumResponse.Datum item) {



        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light); // Context, this, etc.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_demo);

        //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageView dialog_img = dialog.findViewById(R.id.dialog_img);

       /* ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getEventImage()))
                .setResizeOptions(new ResizeOptions(150, 150))
                .build();
        dialog_img.setController(

                Fresco.newDraweeControllerBuilder()
                        .setOldController(dialog_img.getController())
                        .setImageRequest(request)
                        .build());*/

         //dialog_img.setImageURI(Uri.parse(item.getEventImage()));

        Glide.with(this).load(item.getEventImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dialog_img);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialog_ok);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

}