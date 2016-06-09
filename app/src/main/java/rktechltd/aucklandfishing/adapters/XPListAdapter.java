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
 * A custom list adapter for fishing experience
 * Created by KatSalceda on 7/06/2016.
 */
public class XPListAdapter extends ArrayAdapter<FishingExperience> {
    private List list;
    /**
     * Constructor of the custom list adapter
     * @param context
     */
    public XPListAdapter(Context context) {
        super(context, R.layout.xplist);//calls the superclass constructor
        list = new ArrayList<FishingExperience>();//initalize the list as FishingExperience
        Log.d("Adapter", "constructor");
    }
    /**
     * an implementation of the add method adding an object to the custom list
     * @param object
     */
    @Override
    public void add(FishingExperience object) {
        list.add(object);//adds the FishingExperience to the list
        Log.d("Adapter","added");
    }
    /**
     * Returns the size of the custom list
     * @return int
     */
    @Override
    public int getCount() {
        return list.size();
    }
    /**
     * Returns the FishingExperience in the position
     * @param position
     * @return
     */
    @Override
    public FishingExperience getItem(int position) {
        Log.d("Adapter","getting an item");
        return (FishingExperience)list.get(position);
    }
    /**
     * returns the row item for the list
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        XPHolder xpHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
             LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             row = inflater.inflate(R.layout.xplist,parent, false);
            xpHolder = new XPHolder();
            xpHolder.txtLocation = (TextView) row.findViewById(R.id.tvLocationName);//casting of textview
            xpHolder.txtLatitude = (TextView) row.findViewById(R.id.tvXPLatitude);//casting of textview
            xpHolder.txtLongitude = (TextView) row.findViewById(R.id.tvXPLongitude);//casting of textview
            xpHolder.txtDate = (TextView) row.findViewById(R.id.tvXPDate);//casting of textview
            xpHolder.txtTime = (TextView) row.findViewById(R.id.tvXPTime);//casting of textview
            xpHolder.txtId = (TextView) row.findViewById(R.id.tvXPId);//casting of textview
            row.setTag(xpHolder);//Set the holder to he row
        } else {
            xpHolder = (XPHolder) row.getTag();//recycles the existing rows
        }
        // Get the data item for this position
        FishingExperience fx = getItem(position);
        Log.d("Adapter",""+fx.getLocationName());
        xpHolder.txtLocation.setText("Location: "+fx.getLocationName().toString());
        xpHolder.txtLatitude.setText("Lat: "+fx.getLatitude());
        xpHolder.txtLongitude.setText("Long:  "+fx.getLongitude());
        String PATTERN="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat();
        dateFormat.applyPattern(PATTERN);
        String date1=dateFormat.format(fx.getDate());
        xpHolder.txtDate.setText("Date: "+date1);
        xpHolder.txtTime.setText("Time: "+fx.getTime().toString());
        xpHolder.txtId.setText(fx.getExperienceId()+"");
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
