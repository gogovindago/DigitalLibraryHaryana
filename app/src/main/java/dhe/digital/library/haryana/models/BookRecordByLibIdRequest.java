package dhe.digital.library.haryana.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookRecordByLibIdRequest {

        @SerializedName("LibraryId")
        @Expose
        private String libraryId;

        public String getLibraryId() {
            return libraryId;
        }

        public void setLibraryId(String libraryId) {
            this.libraryId = libraryId;
        }


}
