package dhe.digital.library.haryana.allinterface;

import java.util.List;

import dhe.digital.library.haryana.models.BookRecordByLibIdResponse;
import dhe.digital.library.haryana.models.BooksDetailResponse;


public interface GetBookDetailData_interface {


    void GetBookDetailbyIdData(List<BooksDetailResponse.Datum> list);


}
