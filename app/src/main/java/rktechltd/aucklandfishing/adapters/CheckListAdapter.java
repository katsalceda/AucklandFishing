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
import rktechltd.aucklandfishing.models.Checklist;
import rktechltd.aucklandfishing.utilities.ImageHelper;


/**
 * A custom list adapter for checklist
 * Created by romelyn on 1/06/2016.
 */
public class CheckListAdapter extends ArrayAdapter<Checklist> {
    private List list;

    /**
     * Constructor of the custom list adapter
     * @param context
     */
    public CheckListAdapter(Context context) {
        super(context, R.layout.checklist);//calls the superclass constructor
        list = new ArrayList<Checklist>();//initalize the list as checklist
        Log.d("Adapter", "constructor");
    }
    /**
     * an implementation of the add method adding an object to the custom list
     * @param object
     */
    @Override
    public void add(Checklist object) {
        list.add(object); //adds the checklist to the list
        Log.d("Adapter","added");
    }

    /**
     * Returns the size of the custom list
     * @return int
     */
    @Override
    public int getCount()
    {
        return list.size();
    }

    /**
     * Returns the checklist in the position
     * @param position
     * @return
     */
    @Override
    public Checklist getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Checklist)list.get(position);
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
        ChecklistHolder clHolder; // view lookup cache stored in tag'
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.checklist,parent, false);
            clHolder = new ChecklistHolder();
            clHolder.txtTitle = (TextView) row.findViewById(R.id.tvTitle);//casting of textview
            clHolder.txtDescription = (TextView) row.findViewById(R.id.tvDescription);//casting of textview
            clHolder.imgCL = (ImageView) row.findViewById(R.id.imgCL);//casting of image
            row.setTag(clHolder);//Set the holder to he row
        } else {
            //reuse the existing row
            clHolder = (ChecklistHolder)row.getTag();
        }
        // Get the data item for this position
        Checklist cl = (Checklist)getItem(position);
        clHolder.txtTitle.setText(cl.getTitle().toString());
        clHolder.txtDescription.setText(cl.getDescription().toString());
        clHolder.imgCL.setImageBitmap(ImageHelper.convertImage(cl.getImage()));
        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
    }

    // View lookup cache
    static class ChecklistHolder
    {
        TextView txtTitle = null;
        TextView txtDescription = null;
        ImageView imgCL = null;
    }
}




