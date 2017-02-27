package by.test.game.ui.dialog;

import java.lang.ref.WeakReference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import by.test.game.R;

/**
 * Created by kirila on 27.2.17.
 */

public class Dialog extends DialogFragment {

    private final int LAYOUT = R.layout.dialog;

    private static final String EXTRA_LABEL = "by.test.game.ui.dialog.label";

    private android.app.Dialog mDialog;
    private WeakReference<DialogListener> mListener;

    @BindView(R.id.dialog_label)
    TextView mDialogLabel;
    @BindView(R.id.start_again)
    TextView mStartAgain;
    @BindView(R.id.exit)
    TextView mExit;

    public static Dialog newInstance(String label){
        Dialog dialog = new Dialog();
        Bundle args = new Bundle();
        args.putString(EXTRA_LABEL, label);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(LAYOUT, null);
        ButterKnife.bind(this, view);
        mDialogLabel.setText(getArguments().getString(EXTRA_LABEL));
        mDialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mDialog.dismiss();
                }
                return true;
            }
        });
        mDialog.setCanceledOnTouchOutside(false);
        return mDialog;
    }

    @OnClick(R.id.start_again)
    public void startAgainClicked(){
        mListener.get().startAgain();
        mDialog.dismiss();
    }

    @OnClick(R.id.exit)
    public void exitClicked(){
        mListener.get().exit();
        mDialog.dismiss();
    }

    public void setListener(DialogListener listener) {
        mListener = new WeakReference<>(listener);
    }

    public interface DialogListener {
        void startAgain();

        void exit();
    }

}
