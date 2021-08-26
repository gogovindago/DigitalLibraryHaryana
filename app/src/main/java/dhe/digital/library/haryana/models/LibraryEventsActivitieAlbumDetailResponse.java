package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LibraryEventsActivitieAlbumDetailResponse {

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

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("library_Id")
        @Expose
        private int libraryId;
        @SerializedName("event_Title")
        @Expose
        private String eventTitle;
        @SerializedName("event_details")
        @Expose
        private String eventDetails;
        @SerializedName("event_image")
        @Expose
        private String eventImage;
        @SerializedName("content_Type")
        @Expose
        private String contentType;
        @SerializedName("extensionType")
        @Expose
        private String extensionType;
        @SerializedName("createdBy")
        @Expose
        private String createdBy;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("modifiedBy")
        @Expose
        private Object modifiedBy;
        @SerializedName("modifiedDate")
        @Expose
        private Object modifiedDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(int libraryId) {
            this.libraryId = libraryId;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public String getEventDetails() {
            return eventDetails;
        }

        public void setEventDetails(String eventDetails) {
            this.eventDetails = eventDetails;
        }

        public String getEventImage() {
            return eventImage;
        }

        public void setEventImage(String eventImage) {
            this.eventImage = eventImage;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getExtensionType() {
            return extensionType;
        }

        public void setExtensionType(String extensionType) {
            this.extensionType = extensionType;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Object getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(Object modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

    }

}