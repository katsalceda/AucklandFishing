package rktechltd.aucklandfishing.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.NetRule;

/**
 * A custom list adapter for netrules
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */
public class NetRulesListAdapter extends ArrayAdapter<NetRule> {
    private List list;
    /**
     * Constructor of the custom list adapter
     * @param context
     */
    public NetRulesListAdapter(Context context) {
        super(context, R.layout.netrulelist);//calls the superclass constructor
        list = new ArrayList<NetRule>();//initalize the list as NetRule
        Log.d("Adapter", "constructor");
    }
    /**
     * an implementation of the add method adding an object to the custom list
     * @param object
     */
    @Override
    public void add(NetRule object) {
        list.add(object);//adds the NetRule to the list
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
     * Returns the NetRule in the position
     * @param position
     * @return
     */
    @Override
    public NetRule getItem(int position) {
        Log.d("Adapter","getting an item");
        return (NetRule)list.get(position);
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
        RuleHolder ruleHolder; // view lookup cache stored in tag
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.netrulelist,parent, false);
            ruleHolder = new RuleHolder();
            ruleHolder.txtRuleTitle = (TextView) row.findViewById(R.id.tvRuleTitle);//casting of textview
            ruleHolder.txtRuleDescription = (TextView) row.findViewById(R.id.tvRuleDescription);//casting of textview
            ruleHolder.txtPenalty = (TextView) row.findViewById(R.id.tvPenalty);//casting of textview
            row.setTag(ruleHolder);//Set the holder to he row
        } else {
            ruleHolder = (RuleHolder) row.getTag();//recycles the existing rows
        }
        // Get the data item for this position
        NetRule rule = getItem(position);
        Log.d("Adapter",""+rule.getTitle());
        ruleHolder.txtRuleTitle.setText(rule.getDescription().toString());
        ruleHolder.txtRuleDescription.setText(rule.getTitle().toString());
        ruleHolder.txtPenalty.setText("PENALTY: "+rule.getPenalty());
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
      //  ImageView imgRule =null;
    }
}
