package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ImportantLinksTypeResponse {

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
    public static class Datum {

        @SerializedName("linkTypeId")
        @Expose
        private int linkTypeId;
        @SerializedName("linkType")
        @Expose
        private String linkType;
        @SerializedName("typeId")
        @Expose
        private int typeId;

        public int getLinkTypeId() {
            return linkTypeId;
        }

        public void setLinkTypeId(int linkTypeId) {
            this.linkTypeId = linkTypeId;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

    }

}
