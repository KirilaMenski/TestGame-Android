package by.test.game.ui.adapter;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.game.R;
import by.test.game.ui.entity.ImageItem;
import by.test.game.util.RoundedTransformation;

/**
 * Created by kirila on 27.2.17.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private final int LAYOUT = R.layout.item_image;

    private WeakReference<FragmentActivity> mActivity;
    private WeakReference<ImageAdapterListener> mListener;
    private List<ImageItem> mImageItems;

    public ImageAdapter(FragmentActivity activity, List<ImageItem> imageItems, ImageAdapterListener listener) {
        mActivity = new WeakReference<>(activity);
        mListener = new WeakReference<>(listener);
        mImageItems = imageItems;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity.get());
        View view = inflater.inflate(LAYOUT, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        final ImageItem item = mImageItems.get(position);
        holder.bindView(item);
        holder.mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.get().itemClicked(item.isExcess(), item.getLabel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageItems.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView mImage;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(ImageItem item) {
            Picasso.with(mActivity.get())
                    .load(item.getImagePath())
                    .transform(new RoundedTransformation(10, 0))
                    .resizeDimen(R.dimen.image_width, R.dimen.image_height)
                    .centerCrop()
                    .into(mImage);
        }

    }

    public interface ImageAdapterListener {
        void itemClicked(boolean excess, String textLabel);
    }

}
