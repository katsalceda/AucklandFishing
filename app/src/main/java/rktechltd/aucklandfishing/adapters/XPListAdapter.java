package rktechltd.aucklandfishing.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.FishingExperience;

/**
 * Created by KatSalceda on 7/06/16.
 */
public class XPListAdapter extends ArrayAdapter<FishingExperience> {
    private List list;
    public XPListAdapter(Context context) {

        super(context, R.layout.xplist);
        list = new ArrayList<FishingExperience>();
        Log.d("Adapter", "constructor");
    }

    @Override
    public void add(FishingExperience object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FishingExperience getItem(int position) {
        Log.d("Adapter","getting an item");
        return (FishingExperience)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        XPHolder xpHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.xplist,parent, false);
            xpHolder = new XPHolder();
            xpHolder.txtXPLocationName = (TextView) row.findViewById(R.id.tvXPLocationName);
            xpHolder.txtXPLatitude = (TextView) row.findViewById(R.id.tvXPLatitude);
            xpHolder.txtXPLongitude= (TextView) row.findViewById(R.id.tvXPLongitude);
            xpHolder.txtXPDate = (TextView) row.findViewById(R.id.tvXPDate);
            xpHolder.txtXPTime = (TextView) row.findViewById(R.id.tvXPTime);
            xpHolder.txtXPRemarks = (TextView) row.findViewById(R.id.tvXPRemarks);

            // clHolder.imgCL = (ImageView) row.findViewById(R.id.imgCL);
            row.setTag(xpHolder);
        } else {
            xpHolder = (XPHolder) row.getTag();
        }
        // Get the data item for this position
        FishingExperience xp = getItem(position);
        Log.d("Adapter", "" + xp.getLocationName());
        xpHolder.txtXPLocationName.setText("LOCATION NAME: " + xp.getLocationName());
        xpHolder.txtXPLatitude .setText("LATITUDE: " + xp.getLatitude());
        xpHolder.txtXPLongitude.setText("LONGITUDE " + xp.getLongitude());
        xpHolder.txtXPDate.setText(xp.getDate().toString());
        xpHolder.txtXPTime.setText(xp.getTime().toString());
        xpHolder.txtXPRemarks.setText("REMARKS: " + xp.getRemark());

        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
    }

    // View lookup cache
    static class XPHolder
    {
        TextView txtXPLocationName = null;
        TextView txtXPLatitude = null;
        TextView txtXPLongitude = null;
        TextView txtXPDate = null;
        TextView txtXPTime = null;
        TextView txtXPRemarks = null;
    }
}