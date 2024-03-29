package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.BlogListResponse;

public class BlogListBYLibIdAdapter extends RecyclerView.Adapter<BlogListBYLibIdAdapter.ViewHolder> {

    ArrayList<BlogListResponse.Datum> mValues = new ArrayList<BlogListResponse.Datum>();

    Context mContext;
    protected ItemListener mListener;
    public int currposition;

    public BlogListBYLibIdAdapter(Context context, ArrayList values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public int currposition;
        public TextView textViewTitle, textViewAuthor, txtpublisher, textViewquantity, txtserialno,txtblogcreatedDate,txtblogstatus;
        public ImageView imageView;
        public RelativeLayout ll;
        BlogListResponse.Datum item;

        public ViewHolder(View v) {

            super(v);

              v.setOnClickListener(this);

            imageView =  v.findViewById(R.id.imageView);
            txtblogstatus =  v.findViewById(R.id.txtblogstatus);
            textViewTitle =  v.findViewById(R.id.textViewTitle);
            txtblogcreatedDate =  v.findViewById(R.id.txtblogcreatedDate);
            textViewAuthor =  v.findViewById(R.id.textViewAuthor);
            txtpublisher =  v.findViewById(R.id.txtpublisher);
            textViewquantity = v.findViewById(R.id.textViewquantity);
            txtserialno =  v.findViewById(R.id.txtserialno);
            ll =  v.findViewById(R.id.ll);

            textViewquantity.setOnClickListener(this);

        }

        public void setData(BlogListResponse.Datum item, int currposition) {
            this.item = item;
            this.currposition = currposition;


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {


                textViewTitle.setText(Html.fromHtml("<strong>Blog Title:-<br> </strong>" + item.getBlogTitle(), Html.FROM_HTML_MODE_COMPACT));
                txtblogcreatedDate.setText(Html.fromHtml("<strong>Created Date:-<br> </strong>" + item.getCreatedDate(), Html.FROM_HTML_MODE_COMPACT));
                textViewAuthor.setText(Html.fromHtml("<strong>Created By:-<br> </strong>" + item.getCreatedBy(), Html.FROM_HTML_MODE_COMPACT));
                txtpublisher.setText(Html.fromHtml("<strong>Blog:-<br> </strong>" + item.getBlogBody(), Html.FROM_HTML_MODE_COMPACT));

                txtserialno.setText(String.valueOf(++currposition));
                txtblogstatus.setText(item.getIsActive());
                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.drawable.nophoto) // any placeholder to load at start
                        .error(R.drawable.ic_baseline_image_not_supported_24)  // any image in case of error
                        .override(50, 50) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);



            } else {

                textViewTitle.setText(Html.fromHtml("<strong> Book Title:-<br></strong>" + item.getBlogTitle()));
                txtblogcreatedDate.setText(Html.fromHtml("<strong> Created Date:-<br></strong>" + item.getCreatedDate()));
                textViewAuthor.setText(Html.fromHtml("<strong> Created By:-<br></strong>" + item.getCreatedBy()));
                txtpublisher.setText(Html.fromHtml("<strong> Blog:-<br></strong>" + item.getBlogBody()));

                txtserialno.setText(String.valueOf(++currposition));
                txtblogstatus.setText(item.getIsActive());


                Glide.with(mContext)
                        .load(item.getImageURL()) // image url
                        .placeholder(R.drawable.nophoto)// any placeholder to load at start
                        .error(R.drawable.ic_baseline_image_not_supported_24)  // any image in case of error
                        .override(50, 50) // resizing
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);

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
                mListener.onItemClick(item, currposition,"BlogDetail");


          /*  switch (view.getId()) {

                case R.id.textViewquantity:

                    if (mListener != null) {

                        mListener.onItemClick(item, currposition, "bookdetail");


                    }
                    break;


            }*/
            }
        }
    }

    @Override
    public BlogListBYLibIdAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_blogs_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        currposition = position;
        holder.setData(mValues.get(position), currposition);

    }


    @Override
    public int getItemCount() {

        return mValues.size();

    }

    public interface ItemListener {
        void onItemClick(BlogListResponse.Datum item, int currposition, String type);
    }
}