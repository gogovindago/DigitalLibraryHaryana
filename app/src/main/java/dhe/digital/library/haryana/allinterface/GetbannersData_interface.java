package dhe.digital.library.haryana.allinterface;

import java.util.List;

import dhe.digital.library.haryana.models.HomePageResponse;



public interface GetbannersData_interface {


    void GetbannersData(List<HomePageResponse.Banner> list);
    void GetOtherDigitalTrendingLibraryData(List<HomePageResponse.OtherDigitalTrendingLibrary> list);
    void GetTrendingVideosData(List<HomePageResponse.TrendingVideo> list);
    void GetTrendingeBookData(List<HomePageResponse.TrendingeBook> list);
    void GetTrendingJournalData(List<HomePageResponse.TrendingJournal> list);
    void GetImportantsLinkData(List<HomePageResponse.ImportantLink> list);
    void GetUdaanVideosData(List<HomePageResponse.TrendingUdaanVideo> list);
    void GetRegisteredUserData(List<HomePageResponse.RegisteredUser> list);


}
