
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

        @SerializedName("importantLinks")
        @Expose
        private List<ImportantLink> importantLinks = null;

        @SerializedName("trendingUdaanVideos")
        @Expose
        private List<TrendingUdaanVideo> trendingUdaanVideos = new ArrayList<TrendingUdaanVideo>();

          @SerializedName("registeredUser")
        @Expose
        private List<RegisteredUser> registeredUser = new ArrayList<RegisteredUser>();

        public List<RegisteredUser> getRegisteredUser() {
            return registeredUser;
        }

        public void setRegisteredUser(List<RegisteredUser> registeredUser) {
            this.registeredUser = registeredUser;
        }

        public List<TrendingUdaanVideo> getTrendingUdaanVideos() {
            return trendingUdaanVideos;
        }

        public void setTrendingUdaanVideos(List<TrendingUdaanVideo> trendingUdaanVideos) {
            this.trendingUdaanVideos = trendingUdaanVideos;
        }


        public List<ImportantLink> getImportantLinks() {
            return importantLinks;
        }

        public void setImportantLinks(List<ImportantLink> importantLinks) {
            this.importantLinks = importantLinks;
        }

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

    public class TrendingUdaanVideo {

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

        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
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

        public String getVideoIframeUrl() {
            return videoIframeUrl;
        }

        public void setVideoIframeUrl(String videoIframeUrl) {
            this.videoIframeUrl = videoIframeUrl;
        }

    }

    public class ImportantLink {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("imageLogo")
        @Expose
        private String imageLogo;
        @SerializedName("url")
        @Expose
        private String url;

        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageLogo() {
            return imageLogo;
        }

        public void setImageLogo(String imageLogo) {
            this.imageLogo = imageLogo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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


        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

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


        @SerializedName("totalCount")
        @Expose
        private String totalCount;


        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }


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


        @SerializedName("totalCount")
        @Expose
        private String totalCount;


        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }


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


        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }


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


        @SerializedName("totalCount")
        @Expose
        private String totalCount;


        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

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


    public class RegisteredUser {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("imageLogo")
        @Expose
        private String imageLogo;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("totalCount")
        @Expose
        private String totalCount;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageLogo() {
            return imageLogo;
        }

        public void setImageLogo(String imageLogo) {
            this.imageLogo = imageLogo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

    }






}
