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
 * Created by romelyn on 7/06/2016.
 */
public class FAQListAdapter extends ArrayAdapter<Faq>{
    private List list;
    public FAQListAdapter(Context context) {

        super(context, R.layout.faqlist);
        list = new ArrayList<Faq>();
        Log.d("Adapter", "constructor");
    }

    @Override
    public void add(Faq object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Faq getItem(int position) {
        Log.d("Adapter","getting an item");
        return (Faq)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        FaqHolder faqHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.faqlist,parent, false);
            faqHolder = new FaqHolder();
            faqHolder.txtQuestion = (TextView) row.findViewById(R.id.tvQuestion);
            faqHolder.txtAnswer = (TextView) row.findViewById(R.id.tvAnswer);
           // clHolder.imgCL = (ImageView) row.findViewById(R.id.imgCL);
            row.setTag(faqHolder);
        } else {
            faqHolder = (FaqHolder) row.getTag();
        }
        // Get the data item for this position
        Faq faq = (Faq)getItem(position);
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