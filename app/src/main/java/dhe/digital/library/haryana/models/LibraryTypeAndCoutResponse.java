package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LibraryTypeAndCoutResponse {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public static class Datum {

        @SerializedName("libraryTypeId")
        @Expose
        private Integer libraryTypeId;
        @SerializedName("libraryType")
        @Expose
        private String libraryType;
        @SerializedName("totalLibraries")
        @Expose
        private Integer totalLibraries;

        public Integer getLibraryTypeId() {
            return libraryTypeId;
        }

        public void setLibraryTypeId(Integer libraryTypeId) {
            this.libraryTypeId = libraryTypeId;
        }

        public String getLibraryType() {
            return libraryType;
        }

        public void setLibraryType(String libraryType) {
            this.libraryType = libraryType;
        }

        public Integer getTotalLibraries() {
            return totalLibraries;
        }

        public void setTotalLibraries(Integer totalLibraries) {
            this.totalLibraries = totalLibraries;
        }

    }

}