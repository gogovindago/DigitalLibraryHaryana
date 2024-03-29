package dhe.digital.library.haryana.ui.activity;

import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.LibraryGalleryLibIdAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryGalleryData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryGalleryBinding;
import dhe.digital.library.haryana.models.LibraryEventsActivitieAlbumDetailResponse;
import dhe.digital.library.haryana.models.LibraryGalleryResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryGalleryActivity extends BaseActivity implements GetLibraryGalleryData_interface, LibraryGalleryLibIdAdapter.ItemListener {


    ActivityLibraryGalleryBinding binding;
    LinearLayoutManager manager;
    String typeId,  titleOfPage;

    LibraryGalleryLibIdAdapter adaptermain;
    private List<LibraryGalleryResponse.Datum> arrayList = new ArrayList<LibraryGalleryResponse.Datum>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_library_gallery);

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
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Gallery of </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Gallery of " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }




        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryGalleryActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.getLibraryGalleryDataMethod(LibraryGalleryActivity.this, LibraryGalleryActivity.this, binding.rrmain,typeId,  binding.simpleSwipeRefreshLayout, binding.txtnodatamsg, LibraryGalleryActivity.this);

                } else {

                    Toast.makeText(LibraryGalleryActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

        // calling api


        if (GlobalClass.isNetworkConnected(LibraryGalleryActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.getLibraryGalleryDataMethod(LibraryGalleryActivity.this, LibraryGalleryActivity.this, binding.rrmain,typeId,  binding.simpleSwipeRefreshLayout,binding.txtnodatamsg, LibraryGalleryActivity.this);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }
        
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

    }

    @Override
    public void GetLibraryGalleryData(List<LibraryGalleryResponse.Datum> list) {
        arrayList.clear();
        arrayList.addAll(list);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new LibraryGalleryLibIdAdapter(this, (ArrayList) arrayList, this);
        binding.recyclerView.setAdapter(adaptermain);

    }

    @Override
    public void onItemClick(LibraryGalleryResponse.Datum item, int currposition, String type) {

        openDialog(item);
    }
    public void openDialog(LibraryGalleryResponse.Datum item) {


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

        Glide.with(this).load(item.getImageURL())
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