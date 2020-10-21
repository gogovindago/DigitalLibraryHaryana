package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Govind Kumar on 10/20/2020.
 * * PhpExpert
 * *  govind224556@gmail.com
 **/
public class SignupResponse {



    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Data data;



    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public String getSysMessage() {
        return sysMessage;
    }

    public void setSysMessage(String sysMessage) {
        this.sysMessage = sysMessage;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("phoneNo")
        @Expose
        private String phoneNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("libraryId")
        @Expose
        private int libraryId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("msgStatus")
        @Expose
        private String msgStatus;
        @SerializedName("purpose")
        @Expose
        private String purpose;
        @SerializedName("token")
        @Expose
        private String token;


        @SerializedName("Pic")
        @Expose
        private String Pic;

        public void setPic(String pic) {
            Pic = pic;
        }

        public String getPic() {
            return Pic;
        }

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

        public int getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(int libraryId) {
            this.libraryId = libraryId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getMsgStatus() {
            return msgStatus;
        }

        public void setMsgStatus(String msgStatus) {
            this.msgStatus = msgStatus;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }

}