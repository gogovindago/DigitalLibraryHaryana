package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentsOnBlogRequest {

    @SerializedName("LibraryID")
    @Expose
    private String libraryID;
    @SerializedName("BlogId")
    @Expose
    private String blogId;
    @SerializedName("PhoneNo")
    @Expose
    private String phoneNo;
    @SerializedName("Comments")
    @Expose
    private String comments;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;


    @SerializedName("CommentedDate")
    @Expose
    private String CommentedDate;


    public String getCommentedDate() {
        return CommentedDate;
    }

    public void setCommentedDate(String commentedDate) {
        CommentedDate = commentedDate;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
