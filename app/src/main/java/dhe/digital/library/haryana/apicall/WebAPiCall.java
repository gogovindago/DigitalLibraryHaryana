package dhe.digital.library.haryana.apicall;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dhe.digital.library.haryana.allinterface.GetAllData_interface;
import dhe.digital.library.haryana.allinterface.GetAllHearingSpeechData_interface;
import dhe.digital.library.haryana.allinterface.GetAllLibraryTypesData_interface;
import dhe.digital.library.haryana.allinterface.GetLibTypeByIdData_interface;
import dhe.digital.library.haryana.allinterface.GetbannersData_interface;
import dhe.digital.library.haryana.allinterface.LoginData_interface;
import dhe.digital.library.haryana.allinterface.OtpVerifyData_interface;
import dhe.digital.library.haryana.allinterface.ProfileData_interface;
import dhe.digital.library.haryana.allinterface.SearchingData_interface;
import dhe.digital.library.haryana.allinterface.SignupData_interface;
import dhe.digital.library.haryana.models.ForgotPasswordRequest;
import dhe.digital.library.haryana.models.ForgotPasswordResponse;
import dhe.digital.library.haryana.models.HearingSpeechimpairedDataResponse;
import dhe.digital.library.haryana.models.HomePageResponse;
import dhe.digital.library.haryana.models.LibraryTypeAndCoutResponse;
import dhe.digital.library.haryana.models.LibraryTypeByIdResponse;
import dhe.digital.library.haryana.models.LoginRequest;
import dhe.digital.library.haryana.models.LoginResponse;
import dhe.digital.library.haryana.models.ProfileDataResponse;
import dhe.digital.library.haryana.models.ReadViewsCountRequest;
import dhe.digital.library.haryana.models.ReadViewsCountResponse;
import dhe.digital.library.haryana.models.SearchResponse;
import dhe.digital.library.haryana.models.SignupRequest;
import dhe.digital.library.haryana.models.SignupResponse;
import dhe.digital.library.haryana.models.VerifyOtpRequest;
import dhe.digital.library.haryana.models.VerifyOtpResponse;
import dhe.digital.library.haryana.models.ViewAllResponse;
import dhe.digital.library.haryana.retrofitinterface.ApiClient;
import dhe.digital.library.haryana.ui.activity.MainActivity;
import dhe.digital.library.haryana.utility.GlobalClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebAPiCall {


    SweetAlertDialog pDialog;
    ProgressDialog pd;


    public void dailogsuccessanddismis(final Context context, final Activity activity, String msgtitle, String msgcontentText) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismissWithAnimation();

            }
        });
        sweetAlertDialog.show();

    }


    public void dailogsuccessWithActivity(final Context context, final Activity activity, String msgtitle, String msgcontentText) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                activity.finish();
            }
        });
        sweetAlertDialog.show();

    }

    public void dailogsuccess(final Context context, String msgtitle, String msgcontentText) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(2);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();

            }
        });
        sweetAlertDialog.show();

    }

    public void dailogError(final Context context, String msgtitle, String msgcontentText) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setTitle(msgtitle + "!");
        sweetAlertDialog.setContentText(msgcontentText);
        sweetAlertDialog.setVolumeControlStream(2);
        sweetAlertDialog.getAlerType();
        sweetAlertDialog.changeAlertType(1);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setCanceledOnTouchOutside(false);
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.show();

    }


    public void dailogshow(final Context context) {
        pDialog = new SweetAlertDialog(context);
        pDialog.changeAlertType(5);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

//        pd = new ProgressDialog(context);
//        pd.setMessage("loading...");
//        pd.setCancelable(false);
//        pd.show();
    }

    public void loadershowwithMsg(final Context context, String msg) {
        pDialog = new SweetAlertDialog(context);
        pDialog.changeAlertType(5);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(msg);
        pDialog.setCancelable(false);
        pDialog.show();

//        pd = new ProgressDialog(context);
//        pd.setMessage("loading...");
//        pd.setCancelable(false);
//        pd.show();
    }

    public void dailoghide(final Context context) {
        // pd.dismiss();
        pDialog.dismissWithAnimation();
    }


    public void getHomePageDataMethod(final Activity activity, final Context context, LinearLayout llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetbannersData_interface getbannersData_interface) {

        // loadershowwithMsg(context, "Loading...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<HomePageResponse> responseCall = ApiClient.getClient().getHomePageDataAPi();
        responseCall.enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                // dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        mSwipeRefreshLayout.setRefreshing(false);

                        llmain.setVisibility(View.VISIBLE);

                        getbannersData_interface.GetbannersData(response.body().getData().getBanners());
                        getbannersData_interface.GetOtherDigitalTrendingLibraryData(response.body().getData().getOtherDigitalTrendingLibraries());
                        getbannersData_interface.GetTrendingeBookData(response.body().getData().getTrendingeBooks());
                        getbannersData_interface.GetTrendingVideosData(response.body().getData().getTrendingVideos());
                        getbannersData_interface.GetTrendingJournalData(response.body().getData().getTrendingJournals());
                        getbannersData_interface.GetImportantsLinkData(response.body().getData().getImportantLinks());
                        getbannersData_interface.GetUdaanVideosData(response.body().getData().getTrendingUdaanVideos());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetAllLibraryTypesDataMethod(final Activity activity, final Context context, RecyclerView llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllLibraryTypesData_interface getAllLibraryTypesData_interface) {

        // loadershowwithMsg(context, "Loading...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<LibraryTypeAndCoutResponse> responseCall = ApiClient.getClient().getAllLibraryTypeAPi();
        responseCall.enqueue(new Callback<LibraryTypeAndCoutResponse>() {
            @Override
            public void onResponse(Call<LibraryTypeAndCoutResponse> call, Response<LibraryTypeAndCoutResponse> response) {
                // dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        mSwipeRefreshLayout.setRefreshing(false);

                        llmain.setVisibility(View.VISIBLE);

                        getAllLibraryTypesData_interface.GetLibrarytypesData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LibraryTypeAndCoutResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetAllHearingSpeechDataMethod(final Activity activity, final Context context, RecyclerView llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllHearingSpeechData_interface getAllHearingSpeechData_interface) {

        // loadershowwithMsg(context, "Loading...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<HearingSpeechimpairedDataResponse> responseCall = ApiClient.getClient().getAllGetHearingSpeechimpairedDataAPi();
        responseCall.enqueue(new Callback<HearingSpeechimpairedDataResponse>() {
            @Override
            public void onResponse(Call<HearingSpeechimpairedDataResponse> call, Response<HearingSpeechimpairedDataResponse> response) {
                // dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        mSwipeRefreshLayout.setRefreshing(false);

                        llmain.setVisibility(View.VISIBLE);

                        getAllHearingSpeechData_interface.GetHearingSpeechData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<HearingSpeechimpairedDataResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void getAllDataMethod(final Activity activity, final Context context, RecyclerView llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetAllData_interface getAllData_interface, String typeId) {

        // loadershowwithMsg(context, "Loading...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<ViewAllResponse> responseCall = ApiClient.getClient().getAllDataAPi(typeId);
        responseCall.enqueue(new Callback<ViewAllResponse>() {
            @Override
            public void onResponse(Call<ViewAllResponse> call, Response<ViewAllResponse> response) {
                // dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {
                        llmain.setVisibility(View.VISIBLE);

                        getAllData_interface.GetAllData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ViewAllResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getLibraryTypeByIdDataMethod(final Activity activity, final Context context, RecyclerView llmain, SwipeRefreshLayout mSwipeRefreshLayout, final GetLibTypeByIdData_interface getLibTypeByIdData_interface, String typeId) {

        // loadershowwithMsg(context, "Loading...");
        mSwipeRefreshLayout.setRefreshing(true);

        Call<LibraryTypeByIdResponse> responseCall = ApiClient.getClient().getLibraryTypeByIdDataAPi(typeId);
        responseCall.enqueue(new Callback<LibraryTypeByIdResponse>() {
            @Override
            public void onResponse(Call<LibraryTypeByIdResponse> call, Response<LibraryTypeByIdResponse> response) {
                // dailoghide(context);
                mSwipeRefreshLayout.setRefreshing(false);

                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {
                        llmain.setVisibility(View.VISIBLE);

                        getLibTypeByIdData_interface.GetAllLibTypeByIdData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LibraryTypeByIdResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);

                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void ProfileDataMethod(final Activity activity, final Context context, ConstraintLayout llmain, final ProfileData_interface profileData_interface, String MobileNo) {

        loadershowwithMsg(context, "Loading...");

        Call<ProfileDataResponse> responseCall = ApiClient.getClient().getProfileDataAPi(MobileNo);
        responseCall.enqueue(new Callback<ProfileDataResponse>() {
            @Override
            public void onResponse(Call<ProfileDataResponse> call, Response<ProfileDataResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {
                        llmain.setVisibility(View.VISIBLE);

                        profileData_interface.userprofiledata(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileDataResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void SearchDataMethod(final Activity activity, final Context context, SwipeRefreshLayout swipeRefreshLayout, RecyclerView layout, final SearchingData_interface searchingData_interface, String searchingData) {

        // loadershowwithMsg(context, "Searching...");
        swipeRefreshLayout.setRefreshing(true);

        Call<SearchResponse> responseCall = ApiClient.getClient().getsearchingDataAPi(searchingData);
        responseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                //dailoghide(context);
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {
                        layout.setVisibility(View.VISIBLE);

                        searchingData_interface.userSearchingdata(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                // dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    //    @GET("UserLogin/{PhoneNo}/{Password}/{FcmToken}")
    //  public void loginPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, String PhoneNo, String Password, String FcmToken) {
    public void loginPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Detail for login.");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(request);
        userpost_responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        // dailogsuccess(context, "Login Successfull.", "Welcome to Shiksha Sahyogi,Haryana.");
                        loginData_interface.alluserdata((LoginResponse.Data) response.body().getData());

                        Intent intentlogin = new Intent(context, MainActivity.class);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intentlogin);
                        activity.finish();


                    } else {
                        dailogError(context, "Credentials Not Found!", "You have entered wrong credentials.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void forgotPasswordPostDataMethod(final Activity activity, final Context context, ForgotPasswordRequest request) {

        loadershowwithMsg(context, "We are Sending auto generated password on your Registered Mobile number.");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<ForgotPasswordResponse> userpost_responseCall = ApiClient.getClient().ForgetPasswordUser(request);
        userpost_responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccessWithActivity(context, activity, " Password has been Changed and Sent Successfully.", "New auto generated password has been sent on your Registered Mobile number .");


                    } else {
                        dailogError(context, "Mobile Number Not Found!", "The Mobile Number You have entered is not Regitered with Us.");
                    }


                } else {
                    GlobalClass.showtost(context, "Something went wrong. Please try after sometimes." + response.message());
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void readCountDataMethod(final Activity activity, final Context context, ReadViewsCountRequest request) {

        // loadershowwithMsg(context, "We are Sending auto generated password on your Registered Mobile number.");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<ReadViewsCountResponse> userpost_responseCall = ApiClient.getClient().ReadCountIncreaseData(request);
        userpost_responseCall.enqueue(new Callback<ReadViewsCountResponse>() {
            @Override
            public void onResponse(Call<ReadViewsCountResponse> call, Response<ReadViewsCountResponse> response) {
                //   dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        //  dailogsuccessWithActivity(context, activity, " Password has been Changed Successfully.", "New auto generated password has been sent on your Registered Mobile number .");


                    } else {
                        // dailogError(context, "Mobile Number Not Found!", "The Mobile Number You have entered is not Regitered with Us.");
                    }


                } else {
                    GlobalClass.showtost(context, "Something went wrong. Please try after sometimes." + response.message());
                }

            }

            @Override
            public void onFailure(Call<ReadViewsCountResponse> call, Throwable t) {

                //  dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void VerifyOtpPostDataMethod(final Activity activity, final Context context, OtpVerifyData_interface otpVerifyData_interface, VerifyOtpRequest request) {

        loadershowwithMsg(context, "Registration Process is going on....");

        // Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(PhoneNo, Password, FcmToken);
        Call<VerifyOtpResponse> userpost_responseCall = ApiClient.getClient().verifyOTP(request);
        userpost_responseCall.enqueue(new Callback<VerifyOtpResponse>() {
            @Override
            public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        // dailogsuccessanddismis(context,activity, "New Password Sent Successfull.", "New auto generated password has been sent on your Registered Mobile number .");
                        otpVerifyData_interface.userOtpVerifydata(response.body().getResponse());


                    } else {
                        dailogError(context, "Mobile Number Not Found!", "The Mobile Number You have entered is not Regitered with Us.");
                    }


                } else {
                    GlobalClass.showtost(context, "Something went wrong. Please try after sometimes." + response.message());
                }

            }

            @Override
            public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void signupPostDataMethod(final Activity activity, final Context context, final SignupData_interface data_interface, SignupRequest request) {

        loadershowwithMsg(context, "we are sending a 4 digits OTP on given Mobile Number for Registration....");

        Call<SignupResponse> userpost_responseCall = ApiClient.getClient().signupUser(request);
        userpost_responseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccess(context, "OTP Sent.", "Please check your Mobile. OTP is  valid for 10 minutes.");
                        data_interface.alluserdata((SignupResponse.Data) response.body().getData());

                    } else if (response.body().getResponse() == 303) {
                        data_interface.alluserdata((SignupResponse.Data) response.body().getData());

                    } else if (response.body().getResponse() == 304) {
                        dailogsuccess(context, "Already,This Mobile Number is Registered", "Already,This Mobile Number is Registered with us,Please try with different number. ");
                    } else {

                        dailogError(context, "Something went wrong !", "Please try after sometimes.");

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }























   /* public void loginPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Detail for login.");

        Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(request);
        userpost_responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 1) {

                        // dailogsuccess(context, "Login Successfull.", "Welcome to Shiksha Sahyogi,Haryana.");
                        loginData_interface.alluserdata(response.body().getData());
                        Intent intentlogin = new Intent(context, MainActivity.class);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intentlogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intentlogin);
                        activity.finish();


                    } else {
                        dailogError(context, "Credentials Not Found!", "You have entered wrong credentials.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void otpDataMethod(final Activity activity, final Context context, String token, final OtpData_interface otpData_interface, OtpRequest request) {

        loadershowwithMsg(context, "We are veryfing your Detail for sending OTP.");

        Call<OtpResponse> userpost_responseCall = ApiClient.getClient().otpDataApi(token, request);
        userpost_responseCall.enqueue(new Callback<OtpResponse>() {
            @Override
            public void onResponse(Call<OtpResponse> call, Response<OtpResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccessanddismis(activity, activity, "OTP Sent.", "Please wait for OTP.");
                        otpData_interface.userOtpdata(response.body().getData());


                    } else {
                        dailogError(context, "Credentials Not Found!", "You have entered wrong credentials.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<OtpResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void CCLStatusChangeDataMethod(final Activity activity, final Context context, String token, CclStatusChangeRequest request) {

        loadershowwithMsg(context, "We are Updating your Leave Action  for selected Application.");

        Call<CclStatusChangeResponse> userpost_responseCall = ApiClient.getClient().CCLStatusChangeDataApi(token, request);
        userpost_responseCall.enqueue(new Callback<CclStatusChangeResponse>() {
            @Override
            public void onResponse(Call<CclStatusChangeResponse> call, Response<CclStatusChangeResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccessWithActivity(activity, activity, "Record", response.body().getSysMessage());


                    } else {
                        dailogError(context, "Record!", response.message());
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<CclStatusChangeResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void OthersLeaveStatusChangeDataMethod(final Activity activity, final Context context, String token, OthersLeaveStatusChangeRequest request) {

        loadershowwithMsg(context, "We are Updating your Leave Action  for selected Application.");

        Call<OthersLeaveStatusChangeResponse> userpost_responseCall = ApiClient.getClient().OthersLeaveStatusChangeDataApi(token, request);
        userpost_responseCall.enqueue(new Callback<OthersLeaveStatusChangeResponse>() {
            @Override
            public void onResponse(Call<OthersLeaveStatusChangeResponse> call, Response<OthersLeaveStatusChangeResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        dailogsuccessWithActivity(activity, activity, "Record", response.body().getSysMessage());


                    } else {
                        dailogError(context, "Record!", response.message());
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<OthersLeaveStatusChangeResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void ForgotPassworDataMethod(final Activity activity, final Context context, String username, String dob) {

        loadershowwithMsg(context, "We are veryfing your Detail for login.");

        Call<ForgotPasswordResponse> userpost_responseCall = ApiClient.getClient().ForgotPasswordApi(username, dob);
        userpost_responseCall.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {


                    if (response.body().getResponse() == 200) {

                        //dailogsuccess(context, "Forget password Reset Successfully.", response.message());
                        dailogsuccessWithActivity(context, activity, "Forget password Reset Successfully.", response.message());


                    } else {
                        dailogError(context, "Credentials Not Found!", "You have entered wrong credentials.");
                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void employeeProfileDataMethod(final Activity activity, final Context context, final EmployeeProfileData_interface employeeProfileData_interface, String token, String employeeUser_Id) {

        loadershowwithMsg(context, "We are Fetching your Data from server.");

        Call<EmployeeProfiledataResponse> EmployeeProfiledataResponseCall = ApiClient.getClient().EMPLOYEE_PROFILE_DATA_RESPONSE_CALL(token, employeeUser_Id);
        EmployeeProfiledataResponseCall.enqueue(new Callback<EmployeeProfiledataResponse>() {
            @Override
            public void onResponse(Call<EmployeeProfiledataResponse> call, Response<EmployeeProfiledataResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        employeeProfileData_interface.EmployeeProfileData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<EmployeeProfiledataResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetbannersDataMethod(final Activity activity, final Context context, final GetbannersData_interface getbannersData_interface) {

        loadershowwithMsg(context, "We are Fetching all Notices from server.");

        Call<NoticeboardResponse> responseCall = ApiClient.getClient().getbannersAPi();
        responseCall.enqueue(new Callback<NoticeboardResponse>() {
            @Override
            public void onResponse(Call<NoticeboardResponse> call, Response<NoticeboardResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        getbannersData_interface.GetbannersData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<NoticeboardResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetSessionDataMethod(final Activity activity, final Context context, String token, final SessionData_interface sessionData_interface) {

        loadershowwithMsg(context, "We are Fetching all Session List from server.");

        Call<SessionResponse> responseCall = ApiClient.getClient().getsessionAPi(token);
        responseCall.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        sessionData_interface.SessionData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetSemesterDataMethod(final Activity activity, final Context context, String token, final SemesterData_interface semesterData_interface) {

        loadershowwithMsg(context, "We are Fetching all Session List from server.");

        Call<SemesterResponse> responseCall = ApiClient.getClient().getsemesterAPi(token);
        responseCall.enqueue(new Callback<SemesterResponse>() {
            @Override
            public void onResponse(Call<SemesterResponse> call, Response<SemesterResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        semesterData_interface.SemesternData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<SemesterResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetStaffPositionsMethod(final Activity activity, final Context context, String token, String SubjectId, String CollegeId, final StaffPositions_interface staffPositions_interface) {

        loadershowwithMsg(context, "We are Fetching all Staff Current Positions  from server.");

        Call<GetStaffPositionsResponse> responseCall = ApiClient.getClient().getStaffPositionsAPi(token, CollegeId, SubjectId);
        responseCall.enqueue(new Callback<GetStaffPositionsResponse>() {
            @Override
            public void onResponse(Call<GetStaffPositionsResponse> call, Response<GetStaffPositionsResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        staffPositions_interface.StaffPositionsData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetStaffPositionsResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }
    *//*("Emp_Id") String CollegeId, @Query("LeaveType_Id")*//*

    public void GetLeaveHistoryMethod(final Activity activity, final Context context, String token, String LeaveType_Id, String Emp_Id, final LeaveHistory_interface leaveHistory_interface) {

        loadershowwithMsg(context, "We are Fetching your Leave History  from server.");

        Call<LeaveHistoryResponse> responseCall = ApiClient.getClient().getLeaveHistoryAPi(token, Emp_Id, LeaveType_Id
        );
        responseCall.enqueue(new Callback<LeaveHistoryResponse>() {
            @Override
            public void onResponse(Call<LeaveHistoryResponse> call, Response<LeaveHistoryResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        leaveHistory_interface.leaveHistorData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LeaveHistoryResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void GetAppliedLeavesMethod(final Activity activity, final Context context, String token, String LeaveType_Id, String Emp_Id, final AllAppliedLeave_interface allAppliedLeave_interface) {

        loadershowwithMsg(context, "We are Fetching your Applied Leave History  from server.");

        Call<AllAppliedLeaveResponse> responseCall = ApiClient.getClient().getAllAppliedLeavesAPi(token, Emp_Id, LeaveType_Id
        );
        responseCall.enqueue(new Callback<AllAppliedLeaveResponse>() {
            @Override
            public void onResponse(Call<AllAppliedLeaveResponse> call, Response<AllAppliedLeaveResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        allAppliedLeave_interface.AllAppliedleaveData(response.body().getData().getCCLDetails());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllAppliedLeaveResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void GetAppliedLeavesMethod(final Activity activity, final Context context, String token, String UserID, final AllAssignmentData_interface allAssignmentData_interface) {

        loadershowwithMsg(context, "We are Fetching your All Assignments  from server.");

        Call<AssignmentListResponse> responseCall = ApiClient.getClient().getAllAssignmentListAPi(token, UserID
        );
        responseCall.enqueue(new Callback<AssignmentListResponse>() {
            @Override
            public void onResponse(Call<AssignmentListResponse> call, Response<AssignmentListResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        allAssignmentData_interface.AllAssignmentData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AssignmentListResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void GetAllAppliedOthersLeavesDatatMethod(final Activity activity, final Context context, String token, String leavetype_Id, final OthersLeavesApplyList_interface othersLeavesApplyList_interface) {

        loadershowwithMsg(context, "We are Fetching  all List of leaves of  selected  Leave Type from server.");

        Call<OthersLeaveApplyListingResponse> responseCall = ApiClient.getClient().getAllAppliedOthersLeavesAPi(token,
                leavetype_Id
        );
        responseCall.enqueue(new Callback<OthersLeaveApplyListingResponse>() {
            @Override
            public void onResponse(Call<OthersLeaveApplyListingResponse> call, Response<OthersLeaveApplyListingResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        othersLeavesApplyList_interface.OthersLeavesApplyListData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<OthersLeaveApplyListingResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetViversOthersLeavesStatusResultMethod(final Activity activity, final Context context, String token, String College_Id, String leavetype_Id, String role, String leaveId, final OthersLeavesApplyList_interface othersLeavesApplyList_interface) {

        loadershowwithMsg(context, "We are Fetching All Applied Leave History  from server.");

        Call<OthersLeaveApplyListingResponse> responseCall = ApiClient.getClient().getViewersLeaveStatusListAPi(token,
                College_Id,
                leavetype_Id,
                role, leaveId
        );
        responseCall.enqueue(new Callback<OthersLeaveApplyListingResponse>() {
            @Override
            public void onResponse(Call<OthersLeaveApplyListingResponse> call, Response<OthersLeaveApplyListingResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        othersLeavesApplyList_interface.OthersLeavesApplyListData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<OthersLeaveApplyListingResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetLeavesStatusResultMethod(final Activity activity, final Context context, String token, String College_Id, String leavetype_Id, String role, final LeaveStatus_interface leaveStatus_interface) {

        loadershowwithMsg(context, "We are Fetching All Applied Leave History  from server.");

        Call<LeaveStatusResponse> responseCall = ApiClient.getClient().getLeaveStatusListAPi(token,
                College_Id,
                leavetype_Id,
                role
        );
        responseCall.enqueue(new Callback<LeaveStatusResponse>() {
            @Override
            public void onResponse(Call<LeaveStatusResponse> call, Response<LeaveStatusResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        leaveStatus_interface.LeaveStatusData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LeaveStatusResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetCclDetailforAuthorityDatatMethod(final Activity activity, final Context context, String token, String leavetype_Id, final CclDetailforAuthority_interface cclDetailforAuthorityInterface) {

        loadershowwithMsg(context, "We are Fetching Detail of selected  Leave from server.");

        Call<CclDetailForAuthorityResponse> responseCall = ApiClient.getClient().getCclDetailforAuthorityDataAPi(token,
                leavetype_Id
        );
        responseCall.enqueue(new Callback<CclDetailForAuthorityResponse>() {
            @Override
            public void onResponse(Call<CclDetailForAuthorityResponse> call, Response<CclDetailForAuthorityResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        cclDetailforAuthorityInterface.CclDetailForAuthorityData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<CclDetailForAuthorityResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void GetOthersLeaveDetailforAuthorityDatatMethod(final Activity activity, final Context context, String token, String leave_Id, final OthersLeaveDetailforAuthority_interface anInterface) {

        loadershowwithMsg(context, "We are Fetching Detail of selected  Leave from server.");

        Call<OthersLeaveDetailForViewerForRemarkResponse> responseCall = ApiClient.getClient().getOthersLeaveDetailforAuthorityDataAPi(token,
                leave_Id
        );
        responseCall.enqueue(new Callback<OthersLeaveDetailForViewerForRemarkResponse>() {
            @Override
            public void onResponse(Call<OthersLeaveDetailForViewerForRemarkResponse> call, Response<OthersLeaveDetailForViewerForRemarkResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        anInterface.OthersLeaveDetailForAuthorityData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<OthersLeaveDetailForViewerForRemarkResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void LeaveTypeMethod(final Activity activity, final Context context, String token, final Leavetype_interface leavetype_interface) {

        loadershowwithMsg(context, "We are Fetching all Leave Types from server.");

        Call<LeaveTypeResponse> responseCall = ApiClient.getClient().GetLeaveTypeApi(token);
        responseCall.enqueue(new Callback<LeaveTypeResponse>() {
            @Override
            public void onResponse(Call<LeaveTypeResponse> call, Response<LeaveTypeResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getResponse() == 200) {

                        leavetype_interface.LeavetypeData(response.body().getData());


                    } else {

                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LeaveTypeResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void cclPostDataMethod(final Activity activity, final Context context,
                                  String token,
                                  RequestBody emp_Id,
                                  RequestBody college_Id,
                                  RequestBody designation_Id,
                                  RequestBody probation_Period,
                                  RequestBody total_Child,
                                  RequestBody child_name,
                                  RequestBody child_dob,
                                  RequestBody child_dob_18,
                                  RequestBody leave_pupose,
                                  RequestBody total_leave_required,
                                  RequestBody leave_start_date,
                                  RequestBody leave_end_date,
                                  RequestBody total_leave_availed,
                                  RequestBody regular,
                                  RequestBody sp,
                                  RequestBody wl,
                                  RequestBody el,
                                  MultipartBody.Part previousOrderFileName,
                                  MultipartBody.Part child_BC_FileName,
                                  MultipartBody.Part affidavitFileName) {

        loadershowwithMsg(context, "Form Uploading process is going on...");
        Call<CclDataResponse> userpost_responseCall = ApiClient.getClient().cclDataApi(
                token,
                emp_Id,
                college_Id,
                designation_Id,
                probation_Period,
                total_Child,
                child_name,
                child_dob,
                child_dob_18,
                leave_pupose,
                total_leave_required,
                leave_start_date,
                leave_end_date,
                total_leave_availed,
                regular,
                sp,
                wl,
                el,
                previousOrderFileName,
                child_BC_FileName,
                affidavitFileName);
        userpost_responseCall.enqueue(new Callback<CclDataResponse>() {

            @Override
            public void onResponse(Call<CclDataResponse> call, Response<CclDataResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Form Uploaded successfully", "Form Uploading process completed successful.");
                    } else {
                        dailogError(context, "Error!", "Server is busy,Form Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<CclDataResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void othersLeavePostDataMethod(final Activity activity, final Context context,
                                          String token,
                                          RequestBody Leave_Type_Id,
                                          RequestBody emp_Id,
                                          RequestBody college_Id,
                                          RequestBody designation_Id,
                                          RequestBody probation_Period,
                                          RequestBody leave_pupose,
                                          RequestBody total_leave_required,
                                          RequestBody leave_start_date,
                                          RequestBody leave_end_date,
                                          RequestBody total_leave_availed,
                                          RequestBody aspecteddate,
                                          MultipartBody.Part documentFileName
    ) {

        loadershowwithMsg(context, "Form Uploading process is going on...");


        Call<OthersLeaveApplyResponse> userpost_responseCall = ApiClient.getClient().othersLeaveApplyApi(
                token,
                Leave_Type_Id,
                emp_Id,
                college_Id,
                designation_Id,
                probation_Period,
                leave_pupose,
                total_leave_required,
                leave_start_date,
                leave_end_date,
                total_leave_availed,
                aspecteddate,
                documentFileName);
        userpost_responseCall.enqueue(new Callback<OthersLeaveApplyResponse>() {

            @Override
            public void onResponse(Call<OthersLeaveApplyResponse> call, Response<OthersLeaveApplyResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Form Uploaded successfully", "Form Uploading process completed successful.");
                    } else {
                        dailogError(context, "Error!", "Server is busy,Form Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<OthersLeaveApplyResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void CreateAssignmentPostDataMethod(final Activity activity, final Context context,
                                               String token,
                                               RequestBody SessionID,
                                               RequestBody SemesterID,
                                               RequestBody AsgnDesc,
                                               RequestBody assignment_start_date,
                                               RequestBody assignment_end_date,
                                               RequestBody CreatedBy,
                                               MultipartBody.Part documentFileName
    ) {

        loadershowwithMsg(context, "Assignment Uploading process is going on...");

///SessionID
//SemesterID
//AsgnDesc
//startdate
//enddate
//Document
//createdBy
        Call<AddAssignmentResponse> userpost_responseCall = ApiClient.getClient().addAssignmentApi(
                token,
                SessionID,
                SemesterID,
                AsgnDesc,
                assignment_start_date,
                assignment_end_date,
                CreatedBy,
                documentFileName);
        userpost_responseCall.enqueue(new Callback<AddAssignmentResponse>() {

            @Override
            public void onResponse(Call<AddAssignmentResponse> call, Response<AddAssignmentResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {
                    if (response.body().getResponse() == 200) {
                        dailogsuccessWithActivity(context, activity, "Assignment Uploaded successfully", "Assignment Uploading process completed successful.");
                    } else {
                        dailogError(context, "Error!", "Server is busy,Assignment Uploading process failed!,Please try after sometimes");


                    }


                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }


            }

            @Override
            public void onFailure(Call<AddAssignmentResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

*/
}
























































/*    public void allDistrict_listMethod(final Context context, final AllDistrict_interface category_interface) {

        loadershowwithMsg(context, "Getting All District List...");
        Call<AllDistrictListResponse> userpost_responseCall = ApiClient.getClient().getDistrictlistAPi();
        userpost_responseCall.enqueue(new Callback<AllDistrictListResponse>() {
            @Override
            public void onResponse(Call<AllDistrictListResponse> call, Response<AllDistrictListResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    category_interface.alldistric_list((ArrayList<AllDistrictListResponse.AllDistrict>) response.body().getAllDistrict());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllDistrictListResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allFacultydesignation_listMethod(final Context context, final AllFacultyDesignation_interface allFacultyDesignation_interface) {

        loadershowwithMsg(context, "Getting All Designation List...");
        Call<FacultyDesignationListResponse> userpost_responseCall = ApiClient.getClient().DESIGNATION_LIST_RESPONSE_CALL();
        userpost_responseCall.enqueue(new Callback<FacultyDesignationListResponse>() {
            @Override
            public void onResponse(Call<FacultyDesignationListResponse> call, Response<FacultyDesignationListResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().getResponse() == 200) {

                        GlobalClass.showtost(context, "" + response.message());
                        allFacultyDesignation_interface.allfaculty_list((ArrayList<FacultyDesignationListResponse.Datum>) response.body().getData());

                    } else {

                        GlobalClass.showtost(context, "" + response.message());
                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<FacultyDesignationListResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void allStateforOthers_listMethod(final Context context, final AllStateforOthers_interface allStateforOthers_interface) {

        loadershowwithMsg(context, "Getting All State List...");
        Call<AllStateForOthersResponse> userpost_responseCall = ApiClient.getClient().getAllStatelistAPiForOthers();
        userpost_responseCall.enqueue(new Callback<AllStateForOthersResponse>() {
            @Override
            public void onResponse(Call<AllStateForOthersResponse> call, Response<AllStateForOthersResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    allStateforOthers_interface.allStateforOthers_list((ArrayList<AllStateForOthersResponse.Datum>) response.body().getData());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllStateForOthersResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void getCollegeListOfDistrictMethod(final Context context, final AllCollegeofDistrict_interface allCollegeofDistrict_interface, String District_Id) {

        loadershowwithMsg(context, "Getting all college list of your district...");
        Call<CollegeListOfDistrictResponse> userpost_responseCall = ApiClient.getClient().getCollegeListOfDistrictAPi(District_Id);
        userpost_responseCall.enqueue(new Callback<CollegeListOfDistrictResponse>() {
            @Override
            public void onResponse(Call<CollegeListOfDistrictResponse> call, Response<CollegeListOfDistrictResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    allCollegeofDistrict_interface.AllCollegeofDistrict_list((ArrayList<CollegeListOfDistrictResponse.AllCollegeByDistrictID>) response.body().getAllCollegeByDistrictID());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<CollegeListOfDistrictResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void getOtherDistrictMethod(final Context context, final AllDistrictOfState_interface allDistrictOfState_interface, String District_Id) {

        loadershowwithMsg(context, "Getting all  districts of your state ...");
        Call<AllDistrictForOthersResponse> userpost_responseCall = ApiClient.getClient().getDistrictforOthersAPi(District_Id);
        userpost_responseCall.enqueue(new Callback<AllDistrictForOthersResponse>() {
            @Override
            public void onResponse(Call<AllDistrictForOthersResponse> call, Response<AllDistrictForOthersResponse> response) {
                dailoghide(context);
                if (response.isSuccessful() && response.code() == 200) {
                    GlobalClass.showtost(context, "" + response.message());
                    allDistrictOfState_interface.AllDistrictforOthers_list((ArrayList<AllDistrictForOthersResponse.Datum>) response.body().getData());
                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllDistrictForOthersResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                //Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                // Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void registrationPostDataMethodold(final Activity activity, final Context context, final RegistrationRequest request) {

        loadershowwithMsg(context, "Registration process is going on...");
        Call<RegistrationResponeold> userpost_responseCall = ApiClient.getClient().SignupUser(request);
        userpost_responseCall.enqueue(new Callback<RegistrationResponeold>() {

            @Override
            public void onResponse(Call<RegistrationResponeold> call, Response<RegistrationResponeold> response) {
                dailoghide(context);
                if (response.code() == 200) {
                    if (response.body().getStatusCode() == 200) {
                        dailogsuccessWithActivity(context, activity, "Registration successful", "Registration process completed successful.");
                    } else {

                        dailogError(context, "Error!", "Server is busy,Registration process failed!,Please try after sometimes");
                    }


                } else {
                    //  GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponeold> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
                                      }

    *//*@Part("First_Name") RequestBody First_Name,
                                                                             @Part("Last_Name")RequestBody Last_Name,
                                                                             @Part("Mobile")RequestBody Mobile,
                                                                             @Part("Email")RequestBody Email,
                                                                             @Part("Gender")RequestBody Gender,
                                                                              @Part("College_Id")RequestBody College_Id,
                                                                             @Part("Course_Type")RequestBody Course_Type,
                                                                             @Part("Course_Name")RequestBody Course_Name,
                                                                             @Part("Course_Year")RequestBody Course_Year,
                                                                              @Part("District_Id")RequestBody District_Id,
                                                                             @Part("FcmToken_Id")RequestBody FcmToken_Id,
                                                                             @Part("AccountType")RequestBody AccountType,
                                                                             @Part("StateId")RequestBody StateId,
                                                                             @Part("otherDistrictName")RequestBody otherDistrictName,
                                                                             @Part("designation")RequestBody designation,
                                                                             @Part MultipartBody.Part  ImagePath);*//*
    public void registrationPostDataMethod(final Activity activity, final Context context, RequestBody First_Name, RequestBody Last_Name,
                                           RequestBody Mobile, RequestBody Email,RequestBody password,RequestBody confirmpassword,
                                           RequestBody Gender, RequestBody College_Id, RequestBody Course_Type, RequestBody Course_Name,  RequestBody Course_Year,  RequestBody District_Id,

                                           RequestBody FcmToken_Id, RequestBody AccountType, RequestBody StateId, RequestBody otherDistrictName,
                                           RequestBody designation, RequestBody profession, MultipartBody.Part image) {

        loadershowwithMsg(context, "Registration process is going on...");
        Call<CclDataResponse> userpost_responseCall = ApiClient.getClient().SignupUsermultipart(First_Name,Last_Name,Mobile,Email,password,confirmpassword,
                Gender,College_Id,Course_Type,Course_Name,Course_Year,District_Id,FcmToken_Id,AccountType,StateId,otherDistrictName,designation,profession,image);
        userpost_responseCall.enqueue(new Callback<CclDataResponse>() {

            @Override
            public void onResponse(Call<CclDataResponse> call, Response<CclDataResponse> response) {
                dailoghide(context);
                if (response.code() == 200) {
                    if (response.body().getStatusCode() == 200) {
                        dailogsuccessWithActivity(context, activity, "Registration successful", "Registration process completed successful.");
                    } else if (response.body().getStatusCode() == 304){

                        dailogError(context, "Error!", "This Number is already Registered with Us, you can try with other Number!");
                    }else {
                        dailogError(context, "Error!", "Server is busy,Registration process failed!,Please try after sometimes");


                    }


                    // GlobalClass.showtost(context, "" + response.body().getMessage());
                  *//*  new SweetAlertDialog(context)
                            .setTitleText(response.message())
                            .show();*//*

//                    Intent intent = new Intent(context, LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);

                   *//* if (response.body().getStatus().equals("1")) {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());
                        Intent intent2 = new Intent(context, AlloptionActivity.class);
                        context.startActivity(intent2);


                    } else {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());

                        // Intent intent = new Intent(context, EmpLoginView.class);
                        // context.startActivity(intent);
                    }*//*




 *//*    CSPreferences.putString(context,"auth_key",response.body().getData().getAuthKey());
                    CSPreferences.putString(context,"id",response.body().getData().getUser().getId().toString());
                    CSPreferences.putString(context,"name",response.body().getData().getUser().getName());
                    CSPreferences.putString(context,"last_name",response.body().getData().getUser().getLastName());
                    CSPreferences.putString(context,"email",response.body().getData().getUser().getEmail());
                    CSPreferences.putString(context,"type",response.body().getData().getUser().getType());
     *//*            // CSPreferences.putString(context,"otp",response.body().getData().getUser().getOtp().toString());

                } else {
                    //  GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<CclDataResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }




    public void profilepicPostDataMethod(final Activity activity, final Context context, RequestBody registration_id, MultipartBody.Part image) {

        loadershowwithMsg(context, "Your profile photo uploading is going on...");

        Call<ProfilePicSaveResponse> userpost_responseCall = ApiClient.getClient().userProfilePicUploading(registration_id,image);

        userpost_responseCall.enqueue(new Callback<ProfilePicSaveResponse>() {
            @Override
            public void onResponse(Call<ProfilePicSaveResponse> call, Response<ProfilePicSaveResponse> response) {
                dailoghide(context);
                if (response.code() == 200) {
                    if (response.body().getResponse() == 200) {
                        CSPreferences.putString(activity, "Profilepicurl", response.body().getData());
                        dailogsuccessWithActivity(context, activity, "Profile photo uploading successful", "profile photo uploading process completed successful.");
                    } else {

                        dailogError(context, "Error!", "Server is busy,profile photo uploading process failed!,Please try after sometimes");
                    }


                } else {
                    //  GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfilePicSaveResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }

    public void loginPostDataMethod(final Activity activity, final Context context, final LoginData_interface loginData_interface, LoginRequest request) {

        loadershowwithMsg(context, "We are veryfing your Mobile  to  send an OTP for login.");

        Call<LoginResponse> userpost_responseCall = ApiClient.getClient().LoginUser(request);
        userpost_responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    if (response.body().getStatusCode() == 200) {

                        dailogsuccess(context, "OTP Sent.", "Please wait for OTP.");


                        loginData_interface.alluserdata(response.body().getOTP(), response.body().getRegistrationId(), response.body().getStundentName(), response.body().getMobile(), response.body().getEmail(), response.body().getLatitude(), response.body().getLongitude(), response.body().getAdmissionportalUrl(), response.body().getProfilePic());

                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                  *//*  new SweetAlertDialog(context)
                            .setTitleText(response.message())
                            .show();*//*

//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);

                   *//* if (response.body().getStatus().equals("1")) {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());
                        Intent intent2 = new Intent(context, AlloptionActivity.class);
                        context.startActivity(intent2);


                    } else {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());

                        // Intent intent = new Intent(context, EmpLoginView.class);
                        // context.startActivity(intent);
                    }*//*




 *//*    CSPreferences.putString(context,"auth_key",response.body().getData().getAuthKey());
                    CSPreferences.putString(context,"id",response.body().getData().getUser().getId().toString());
                    CSPreferences.putString(context,"name",response.body().getData().getUser().getName());
                    CSPreferences.putString(context,"last_name",response.body().getData().getUser().getLastName());
                    CSPreferences.putString(context,"email",response.body().getData().getUser().getEmail());
                    CSPreferences.putString(context,"type",response.body().getData().getUser().getType());
     *//*            // CSPreferences.putString(context,"otp",response.body().getData().getUser().getOtp().toString());


                    } else {
                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                        dailogError(context, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");

                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


    public void StudentProfileDataMethod(final Activity activity, final Context context, final StudentProfileData_interface studentProfileData_interface, String Student_Reg_Id) {

        loadershowwithMsg(context, "We are Fetching your Data from server.");

        Call<StudentProfileResponse> studentProfileResponseCall = ApiClient.getClient().STUDENT_PROFILE_DATA_RESPONSE_CALL(Student_Reg_Id);
        studentProfileResponseCall.enqueue(new Callback<StudentProfileResponse>() {
            @Override
            public void onResponse(Call<StudentProfileResponse> call, Response<StudentProfileResponse> response) {
                dailoghide(context);
                if (response.isSuccessful()) {

                    // if (response.body().getStatusCode() == 200) {

                    //dailogsuccess(context, "OTP Sent.", "Please wait for OTP.");


                    studentProfileData_interface.StudentProfileData(response.body().getStudentProfile());

                    // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
                  *//*  new SweetAlertDialog(context)
                            .setTitleText(response.message())
                            .show();*//*

//                        Intent intent = new Intent(context, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);

                   *//* if (response.body().getStatus().equals("1")) {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());
                        Intent intent2 = new Intent(context, AlloptionActivity.class);
                        context.startActivity(intent2);


                    } else {
                        // CSPreferences.putString(context,"auth_key",response.body().getProfile().getSessionId());

                        // Intent intent = new Intent(context, EmpLoginView.class);
                        // context.startActivity(intent);
                    }*//*




 *//*    CSPreferences.putString(context,"auth_key",response.body().getData().getAuthKey());
                    CSPreferences.putString(context,"id",response.body().getData().getUser().getId().toString());
                    CSPreferences.putString(context,"name",response.body().getData().getUser().getName());
                    CSPreferences.putString(context,"last_name",response.body().getData().getUser().getLastName());
                    CSPreferences.putString(context,"email",response.body().getData().getUser().getEmail());
                    CSPreferences.putString(context,"type",response.body().getData().getUser().getType());
     *//*            // CSPreferences.putString(context,"otp",response.body().getData().getUser().getOtp().toString());


//                    } else {
//                        // GlobalClass.showtost(context, "This  Number is Not Registered with Us.");
//                        dailogError(context, "Mobile Number Not Found!", "This  Number is Not Registered with Us.");
//
//                    }

                } else {
                    GlobalClass.showtost(context, "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<StudentProfileResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();

                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }




*//*Registration_Id,
Longitude,
Latitude,
ImagePath,
ImageDate,
Description  Remarks*//*

    public void StudentDataMethod(
            final Activity activity, final Context context, String Registration_Id, String Description,
            final String Longitude, String ImageDate, final String Latitude, String ImagePath) {

        loadershowwithMsg(context, "Your Data is Uploading...");

        Call<StudentEventDataSaveResponse> userpost_responseCall =
                ApiClient.getClient().STUDENT_EVENT_DATA_SAVE_RESPONSE_CALL(Registration_Id, Description, Longitude, Latitude, ImageDate, ImagePath);
        userpost_responseCall.enqueue(new Callback<StudentEventDataSaveResponse>() {
            @Override
            public void onResponse(Call<StudentEventDataSaveResponse> call, final Response<StudentEventDataSaveResponse> response) {

                if (response.isSuccessful()) {

                    dailoghide(context);

                    dailogsuccessWithActivity(context, activity, "Good job!", "Your Data has been  Uploaded Successfully.");
                    CSPreferences.putString(context, "lativale", Latitude);
                    CSPreferences.putString(context, "longivalue", Longitude);

                   *//* SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
                    sweetAlertDialog.setTitle("Good job!");
                    sweetAlertDialog.setContentText("Your Data has been  Uploaded Successfully.");
                    sweetAlertDialog.setVolumeControlStream(2);
                    sweetAlertDialog.getAlerType();
                    sweetAlertDialog.changeAlertType(2);
                    sweetAlertDialog.setCanceledOnTouchOutside(false);
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            GlobalClass.showtost(context, "" + response.body().getMessage());
                            activity.finish();
                        }
                    });
                    sweetAlertDialog.show();*//*


                    if (response.body().getStatusCode() == 201) {
                        GlobalClass.showtost(context, "" + response.body().getMessage());
                        activity.finish();

                    } else {
                        GlobalClass.showtost(context, "" + response.body().getMessage());

                    }


                } else {
                    dailoghide(context);
                    GlobalClass.showtost(context, "" + response.message());
                }
            }


            @Override
            public void onFailure(Call<StudentEventDataSaveResponse> call, Throwable t) {

                dailoghide(context);
                t.printStackTrace();
                Toast.makeText(context, "Poor Connection." + t.toString(), Toast.LENGTH_SHORT).show();
                Log.d("dddddd", "onFailure: " + t.getMessage());
            }
        });
    }


}*/
