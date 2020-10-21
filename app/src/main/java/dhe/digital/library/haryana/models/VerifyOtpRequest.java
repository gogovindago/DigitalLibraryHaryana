package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {


    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("otp")
    @Expose
    private String otp;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
