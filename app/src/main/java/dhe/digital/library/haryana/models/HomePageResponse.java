
package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HomePageResponse {

    @SerializedName("response")
    @Expose
    private Integer response;
    @SerializedName("sys_message")
    @Expose
    private String sysMessage;
    @SerializedName("data")
    @Expose
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("banners")
        @Expose
        private List<Banner> banners = new ArrayList<Banner>();
        @SerializedName("otherDigitalTrendingLibraries")
        @Expose
        private List<OtherDigitalTrendingLibrary> otherDigitalTrendingLibraries = new ArrayList<OtherDigitalTrendingLibrary>();
        @SerializedName("trendingVideos")
        @Expose
        private List<TrendingVideo> trendingVideos = new ArrayList<TrendingVideo>();
        @SerializedName("trendingeBooks")
        @Expose
        private List<TrendingeBook> trendingeBooks = new ArrayList<TrendingeBook>();

        @SerializedName("trendingJournals")
        @Expose
        private List<TrendingJournal> trendingJournals = new ArrayList<TrendingJournal>();

        public List<TrendingJournal> getTrendingJournals() {
            return trendingJournals;
        }

        public void setTrendingJournals(List<TrendingJournal> trendingJournals) {
            this.trendingJournals = trendingJournals;
        }

        public List<Banner> getBanners() {
            return banners;
        }

        public void setBanners(List<Banner> banners) {
            this.banners = banners;
        }

        public List<OtherDigitalTrendingLibrary> getOtherDigitalTrendingLibraries() {
            return otherDigitalTrendingLibraries;
        }

        public void setOtherDigitalTrendingLibraries(List<OtherDigitalTrendingLibrary> otherDigitalTrendingLibraries) {
            this.otherDigitalTrendingLibraries = otherDigitalTrendingLibraries;
        }

        public List<TrendingVideo> getTrendingVideos() {
            return trendingVideos;
        }

        public void setTrendingVideos(List<TrendingVideo> trendingVideos) {
            this.trendingVideos = trendingVideos;
        }

        public List<TrendingeBook> getTrendingeBooks() {
            return trendingeBooks;
        }

        public void setTrendingeBooks(List<TrendingeBook> trendingeBooks) {
            this.trendingeBooks = trendingeBooks;
        }

    }



    public class TrendingJournal {

        @SerializedName("bookId")
        @Expose
        private Integer bookId;
        @SerializedName("book_Title")
        @Expose
        private String bookTitle;
        @SerializedName("book_Image")
        @Expose
        private String bookImage;

        @SerializedName("bookIframeUrl")
        @Expose
        private String bookIframeUrl;

        public String getBookIframeUrl() {
            return bookIframeUrl;
        }

        public void setBookIframeUrl(String bookIframeUrl) {
            this.bookIframeUrl = bookIframeUrl;
        }

        public Integer getBookId() {
            return bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBookImage() {
            return bookImage;
        }

        public void setBookImage(String bookImage) {
            this.bookImage = bookImage;
        }

    }

    public class Banner {


        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("fileName")
        @Expose
        private String fileName;
        @SerializedName("filePath")
        @Expose
        private String filePath;
        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("libraryUrl")
        @Expose
        private String libraryUrl;

        public String getLibraryUrl() {
            return libraryUrl;
        }

        public void setLibraryUrl(String libraryUrl) {
            this.libraryUrl = libraryUrl;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

    public class OtherDigitalTrendingLibrary {

        @SerializedName("libraryId")
        @Expose
        private Integer libraryId;
        @SerializedName("libraryName")
        @Expose
        private String libraryName;
        @SerializedName("libraryLogo")
        @Expose
        private String libraryLogo;

        @SerializedName("libraryUrl")
        @Expose
        private String libraryUrl;

        public String getLibraryUrl() {
            return libraryUrl;
        }

        public void setLibraryUrl(String libraryUrl) {
            this.libraryUrl = libraryUrl;
        }

        public Integer getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(Integer libraryId) {
            this.libraryId = libraryId;
        }

        public String getLibraryName() {
            return libraryName;
        }

        public void setLibraryName(String libraryName) {
            this.libraryName = libraryName;
        }

        public String getLibraryLogo() {
            return libraryLogo;
        }

        public void setLibraryLogo(String libraryLogo) {
            this.libraryLogo = libraryLogo;
        }

    }

    public class TrendingVideo {

        @SerializedName("videoId")
        @Expose
        private Integer videoId;
        @SerializedName("video_Title")
        @Expose
        private String videoTitle;
        @SerializedName("video_Image")
        @Expose
        private String videoImage;

        @SerializedName("videoIframeUrl")
        @Expose
        private String videoIframeUrl;

        public String getVideoIframeUrl() {
            return videoIframeUrl;
        }

        public void setVideoIframeUrl(String videoIframeUrl) {
            this.videoIframeUrl = videoIframeUrl;
        }


        public Integer getVideoId() {
            return videoId;
        }

        public void setVideoId(Integer videoId) {
            this.videoId = videoId;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public String getVideoImage() {
            return videoImage;
        }

        public void setVideoImage(String videoImage) {
            this.videoImage = videoImage;
        }

    }


    public class TrendingeBook {

        @SerializedName("bookId")
        @Expose
        private Integer bookId;
        @SerializedName("book_Title")
        @Expose
        private String bookTitle;
        @SerializedName("book_Image")
        @Expose
        private String bookImage;

        @SerializedName("bookIframeUrl")
        @Expose
        private String bookIframeUrl;

        public String getBookIframeUrl() {
            return bookIframeUrl;
        }

        public void setBookIframeUrl(String bookIframeUrl) {
            this.bookIframeUrl = bookIframeUrl;
        }

        public Integer getBookId() {
            return bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public String getBookImage() {
            return bookImage;
        }

        public void setBookImage(String bookImage) {
            this.bookImage = bookImage;
        }

    }

}
