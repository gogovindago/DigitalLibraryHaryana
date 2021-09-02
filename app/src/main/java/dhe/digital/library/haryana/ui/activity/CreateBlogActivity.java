package dhe.digital.library.haryana.ui.activity;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityCreateBlogBinding;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.FileUtils;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateBlogActivity extends BaseActivity {
    Integer typeId;
    String titleOfPage, liburl, PhoneNo, CreatedBy, Titleblog, blogs;
    ActivityCreateBlogBinding binding;
    private MyLoaders myLoaders;
    File imagefile;
    private int REQUEST_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_blog);
        myLoaders = new MyLoaders(getApplicationContext());

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                titleOfPage = extras.getString("title");
                typeId = extras.getInt("itemid");
                liburl = extras.getString("liburl");
                PhoneNo = CSPreferences.readString(this, "PhoneNo");
                CreatedBy = CSPreferences.readString(this, "User_Name");
                String string = liburl;
                String[] parts = string.split("//", 2);
                String part1 = parts[0]; // 004
                String part2 = parts[1]; // 034556-42
                liburl = part2;


                // webViewUrl = extras.getString("typeId");

                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Blog for:-" + titleOfPage);
                // binding.txtmsgcontactus.setText("Contact us to:-"+titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.txtInTitleblog.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Title of blog");
            return false;
        } else if (TextUtils.isEmpty(binding.txtInblogs.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please write blog.");
            return false;

        } else if (liburl.isEmpty()) {
            myLoaders.showSnackBar(view, "Missing your library Url.");
            return false;

        } else if (!isValidMobile(PhoneNo)) {
            myLoaders.showSnackBar(view, "Please check your Registered Mobile number.");
            return false;

        }
        return true;
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    @Override
    public void initData() {


    }

    @Override
    public void initListeners() {


        binding.txtimg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                REQUEST_CODE = 123;
                showFileChooser(REQUEST_CODE);

            }
        });

        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Check_Data(view)) {

                    try {


                        Titleblog = binding.txtInTitleblog.getText().toString().trim();
                        blogs = binding.txtInblogs.getText().toString().trim();

                        RequestBody rq_Titleblog = RequestBody.create(MediaType.parse("multipart/form-data"), Titleblog);
                        RequestBody rq_blogs = RequestBody.create(MediaType.parse("multipart/form-data"), blogs);
                        RequestBody rq_liburl = RequestBody.create(MediaType.parse("multipart/form-data"), liburl);
                        RequestBody rq_PhoneNo = RequestBody.create(MediaType.parse("multipart/form-data"), PhoneNo);
                        RequestBody rq_CreatedBy = RequestBody.create(MediaType.parse("multipart/form-data"), CreatedBy);
                        RequestBody imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                        MultipartBody.Part imagefilebody = MultipartBody.Part.createFormData("Blog_Image_ext", imagefile.getName(), imagefilerequestFile);


                        if (GlobalClass.isNetworkConnected(CreateBlogActivity.this)) {
                            WebAPiCall aPiCall = new WebAPiCall();
                            aPiCall.CreateBlogDataMethod(CreateBlogActivity.this, CreateBlogActivity.this, rq_Titleblog, rq_blogs, rq_PhoneNo,
                                    rq_CreatedBy, rq_liburl,
                                    imagefilebody);

                        } else {

                            Toast.makeText(CreateBlogActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showFileChooser(int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("*/*");

        String[] mimeTypes = {"application/*"};

        // Update with additional mime types here using a String[].
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        // Only pick openable and local files. Theoretically we could pull files from google drive
        // or other applications that have networked files, but that's unnecessary for this example.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If the user doesn't pick a file just return
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == 123) {

            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                String path = "";
                int currentVersion = android.os.Build.VERSION.SDK_INT;
                if (currentVersion >= android.os.Build.VERSION_CODES.N) {
                    // Do something for lollipop and above versions
                    path = FileUtils.getFilePathForN(uri, this);
                } else {
                    // do something for phones running an SDK before lollipop
                    path = FileUtils.getPath(this, uri);
                }
                // "file:///mnt/sdcard/FileName.mp3"
                Log.d("PATHS : ", path);
                File file = null;
                try {
                    file = new File(path);
                    imagefile = file;
                    // binding.txtupload.setText(imagefile.toString());
                    binding.txtimg.setText(imagefile.getName());
                    binding.txtimg.setTextColor(getResources().getColor(R.color.drkgreeen));

                    // binding.attachedpdfAssignment.setVisibility(View.VISIBLE);
                    binding.txtimg.setBackgroundResource(R.drawable.spinner_bordergreen);

                    Log.d("PDF", file.getAbsolutePath());
                    Log.d("PDF", "" + file.getTotalSpace());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

            }


        }
    }

    public void checkpermissions(Activity context) {
        if (Build.VERSION.SDK_INT >= 23) {
            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            INTERNET,
                            READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE

                    )
                    .check();
        }


    }

    PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {

        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(CreateBlogActivity.this);
        }

    };


}