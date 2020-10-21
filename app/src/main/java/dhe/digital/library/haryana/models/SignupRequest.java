package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Govind Kumar on 10/20/2020.
 * * PhpExpert
 * *  govind224556@gmail.com
 **/
public class SignupRequest {



    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("LibraryId")
    @Expose
    private String libraryId;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("FCMToken")
    @Expose
    private String fCMToken;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFCMToken() {
        return fCMToken;
    }

    public void setFCMToken(String fCMToken) {
        this.fCMToken = fCMToken;
    }

}