package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertGrievanceRequest {
    @SerializedName("Complaint_Type_Id")
    @Expose
    private String complaintTypeId;
    @SerializedName("First_Name")
    @Expose
    private String firstName;
    @SerializedName("Middle_Name")
    @Expose
    private String middleName;
    @SerializedName("Last_Name")
    @Expose
    private String lastName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("LibraryId")
    @Expose
    private String libraryId;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Complaint")
    @Expose
    private String complaint;

    public String getComplaintTypeId() {
        return complaintTypeId;
    }

    public void setComplaintTypeId(String complaintTypeId) {
        this.complaintTypeId = complaintTypeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

}