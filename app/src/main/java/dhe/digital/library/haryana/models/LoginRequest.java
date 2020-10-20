package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {




    /*//"PhoneNo":"7018401817",
//"Password":"1234"*/

    @SerializedName("PhoneNo")
    @Expose
    private String PhoneNo;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("FCMToken")
    @Expose
    private String fCMToken;






    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
