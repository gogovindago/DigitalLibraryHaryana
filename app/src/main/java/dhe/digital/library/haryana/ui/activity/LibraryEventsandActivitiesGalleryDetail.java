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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.LibraryEventsandActivitiesLibIdGalleryDetailAdapter;
import dhe.digital.library.haryana.allinterface.GetLibraryEventsandActivitiesByLibIdGallerydetailData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityLibraryeventsandactivitiesgalleryBinding;
import dhe.digital.library.haryana.models.LibraryEventsActivitieAlbumDetailResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;

public class LibraryEventsandActivitiesGalleryDetail extends BaseActivity implements GetLibraryEventsandActivitiesByLibIdGallerydetailData_interface, LibraryEventsandActivitiesLibIdGalleryDetailAdapter.ItemListener {
    LinearLayoutManager manager;


    private ActivityLibraryeventsandactivitiesgalleryBinding binding;
    String typeId, itemType, titleOfPage;

    LibraryEventsandActivitiesLibIdGalleryDetailAdapter adaptermain;
    private List<LibraryEventsActivitieAlbumDetailResponse.Datum> arrayList = new ArrayList<LibraryEventsActivitieAlbumDetailResponse.Datum>();
    private String title, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_libraryeventsandactivitiesgallery);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getString("itemid");
                date = extras.getString("date");
                title = extras.getString("title");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Events & Activities Gallery of </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Events & Activities Gallery of " + titleOfPage + "</h6>"));
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(LibraryEventsandActivitiesGalleryDetail.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getLibraryEventsActivitieAlbumDetailDataMethod(LibraryEventsandActivitiesGalleryDetail.this, LibraryEventsandActivitiesGalleryDetail.this,binding.txtnodatamsg, title, date, typeId, binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivitiesGalleryDetail.this);

        } else {

            Toast.makeText(LibraryEventsandActivitiesGalleryDetail.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(LibraryEventsandActivitiesGalleryDetail.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getLibraryEventsActivitieAlbumDetailDataMethod(LibraryEventsandActivitiesGalleryDetail.this, LibraryEventsandActivitiesGalleryDetail.this, binding.txtnodatamsg, title, date, typeId, binding.rrmain, binding.simpleSwipeRefreshLayout, LibraryEventsandActivitiesGalleryDetail.this);

                } else {

                    Toast.makeText(LibraryEventsandActivitiesGalleryDetail.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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
    public void onItemClick(LibraryEventsActivitieAlbumDetailResponse.Datum item, int currposition) {

    }


    @Override
    public void onImageItemClick(LibraryEventsActivitieAlbumDetailResponse.Datum item, int currposition, String type) {


      /*  if (type.equalsIgnoreCase("bookdetail")) {

            Intent intent = new Intent(this, BooksDeatilActivity.class);
            intent.putExtra("bookserial_Id", item.getEventTitle());
            startActivity(intent);


        } else {
        }*/


        openDialog(item);

    }

    public void openDialog(LibraryEventsActivitieAlbumDetailResponse.Datum item) {


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

    @Override
    public void GetLibraryEventsandActivitiesByLibIdGallerydetailData(List<LibraryEventsActivitieAlbumDetailResponse.Datum> list) {

        binding.txteventsdetail.setText(list.get(0).getEventDetails());


        arrayList.clear();
        arrayList.addAll(list);
       // manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        binding.recyclerView.setLayoutManager(manager);
        adaptermain = new LibraryEventsandActivitiesLibIdGalleryDetailAdapter(this, (ArrayList) arrayList, this);

        binding.recyclerView.setAdapter(adaptermain);

    }
}