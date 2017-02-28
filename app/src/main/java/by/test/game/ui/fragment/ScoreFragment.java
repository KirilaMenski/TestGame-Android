package by.test.game.ui.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import by.test.game.R;
import by.test.game.ui.adapter.ScoreAdapter;
import by.test.game.ui.entity.ScoreItem;
import by.test.game.util.TestGamePreferences;

/**
 * Created by kirila on 27.2.17.
 */

public class ScoreFragment extends Fragment {

    private final int LAYOUT = R.layout.fragment_scores;

    private ScoreAdapter mAdapter;

    @BindView(R.id.scores_recycler)
    RecyclerView mScoreRec;

    public static ScoreFragment newInstance() {
        ScoreFragment fragment = new ScoreFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        updateScoreRec();
        return view;
    }

    private void updateScoreRec() {
        List<String> scores = new ArrayList<>(TestGamePreferences.getList());
        List<ScoreItem> scoreItems = new ArrayList<>();
        for (String str : scores) {
            scoreItems.add(new ScoreItem(str));
        }
        Collections.sort(scoreItems, new ScoreItem());
        mAdapter = new ScoreAdapter(getActivity(), scoreItems);
        mScoreRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mScoreRec.setAdapter(mAdapter);
    }

}
