package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactUsRequest {
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("LibraryUrl")
    @Expose
    private String libraryUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLibraryUrl() {
        return libraryUrl;
    }

    public void setLibraryUrl(String libraryUrl) {
        this.libraryUrl = libraryUrl;
    }
}
