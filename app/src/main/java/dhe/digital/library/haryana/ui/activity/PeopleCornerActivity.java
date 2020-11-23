package dhe.digital.library.haryana.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.adapter.SpinnerLibraryTypeAdapter;
import dhe.digital.library.haryana.databinding.ActivityPeopleCornerBinding;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.FileUtils;

public class PeopleCornerActivity extends BaseActivity {
    ActivityPeopleCornerBinding binding;
    boolean granted = false;

    File imagefile;
    private int REQUEST_CODE;
    boolean skiplogin;
    String typeId, titleOfPage,
            userLibSelectedId;
    int spnLibCurrentPosition;
    SpinnerLibraryTypeAdapter spinnerLibraryTypeAdapter;
    RadioGroup btnRadiogroup;
    RadioButton checkedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people_corner);


        skiplogin = CSPreferences.getBoolean(this, "skiplogin");

        try {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {

                titleOfPage = extras.getString("titleOfPage");
                typeId = extras.getString("typeId");
                // webViewUrl = extras.getString("typeId");
                binding.toolbar.tvToolbarTitle.setAllCaps(true);
                binding.toolbar.tvToolbarTitle.setText(titleOfPage);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        // btnRadiogroup = (RadioGroup) findViewById(R.id.btnRadiogroup);
        checkedRadioButton = (RadioButton) binding.btnRadiogroup.findViewById(binding.btnRadiogroup.getCheckedRadioButtonId());


        binding.btnRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) binding.btnRadiogroup.findViewById(checkedId);
                // This puts the value (true/false) into the variable

                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...llCollegestudent
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    switch (checkedId) {
                        //cardviewbookpdf  cardviewbookimage
                        case R.id.rbbookpdf:

                            binding.cardviewbookpdf.setVisibility(View.VISIBLE);
                            binding.cardviewbookimage.setVisibility(View.VISIBLE);
                            binding.llbookiframe.setVisibility(View.GONE);
                            binding.llbookiframeUrl.setVisibility(View.GONE);
                            break;

                        case R.id.rbbookiframe:

                            binding.cardviewbookpdf.setVisibility(View.GONE);
                            binding.cardviewbookimage.setVisibility(View.VISIBLE);
                            binding.llbookiframe.setVisibility(View.VISIBLE);
                            binding.llbookiframeUrl.setVisibility(View.VISIBLE);

                            break;


                    }
                    Toast.makeText(PeopleCornerActivity.this, checkedRadioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        // checkpermissions(CclLeaveTypeActivity.this);
        requestMultiplePermissions();


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showFileChooser(int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("*/*");

        String[] mimeTypes = {"application/pdf"};

        // Update with additional mime types here using a String[].
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        // Only pick openable and local files. Theoretically we could pull files from google drive
        // or other applications that have networked files, but that's unnecessary for this example.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showFileChooserImg(int REQUEST_CODE) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        // Update with mime types
        intent.setType("*/*");

        // String[] mimeTypes = {"application/pdf"};

        // Update with additional mime types here using a String[].
        // intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        // Only pick openable and local files. Theoretically we could pull files from google drive
        // or other applications that have networked files, but that's unnecessary for this example.
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        // REQUEST_CODE = <some-integer>
        startActivityForResult(intent, REQUEST_CODE);
    }

    @SuppressLint("ResourceAsColor")
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
                int currentVersion = Build.VERSION.SDK_INT;
                if (currentVersion >= Build.VERSION_CODES.N) {
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
                    // binding.txtrequiredDocument.setText(imagefile.toString());
                    binding.txtbookpdf.setText(imagefile.getName());
                    // binding.txtrequiredDocument.setTextColor(R.color.drkgreeen);
                    binding.txtbookpdf.setTextColor(getResources().getColor(R.color.drkgreeen));
                    binding.imgpdfDone.setVisibility(View.VISIBLE);
                    binding.llbookpdft.setBackgroundResource(R.drawable.spinner_bordergreen);
                    // Log.d("PDF", file.getAbsolutePath());
                    // Log.d("PDF", "" + file.getTotalSpace());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

            }


        }


    }


    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            granted = true;
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();


                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            granted = false;

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
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


        binding.txtbookpdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {


//                //showpictures(this);
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("application/pdf");
//                startActivityForResult(intent, 1);
                REQUEST_CODE = 123;
                showFileChooser(REQUEST_CODE);
            }
        });


        binding.cardviewbookimage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {


//                //showpictures(this);
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setType("application/pdf");
//                startActivityForResult(intent, 1);


                if (Build.VERSION.SDK_INT >= 23) {

                    REQUEST_CODE = 124;
                    showFileChooserImg(REQUEST_CODE);
                    // checkpermissions(PeopleCornerActivity.this);
                    requestMultiplePermissions();

                } else {
                    REQUEST_CODE = 124;
                    // selectImage();
                    showFileChooserImg(REQUEST_CODE);

                }

            }
        });


    }

    PermissionListener permissionListener = new PermissionListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPermissionGranted() {

            REQUEST_CODE = 124;
            // selectImage();
            showFileChooserImg(REQUEST_CODE);
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            checkpermissions(PeopleCornerActivity.this);


        }

    };

    public void checkpermissions(Activity context) {

        if (Build.VERSION.SDK_INT >= 23) {

            new TedPermission(context)
                    .setPermissionListener(permissionListener)
                    //.setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                    .setPermissions(
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.CAMERA,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE

                    )
                    .setGotoSettingButton(true)
                    .check();

        }


    }
}