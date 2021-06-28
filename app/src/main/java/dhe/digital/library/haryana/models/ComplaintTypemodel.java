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


    public Integer getLibraryTypeId() {
        return ComplaintTypeId;
    }

    public void setLibraryTypeId(Integer ComplaintTypeId) {
        this.ComplaintTypeId = ComplaintTypeId;
    }

    public String getLibraryType() {
        return ComplaintTypeName;
    }

    public void setLibraryType(String ComplaintTypeName) {
        this.ComplaintTypeName = ComplaintTypeName;
    }



}