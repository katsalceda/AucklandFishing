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
 * Created by romelyn on 7/06/2016.
 */
public class FishListAdapter extends ArrayAdapter<Fish> {
    private List list;
    public FishListAdapter (Context context) {

        super(context, R.layout.faqlist);
        list = new ArrayList<Fish>();
        Log.d("Adapter", "constructor");
        }

    @Override
    public void add(Fish object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
        }

    @Override
    public Fish getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Fish)list.get(position);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        FishHolder fishHolder; // view lookup cache stored in tag
        if (convertView == null) {
        // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(R.layout.fishlist,parent, false);
            fishHolder = new FishHolder();
            fishHolder.txtName = (TextView) row.findViewById(R.id.tvFishName);
            fishHolder.txtMinLength = (TextView) row.findViewById(R.id.tvMinLength);
            fishHolder.txtMaxLimit= (TextView) row.findViewById(R.id.tvMaxLimit);
            fishHolder.ckCombined= (CheckBox) row.findViewById(R.id.cbCombinedBag);
            fishHolder.img = (ImageView)row.findViewById(R.id.imgFish);
            row.setTag(fishHolder);
        } else {
            fishHolder = (FishHolder) row.getTag();
        }
        // Get the data item for this position
        Fish fish = (Fish)getItem(position);
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