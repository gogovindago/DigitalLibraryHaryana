package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.HomePageResponse;
import dhe.digital.library.haryana.utility.CSPreferences;

public class ImportantLinksAdapter extends RecyclerView.Adapter<ImportantLinksAdapter.ViewHolder> {

    List<HomePageResponse.ImportantLink> mValues = new ArrayList<HomePageResponse.ImportantLink>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String UserType;
    public ImportantLinksAdapter(Context context, List values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,txtview;
        public SimpleDraweeView imageView;
        public RelativeLayout relativeLayout;
        HomePageResponse.ImportantLink item;
        public int currposition;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtview = (TextView) v.findViewById(R.id.txtview);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
           // relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);




        }

        public void setData(HomePageResponse.ImportantLink item, int currposition) {
            this.currposition = currposition;
            this.item = item;
           // textView.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

            // relativeLayout.setBackgroundColor(Color.parseColor(item.color));


            try {

                textView.setText(item.getTitle());
                imageView.setImageURI(item.getImageLogo());

                UserType = CSPreferences.readString(mContext, "AccountType");

                if (UserType.equalsIgnoreCase("Admin")) {


//                    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getBookImage()))
//                            .setPostprocessor(new IterativeBoxBlurPostProcessor(7))
//                            .build();
//
//
//                    DraweeController controller = Fresco.newDraweeControllerBuilder()
//                            .setImageRequest(request)
//                            .setOldController(imageView.getController())
//                            .build();
//
//                    imageView.setController(controller);
                    txtview.setVisibility(View.VISIBLE);

                    txtview.setText(String.valueOf(item.getTotalCount()) + " view");


                } else {


                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item, currposition);
            }
        }
    }

    @Override
    public ImportantLinksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemmain, parent, false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_itemmain, parent, false);

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
        void onItemClick(HomePageResponse.ImportantLink item, int currposition);
    }
}