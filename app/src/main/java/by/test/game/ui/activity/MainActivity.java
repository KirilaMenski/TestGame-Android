package by.test.game.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import by.test.game.R;
import by.test.game.ui.fragment.MainFragment;
import by.test.game.util.FragmentsUtil;

public class MainActivity extends AppCompatActivity {

    public final int LAYOUT = R.layout.activity_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
        FragmentsUtil.replaceFragment(this, R.id.main_fragment_container, MainFragment.newInstance(), false);
    }

}
