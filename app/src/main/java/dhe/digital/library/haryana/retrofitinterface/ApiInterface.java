package dhe.digital.library.haryana.retrofitinterface;

import dhe.digital.library.haryana.models.AlumniAchievementsResponse;
import dhe.digital.library.haryana.models.BlogCommentsListResponse;
import dhe.digital.library.haryana.models.BlogCreateResponse;
import dhe.digital.library.haryana.models.BlogListResponse;
import dhe.digital.library.haryana.models.BookRecordByLibIdResponse;
import dhe.digital.library.haryana.models.BookSuggestionRequest;
import dhe.digital.library.haryana.models.BookSuggestionResponse;
import dhe.digital.library.haryana.models.BooksDetailResponse;
import dhe.digital.library.haryana.models.CommentsOnBlogRequest;
import dhe.digital.library.haryana.models.CommentsOnBlogResponse;
import dhe.digital.library.haryana.models.CommitteeDetailsResponse;
import dhe.digital.library.haryana.models.ContactUsRequest;
import dhe.digital.library.haryana.models.ContactUsResponse;
import dhe.digital.library.haryana.models.DonateBookResponse;
import dhe.digital.library.haryana.models.ForgotPasswordRequest;
import dhe.digital.library.haryana.models.ForgotPasswordResponse;
import dhe.digital.library.haryana.models.GetbooktypeResponse;
import dhe.digital.library.haryana.models.GetlanguageResponse;
import dhe.digital.library.haryana.models.HearingSpeechimpairedDataResponse;
import dhe.digital.library.haryana.models.HomePageResponse;
import dhe.digital.library.haryana.models.ImportantLinksTypeResponse;
import dhe.digital.library.haryana.models.InsertGrievanceRequest;
import dhe.digital.library.haryana.models.InsertGrievanceResponse;
import dhe.digital.library.haryana.models.LibraryEventsActivitieAlbumDetailResponse;
import dhe.digital.library.haryana.models.LibraryEventsActivitieAlbumResponse;
import dhe.digital.library.haryana.models.LibraryFacilitiesResponse;
import dhe.digital.library.haryana.models.LibraryGalleryResponse;
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
import dhe.digital.library.haryana.models.StaffDetailsResponse;
import dhe.digital.library.haryana.models.TrackGrievanceResponse;
import dhe.digital.library.haryana.models.VerifyOtpRequest;
import dhe.digital.library.haryana.models.VerifyOtpResponse;
import dhe.digital.library.haryana.models.ViewAllResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface ApiInterface {


    @POST("BookSuggestion")
    Call<BookSuggestionResponse> BookSuggestionApi(@Body BookSuggestionRequest request);

    @POST("InsertGrievance")
    Call<InsertGrievanceResponse> insertGrievanceApi(@Body InsertGrievanceRequest request);


    @POST("UserRegister")
    Call<SignupResponse> signupUser(@Body SignupRequest request);


    //http://112.196.99.107:81/api/commonapi/getbooktype
    @GET("getbooktype")
    Call<GetbooktypeResponse> getbooktypeAPi();

    //http://112.196.99.107:81/api/commonapi/getlanguage
    @GET("getlanguage")
    Call<GetlanguageResponse> getlanguageAPi();


    //http://112.196.99.107:81/api/commonapi/ImportantLinksType
    @GET("ImportantLinksType")
    Call<ImportantLinksTypeResponse> getImportantLinksTypeAPi();


    //http://112.196.99.107:81/api/commonapi/StaffDetails/8
    @GET("StaffDetails/{LibraryId}")
    Call<StaffDetailsResponse> getStaffDetailsAPi(@Path("LibraryId") String s);


    //http://112.196.99.107:81/api/commonapi/CommitteeDetails/8
    @GET("CommitteeDetails/{LibraryId}")
    Call<CommitteeDetailsResponse> getCommitteeDetailsAPi(@Path("LibraryId") String s);


    //http://112.196.99.107:81/api/commonapi/TrackGrievance/1001
    @GET("TrackGrievance/{TrackGrievanceId}")
    Call<TrackGrievanceResponse> getLibraryTrackGrievanceIdAPi(@Path("TrackGrievanceId") String s);

    //http://112.196.99.107:81/api/commonapi/LibraryBlogsComments/1
    @GET("LibraryBlogsComments/{BlogId}")
    Call<BlogCommentsListResponse> getCommentsByBlogsIdAPi(@Path("BlogId") String s);

    // http://112.196.99.107:81/api/commonapi/BlogComments
    @POST("BlogComments")
    Call<CommentsOnBlogResponse> CommentOnBlogData(@Body CommentsOnBlogRequest request);


    //http://112.196.99.107:81/api/commonapi/LibraryFacilities/8
    @GET("LibraryAlumniAchievements/{LibraryId}")
    Call<AlumniAchievementsResponse> getLibraryAlumniAchievementsByLibIdAPi(@Path("LibraryId") String s);


    //http://112.196.99.107:81/api/commonapi/LibraryFacilities/8
    @GET("LibraryFacilities/{LibraryId}")
    Call<LibraryFacilitiesResponse> getLibraryFacilitiesByLibIdAPi(@Path("LibraryId") String s);


    // http://112.196.99.107:81/api/commonapi/GetBookRecordDetail
    @GET("GetBookRecordDetail/{BookId}")
    Call<BooksDetailResponse> getBookDetailByLibIdResponseDataAPi(@Path("BookId") String s);

    // http://112.196.99.107:81/api/commonapi/LibraryEventsActivities
    // @GET("LibraryEventsActivities/{LibraryId}")

    @GET("GetEventsByRank/{LibraryId}")
    Call<LibraryEventsActivitieAlbumResponse> getLibraryEventsActivitiesLibIdResponseDataAPi(@Path("LibraryId") String s);
//http://112.196.99.107:81/api/commonapi/AllEventsActivitiesByTitle/Celebration of 75th Independence Day/18-08-2021/16

    @GET("AllEventsActivitiesByTitle/{EventTitle}/{EventDate}/{LibraryId}")
    Call<LibraryEventsActivitieAlbumDetailResponse> getLibraryEventsActivitieAlbumDetailDataAPi(
            @Path("EventTitle") String eventTitle,
            @Path("EventDate") String eventDate,
            @Path("LibraryId") String s);


    //http://112.196.99.107:81/api/commonapi/DonateBook
    @Multipart
    @POST("DonateBook")
    Call<DonateBookResponse> DonateBookDataAPi(@Part("Book_Title") RequestBody BlogTitle,
                                               @Part("LibraryId") RequestBody LibraryId,
                                               @Part("LanguageId") RequestBody LanguageId,
                                               @Part("CreatedBy") RequestBody CreatedBy,
                                               @Part("BookTypeId") RequestBody BookTypeId,
                                               @Part("BookIframe") RequestBody BookIframe,
                                               @Part("BookIframeUrl") RequestBody BookIframeUrl,
                                               @Part MultipartBody.Part BookImageext);



    /*[{"key":"Book_Title","value":"ABCDEFWZ","description":"","type":"text","enabled":true},
    {"key":"LibraryId","value":"8","description":"","type":"text","enabled":true}
    ,{"key":"LanguageId","value":"1","description":"","type":"text","enabled":true}
    ,{"key":"CreatedBy","value":"ABCDEF","description":"","type":"text","enabled":true}
    ,{"key":"BookTypeId","value":"1","description":"","type":"text","enabled":true}
    ,{"key":"BookIframe","value":"","description":"","type":"text","enabled":true},
    {"key":"BookIframeUrl","value":"","description":"","type":"text","enabled":true},
    {"key":"BookImageext","description":"","type":"file","enabled":true,"value":*/


    //http://112.196.99.107:81/api/commonapi/CreateBlog
    @Multipart
    @POST("CreateBlog")
    Call<BlogCreateResponse> CreateBlogDataAPi(@Part("BlogTitle") RequestBody BlogTitle,
                                               @Part("BlogBody") RequestBody BlogBody,
                                               @Part("PhoneNo") RequestBody PhoneNo,
                                               @Part("CreatedBy") RequestBody CreatedBy,
                                               @Part("LibraryUrl") RequestBody LibrayUrl,
                                               @Part MultipartBody.Part Blog_Image_ext);


    // http://112.196.99.107:81/api/commonapi/LibraryBlogs
    @GET("LibraryBlogs/{LibraryId}")
    Call<BlogListResponse> getBlogListDataAPi(@Path("LibraryId") String s);


    // http://112.196.99.107:81/api/commonapi/GetBookRecord
    @GET("GetBookRecord/{LibraryId}")
    Call<BookRecordByLibIdResponse> getBookRecordByLibIdResponseDataAPi(@Path("LibraryId") String s);


    @GET("GetAllHomeImages/{typeId}")
    Call<ViewAllResponse> getAllDataAPi(@Path("typeId") String s);

    //https://localhost:44375/api/commonapi/GetLibrariesByTypeId/1
    @GET("GetLibrariesByTypeId/{typeId}")
    Call<LibraryTypeByIdResponse> getLibraryTypeByIdDataAPi(@Path("typeId") String s);

    //http://112.196.99.107:81/api/commonapi/GetProfile/9499486861
    @GET("GetProfile/{MobileNo}")
    Call<ProfileDataResponse> getProfileDataAPi(@Path("MobileNo") String s);

    //http://112.196.99.107:81/api/commonapi/SearchData/mri
    @GET("SearchData/{searchingData}")
    Call<SearchResponse> getsearchingDataAPi(@Path("searchingData") String s);

    //http://112.196.99.107:81/api/commonapi/LibraryGallery/8
    @GET("LibraryGallery/{LibraryId}")
    Call<LibraryGalleryResponse> getLibraryGalleryDataAPi(@Path("LibraryId") String s);

    @GET("GetHomeImages")
    Call<HomePageResponse> getHomePageDataAPi();


    @GET("GetAllLibrariesType")
    Call<LibraryTypeAndCoutResponse> getAllLibraryTypeAPi();

    //http://112.196.99.107:81/api/commonapi/GetHearingSpeechimpairedData
    @GET("GetHearingSpeechimpairedData")
    Call<HearingSpeechimpairedDataResponse> getAllGetHearingSpeechimpairedDataAPi();


    //http://112.196.99.107:81/DigitalLibrary/api/commonapi/ContactUs
    @POST("ContactUs")
    Call<ContactUsResponse> contactusapi(@Body ContactUsRequest request);


    //http://112.196.99.107:81/DigitalLibrary/api/commonapi/UserLogin/7018401817/1234
    @POST("UserLogin")
    Call<LoginResponse> LoginUser(@Body LoginRequest request);

    // http://112.196.99.107:81/api/commonapi/ForgetPassword
    @POST("ForgetPassword")
    Call<ForgotPasswordResponse> ForgetPasswordUser(@Body ForgotPasswordRequest request);

    // http://112.196.99.107:81/api/commonapi/ReadData
    @POST("ReadData")
    Call<ReadViewsCountResponse> ReadCountIncreaseData(@Body ReadViewsCountRequest request);

    //http://112.196.99.107:81/api/commonapi/VerifyOTP
    @POST("VerifyOTP")
    Call<VerifyOtpResponse> verifyOTP(@Body VerifyOtpRequest request);


//    @GET("UserLogin/{PhoneNo}/{Password}/{FcmToken}")
//    Call<LoginResponse> LoginUser(@Header("PhoneNo") String token, @Path("Password") String s, @Path("FcmToken") String fcmtoken);
// @GET("UserLogin/{PhoneNo}/{Password}/{FcmToken}")
//    Call<LoginResponse> LoginUser(@Query("PhoneNo") String token, @Query("Password") String s, @Query("FcmToken") String fcmtoken);


    /*

        @POST("User/login")
        Call<LoginResponse> LoginUser(@Body LoginRequest request);

        //username
    //DOB  (format example "1989-01-07")
        @FormUrlEncoded
        @POST("commonapi/UpdatePassword")
        Call<ForgotPasswordResponse> ForgotPasswordApi(@Field("username") String Registration_Id,
                                                       @Field("DOB") String Description);


        @GET("User/GetProfile/{employeeUser_Id}")
        Call<EmployeeProfiledataResponse> EMPLOYEE_PROFILE_DATA_RESPONSE_CALL(@Header("Authorization") String token, @Path("employeeUser_Id") String s);


        @GET("commonapi/GetLeaveType")
        Call<LeaveTypeResponse> GetLeaveTypeApi(@Header("Authorization") String token);

        @GET("commonapi/GetSessions")
        Call<SessionResponse> getsessionAPi(@Header("Authorization") String token);


        @GET("commonapi/GetSemesters")
        Call<SemesterResponse> getsemesterAPi(@Header("Authorization") String token);

        //CollegeId
    //SubjectId
        @GET("commonapi/GetStaffPositions")
        Call<GetStaffPositionsResponse> getStaffPositionsAPi(@Header("Authorization") String token, @Query("CollegeId") String CollegeId, @Query("SubjectId") String SubjectId);

        Emp_Id
        LeaveType_Id
    @GET("commonapi/Getleavefromhistorys")
    Call<LeaveHistoryResponse> getLeaveHistoryAPi(@Header("Authorization") String token, @Query("Emp_Id") String Emp_Id, @Query("LeaveType_Id") String LeaveType_Id);


    @GET("commonapi/GetEmpDetail/{Emp_Id}")
    Call<AllAppliedLeaveResponse> getAllAppliedLeavesAPi(@Header("Authorization") String token, @Path("Emp_Id") String Emp_Id, @Query("LeaveType_Id") String LeaveType_Id);


    @GET("commonapi/GetAssignmentByUserID/{UserID}")
    Call<AssignmentListResponse> getAllAssignmentListAPi(@Header("Authorization") String token, @Path("UserID") String UserID);


    //http://112.196.99.108:91/api/commonapi/GetCCLStatusList/107/1/College
    @GET("commonapi/GetCCLStatusList/{College_Id}/{leavetype_Id}/{role}")
    Call<LeaveStatusResponse> getLeaveStatusListAPi(@Header("Authorization") String token, @Path("College_Id") String leavetype_Id, @Path("leavetype_Id") String LeaveType_Id, @Path("role") String role);

    //http://112.196.99.108:91/api/commonapi/GetCCLStatusList/107/1/College
    @GET("commonapi/GetLeaveStatusList/{College_Id}/{leavetype_Id}/{role}/{leaveId}")
    Call<OthersLeaveApplyListingResponse> getViewersLeaveStatusListAPi(@Header("Authorization") String token, @Path("College_Id") String leavetype_Id, @Path("leavetype_Id") String LeaveType_Id, @Path("role") String role,@Path("leaveId") String leaveId);

    //http://112.196.99.108:91/api/commonapi/GetCCLDetails/85
    @GET("commonapi/GetCCLDetails/{leavetype_Id}")
    Call<CclDetailForAuthorityResponse> getCclDetailforAuthorityDataAPi(@Header("Authorization") String token, @Path("leavetype_Id") String LeaveType_Id);




   // http://112.196.99.108:91/api/commonapi/GetLeaveDetails/6
    @GET("commonapi/GetLeaveDetails/{leave_Id}")
    Call<OthersLeaveDetailForViewerForRemarkResponse> getOthersLeaveDetailforAuthorityDataAPi(@Header("Authorization") String token, @Path("leave_Id") String LeaveType_Id);








    // http://112.196.99.108:91/api/commonapi/GetLeaveList/2

    @GET("commonapi/GetLeaveList/{LeaveType_Id}")
    Call<OthersLeaveApplyListingResponse> getAllAppliedOthersLeavesAPi(@Header("Authorization") String token, @Path("LeaveType_Id") String LeaveType_Id);


///SessionID
//SemesterID
//AsgnDesc
//startdate
//enddate
//Document
//createdBy

//    in multipart with fileupload with token
//SessionID
//SemesterID
//AsgnDesc
//startdate
//enddate
//Document
//createdBy

    @Multipart
    @POST("commonapi/AddAssignments")
    Call<AddAssignmentResponse> addAssignmentApi(@Header("Authorization") String token,
                                                 @Part("SessionID") RequestBody SessionID,
                                                 @Part("SemesterID") RequestBody SemesterID,
                                                 @Part("AsgnDesc") RequestBody AsgnDesc,
                                                 @Part("startdate") RequestBody startdate,
                                                 @Part("enddate") RequestBody enddate,
                                                 @Part("CreatedBy") RequestBody CreatedBy,
                                                 @Part MultipartBody.Part Document);


    @Multipart
    @POST("commonapi/InsertCCL")
    Call<CclDataResponse> cclDataApi(@Header("Authorization") String token,
                                     @Part("emp_Id") RequestBody emp_Id,
                                     @Part("college_Id") RequestBody college_Id,
                                     @Part("designation_Id") RequestBody designation_Id,
                                     @Part("probation_Period") RequestBody probation_Period,
                                     @Part("total_Child") RequestBody total_Child,
                                     @Part("child_name") RequestBody child_name,
                                     @Part("child_dob") RequestBody child_dob,
                                     @Part("child_dob_18") RequestBody child_dob_18,
                                     @Part("leave_pupose") RequestBody leave_pupose,
                                     @Part("total_leave_required") RequestBody total_leave_required,
                                     @Part("leave_start_date") RequestBody leave_start_date,
                                     @Part("leave_end_date") RequestBody leave_end_date,
                                     @Part("total_leave_availed") RequestBody total_leave_availed,
                                     @Part("regular") RequestBody regular,
                                     @Part("sp") RequestBody sp,
                                     @Part("wl") RequestBody wl,
                                     @Part("el") RequestBody el,
                                     @Part MultipartBody.Part previousOrderFileName,
                                     @Part MultipartBody.Part child_BC_FileName,
                                     @Part MultipartBody.Part affidavitFileName);


    @Multipart
    @POST("commonapi/LeaveApply")
    Call<OthersLeaveApplyResponse> othersLeaveApplyApi(@Header("Authorization") String token,
                                                       @Part("Leave_Type_Id") RequestBody Leave_Type_Id,
                                                       @Part("emp_Id") RequestBody emp_Id,
                                                       @Part("College_Id") RequestBody college_Id,
                                                       @Part("Designation_Id") RequestBody designation_Id,
                                                       @Part("Probation_Period") RequestBody probation_Period,
                                                       @Part("leave_pupose") RequestBody leave_pupose,
                                                       @Part("total_leave_required") RequestBody total_leave_required,
                                                       @Part("leave_start_date") RequestBody leave_start_date,
                                                       @Part("leave_end_date") RequestBody leave_end_date,
                                                       @Part("total_leave_availed") RequestBody total_leave_availed,
                                                       @Part("Expected_Date") RequestBody aspecteddate,
                                                       @Part MultipartBody.Part document);


    @POST("commonapi/GenerateOtp")
    Call<OtpResponse> otpDataApi(@Header("Authorization") String token, @Body OtpRequest request);


    @POST("commonapi/CCLStatusChange")
    Call<CclStatusChangeResponse> CCLStatusChangeDataApi(@Header("Authorization") String token, @Body CclStatusChangeRequest request);

    //http://112.196.99.108:91/api/commonapi/LeaveStatusChange
    @POST("commonapi/LeaveStatusChange")
    Call<OthersLeaveStatusChangeResponse> OthersLeaveStatusChangeDataApi(@Header("Authorization") String token, @Body OthersLeaveStatusChangeRequest request);






    @GET("district")
    Call<AllDistrictListResponse> getDistrictlistAPi();

    @GET("getdesignation")
    Call<FacultyDesignationListResponse> DESIGNATION_LIST_RESPONSE_CALL();

    @GET("GetAllState")
    Call<AllStateForOthersResponse> getAllStatelistAPiForOthers();

    @GET("GetDistritByState/{District_Id}")
    Call<AllDistrictForOthersResponse> getDistrictforOthersAPi(@Path("District_Id") String s);


    @GET("college/")
    Call<CollegeListOfDistrictResponse> getCollegeListOfDistrictAPi(@Query("District_Id") String s);

    @POST("register")
    Call<RegistrationResponeold> SignupUser(@Body RegistrationRequest request);


    @Multipart
    @POST("Saveprofile")
    Call<ProfilePicSaveResponse> userProfilePicUploading(@Part("Registration_Id") RequestBody customer_id,
                                                         @Part MultipartBody.Part image);

    @POST("login")
    Call<LoginRespone> LoginUser(@Body LoginRequest request);



   @Multipart
    @POST("Register")
    Call<RegistrationRespone> SignupUsermultipart(@Part("First_Name") RequestBody First_Name,
                                                  @Part("Last_Name") RequestBody Last_Name,
                                                  @Part("Mobile") RequestBody Mobile,
                                                  @Part("Email") RequestBody Email,
                                                  @Part("Password") RequestBody password,
                                                  @Part("Confirmpassword") RequestBody confirmpassword,
                                                  @Part("Gender") RequestBody Gender,
                                                  @Part("College_Id") RequestBody College_Id,
                                                  @Part("Course_Type") RequestBody Course_Type,
                                                  @Part("Course_Name") RequestBody Course_Name,
                                                  @Part("Course_Year") RequestBody Course_Year,
                                                  @Part("District_Id") RequestBody District_Id,
                                                  @Part("FcmToken_Id") RequestBody FcmToken_Id,
                                                  @Part("AccountType") RequestBody AccountType,
                                                  @Part("StateId") RequestBody StateId,
                                                  @Part("otherDistrictName") RequestBody otherDistrictName,
                                                  @Part("designation") RequestBody designation,
                                                  @Part("profession") RequestBody profession,
                                                  @Part MultipartBody.Part ImagePath);
    @GET("StudentProfile")
    Call<StudentProfileResponse> STUDENT_PROFILE_DATA_RESPONSE_CALL(@Query("Registration_Id") String Registration_Id);

    @FormUrlEncoded
    @POST("student")
    Call<StudentEventDataSaveResponse> STUDENT_EVENT_DATA_SAVE_RESPONSE_CALL(@Field("Registration_Id") String Registration_Id,
                                                                             @Field("Description") String Description,
                                                                             @Field("Longitude") String Longitude,
                                                                             @Field("Latitude") String Latitude,
                                                                             @Field("ImageDate") String ImageDate,
                                                                             @Field("ImagePath") String ImagePath);
*/

}
