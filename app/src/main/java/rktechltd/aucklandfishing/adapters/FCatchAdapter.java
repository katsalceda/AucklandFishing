package rktechltd.aucklandfishing.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.FishCatch;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * Created by KatSalceda on 8/06/16.
 */
public class FCatchAdapter extends ArrayAdapter<FishCatch> {
    private List list;
    public FCatchAdapter (Context context) {

        super(context, R.layout.fcatchlist);
        list = new ArrayList<FishCatch>();
        Log.d("Adapter", "constructor");
    }

    @Override
    public void add(FishCatch object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FishCatch getItem(int position) {
        Log.d("Adapter","getting an item");
        return (FishCatch)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        FishCatchHolder fishcatchHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.fcatchlist,parent, false);
            fishcatchHolder = new FishCatchHolder();
            fishcatchHolder.txtName = (TextView) row.findViewById(R.id.tvCatchName);
            fishcatchHolder.txtWeight = (TextView) row.findViewById(R.id.tvCatchWeight);
            fishcatchHolder.txtLength= (TextView) row.findViewById(R.id.tvCatchLength);
            fishcatchHolder.txtRemarks = (TextView) row.findViewById(R.id.tvCatchRemarks);
            fishcatchHolder.img = (ImageView)row.findViewById(R.id.imgCatch);
            row.setTag(fishcatchHolder);
        } else {
            fishcatchHolder = (FishCatchHolder) row.getTag();
        }

    // Get the data item for this position
    FishCatch fc = getItem(position);
    Log.d("Adapter", "" + fc.getFishCatchId());
    fishcatchHolder.txtName.setText("FISH CATCH NAME: " + fc.getName());
    fishcatchHolder.txtWeight .setText("WEIGHT: " + fc.getWeight());
    fishcatchHolder.txtLength.setText("LENGTH: " + fc.getLength());
    fishcatchHolder.txtRemarks.setText("REMARKS: " + fc.getRemark());
    fishcatchHolder.img.setImageBitmap(ImageHelper.convertImage(fc.getPicture()));

    Log.d("Adapter", "returning view");
    // Return the completed view to render on screen
    return row;

}

    // View lookup cache
    static class FishCatchHolder
    {
        TextView txtName = null;
        TextView txtWeight = null;
        TextView txtLength = null;
        TextView txtRemarks = null;
        ImageView img = null;
    }
}