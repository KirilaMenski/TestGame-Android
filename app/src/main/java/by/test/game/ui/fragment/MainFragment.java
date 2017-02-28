package by.test.game.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.test.game.R;
import by.test.game.util.FragmentsUtil;
import by.test.game.util.TestGamePreferences;

/**
 * Created by kirila on 27.2.17.
 */
public class MainFragment extends Fragment {

    private final int LAYOUT = R.layout.fragment_main;

    @BindView(R.id.start_game)
    TextView mStartGame;
    @BindView(R.id.score)
    TextView mScores;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);
        if(TestGamePreferences.getLvl() != 1){
            mStartGame.setText(getString(R.string.continue_game));
        } else {
            mStartGame.setText(getString(R.string.start_game));
        }
        return view;
    }

    @OnClick(R.id.start_game)
    public void startGame() {
        FragmentsUtil.replaceAnimFragment(getActivity(), R.id.main_fragment_container, GameFragment.newInstance(), true, R.anim.right_out, R.anim.left_out);
    }

    @OnClick(R.id.score)
    public void openScores(){
        FragmentsUtil.replaceAnimFragment(getActivity(), R.id.main_fragment_container, ScoreFragment.newInstance(), true, R.anim.right_out, R.anim.left_out);
    }

}
