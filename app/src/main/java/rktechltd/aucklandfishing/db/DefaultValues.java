package rktechltd.aucklandfishing.db;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.models.Category;
import rktechltd.aucklandfishing.models.Checklist;
import rktechltd.aucklandfishing.models.Faq;
import rktechltd.aucklandfishing.models.Fish;
import rktechltd.aucklandfishing.models.NetRule;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * Created by romelyn on 30/05/2016.
 */
public class DefaultValues {
    private Context context;
    //Existing checklist data
    DefaultValues(Context context){
        this.context = context;
    }
    public final static List<Checklist> CHECKLISTS = new ArrayList<>();
    //Existing category data
    public final static List<Category> CATEGORIES= new ArrayList<>();
    //existing faq data
    public static List<Faq> FAQS = new ArrayList<>();
    //Existing fish data
    public static List<Fish> FISHES = new ArrayList<>();
    //existing faq data
    public static List<NetRule> NETRULES = new ArrayList<>();

}
