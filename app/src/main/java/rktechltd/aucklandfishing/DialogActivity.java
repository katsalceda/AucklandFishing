package rktechltd.aucklandfishing;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import rktechltd.aucklandfishing.db.backgroundTasks.FCatchBackgroundTask;

/**
 * A class that handles the dialog
 * Back code for the dialog
 * @author ROmelyn Ungab
 * @Author Katrina Salceda
 */
public class DialogActivity extends DialogFragment {

    LayoutInflater inflater;
    View v;
    /**
     * On creatting the activity
     * @param savedInstanceState
     */
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialog_xp_activity, null);
        final TextView fxid = (TextView) getActivity().findViewById(R.id.tvXPId);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView txLength =(TextView)v.findViewById(R.id.tfLength);
                TextView txWeight =(TextView)v.findViewById(R.id.tfWeight);
                TextView txCatchName =(TextView)v.findViewById(R.id.tfCatchName);
                TextView txRemarks =(TextView)v.findViewById(R.id.tfRemarks);
                TextView txId = (TextView) getActivity().findViewById(R.id.tvXPId);

                String xpid =txId.getText().toString();
                String length =txLength.getText().toString();
                String weight =txWeight.getText().toString();
                String catchName = txCatchName.getText().toString();
                String remarks = txRemarks.getText().toString();

                FCatchBackgroundTask fcbg = new FCatchBackgroundTask(getActivity());
                fcbg.execute("I",xpid,length,weight,catchName,remarks);
            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
