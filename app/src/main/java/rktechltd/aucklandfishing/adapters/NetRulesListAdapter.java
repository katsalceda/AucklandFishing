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
import rktechltd.aucklandfishing.models.NetRule;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * Created by romelyn on 7/06/2016.
 */
public class NetRulesListAdapter extends ArrayAdapter<NetRule> {
    private List list;
    public NetRulesListAdapter(Context context) {

        super(context, R.layout.netrulelist);
        list = new ArrayList<NetRule>();
        Log.d("Adapter", "constructor");
    }

    @Override
    public void add(NetRule object) {
        list.add(object);
        Log.d("Adapter","added");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NetRule getItem(int position) {
        Log.d("Adapter","getting an item");
        return (NetRule)list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Adapter","getting view");
        View row = convertView;
        // Check if an existing view is being reused, otherwise inflate the view
        RuleHolder ruleHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.netrulelist,parent, false);
            ruleHolder = new RuleHolder();
            ruleHolder.txtRuleTitle = (TextView) row.findViewById(R.id.tvRuleTitle);
            ruleHolder.txtRuleDescription = (TextView) row.findViewById(R.id.tvRuleDescription);
            ruleHolder.txtPenalty = (TextView) row.findViewById(R.id.tvPenalty);
            ruleHolder.imgRule = (ImageView) row.findViewById(R.id.imgRule);

            // clHolder.imgCL = (ImageView) row.findViewById(R.id.imgCL);
            row.setTag(ruleHolder);
        } else {
            ruleHolder = (RuleHolder) row.getTag();
        }
        // Get the data item for this position
        NetRule rule = getItem(position);
        Log.d("Adapter",""+rule.getTitle());
        ruleHolder.txtRuleTitle.setText(rule.getDescription().toString());
        ruleHolder.txtRuleDescription.setText(rule.getTitle().toString());
        ruleHolder.txtPenalty.setText("PENALTY: "+rule.getPenalty());
        ruleHolder.imgRule.setImageBitmap(ImageHelper.convertImage(rule.getImage()));
        Log.d("Adapter","returning view");
        // Return the completed view to render on screen
        return row;
    }

    // View lookup cache
    static class RuleHolder
    {
        TextView txtRuleTitle = null;
        TextView txtRuleDescription = null;
        TextView txtPenalty = null;
        ImageView imgRule =null;
    }
}
