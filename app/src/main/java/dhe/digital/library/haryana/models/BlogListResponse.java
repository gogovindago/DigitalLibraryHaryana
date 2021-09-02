package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BlogListResponse {


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

        @SerializedName("blogId")
        @Expose
        private int blogId;
        @SerializedName("blog_Image")
        @Expose
        private String blogImage;
        @SerializedName("blogTitle")
        @Expose
        private String blogTitle;
        @SerializedName("blogBody")
        @Expose
        private String blogBody;
        @SerializedName("memberId")
        @Expose
        private int memberId;
        @SerializedName("libraryId")
        @Expose
        private int libraryId;
        @SerializedName("isActive")
        @Expose
        private String isActive;
        @SerializedName("createdBy")
        @Expose
        private String createdBy;
        @SerializedName("createdDate")
        @Expose
        private String createdDate;
        @SerializedName("imageURL")
        @Expose
        private String imageURL;

        public int getBlogId() {
            return blogId;
        }

        public void setBlogId(int blogId) {
            this.blogId = blogId;
        }

        public String getBlogImage() {
            return blogImage;
        }

        public void setBlogImage(String blogImage) {
            this.blogImage = blogImage;
        }

        public String getBlogTitle() {
            return blogTitle;
        }

        public void setBlogTitle(String blogTitle) {
            this.blogTitle = blogTitle;
        }

        public String getBlogBody() {
            return blogBody;
        }

        public void setBlogBody(String blogBody) {
            this.blogBody = blogBody;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public int getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(int libraryId) {
            this.libraryId = libraryId;
        }

        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
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

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
    }
}
