package rktechltd.aucklandfishing.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.FishingExperience;
import rktechltd.aucklandfishing.models.NetRule;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * Created by KatSalceda on 7/06/2016.
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
             LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.xplist,parent, false);


            xpHolder = new XPHolder();
            xpHolder.txtLocation = (TextView) row.findViewById(R.id.tvLocationName);
            xpHolder.txtLatitude = (TextView) row.findViewById(R.id.tvXPLatitude);
            xpHolder.txtLongitude = (TextView) row.findViewById(R.id.tvXPLongitude);
            xpHolder.txtDate = (TextView) row.findViewById(R.id.tvXPDate);
            xpHolder.txtTime = (TextView) row.findViewById(R.id.tvXPTime);
            xpHolder.txtId =(TextView) row.findViewById(R.id.tvXPId);

            row.setTag(xpHolder);
        } else {
            xpHolder = (XPHolder) row.getTag();
        }
        // Get the data item for this position
        FishingExperience fx = getItem(position);
        Log.d("Adapter",""+fx.getLocationName());
        xpHolder.txtLocation.setText("Location: "+fx.getLocationName().toString());
        xpHolder.txtLatitude.setText("Latitude: "+fx.getLatitude());
        xpHolder.txtLongitude.setText("Longitude:  "+fx.getLongitude());
        String PATTERN="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat();
        dateFormat.applyPattern(PATTERN);
        String date1=dateFormat.format(fx.getDate());
        xpHolder.txtDate.setText("Date: "+date1);
        xpHolder.txtTime.setText("Time: "+fx.getTime().toString());
        xpHolder.txtId.setText(fx.getExperienceId()+"");
        //xpHolder.txtId.visible
        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
    }

    // View lookup cache
    static class XPHolder
    {
        TextView txtLocation = null;
        TextView txtLatitude = null;
        TextView txtLongitude = null;
        TextView txtDate = null;
        TextView txtTime = null;
        TextView txtId =null;
    }
}
