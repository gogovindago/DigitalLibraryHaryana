package dhe.digital.library.haryana.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import dhe.digital.library.haryana.adapter.SpinnerBookTypeAdapter;
import dhe.digital.library.haryana.adapter.SpinnerLanguageTypeAdapter;
import dhe.digital.library.haryana.allinterface.GetBookTypeData_interface;
import dhe.digital.library.haryana.allinterface.GetLanguageTypeData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityPeopleCornerBinding;
import dhe.digital.library.haryana.models.GetbooktypeResponse;
import dhe.digital.library.haryana.models.GetlanguageResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.FileUtils;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PeopleCornerActivity extends BaseActivity implements GetBookTypeData_interface, AdapterView.OnItemSelectedListener, GetLanguageTypeData_interface {
    ActivityPeopleCornerBinding binding;
    boolean granted = false;

    File imagefile, pdf_file;
    private int REQUEST_CODE;
    boolean skiplogin;
    String titleOfPage, userBooktype, userBooklanguagetype, liburl, BookIframe, BookIframeUrl, PhoneNo, CreatedBy, booktitle;
    RadioGroup btnRadiogroup;
    RadioButton checkedRadioButton;

    SpinnerBookTypeAdapter spnBookAdapter;
    SpinnerLanguageTypeAdapter languageTypeAdapter;
    private List<GetbooktypeResponse.Datum> bookTypeList = new ArrayList<GetbooktypeResponse.Datum>();
    private List<GetlanguageResponse.Datum> languageTypedataList = new ArrayList<GetlanguageResponse.Datum>();

    private int spnBookTypeCurrentPosition = 0, spnLanguageTypeCurrentPosition = 0;
    private MyLoaders myLoaders;
    private int userBooklanguageLibId, userBookLibId, LibId;
    private boolean Is_iframe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_people_corner);
        myLoaders = new MyLoaders(getApplicationContext());


        skiplogin = CSPreferences.getBoolean(this, "skiplogin");
        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                titleOfPage = extras.getString("title");
                LibId = extras.getInt("itemid");
                liburl = extras.getString("liburl");
                PhoneNo = CSPreferences.readString(this, "PhoneNo");
                CreatedBy = CSPreferences.readString(this, "User_Name");
                String string = liburl;
                String[] parts = string.split("//", 2);
                String part1 = parts[0]; // 004
                String part2 = parts[1]; // 034556-42
                liburl = part2;


                // webViewUrl = extras.getString("LibId");

                binding.toolbar.tvToolbarTitle.setAllCaps(false);
                binding.toolbar.tvToolbarTitle.setText("Book for - " + titleOfPage);
                // binding.txtmsgcontactus.setText("Contact us to:-"+titleOfPage);
                binding.edtusername.setText(CreatedBy);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if (GlobalClass.isNetworkConnected(PeopleCornerActivity.this)) {

            WebAPiCall webapiCall = new WebAPiCall();

            // webapiCall.getAllDataMethod(PeopleCornerActivity.this, PeopleCornerActivity.this, binding.recyclerView, binding.simpleSwipeRefreshLayout, PeopleCornerActivity.this, LibId);
            webapiCall.getbooktypeMethod(PeopleCornerActivity.this, PeopleCornerActivity.this, PeopleCornerActivity.this);
            // binding.spnimporttype.setSelection(0, true);
        } else {

            Toast.makeText(PeopleCornerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
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

                            Is_iframe = false;
                            binding.cardviewbookpdf.setVisibility(View.VISIBLE);
                            binding.cardviewbookimage.setVisibility(View.VISIBLE);
                            binding.llbookiframe.setVisibility(View.GONE);
                            binding.llbookiframeUrl.setVisibility(View.GONE);
                            break;

                        case R.id.rbbookiframe:
                            Is_iframe = true;
                            imagefile = null;
                            pdf_file = null;
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
                    pdf_file = file;
                    // binding.txtrequiredDocument.setText(imagefile.toString());
                    binding.txtbookpdf.setText(pdf_file.getName());
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


        } else if (requestCode == 124) {

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
                    binding.txtbookimage.setText(imagefile.getName());
                    // binding.txtrequiredDocument.setTextColor(R.color.drkgreeen);
                    binding.txtbookimage.setTextColor(getResources().getColor(R.color.drkgreeen));
                    binding.imgbookDone.setVisibility(View.VISIBLE);
                    binding.llbookimage.setBackgroundResource(R.drawable.spinner_bordergreen);
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


        binding.spnbooktypetype.setOnItemSelectedListener(this);
        binding.spnbooklang.setOnItemSelectedListener(this);


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Check_Data(view)) {

                    try {


                        if (Is_iframe) {

                            BookIframe = binding.edtbookiframe.getText().toString().trim();
                            BookIframeUrl = binding.edtiframeurl.getText().toString().trim();

                        } else {
                            BookIframe = null;
                            BookIframeUrl = null;
                        }


                        booktitle = binding.edtbooktitle.getText().toString().trim();

                        RequestBody rq_Titleblog = RequestBody.create(MediaType.parse("multipart/form-data"), booktitle);
                        RequestBody rq_LibId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(LibId));
                        RequestBody rq_LanguageTypeId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(userBooklanguageLibId));
                        RequestBody rq_BookTypeId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(userBookLibId));
                        RequestBody rq_CreatedBy = RequestBody.create(MediaType.parse("multipart/form-data"), CreatedBy);


                        RequestBody rq_BookIframe = null;
                        try {
                            rq_BookIframe = RequestBody.create(MediaType.parse("multipart/form-data"), BookIframe);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        RequestBody rq_BookIframeUrl = null;
                        try {
                            rq_BookIframeUrl = RequestBody.create(MediaType.parse("multipart/form-data"), BookIframeUrl);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }



                        RequestBody imagefilerequestFile = null;
                        MultipartBody.Part imagefilebody = null;

                        if (imagefile == null) {
                            imagefilebody = null;

                        } else {

                            try {
                                imagefilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imagefile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            imagefilebody = MultipartBody.Part.createFormData("BookImageext", imagefile.getName(), imagefilerequestFile);
                        }

                        RequestBody pdffilerequestFile = null;
                        MultipartBody.Part pdffilebody = null;

                        if (pdf_file == null) {

                            pdffilebody = null;

                        } else {

                            try {
                                pdffilerequestFile = RequestBody.create(MediaType.parse("multipart/form-data"), pdf_file);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            pdffilebody = MultipartBody.Part.createFormData("BookPDFext", pdf_file.getName(), pdffilerequestFile);
                        }

                        if (GlobalClass.isNetworkConnected(PeopleCornerActivity.this)) {
                            WebAPiCall aPiCall = new WebAPiCall();
                            aPiCall.DonateBookDataMethod(PeopleCornerActivity.this, PeopleCornerActivity.this, rq_Titleblog, rq_LibId, rq_LanguageTypeId,
                                    rq_CreatedBy, rq_BookTypeId, rq_BookIframe, rq_BookIframeUrl, imagefilebody, pdffilebody);

                            /*BlogTitle LibraryId LanguageId CreatedBy BookTypeId BookIframe BookIframeUrl BookImageext */

                        } else {

                            Toast.makeText(PeopleCornerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }


    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.edtusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter User name");
            return false;
        } else if (TextUtils.isEmpty(binding.edtbooktitle.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please write Title of Book.");
            return false;

        } else if (spnBookTypeCurrentPosition == 0) {
            myLoaders.showSnackBar(view, " Please select Book Type.");
            return false;

        } else if (spnLanguageTypeCurrentPosition == 0) {
            myLoaders.showSnackBar(view, "Please select Language type.");
            return false;

        }
        return true;
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

    @Override
    public void GetBookTypeData(List<GetbooktypeResponse.Datum> list) {

        bookTypeList.clear();
        bookTypeList.addAll(list);

        GetbooktypeResponse.Datum datum = new GetbooktypeResponse.Datum();
        datum.setBookType("Select your Book Type");
        datum.setBookTypeId(0);
        bookTypeList.add(0, datum);
        spnBookAdapter = new SpinnerBookTypeAdapter(getApplicationContext(), bookTypeList);
        binding.spnbooktypetype.setAdapter(spnBookAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        int id = adapterView.getId();
        if (id == R.id.spnbooktypetype) {

            if (position != 0) {

                spnBookTypeCurrentPosition = position;

                userBooktype = bookTypeList.get(position).getBookType();
                userBookLibId = bookTypeList.get(position).getBookTypeId();
                Toast.makeText(getApplicationContext(), bookTypeList.get(position).getBookType(), Toast.LENGTH_LONG).show();
                binding.llbooklangtype.setVisibility(View.VISIBLE);

                if (GlobalClass.isNetworkConnected(PeopleCornerActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();

                    webapiCall.getLanguagetypeMethod(PeopleCornerActivity.this, PeopleCornerActivity.this, PeopleCornerActivity.this);
                    // binding.spnimporttype.setSelection(0, true);
                } else {

                    Toast.makeText(PeopleCornerActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }

            } else {
                binding.llbooklangtype.setVisibility(View.GONE);
                spnBookTypeCurrentPosition = position;

            }

        } else if (id == R.id.spnbooklang) {

            if (position != 0) {

                spnLanguageTypeCurrentPosition = position;

                userBooklanguagetype = languageTypedataList.get(position).getLanguage();
                userBooklanguageLibId = languageTypedataList.get(position).getLanguageId();
                Toast.makeText(getApplicationContext(), languageTypedataList.get(position).getLanguage(), Toast.LENGTH_LONG).show();
            } else {
                spnLanguageTypeCurrentPosition = position;
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void GetLanguageTypeData(List<GetlanguageResponse.Datum> list) {
        languageTypedataList.clear();
        languageTypedataList.addAll(list);

        GetlanguageResponse.Datum languagedatum = new GetlanguageResponse.Datum();
        languagedatum.setLanguage("Select your Book Language");
        languagedatum.setLanguageId(0);
        languageTypedataList.add(0, languagedatum);
        languageTypeAdapter = new SpinnerLanguageTypeAdapter(getApplicationContext(), languageTypedataList);
        binding.spnbooklang.setAdapter(languageTypeAdapter);

    }
}