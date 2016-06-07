package rktechltd.aucklandfishing.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


/**
 * Created by romelyn on 1/06/2016.
 */
public class CheckListAdapter extends ArrayAdapter<Checklist> {
    private List list;
    public CheckListAdapter(Context context) {
        super(context, R.layout.checklist);
        list = new ArrayList<Checklist>();
    }

    @Override
    public void add(Checklist object) {
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Checklist getItem(int position) {
        return (Checklist)list.get(position);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        ChecklistHolder clHolder; // view lookup cache stored in tag
        if (convertView == null) {
            clHolder = new ChecklistHolder(convertView);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.checklist, parent, false);
            convertView.setTag(clHolder);
        } else {
            clHolder = (ChecklistHolder) convertView.getTag();
        }
        // Get the data item for this position
        Checklist checklist = getItem(position);
        // Populate the data into the template view using the data object
        clHolder.populateTitle(checklist.getTitle());
        clHolder.populateDescription(checklist.getDescription());
        clHolder.populateImage(checklist.getImage());
        // Return the completed view to render on screen
        return convertView;
    }



    // View lookup cache
    public class ChecklistHolder {
        public TextView txtTitle = null;
        public TextView txtDescription = null;
        public ImageView imgCL = null;

        ChecklistHolder(View row) {
            txtTitle = (TextView) row.findViewById(R.id.tvTitle);
            txtDescription = (TextView) row.findViewById(R.id.tvDescription);
            imgCL = (ImageView) row.findViewById(R.id.imgCL);
        }

        public void populateTitle(String title) {
            txtTitle.setText(title);
        }

        public void populateDescription(String des) {
            txtDescription.setText(des);
        }

        public void populateImage(byte[] img) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            imgCL.setImageBitmap(bitmap);
        }

    }
}




