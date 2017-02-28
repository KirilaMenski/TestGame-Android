package by.test.game.ui.adapter;

import java.lang.ref.WeakReference;
import java.util.List;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.game.R;
import by.test.game.ui.entity.ScoreItem;

/**
 * Created by kirila on 28.2.17.
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreHolder> {

    private final int LAYOUT = R.layout.item_score;

    private List<ScoreItem> mScores;
    private WeakReference<FragmentActivity> mActivity;

    public ScoreAdapter(FragmentActivity activity, List<ScoreItem> scores) {
        mActivity = new WeakReference<>(activity);
        mScores = scores;
    }

    @Override
    public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mActivity.get());
        View view = inflater.inflate(LAYOUT, parent, false);
        return new ScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        int score = mScores.get(position).getScore();
        holder.bindView((position + 1) + ". " + score);
    }

    @Override
    public int getItemCount() {
        return mScores.size();
    }

    public class ScoreHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.score_item)
        TextView mScore;

        public ScoreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(String score){
            mScore.setText(score);
        }

    }
}
