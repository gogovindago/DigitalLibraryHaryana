package dhe.digital.library.haryana.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import dhe.digital.library.haryana.R;
import dhe.digital.library.haryana.models.HomePageResponse;
import dhe.digital.library.haryana.utility.CSPreferences;

public class TrendingUdaanVideosAdapter extends RecyclerView.Adapter<TrendingUdaanVideosAdapter.ViewHolder> {

    List<HomePageResponse.TrendingUdaanVideo> mValues = new ArrayList<HomePageResponse.TrendingUdaanVideo>();
    Context mContext;
    protected ItemListener mListener;
    int currposition;
    String UserType;

    public TrendingUdaanVideosAdapter(Context context, List values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,txtview;
        public SimpleDraweeView imageView;
        public RelativeLayout relativeLayout;
        HomePageResponse.TrendingUdaanVideo item;
        public int currposition;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            txtview = (TextView) v.findViewById(R.id.txtview);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);


        }

        public void setData(HomePageResponse.TrendingUdaanVideo item, int currposition) {
            this.currposition = currposition;
            this.item = item;
            textView.setText(item.getVideoTitle());
            imageView.setImageURI(item.getVideoImage());
            // relativeLayout.setBackgroundColor(Color.parseColor(item.color));
            try {

                textView.setText(item.getVideoTitle());
                imageView.setImageURI(item.getVideoImage());

                UserType = CSPreferences.readString(mContext, "AccountType");

                if (UserType.equalsIgnoreCase("Admin")) {


//                    ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getFilePath()))
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
    public TrendingUdaanVideosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
        void onItemClick(HomePageResponse.TrendingUdaanVideo item, int currposition);
    }
}