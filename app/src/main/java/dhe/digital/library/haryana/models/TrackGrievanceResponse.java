package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrackGrievanceResponse {
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

        @SerializedName("grievance_Id")
        @Expose
        private int grievanceId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("status_Date")
        @Expose
        private String statusDate;
        @SerializedName("status_Reason")
        @Expose
        private Object statusReason;

        public int getGrievanceId() {
            return grievanceId;
        }

        public void setGrievanceId(int grievanceId) {
            this.grievanceId = grievanceId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDate() {
            return statusDate;
        }

        public void setStatusDate(String statusDate) {
            this.statusDate = statusDate;
        }

        public Object getStatusReason() {
            return statusReason;
        }

        public void setStatusReason(Object statusReason) {
            this.statusReason = statusReason;
        }
    }

}
