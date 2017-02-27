package by.test.game.ui.adapter;

import java.lang.ref.WeakReference;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.game.R;

/**
 * Created by kirila on 27.2.17.
 */
public class LifeAdapter extends RecyclerView.Adapter<LifeAdapter.LifeHolder> {

    private final int LAYOUT = R.layout.item_life;

    private WeakReference<FragmentActivity> mActivity;
    private final int mSize = 5;
    private int mLife;

    public LifeAdapter(FragmentActivity activity, int life) {
        mActivity = new WeakReference<>(activity);
        mLife = life;
    }

    @Override
    public LifeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity.get());
        View view = inflater.inflate(LAYOUT, parent, false);
        return new LifeHolder(view);
    }

    @Override
    public void onBindViewHolder(LifeHolder holder, int position) {
        holder.mLife.setImageDrawable(ContextCompat.getDrawable(mActivity.get(),
                mLife >= (position + 1) ? R.drawable.ic_heart_full_32dp : R.drawable.ic_heart_empty_32dp));
    }

    @Override
    public int getItemCount() {
        return mSize;
    }

    public class LifeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.life)
        ImageView mLife;

        public LifeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
