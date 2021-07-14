package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommitteeDetailsResponse {

    @SerializedName("response")
    @Expose
    private int response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("committeeId")
        @Expose
        private int committeeId;
        @SerializedName("designationId")
        @Expose
        private int designationId;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("memberName")
        @Expose
        private String memberName;

        public int getCommitteeId() {
            return committeeId;
        }

        public void setCommitteeId(int committeeId) {
            this.committeeId = committeeId;
        }

        public int getDesignationId() {
            return designationId;
        }

        public void setDesignationId(int designationId) {
            this.designationId = designationId;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

    }
}
