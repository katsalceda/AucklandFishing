package rktechltd.aucklandfishing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by KatSalceda on 3/06/16.
 */
public class DialogActivity extends DialogFragment {

    LayoutInflater inflater;
    View v;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialog_xp_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO Auto-generated method stub
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
