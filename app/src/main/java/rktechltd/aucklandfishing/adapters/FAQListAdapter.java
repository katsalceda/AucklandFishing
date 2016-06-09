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
import rktechltd.aucklandfishing.models.Faq;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * A custom list adapter for faq
 * Created by romelyn on 7/06/2016.
 */
public class FAQListAdapter extends ArrayAdapter<Faq>{
    private List list;
    /**
     * Constructor of the custom list adapter
     * @param context
     */
    public FAQListAdapter(Context context) {

        super(context, R.layout.faqlist);//calls the superclass constructor
        list = new ArrayList<Faq>();//initalize the list as Faq
        Log.d("Adapter", "constructor");
    }

    /**
     * an implementation of the add method adding an object to the custom list
     * @param object
     */
    @Override
    public void add(Faq object) {
        list.add(object);//adds the Faq to the list
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
     * Returns the Faq in the position
     * @param position
     * @return
     */
    @Override
    public Faq getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Faq)list.get(position);
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
         FaqHolder faqHolder;   // view lookup cache stored in tag'
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.faqlist,parent, false);
            faqHolder = new FaqHolder();
            faqHolder.txtQuestion = (TextView) row.findViewById(R.id.tvQuestion); //casting of textview
            faqHolder.txtAnswer = (TextView) row.findViewById(R.id.tvAnswer);//casting of textview
            row.setTag(faqHolder);//Set the holder to he row
        } else {
            faqHolder = (FaqHolder) row.getTag();//recycles the existing rows
        }
        // Get the data item for this position
        Faq faq = getItem(position);
        faqHolder.txtQuestion.setText(faq.getQuestion().toString());
        faqHolder.txtAnswer.setText(faq.getAnswer().toString());

        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
    }

    // View lookup cache
    static class FaqHolder
    {
        TextView txtQuestion = null;
        TextView txtAnswer = null;
    }
}