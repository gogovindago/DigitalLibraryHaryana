package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BookRecordByLibIdResponse {

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

        @SerializedName("srno")
        @Expose
        private int srno;
        @SerializedName("bookTitle")
        @Expose
        private String bookTitle;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("publishers")
        @Expose
        private String publishers;
        @SerializedName("quantity")
        @Expose
        private int quantity;
        @SerializedName("maxID")
        @Expose
        private int maxID;

        public int getSrno() {
            return srno;
        }

        public void setSrno(int srno) {
            this.srno = srno;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPublishers() {
            return publishers;
        }

        public void setPublishers(String publishers) {
            this.publishers = publishers;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getMaxID() {
            return maxID;
        }

        public void setMaxID(int maxID) {
            this.maxID = maxID;
        }

    }
}
