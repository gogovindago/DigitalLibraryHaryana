package dhe.digital.library.haryana.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.BooksDetailResponse;

public class BookDetailAdapter extends RecyclerView.Adapter<BookDetailAdapter.ViewHolder> {

    ArrayList<BooksDetailResponse.Datum> mValues = new ArrayList<BooksDetailResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public BookDetailAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textViewTitle, textViewAuthor, txtpublisher, textViewquantity, txtserialno, txtlinname;
        public ImageView imageView;
        public RelativeLayout ll;
        BooksDetailResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);

            txtlinname = (TextView) v.findViewById(R.id.txtlinname);
            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            textViewAuthor = (TextView) v.findViewById(R.id.textViewAuthor);
            txtpublisher = (TextView) v.findViewById(R.id.txtpublisher);
            textViewquantity = (TextView) v.findViewById(R.id.textViewquantity);
            txtserialno = (TextView) v.findViewById(R.id.txtserialno);
            ll = (RelativeLayout) v.findViewById(R.id.ll);

        }

        public void setData(BooksDetailResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                //  textViewTitle.setText("Book Title:-\n " + item.getBookTitle());
                textViewAuthor.setText("Author:-\n" + item.getAuthorName());
                txtpublisher.setText("Publisher:-\n " + item.getPublishers());
                textViewquantity.setText("Year:-" + item.getBookYear());
                // txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));


                textViewTitle.setText(Html.fromHtml("<strong>&#128196; Book Title:-<br></strong>" + item.getBookTitle(), Html.FROM_HTML_MODE_COMPACT));

                txtlinname.setText(Html.fromHtml("<strong>&#127979-;<br></strong>" + item.getLibraryName(), Html.FROM_HTML_MODE_COMPACT));

            } else {

                textViewTitle.setText(Html.fromHtml("<strong> &#128196;Book Title:-<br></strong>" + item.getBookTitle()));

                txtlinname.setText(Html.fromHtml("<strong>&#127979;-<br></strong>" + item.getLibraryName()));

                // textViewTitle.setText("Book Title:-\n " + item.getBookTitle());
                textViewAuthor.setText("Author:-\n" + item.getAuthorName());
                txtpublisher.setText("Publisher:-\n " + item.getPublishers());
                textViewquantity.setText("Year:-" + item.getBookYear());
                //  txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));


                // binding.toolbar.tvToolbarTitle.setText(Html.fromHtml("<h6>Books Available in " + titleOfPage + "</h6>"));
            }

//            textViewTitle.setText("Book Title:-\n " + item.getBookTitle());
//            textViewAuthor.setText("Author:-\n" + item.getAuthor());
//            txtpublisher.setText("Publisher:-\n " + item.getPublishers());
//            textViewquantity.setText("Quantity:-" + String.valueOf(item.getQuantity()));
//            txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));

//if (currposition%2==0) {
//    ll.setBackgroundColor(R.drawable.spinner_border);
//}

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public BookDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_booksdetail_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();

    }

    public interface ItemListener {
        void onItemClick(BooksDetailResponse.Datum item, int currposition);
    }
}