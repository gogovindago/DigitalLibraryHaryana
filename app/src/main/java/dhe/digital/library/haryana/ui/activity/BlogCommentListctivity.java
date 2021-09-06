package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.CommentsListBlogIdAdapter;
import dhe.digital.library.haryana.allinterface.BlogCommentsData_interface;
import dhe.digital.library.haryana.allinterface.CommentsOnBlogData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBlogCommentListctivityBinding;
import dhe.digital.library.haryana.models.BlogCommentsListResponse;
import dhe.digital.library.haryana.models.CommentsOnBlogRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;

public class BlogCommentListctivity extends BaseActivity implements BlogCommentsData_interface, CommentsListBlogIdAdapter.ItemListener, CommentsOnBlogData_interface {

    private List<BlogCommentsListResponse.Datum> arrayList = new ArrayList<BlogCommentsListResponse.Datum>();
    Integer typeId;
    String titleOfPage, BodyOfComment, User_Name, Commenter_PhoneNo, liburl, CreatedBy, CreatedDate, ImageURL, BlogBody, LibraryId, IsActive, BlogTitle;
    ActivityBlogCommentListctivityBinding binding;
    LinearLayoutManager manager;
    CommentsListBlogIdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_blog_comment_listctivity);

        try {


            User_Name = CSPreferences.readString(this, "User_Name");
            Commenter_PhoneNo = CSPreferences.readString(this, "PhoneNo");


            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                titleOfPage = extras.getString("title");
                typeId = Integer.valueOf(extras.getString("itemid"));

                liburl = extras.getString("CreatedBy");
                CreatedBy = extras.getString("CreatedBy");
                CreatedDate = extras.getString("CreatedDate");
                ImageURL = extras.getString("ImageURL");
                BlogBody = extras.getString("BlogBody");
                LibraryId = extras.getString("LibraryId");
                IsActive = extras.getString("IsActive");
                BlogTitle = extras.getString("BlogTitle");


                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Blog for:-" + titleOfPage);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                    binding.textViewTitle.setText(Html.fromHtml("<strong>Blog Title:-<br> </strong>" + BlogTitle, Html.FROM_HTML_MODE_COMPACT));
                    binding.txtblogcreatedDate.setText(Html.fromHtml("<strong>Created Date:-<br> </strong>" + CreatedDate, Html.FROM_HTML_MODE_COMPACT));
                    binding.textViewAuthor.setText(Html.fromHtml("<strong>Created By:-<br> </strong>" + CreatedBy, Html.FROM_HTML_MODE_COMPACT));
                    binding.txtBlogBody.setText(Html.fromHtml("<strong>Blog:-<br> </strong>" + BlogBody, Html.FROM_HTML_MODE_COMPACT));


                    binding.txtblogstatus.setText(IsActive);
                    Glide.with(this)
                            .load(ImageURL) // image url
                            .placeholder(R.drawable.nophoto) // any placeholder to load at start
                            .error(R.drawable.ic_baseline_image_not_supported_24)  // any image in case of error
                            .override(50, 50) // resizing
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding.imageView);


                } else {

                    binding.textViewTitle.setText(Html.fromHtml("<strong> Book Title:-<br></strong>" + BlogTitle));
                    binding.txtblogcreatedDate.setText(Html.fromHtml("<strong> Created Date:-<br></strong>" + CreatedDate));
                    binding.textViewAuthor.setText(Html.fromHtml("<strong> Created By:-<br></strong>" + CreatedBy));
                    binding.txtBlogBody.setText(Html.fromHtml("<strong> Blog:-<br></strong>" + BlogBody));
                    binding.txtblogstatus.setText(IsActive);


                    Glide.with(this)
                            .load(ImageURL) // image url
                            .placeholder(R.drawable.nophoto)// any placeholder to load at start
                            .error(R.drawable.ic_baseline_image_not_supported_24)  // any image in case of error
                            .override(50, 50) // resizing
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(binding.imageView);

                }









            /*
               // binding.txtserialno.setText(LibraryId);
                binding.textViewTitle.setText(BlogTitle);
                binding.txtBlogBody.setText(BlogBody);
                binding.textViewAuthor.setText(CreatedBy);
                binding.txtblogcreatedDate.setText(CreatedDate);
                binding.txtblogstatus.setText(IsActive);
                Glide.with(this)
                        .load(ImageURL) // image url
                        .placeholder(R.drawable.nophoto) // any placeholder to load at start
                        .error(R.drawable.ic_baseline_image_not_supported_24)  // any image in case of error
                        .override(50, 50) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.imageView);
*/

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(BlogCommentListctivity.this)) {


            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getCommentsByBlogsIdMethod(BlogCommentListctivity.this, BlogCommentListctivity.this, String.valueOf(typeId), binding.rvblog, BlogCommentListctivity.this);

        } else {

            Toast.makeText(BlogCommentListctivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


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


        binding.buttonGchatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BodyOfComment = binding.editGchatMessage.getText().toString().trim();
                if (BodyOfComment.isEmpty()) {
                    // If name or password is not entered
                    Toast.makeText(getApplicationContext(), "Message is required", Toast.LENGTH_LONG).show();
                } else {

                    if (GlobalClass.isNetworkConnected(BlogCommentListctivity.this)) {
                        binding.editGchatMessage.setText("");

                        CommentsOnBlogRequest request = new CommentsOnBlogRequest();
                        request.setBlogId(String.valueOf(typeId));
                        request.setCreatedBy(User_Name);
                        request.setComments(BodyOfComment);
                        request.setLibraryID(LibraryId);
                        request.setPhoneNo(Commenter_PhoneNo);

                        WebAPiCall webapiCall = new WebAPiCall();

                        webapiCall.CommentOnBlogDataMethod(BlogCommentListctivity.this, BlogCommentListctivity.this, request, BlogCommentListctivity.this);

                    } else {

                        Toast.makeText(BlogCommentListctivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                }


            }
        });


    }


    @Override
    public void allBlogCommentsdata(List<BlogCommentsListResponse.Datum> list) {

        try {

            if (list == null) {

                binding.txtnodatamsg.setVisibility(View.VISIBLE);
                binding.txtnodatamsg.setText("No any Comment Yet.");


            } else {
                binding.txtnodatamsg.setVisibility(View.GONE);
                arrayList.clear();
                arrayList.addAll(list);

                manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                binding.rvblog.setLayoutManager(manager);
                adapter = new CommentsListBlogIdAdapter(this, (ArrayList) arrayList, this);
                binding.rvblog.setAdapter(adapter);
            }
        } catch (Exception e) {

            binding.txtnodatamsg.setVisibility(View.VISIBLE);
            binding.txtnodatamsg.setText("No any Comment Yet.");

            e.printStackTrace();
        }


    }

    @Override
    public void onItemClick(BlogCommentsListResponse.Datum item, int currposition, String type) {

    }

    @Override
    public void commentsStatusdata(Integer data, String sysMessage) {

        binding.txtnodatamsg.setVisibility(View.VISIBLE);
        binding.txtnodatamsg.setText("Your comment will show,Once Admin will approve.");
        GlobalClass.dailogsuccess(this, "Comment Done", "Your comment will show,Once Admin will approve.");
        Toast.makeText(BlogCommentListctivity.this, "Your comment will show,Once Admin will approve.", Toast.LENGTH_LONG).show();


        /*if (GlobalClass.isNetworkConnected(BlogCommentListctivity.this)) {


            WebAPiCall webapiCall = new WebAPiCall();

            webapiCall.getCommentsByBlogsIdMethod(BlogCommentListctivity.this, BlogCommentListctivity.this, String.valueOf(typeId), binding.rvblog, BlogCommentListctivity.this);

        } else {

            Toast.makeText(BlogCommentListctivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }*/

    }
}