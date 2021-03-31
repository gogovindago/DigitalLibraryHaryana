package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginResponse {


    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
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

        @SerializedName("pic")
        @Expose
        private String Pic;


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
        private Integer libraryId;
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


        @SerializedName("userType")
        @Expose
        private String userType;


        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }


        public String getPic() {
            return Pic;
        }

        public void setPic(String pic) {
            Pic = pic;
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

        public Integer getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(Integer libraryId) {
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
