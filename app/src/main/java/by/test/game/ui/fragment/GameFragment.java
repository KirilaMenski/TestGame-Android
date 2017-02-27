package by.test.game.ui.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.test.game.R;
import by.test.game.ui.activity.MainActivity;
import by.test.game.ui.adapter.ImageAdapter;
import by.test.game.ui.adapter.LifeAdapter;
import by.test.game.ui.dialog.Dialog;
import by.test.game.ui.entity.ImageItem;

/**
 * Created by kirila on 27.2.17.
 */

public class GameFragment extends Fragment implements ImageAdapter.ImageAdapterListener, Dialog.DialogListener {

    private final String ASSETS = "file:///android_asset/";
    private final String FOLDER = "level_";
    private final String EXCESS_VALUE = "w";
    private final int MAX_LVL = 10;

    private final int LAYOUT = R.layout.fragment_game;

    private ImageAdapter mLvlAdapter;
    private MainActivity mMainActivity;
    private LifeAdapter mLifeAdapter;

    private int mLevel = 1;
    private int mLife = 5;
    private int mScore = 0;

    @BindView(R.id.game_score)
    TextView mScoreLabel;
    @BindView(R.id.game_label)
    TextView mGameLabel;
    @BindView(R.id.next_lvl)
    TextView mNextLvl;
    @BindView(R.id.image_recycler)
    RecyclerView mImageRecycler;
    @BindView(R.id.life_recycler)
    RecyclerView mLifeRecycler;


    public static GameFragment newInstance() {
        GameFragment fragment = new GameFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        mMainActivity = (MainActivity) getActivity();
        loadAssets(mLevel);
        updateRecyclerLife(mLife);
        updateScore(mScore);
        return view;
    }

    private void loadAssets(int lvl) {
        List<ImageItem> imageItems = new ArrayList<>();
        List<String> imagesPath = null;
        List<String> labels = Arrays.asList(getContext().getResources().getStringArray(R.array.game_labels));
        try {
            imagesPath = new ArrayList<>(Arrays.asList(getContext().getAssets().list(FOLDER + lvl)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < imagesPath.size(); i++) {
            boolean excess = imagesPath.get(i).contains(EXCESS_VALUE);
            imageItems.add(new ImageItem(labels.get(lvl - 1), ASSETS + FOLDER + lvl + "/" + imagesPath.get(i), excess));
        }
        Collections.shuffle(imageItems);
        updateRecyclerLvl(imageItems);
    }

    private void updateRecyclerLvl(List<ImageItem> imageItems) {
        mGameLabel.setText(getString(R.string.choose_image));
        mLvlAdapter = new ImageAdapter(getActivity(), imageItems, this);
        mImageRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mImageRecycler.setAdapter(mLvlAdapter);
    }

    public void updateRecyclerLife(int life) {
        mLifeAdapter = new LifeAdapter(getActivity(), life);
        mLifeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mLifeRecycler.setAdapter(mLifeAdapter);
        mLifeRecycler.getAdapter().notifyDataSetChanged();

        if (life == 0) {
            loadDialog(getString(R.string.loose));
        }

    }

    public void updateScore(int scores) {
        mScoreLabel.setText(getString(R.string.scores, scores));
    }

    @OnClick(R.id.next_lvl)
    public void nextLevelClicked() {
        mLevel++;
        mNextLvl.setVisibility(View.INVISIBLE);
        if(mLevel > MAX_LVL){
            loadDialog(getString(R.string.win));
            return;
        }
        loadAssets(mLevel);
    }

    private void loadDialog(String string) {
        Dialog dialog = Dialog.newInstance(string);
        dialog.setListener(this);
        dialog.show(getFragmentManager(), string);
    }

    @Override
    public void itemClicked(boolean excess, String gameLabel) {
        if (!excess) {
            mGameLabel.setText(getString(R.string.wrong_answer));
            mLife--;
            updateRecyclerLife(mLife);
        } else {
            mNextLvl.setVisibility(View.VISIBLE);
            mGameLabel.setText(getString(R.string.right_answer, gameLabel));
            mScore += mLevel * 25;
            updateScore(mScore);
        }
    }

    @Override
    public void startAgain() {
        mLife = 5;
        mScore = 0;
        mLevel = 1;
        loadAssets(mLevel);
        updateRecyclerLife(mLife);
        updateScore(mScore);
    }

    @Override
    public void exit() {
        getActivity().finish();
    }
}
