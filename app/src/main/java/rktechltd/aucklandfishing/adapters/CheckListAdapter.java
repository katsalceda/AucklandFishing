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
 * Created by romelyn on 1/06/2016.
 */
public class CheckListAdapter extends ArrayAdapter<Checklist> {
    private List list;
    public CheckListAdapter(Context context) {

        super(context, R.layout.checklist);
        list = new ArrayList<Checklist>();
        Log.d("Adapter", "constructor");
    }

    @Override
    public void add(Checklist object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Checklist getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Checklist)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        ChecklistHolder clHolder; // view lookup cache stored in tag
        if (convertView == null) {
           // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.checklist,parent, false);
            clHolder = new ChecklistHolder();
            clHolder.txtTitle = (TextView) row.findViewById(R.id.tvTitle);
            clHolder.txtDescription = (TextView) row.findViewById(R.id.tvDescription);
            clHolder.imgCL = (ImageView) row.findViewById(R.id.imgCL);
            row.setTag(clHolder);
        } else {
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




