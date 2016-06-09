package rktechltd.aucklandfishing.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.Fish;

/**
 * A custom list adapter for fish
 * Created by romelyn on 7/06/2016.
 */
public class FishListAdapter extends ArrayAdapter<Fish> {
    private List list;
    /**
     * Constructor of the custom list adapter
     * @param context
     */
    public FishListAdapter (Context context) {

        super(context, R.layout.faqlist);//calls the superclass constructor
        list = new ArrayList<Fish>();//initalize the list as Fish
        Log.d("Adapter", "constructor");
        }
    /**
     * an implementation of the add method adding an object to the custom list
     * @param object
     */
    @Override
    public void add(Fish object) {
        list.add(object);//adds the Fish to the list
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
     * Returns the Fish in the position
     * @param position
     * @return
     */
    @Override
    public Fish getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Fish)list.get(position);
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

        FishHolder fishHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.fishlist,parent, false);
            fishHolder = new FishHolder();
            fishHolder.txtName = (TextView) row.findViewById(R.id.tvFishName);//casting of textview
            fishHolder.txtMinLength = (TextView) row.findViewById(R.id.tvMinLength);//casting of textview
            fishHolder.txtMaxLimit= (TextView) row.findViewById(R.id.tvMaxLimit);//casting of textview
            fishHolder.ckCombined= (CheckBox) row.findViewById(R.id.cbCombinedBag);//casting of textview
            fishHolder.img = (ImageView)row.findViewById(R.id.imgFish);//casting of imageview
            row.setTag(fishHolder);//Set the holder to he row
        } else {
            fishHolder = (FishHolder) row.getTag();//recycles the existing rows
        }
        // Get the data item for this position
        Fish fish = getItem(position);
        fishHolder.txtName.setText(fish.getFishName().toString());
        if(fish.getMinFishLengthCm()==0)
            fishHolder.txtMinLength.setText("MIN FISH LENGTH: NONE");
        else
            fishHolder.txtMinLength.setText("MIN FISH LENGTH: "+fish.getMinFishLengthCm()+" CM.");
        if(fish.getMaxDailyLimit()==0)
            fishHolder.txtMaxLimit.setText("MAX DAILY LIMIT: NONE");
        else
            fishHolder.txtMaxLimit.setText("MAX DAILY LIMIT: "+fish.getMaxDailyLimit()+" PCS ");
        if(fish.isCombinedBag()==1)
            fishHolder.ckCombined.setChecked(true);
        else
            fishHolder.ckCombined.setChecked(false);

        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
        }

// View lookup cache
static class FishHolder
{
    TextView txtName = null;
    TextView txtMinLength = null;
    TextView txtMaxLimit = null;
    CheckBox ckCombined = null;
    ImageView img = null;
}
}