package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BooksDetailResponse {
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

        @SerializedName("library Name")
        @Expose
        private String libraryName;
        @SerializedName("phoneNo")
        @Expose
        private String phoneNo;
        @SerializedName("book Title")
        @Expose
        private String bookTitle;
        @SerializedName("author Name")
        @Expose
        private String authorName;
        @SerializedName("subject Book")
        @Expose
        private String subjectBook;
        @SerializedName("publishers")
        @Expose
        private String publishers;
        @SerializedName("book Year")
        @Expose
        private String bookYear;

        public String getLibraryName() {
            return libraryName;
        }

        public void setLibraryName(String libraryName) {
            this.libraryName = libraryName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getSubjectBook() {
            return subjectBook;
        }

        public void setSubjectBook(String subjectBook) {
            this.subjectBook = subjectBook;
        }

        public String getPublishers() {
            return publishers;
        }

        public void setPublishers(String publishers) {
            this.publishers = publishers;
        }

        public String getBookYear() {
            return bookYear;
        }

        public void setBookYear(String bookYear) {
            this.bookYear = bookYear;
        }

    }

}
