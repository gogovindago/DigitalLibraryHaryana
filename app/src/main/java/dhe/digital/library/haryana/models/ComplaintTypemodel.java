package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplaintTypemodel {


    @SerializedName("ComplaintTypeId")
    @Expose
    private Integer ComplaintTypeId;

    @SerializedName("ComplaintTypeName")
    @Expose
    private String ComplaintTypeName;

    public Integer getComplaintTypeId() {
        return ComplaintTypeId;
    }

    public void setComplaintTypeId(Integer complaintTypeId) {
        ComplaintTypeId = complaintTypeId;
    }

    public String getComplaintTypeName() {
        return ComplaintTypeName;
    }

    public void setComplaintTypeName(String complaintTypeName) {
        ComplaintTypeName = complaintTypeName;
    }
}