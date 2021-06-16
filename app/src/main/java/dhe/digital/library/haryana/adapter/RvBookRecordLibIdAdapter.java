package dhe.digital.library.haryana.adapter;

import android.content.Context;
import android.graphics.Color;
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
import dhe.digital.library.haryana.models.BookRecordByLibIdResponse;

public class RvBookRecordLibIdAdapter extends RecyclerView.Adapter<RvBookRecordLibIdAdapter.ViewHolder> {

    ArrayList<BookRecordByLibIdResponse.Datum> mValues = new ArrayList<BookRecordByLibIdResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public RvBookRecordLibIdAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textViewTitle, textViewAuthor, txtpublisher, textViewquantity, txtserialno;
        public ImageView imageView;
        public RelativeLayout ll;
        BookRecordByLibIdResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);

            textViewTitle = (TextView) v.findViewById(R.id.textViewTitle);
            textViewAuthor = (TextView) v.findViewById(R.id.textViewAuthor);
            txtpublisher = (TextView) v.findViewById(R.id.txtpublisher);
            textViewquantity = (TextView) v.findViewById(R.id.textViewquantity);
            txtserialno = (TextView) v.findViewById(R.id.txtserialno);
            ll = (RelativeLayout) v.findViewById(R.id.ll);

        }

        public void setData(BookRecordByLibIdResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;

            textViewTitle.setText("Book Title:-\n " + item.getBookTitle());
            textViewAuthor.setText("Author:-\n" + item.getAuthor());
            txtpublisher.setText("Publisher:-\n " + item.getPublishers());
            textViewquantity.setText("Quantity:-" + String.valueOf(item.getQuantity()));
            txtserialno.setText("Sr.no. " + String.valueOf(item.getSrno()));

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
    public RvBookRecordLibIdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_books, parent, false);

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
        void onItemClick(BookRecordByLibIdResponse.Datum item, int currposition);
    }
}