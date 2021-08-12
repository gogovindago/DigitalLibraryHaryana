package dhe.digital.library.haryana.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import java.util.regex.Pattern;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.BookSuggestionData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityBooksuggestionBinding;
import dhe.digital.library.haryana.models.BookSuggestionRequest;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import dhe.digital.library.haryana.utility.MyLoaders;

public class BookSuggestionActivity extends BaseActivity implements BookSuggestionData_interface {

    private MyLoaders myLoaders;
    ActivityBooksuggestionBinding binding;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", userComplaintTypeId, LibtypeId, titleOfPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_booksuggestion);
        myLoaders = new MyLoaders(getApplicationContext());


        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {


                titleOfPage = extras.getString("title");
                LibtypeId = String.valueOf(extras.getInt("itemid", 0));
                //  itemType = extras.getString("itemType");
                // webViewUrl = extras.getString("typeId");

                //  binding.toolbar.tvToolbarTitle.setAllCaps(true);
                // binding.toolbar.tvToolbarTitle.setText("Books Available in "+titleOfPage);

/*

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in </h6>" + titleOfPage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + titleOfPage + "</h6>"));
                }
*/


            }

        } catch (Exception e) {
            e.printStackTrace();
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

        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        binding.btnbooksuggestionsbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Data(view)) {


                    BookSuggestionRequest request = new BookSuggestionRequest();
                    request.setLibraryID(LibtypeId);
                    request.setName(binding.txtlytusername.getText().toString().trim()+binding.txtlytLastName.getText().toString().trim());
                    request.setEmail(binding.txtlytemail.getText().toString().trim());
                    request.setBookName(binding.bookname.getText().toString().trim());
                    request.setSuggestions(binding.txtlytsuggestion.getText().toString().trim());
                    request.setAuthorName(binding.AuthorName.getText().toString().trim());
                    request.setSubject(binding.Subject.getText().toString().trim());


                    /*{
    "LibraryID":"8",
    "Name":"ABC",
    "Email":"abc@gmail.com",
    "Suggestions":"ABCDEF",
    "BookName":"Book",
    "AuthorName":"Author",
    "Subject":"Subject"
}*/

                    if (GlobalClass.isNetworkConnected(BookSuggestionActivity.this)) {

                        WebAPiCall webapiCall = new WebAPiCall();
                        webapiCall.BookSuggestionPostDataMethod(BookSuggestionActivity.this, BookSuggestionActivity.this, BookSuggestionActivity.this, request);


                    } else {

                        Toast.makeText(BookSuggestionActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }


                } else {
                }

            }
        });


    }


    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() >= 10 && phone.length() < 11;
            //return phone.length()==10;
        }
        return false;
    }

    public boolean Check_Data(View view) {

        if (TextUtils.isEmpty(binding.txtlytusername.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your User first-Name");
            return false;

        } /*else if (TextUtils.isEmpty(binding.txtlytmidname.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Middle-Name");
            return false;

        }*/ else if (TextUtils.isEmpty(binding.txtlytLastName.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Last-Name");
            return false;

        } else if (!binding.txtlytemail.getText().toString().trim().matches(emailPattern)) {
            myLoaders.showSnackBar(view, "Please Enter Valid Email-Id");
            return false;

        }else if (TextUtils.isEmpty(binding.Subject.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Subject");
            return false;

        }else if (TextUtils.isEmpty(binding.bookname.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Book Name");
            return false;

        }else if (TextUtils.isEmpty(binding.AuthorName.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter Book Author Name");
            return false;

        } else if (TextUtils.isEmpty(binding.txtlytsuggestion.getText().toString().trim())) {
            myLoaders.showSnackBar(view, "Please Enter your Suggestion");
            return false;
        }

        return true;
    }


    @Override
    public void allBookSuggestiondata(Integer data, String sysMessage) {
        if (data == 200) {
            binding.rrmain.setVisibility(View.GONE);
            binding.txtmsg.setVisibility(View.VISIBLE);
            binding.btnOk.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.txtmsg.setText(Html.fromHtml("<h6> <b> Suggestion Status:- </b><br> <br>" + sysMessage + "</h6>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                binding.txtmsg.setText(Html.fromHtml("<h6><b> Suggestion Status:- </b><br> <br>" + sysMessage + "</h6>"));
            }


        } else {

        }

    }
}