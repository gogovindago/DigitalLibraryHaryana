package dhe.digital.library.haryana.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;
import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.allinterface.ProfileData_interface;
import dhe.digital.library.haryana.apicall.WebAPiCall;
import dhe.digital.library.haryana.databinding.ActivityProfileBinding;
import dhe.digital.library.haryana.models.ProfileDataResponse;
import dhe.digital.library.haryana.utility.BaseActivity;
import dhe.digital.library.haryana.utility.CSPreferences;
import dhe.digital.library.haryana.utility.GlobalClass;


public class ProfileActivity extends BaseActivity implements ProfileData_interface {
    ActivityProfileBinding binding;
    String User_PhoneNo, token, Url = "http://library.highereduhry.com/Admin/Login.aspx", tittle = "Library management System (LMS)";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.simpleSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override

            public void onRefresh() {

                if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {

                    WebAPiCall webapiCall = new WebAPiCall();
                    webapiCall.ProfileDataMethod(ProfileActivity.this, ProfileActivity.this, binding.allconst, ProfileActivity.this, User_PhoneNo);

                } else {

                    Toast.makeText(ProfileActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                }
                binding.simpleSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }


    @Override
    public void initData() {

        try {
            User_PhoneNo = CSPreferences.readString(ProfileActivity.this, "PhoneNo");
            token = CSPreferences.readString(ProfileActivity.this, "token");
            binding.edtRegistraionId.setText(User_PhoneNo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GlobalClass.isNetworkConnected(ProfileActivity.this)) {
            WebAPiCall webapiCall = new WebAPiCall();
            webapiCall.ProfileDataMethod(ProfileActivity.this, ProfileActivity.this, binding.allconst, ProfileActivity.this, User_PhoneNo);

        } else {

            Toast.makeText(this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void initListeners() {


        binding.btnLms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent certificate = new Intent(ProfileActivity.this, OpenBooksActivity.class);
                certificate.putExtra("bookurl", Url);
                certificate.putExtra("title", tittle);
                startActivity(certificate);

            }
        });

    }

    @Override
    public void userprofiledata(List<ProfileDataResponse.Datum> list) {


        try {

           /* Glide.with(this)
                    .load(list.get(0).getPic()) // image url
                    .placeholder(R.mipmap.ic_launcher_round) // any placeholder to load at start
                    .error(R.mipmap.ic_launcher_round)  // any image in case of error
                    .override(85, 85) // resizing
                    .centerCrop()
                    .into(binding.profileImage);
*/
            binding.name.setText(list.get(0).getFullName());
            // binding.fathername.setText(list.get(0).getFatherName());
            binding.edtmobile.setText(list.get(0).getPhoneNo());
            binding.edtemail.setText(list.get(0).getEmail());
            binding.edtwatchedvideo.setText(list.get(0).getVideo());
            binding.edtreadbooks.setText(list.get(0).getBook());


            // binding.edtgender.setText(list.get(0).getGender());
            /// binding.edtcollegeName.setText(list.get(0).getCollege());
            //binding.edtfacultyDesignation.setText(list.get(0).getDesignation());
            //binding.edtdistrict.setText(list.get(0).getCDISTRICT());
            // binding.edtsubject.setText(list.get(0).getSubject());
            // binding.txtAccountCreatedat.setText(" DOFRetirement: " + list.get(0).getDORetirement());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}