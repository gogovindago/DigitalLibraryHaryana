package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadViewsCountRequest {

    /*{
    "phone":"8269970959",
    "bookid":"12",
    "type":"Book"
}*/

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("bookid")
    @Expose
    private String bookid;

    @SerializedName("type")
    @Expose
    private String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
