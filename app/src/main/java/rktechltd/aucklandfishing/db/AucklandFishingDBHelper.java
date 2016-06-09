package rktechltd.aucklandfishing.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import rktechltd.aucklandfishing.R;
import rktechltd.aucklandfishing.SplashActivity;
import rktechltd.aucklandfishing.models.Category;
import rktechltd.aucklandfishing.models.Checklist;
import rktechltd.aucklandfishing.models.Faq;
import rktechltd.aucklandfishing.models.Fish;
import rktechltd.aucklandfishing.models.FishCatch;
import rktechltd.aucklandfishing.models.FishingExperience;
import rktechltd.aucklandfishing.models.NetRule;
import rktechltd.aucklandfishing.utilities.ImageHelper;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class AucklandFishingDBHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DB_NAME = "aklfishingdatabase.db";
    public static final int DB_VERSION =48;

    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = " DATE";
    private static final String TIME_TYPE = " TIME";
    private static final String NUMERIC_TYPE = " NUMERIC";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_CATEGORY_TABLE = "CREATE TABLE " + AucklandFishingDBTables.Category.TABLE_NAME + " (" +
            AucklandFishingDBTables.Category.COLUMN_CAT_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Category.COLUMN_CAT_NAME + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Category.COLUMN_CAT_DESC + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Category.PRIMARY_KEY +
            " )";
  //  private static final String CREATE_CATEGORY_TABLE="CREATE TABLE category (categoryId NUMERIC,categoryName TEXT,categoryDescription TEXT)";

    private static final String CREATE_CHECKLIST_TABLE = "CREATE TABLE " + AucklandFishingDBTables.CheckList.TABLE_NAME + " (" +
            AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.CheckList.PRIMARY_KEY +
            " )";
    private static final String CREATE_FAQ_TABLE = "CREATE TABLE " + AucklandFishingDBTables.Faq.TABLE_NAME + " (" +
            AucklandFishingDBTables.Faq.COLUMN_FAQ_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Faq.PRIMARY_KEY +
            " )";
    private static final String CREATE_FISH_TABLE = "CREATE TABLE " + AucklandFishingDBTables.Fish.TABLE_NAME + " (" +
            AucklandFishingDBTables.Fish.COLUMN_FISH_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_FISH_NAME + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_FISH_CAT + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_FISH_DESC + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Fish.PRIMARY_KEY +
            " )";


    private static final String CREATE_FISHEXPERIENCE_TABLE = "CREATE TABLE " + AucklandFishingDBTables.FishingExperience.TABLE_NAME + " (" +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_NAME + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LATITUDE + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LONGITUDE + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + DATE_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_TIME + TIME_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_REMARK + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.PRIMARY_KEY +
            " )";

    private static final String CREATE_FISHCATCH_TABLE = "CREATE TABLE " + AucklandFishingDBTables.FishCatch.TABLE_NAME + " (" +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_EXPERIENCE + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_NAME + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_LENGTH + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_WEIGHT + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_REMARK + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_IMAGE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishCatch.PRIMARY_KEY +
            " )";

    private static final String CREATE_NETRULES_TABLE = "CREATE TABLE " + AucklandFishingDBTables.NetRules.TABLE_NAME + " (" +
            AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE + BLOB_TYPE + COMMA_SEP +
            AucklandFishingDBTables.NetRules.PRIMARY_KEY +
            " )";

    private static final String DELETE_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Category.TABLE_NAME;
    private static final String DELETE_FISH_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Fish.TABLE_NAME;
   // private static final String DELETE_lOCATION_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Location.TABLE_NAME;
    private static final String DELETE_FISHEXPERIENCE_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.FishingExperience.TABLE_NAME;
    private static final String DELETE_FISHCATCH_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.FishCatch.TABLE_NAME;
    private static final String DELETE_CHECKLIST_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.CheckList.TABLE_NAME;
    private static final String DELETE_NETRULES_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.NetRules.TABLE_NAME;
    private static final String DELETE_FAQ_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Faq.TABLE_NAME;

    private Checklist[] CHECKLISTS = new Checklist[10];
    private Category[] CATEGORIES= new Category[3];
    public Faq[] FAQS = new Faq[3];
    public Fish[] FISHES = new Fish[85];
    public NetRule[] NETRULES = new NetRule[8];

    private static SQLiteDatabase db;
    public AucklandFishingDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("DB","constructor");
        //this.context=context;
        AucklandFishingDBHelper.db = getWritableDatabase();
        Log.d("AKLFishingDB"," created DB");
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_TABLE);
        Log.d("AKLFishingDB", "CAT created");
        db.execSQL(CREATE_FISH_TABLE);
        Log.d("AKLFishingDB", "FISH created");

        db.execSQL(CREATE_FISHEXPERIENCE_TABLE);
        Log.d("AKLFishingDB", "FX created");
        db.execSQL(CREATE_FISHCATCH_TABLE);
        Log.d("AKLFishingDB", "CATCH created");
        db.execSQL(CREATE_CHECKLIST_TABLE);
        Log.d("AKLFishingDB", "CHECK created");
        db.execSQL(CREATE_NETRULES_TABLE);
        Log.d("AKLFishingDB", "NET created");
        db.execSQL(CREATE_FAQ_TABLE);
        Log.d("AKLFishingDB", "FAQ created");
        Log.d("AKLFishingDB",db.toString());
        initializeTables();
        for(int i=0; i<CHECKLISTS.length; i++) {
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.CheckList.TABLE_NAME +
                    "( " + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID + " , "
                    + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + " , "
                    + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION + " , "
                    + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE + " )" +
                    " VALUES( " + CHECKLISTS[i].getCheckListId() + " , '"
                    + CHECKLISTS[i].getTitle() + "' , '"
                    + CHECKLISTS[i].getDescription() + "' , '"
                    + CHECKLISTS[i].getImage() + "' )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
        }

        for(int i=0; i<CATEGORIES.length; i++){
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Category.TABLE_NAME +
                    "( " + AucklandFishingDBTables.Category.COLUMN_CAT_ID + " , "
                    + AucklandFishingDBTables.Category.COLUMN_CAT_NAME + " , "
                    + AucklandFishingDBTables.Category.COLUMN_CAT_DESC + " )" +
                    " VALUES( " + CATEGORIES[i].getCategoryId() + " , '"
                    + CATEGORIES[i].getCategoryName() + "' , '"
                    + CATEGORIES[i].getCategoryDescription() + "' )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
        }


        for(int i=0; i<FAQS.length;i++){
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Faq.TABLE_NAME +
                    "( " + AucklandFishingDBTables.Faq.COLUMN_FAQ_ID + " , "
                    + AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION + " , "
                    + AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER + " )" +
                    " VALUES( " + FAQS[i].getFaqId() + " , '"
                    + FAQS[i].getQuestion() + "' , '"
                    + FAQS[i].getAnswer() + "' )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
        }

        for(int i=0; i<FISHES.length;i++){
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Fish.TABLE_NAME +
                    "( " + AucklandFishingDBTables.Fish.COLUMN_FISH_ID + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_FISH_NAME + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_FISH_DESC + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_FISH_CAT + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM +" , "
                    + AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT + " , "
                    + AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG + " )" +
                    " VALUES( " + FISHES[i].getFishId() + " , '"
                    +  FISHES[i].getFishName() + "' , '"
                    +  FISHES[i].getFishDescription() + "' , '"
                    +  FISHES[i].getFishImage()+ "' , "
                    +  FISHES[i].getFishCat() + " , "
                    +  FISHES[i].getMinFishLengthCm() + " , "
                    +  FISHES[i].getMaxDailyLimit() + " , "
                    +  FISHES[i].isCombinedBag() + " )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
        }

        for(int i=0; i<NETRULES.length;i++){
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.NetRules.TABLE_NAME +
                    "( " + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID + " , "
                    + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION+ " , "
                    + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE + " , "
                    + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY + " , "
                    + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE + " )" +
                    " VALUES( " + NETRULES[i].getRulesId() + " , '"
                    +  NETRULES[i].getDescription() + "' , '"
                    +  NETRULES[i].getTitle()+ "' , "
                    +  NETRULES[i].getPenalty() + " , '"
                    +  NETRULES[i].getImage() + "' )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
        }

    }

    public void initializeTables(){
        Log.d("AKLFishingDBContext", SplashActivity.getAppContext().toString());
        context=SplashActivity.getAppContext();
        Log.d("AKLFishingDBContext", context.toString());
        Log.d("AKLFishingDB", "initializing");
        CHECKLISTS[0] = new Checklist(1, "Life Jackets and personal floatation device (PFDs)", "Maritime law requires ALL skippers to carry enough life jackets of the right size and type for everyone on board. Wearing a life jacket is mandatory at all times unless the risk is very low.", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_lifejackets)));
        CHECKLISTS[1] = new Checklist(2, "Communication Equipment", "This include distress beacons (EPIRB or PLB) VHF radio, flares, and cellphones.  On any trip you need to carry two means of waterproof communication and three means if you are over 2 miles from shore.", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_communication)));
        CHECKLISTS[2] = new Checklist(3, "Navigation", "You will need a chart and compass in all but the smallest of boats if you go more than a mile or two from shore.  A GPS and a depth sounder are also very useful.  Exactly what you carry will depend on the size and type of your boat and how far from land you go.  Talk to Coastguard of New Zealand for advice.", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_navigation)));
        CHECKLISTS[3] = new Checklist(4, "Anchor", "To determine the right size for your boat, the anchor should weigh not less than 1.5kg per meter of boat length, with chain at least equal to the length of the boat.  A non-floating rope well secured to the boat should be as long as is.",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_anchor)));
        CHECKLISTS[4] = new Checklist(5, "First Aid Kit", "Your kit should contain enough supplies to cover minor accidents or injuries.  Remember to carry a remedy for sea sickness as well as sun block.",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_firstaid)));
        CHECKLISTS[5] = new Checklist(6, "Fire Extinguishers", "If you have an engine or cooker on board always carry at least one fire extinguisher that is suitable for your type of boating.  Know how to use it and ensure it is serviced regularly.",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_fireextinguishers)));
        CHECKLISTS[6] = new Checklist(7, "Throwing Line", "A floating line at least 12m in length with a floating weight at one end",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_throwingline)));
        CHECKLISTS[7] = new Checklist(8, "Protective Clothin", "Carry adequate warm protective clothing",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_protectiveclothing)));
        CHECKLISTS[8] = new Checklist(9, "Knife", "Has many uses - remember to keep it sharp.",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_knife)));
        CHECKLISTS[9] = new Checklist(10, "Torch", "Always carry a torch with spare batteries and bulb.",  ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.safetychecklist_torch)));

        CATEGORIES[0] =  new Category(1, "Fin Fish", "Fin Fish");
        CATEGORIES[1] =  new Category(2, "Cray Fish", "Cray Fish");
        CATEGORIES[2] =  new Category(3, "Shell Fish", "Shell Fish");

        FAQS[0] = new Faq(1, "Do I need a fishing licence?", "Yes you need a fishing licence to fish in all freshwater fisheries in NZ, these can be purchased online or once you arrive in NZ at most sports stores and retailers  specialising in fishing and the outdoors.");
        FAQS[1] = new Faq(2, "When does the fishing season start in New Zealand?", "The lowland streams all open on the 1st October with the back country rivers opening on the 1st NOVEMBER. The Southern Lakes are open all year round for fishing with some great options if your here during this time and interested in a days fishing.");
        FAQS[2] = new Faq(3, "Can I bring my own fishing gear to New Zealand?", "Yes, we recommend you bring your own gear to NZ where where possible. Using your own fishing equipment gives  you added confidence.");

        FISHES[0] = new Fish(1, "Blue Cod", "Blue Cod", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluecod)), 1, 30, 20, 1);
        FISHES[1] = new Fish(2, "Blue Moki", "Blue Moki",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluemoki)), 1, 40, 20, 1);
        FISHES[2] = new Fish(3, "Blue Nose", "Blue Nose",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluenose)), 1, 0, 5, 1);
        FISHES[3] = new Fish(4, "Butterfish ", "Butterfish",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_butterfish)), 1, 35, 20, 1);
        FISHES[4] = new Fish(5, "Elephant Fish", "Elephant Fish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_elephantfish)), 1, 0, 20, 1);
        FISHES[5] = new Fish(6, "Flat Fish", "Flat Fish",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_flatfish)), 1, 25, 20, 1);
        FISHES[6] = new Fish(7, "John Dory", "John Dory", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_johndory)), 1, 0, 20, 1);
        FISHES[7] = new Fish(8, "Kahawai", "Kahawai", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_kahawai)), 1, 0, 20, 1);
        FISHES[8] = new Fish(9, "Parore", "Parore", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_parore)), 1, 0, 20, 1);
        FISHES[9] = new Fish(10, "Porae", "Porae", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_porae)), 1, 0, 20, 1);
        FISHES[10] = new Fish(11, "Rays Bream", "Rays Bream", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_raysbream)), 1, 0, 0, 1);
        FISHES[11] = new Fish(12, "Gurnard", "Gurnard", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_gurnard)), 1, 25, 20, 1);
        FISHES[12] = new Fish(13, "Red Moki", "Red Moki", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_redmoki)), 1, 40, 20, 1);
        FISHES[13] = new Fish(14, "Red Snapper", "Red Snapper", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_redsnapper)), 1, 20, 0, 1);
        FISHES[14] = new Fish(15, "Rig", "Rig", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_rig)), 1, 0, 20, 1);
        FISHES[15] = new Fish(16, "Sand Flounder", "Sand Flounder", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_sandflounder)), 1, 23, 20, 1);
        FISHES[16] = new Fish(17, "School Shark", "School Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_schoolshark)), 1, 0, 20, 1);
        FISHES[17] = new Fish(18, "Tarakihi", "Tarakihi", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_tarakihi)), 1, 25, 20, 1);
        FISHES[18] = new Fish(19, "Trevally", "Trevally", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_trevally)), 1, 25, 20, 1);
        FISHES[19] = new Fish(20, "Eel", "Eel",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_eels)), 1, 0, 6, 1);
        FISHES[20] = new Fish(21, "Garfish", "Garfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_garfish)), 1, 0, 0, 1);
        FISHES[21] = new Fish(22, "Groper", "Groper", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_groper)), 1, 0, 5, 1);
        FISHES[22] = new Fish(23, "Kingfish", "Kingfish",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_kingfish)), 1, 75, 3, 1);
        FISHES[23] = new Fish(24, "Grey Mullet", "Grey Mullet", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_greymullet)), 1, 0, 30, 1);
        FISHES[24] = new Fish(25, "Pilchards", "Pilchards", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_pilchards)), 1, 0, 0, 1);
        FISHES[25] = new Fish(26, "Snapper", "Snapper", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_snapper)), 1, 27, 10, 1);
        FISHES[26] = new Fish(27, "Yellow Eyed Mullet", "Yellow Eyed Mullet",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_yelloweyedmullet)), 1, 0, 0, 1);

        FISHES[27] = new Fish(28, "Anchovy", "Anchovy", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_finfish_anchovy)), 1, 0, 0, 1);
        FISHES[28] = new Fish(29, "Barracouta", "Barracouta", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_barracouta)), 1, 0, 0, 1);
        FISHES[29] = new Fish(30, "Big Eye Tuna", "Big Eye Tuna", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bigeyetuna)), 1, 0, 0, 1);
        FISHES[30] = new Fish(31, "Blue Mackerel", "Blue Mackerel", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluemackerel)), 1, 0, 0, 1);
        FISHES[31] = new Fish(32, "Blue Marlin", "Blue Marlin",ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluemarlin)), 1, 0, 0, 1);
        FISHES[32] = new Fish(33, "Blue Mao", "Blue Mao", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bluemaomao)), 1, 0, 0, 1);

        FISHES[33] = new Fish(34, "Blue Shark", "Blue Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_blueshark)), 1, 0, 0, 1);
        FISHES[34] = new Fish(35, "Bronze Shark", "Bronze Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_bronzeshark)), 1, 0, 0, 1);
        FISHES[35] = new Fish(36, "Cardinalfish", "Cardinalfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_cardinalfish)), 1, 0, 0, 1);
        FISHES[36] = new Fish(37, "Frostfish", "Frostfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_frostfish)), 1, 0, 0, 1);

        FISHES[37] = new Fish(38, "Gemfish", "Gemfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_gemfish)), 1, 0, 0, 1);
        FISHES[38] = new Fish(39, "Greenback Flounder", "Greenback Flounder", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_greenbackflounder)), 1, 25, 20, 1);
        FISHES[39] = new Fish(40, "Hake", "Hake", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_hake)), 1, 0, 0, 1);
        FISHES[40] = new Fish(41, "Hammerhead Shark ", "Hammerhead Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_hammerheadshark)), 1, 0, 0, 1);
        FISHES[41] = new Fish(42, "Hoki", "Hoki", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_hoki)), 1, 0, 0, 1);
        FISHES[42] = new Fish(43, "Jack Mackerel", "Jack Mackerel", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_jackmackerel)), 1, 0, 0, 1);
        FISHES[43] = new Fish(44, "Leather Jacket", "Leather Jacket", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_leatherjacket)), 1, 0, 0, 1);
        FISHES[44] = new Fish(45, "Ling", "Ling", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_ling)), 1, 0, 0, 1);
        FISHES[45] = new Fish(46, "Mako Shark", "Mako Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_makoshark)), 1, 0, 0, 1);

        FISHES[46] = new Fish(47, "Marble Fish", "Marble Fish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_marblefish)), 1, 0, 0, 1);
        FISHES[47] = new Fish(48, "Marlin", "Marlin", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_marlin)), 1, 0, 0, 1);
        FISHES[48] = new Fish(49, "Moonfish", "Moonfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_moonfish)), 1, 0, 0, 1);
        FISHES[49] = new Fish(50, "Pacific Bluefin Tuna", "Pacific Bluefin Tuna", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_pacificbluefintuna)), 1, 0, 0, 1);
        FISHES[50] = new Fish(51, "Porbeagle Shark", "Porbeagle Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_porbeagleshark)), 1, 0, 0, 1);
        FISHES[51] = new Fish(52, "Quinnat Salmon", "Quinnat Salmon", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_quinnatsalmon)), 1, 0, 0, 1);
        FISHES[52] = new Fish(53, "Red Cod", "Red Cod", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_redcod)), 1, 25, 20, 1);
        FISHES[53] = new Fish(54, "Sea Perch", "Sea Perch", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_seaperch)), 1, 0, 10, 1);
        FISHES[54] = new Fish(55, "7 Gill Shark", "7 Gill Shark", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_sevengillshark)), 1, 0, 0, 1);
        FISHES[55] = new Fish(56, "Silver Warehou", "Silver Warehou", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_silverwarehou)), 1, 0, 0, 1);

        FISHES[56] = new Fish(57, "Skate", "Skate", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_skate)), 1, 0, 0, 1);
        FISHES[57] = new Fish(58, "Skipjack", "Skipjack", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_skipjack)), 1, 0, 0, 1);
        FISHES[58] = new Fish(59, "Slender Sprat", "Slender Sprat", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_slendersprat)), 1, 0, 0, 1);
        FISHES[59] = new Fish(60, "Smelt", "Smelt", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_smelt)), 1, 0, 0, 1);
        FISHES[60] = new Fish(61, "Spiny Dogfish", "Spiny Dogfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_spinydogfish)), 1, 0, 0, 1);
        FISHES[61] = new Fish(62, "Stargazer", "Stargazer", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_stargazer)), 1, 0, 0, 1);
        FISHES[62] = new Fish(63, "Swordfish", "Swordfish", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_swordfish)), 1, 0, 0, 1);
        FISHES[63] = new Fish(64, "Trumpeter", "Trumpeter", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_trumpeter)), 1, 0, 0, 1);
        FISHES[64] = new Fish(65, "Warehou", "Warehou", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_warehou)), 1, 0, 0, 1);
        FISHES[65] = new Fish(66, "Wrasse", "Wrasse", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_wrasse)), 1, 0, 0, 1);
        FISHES[66] = new Fish(67, "Yellowfin Tuna", "Yellowfin Tuna", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.finfish_yellowfintuna)), 1, 0, 0, 1);

        FISHES[67] = new Fish(68, "Koura", "Koura", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.crayfish_koura)), 2, 0, 0, 1);
        FISHES[68] = new Fish(69, "Packhorse Rock Lobster", "Packhorse Rock Lobster", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.crayfish_packhorserocklobster)), 2, 22, 6, 1);
        FISHES[69] = new Fish(70, "Spiny Rock Lobster", "Spiny Rock Lobster", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.crayfish_spinyrocklobster)), 2, 6, 6, 1);

        FISHES[70] = new Fish(71, "Arrow Squid", "Arrow Squid", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_arrowsquid)), 3, 0, 50, 1);
        FISHES[71] = new Fish(72, "Cockle", "Cockle", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_cockle)), 3, 0, 50, 1);
        FISHES[72] = new Fish(73, "Kina", "Kina", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_kina)), 3, 0, 50, 1);
        FISHES[73] = new Fish(74, "Knobbed Whelk", "Knobbed Whelk", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_knobbedwhelk)), 3, 0, 50, 1);
        FISHES[74] = new Fish(75, "Mussel", "Mussel", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_mussel)), 3, 0, 25, 1);
        FISHES[75] = new Fish(76, "Octopus", "Octopus", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_octopus)), 3, 0, 50, 1);
        FISHES[76] = new Fish(77, "Oyster (dredge)", "Oyster (dredge)", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_oysterdredge)), 3, 6, 50, 1);
        FISHES[77] = new Fish(78, "Oyster (rock/pacific)", "Oyster (rock/pacific)", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_oysterrockpacific)), 3, 0, 100, 1);
        FISHES[78] = new Fish(79, "Paddle Crab", "Paddle Crab", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_paddlecrab)), 3, 0, 50, 1);
        FISHES[79] = new Fish(80, "Paua", "Paua", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_paua)), 3, 13, 15, 1);
        FISHES[80] = new Fish(81, "Pipi", "Pipi", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_pipi)), 3, 0, 50, 1);
        FISHES[81] = new Fish(82, "Red Crab", "Red Crab", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_redcrab)), 3, 0, 50, 1);
        FISHES[82] = new Fish(83, "Scallop", "Scallop", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_scallop)), 3, 10, 20, 1);
        FISHES[83] = new Fish(84, "Trough Shell", "Trough Shell", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_troughshell)), 3, 0, 50, 1);
        FISHES[84] = new Fish(85, "Tuatua", "Tuatua", ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.shellfish_tuatua)), 3, 0, 50, 1);


        NETRULES[0] = new NetRule(1, "Set nets must have surface float at each end that is clearly and legibly marked with the fishers last name, initials and phone no.", "Set nets must be marked", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netsmarked)));
        NETRULES[1] = new NetRule(2, "Suitable anchors and floats must be used to deploy a set net", "Use stakes to hold nets is prohibited", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_netprohibited)));
        NETRULES[2] = new NetRule(3, "Nets must not be used in a way that allows fish to be stranded in the net by the falling tide", "Stalling of nets is probihited", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_netnotallowed)));
        NETRULES[3] = new NetRule(4, "No net (Either used alone or with other nets) can be set more than a quarter of the way across a channel, bay or waterway", "Restrictions on nets in channels", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_netchannels)));
        NETRULES[4] = new NetRule(5, "NOTE: Drag nets may only be 40m in length", "Set nets must be less than 60m in length", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_60mlength)));
        NETRULES[5] = new NetRule(6, "NOTE: There must be at least 2 fishers on the boat and the second net may only be used if it is less than 10m long and has a mesh of a less than 50mm", "Maximum one net per person or one set and one bait net per vessel", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_baitvessel)));
        NETRULES[6] = new NetRule(7, "A person may not set any set within 60m of another net.", "Nets must be more than 60m apart", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_60mapart)));
        NETRULES[7] = new NetRule(8, "No person may possess or use a baited net other than a fyke net or hinaki net.", "Using baited nets is prohibited", 250, ImageHelper.convertImage(BitmapFactory.decodeResource(context.getResources(),R.drawable.netrules_bait)));

    }
    /**
     * An implementation of the onUpgrade method that will execute when a new version of the db is implemented
     *
     * @param oldVersion int
     * @param db
     * @param newVersion int
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_CATEGORY_TABLE);
        db.execSQL(DELETE_FISH_TABLE);
        //db.execSQL(DELETE_lOCATION_TABLE);
        db.execSQL(DELETE_FISHEXPERIENCE_TABLE);
        db.execSQL(DELETE_FISHCATCH_TABLE);
        db.execSQL(DELETE_CHECKLIST_TABLE);
        db.execSQL(DELETE_NETRULES_TABLE);
        db.execSQL(DELETE_FAQ_TABLE);
        onCreate(db);
    }

    /**
     * a method saving a Category
     *
     * @param aCategory
     * @return boolean
     */
     public boolean saveCategory(Category aCategory){
        SQLiteDatabase db = getWritableDatabase();
        String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Category.TABLE_NAME +
                "( " + AucklandFishingDBTables.Category.COLUMN_CAT_ID + " , "
                + AucklandFishingDBTables.Category.COLUMN_CAT_NAME + " , "
                + AucklandFishingDBTables.Category.COLUMN_CAT_DESC + " )" +
                " VALUES( " + aCategory.getCategoryId() + " , '"
                + aCategory.getCategoryName() + "' , '"
                + aCategory.getCategoryDescription() + "' )";
        Log.d("AKLFishingDB", insertSQL);
        db.execSQL(insertSQL);
        Log.d("AKLFishingDB", "Category added");
        return true;
    }

    //TBA thread
    public boolean saveCheckList(Checklist aCheckList) {
        SQLiteDatabase db = getWritableDatabase();
        String insertSQL = "INSERT INTO " + AucklandFishingDBTables.CheckList.TABLE_NAME +
                "( " + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID + " , "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + " , "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION + " , "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE + " )" +
                " VALUES( " + aCheckList.getCheckListId() + " , '"
                + aCheckList.getTitle() + "' , '"
                + aCheckList.getDescription() + "' , '"
                + aCheckList.getImage() + "' )";
        Log.d("AKLFishingDB",insertSQL);
        db.execSQL(insertSQL);
        Log.d("AKLFishingDB","Checklist added");
        //db.close();
        return true;
    }

    //TBA thread
    public boolean saveFaq(Faq aFaq) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.Faq.COLUMN_FAQ_ID, aFaq.getFaqId());
        cv.put(AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION, aFaq.getQuestion());
        cv.put(AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER, aFaq.getAnswer());
        db.insert(AucklandFishingDBTables.Faq.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","Faq added");
        db.close();
        return true;
    }

    //TBA thread
    public boolean saveFish(Fish aFish) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.Fish.COLUMN_FISH_ID, aFish.getFishId());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_FISH_NAME, aFish.getFishName());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_FISH_CAT, aFish.getFishCat());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_FISH_DESC, aFish.getFishDescription());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE, aFish.getFishImage());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM, aFish.getMinFishLengthCm());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT, aFish.getMinFishLengthCm());
        cv.put(AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG, aFish.isCombinedBag());
        db.insert(AucklandFishingDBTables.Fish.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","Fish added");
        db.close();
        return true;
    }

    //TBA thread
    public boolean saveFishingCatch(FishCatch aFishCatch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID, aFishCatch.getFishCatchId());
        cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_EXPERIENCE, aFishCatch.getFx());
        cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_LENGTH, aFishCatch.getLength());
        cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_WEIGHT, aFishCatch.getWeight());
        if(aFishCatch.getPicture()!=null)
            cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_IMAGE, aFishCatch.getPicture());
        cv.put(AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_REMARK, aFishCatch.getRemark());
        db.insert(AucklandFishingDBTables.FishCatch.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","Catch added");
        db.close();

        return true;
    }

    //TBA thread
    public boolean saveFishingExperience(FishingExperience aFishExp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID, aFishExp.getExperienceId());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_NAME, aFishExp.getLocationName());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LATITUDE, aFishExp.getLatitude());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LONGITUDE, aFishExp.getLongitude());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE, aFishExp.getDate().toString());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_TIME, aFishExp.getTime().toString());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_REMARK, aFishExp.getRemark());
        db.insert(AucklandFishingDBTables.FishingExperience.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB INSERT",cv.toString());
        Log.d("AKLFishingDB","FX added");
        db.close();
        return true;
    }

   /* //TBA thread
    public boolean saveLocation(Location aLocation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.Location.COLUMN_LOCATION_ID, aLocation.getLocationId());
        cv.put(AucklandFishingDBTables.Location.COLUMN_LOCATION_NAME, aLocation.getLocationName());
        cv.put(AucklandFishingDBTables.Location.COLUMN_LOCATION_LONGITUDE, aLocation.getLongitude());
        cv.put(AucklandFishingDBTables.Location.COLUMN_lOCATION_LATITUDE, aLocation.getLatitude());
        cv.put(AucklandFishingDBTables.Location.COLUMN_LOCATION_N0TE, aLocation.getNote());
        db.insert(AucklandFishingDBTables.Location.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","Location added");
        db.close();
        return true;
    }*/

    //TBA thread
    public boolean saveNetRule(NetRule aNetRule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID, aNetRule.getRulesId());
        cv.put(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE, aNetRule.getTitle());
        cv.put(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION, aNetRule.getDescription());
        cv.put(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY, aNetRule.getPenalty());
        cv.put(AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE, aNetRule.getImage());
        db.insert(AucklandFishingDBTables.NetRules.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","NetRule added");
        db.close();
        return true;
    }

    //TBA thread
    public Cursor getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Category> catList = null;
        String query = "SELECT " + AucklandFishingDBTables.Category.COLUMN_CAT_ID + ", "
                + AucklandFishingDBTables.Category.COLUMN_CAT_NAME + ", "
                + AucklandFishingDBTables.Category.COLUMN_CAT_DESC + " FROM "
                + AucklandFishingDBTables.Category.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            catList = new ArrayList<Category>();
            cursor.moveToFirst();
            do {
                Category aCat = new Category(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                catList.add(aCat);
            } while (cursor.moveToNext());
            db.close();

        }
        return cursor;
    }

    //TBA thread
    public Cursor getAllChecklist() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Checklist> checkList = null;
        String query = "SELECT " + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE + " FROM "
                + AucklandFishingDBTables.CheckList.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            checkList = new ArrayList<Checklist>();
            cursor.moveToFirst();
            do {
                Checklist aCheckList = new Checklist(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3));
                checkList.add(aCheckList);
            } while (cursor.moveToNext());
            db.close();

        }
        return cursor;
    }

    //TBA thread
    public Cursor getAllFaqs() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Faq> faqs = null;
        String query = "SELECT " + AucklandFishingDBTables.Faq.COLUMN_FAQ_ID + ", "
                + AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION + ", "
                + AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER + " FROM "
                + AucklandFishingDBTables.Faq.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            faqs = new ArrayList<Faq>();
            cursor.moveToFirst();
            do {
                Faq aFaq = new Faq(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                faqs.add(aFaq);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }

    //TBA thread
    public Cursor getAllFishes() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Fish> fishes = null;
        String query = "SELECT " + AucklandFishingDBTables.Fish.COLUMN_FISH_ID + ", "
                + AucklandFishingDBTables.Fish.COLUMN_FISH_NAME + ", "
                + AucklandFishingDBTables.Fish.COLUMN_FISH_DESC + ", "
                + AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE + ", "
                + AucklandFishingDBTables.Fish.COLUMN_FISH_CAT + ", "
                + AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM + ", "
                + AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT + ", "
                + AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG + " FROM "
                + AucklandFishingDBTables.Fish.TABLE_NAME +
                " ORDER BY "+AucklandFishingDBTables.Fish.COLUMN_FISH_ID;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            fishes = new ArrayList<Fish>();
            cursor.moveToFirst();
            do {
                Fish aFish = new Fish(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
                fishes.add(aFish);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }

    public Cursor getAllFishingExperience() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FishingExperience> experiences = null;
        String query = "SELECT " + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_NAME + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LATITUDE + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LONGITUDE + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_TIME + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_REMARK + " FROM "
                + AucklandFishingDBTables.FishingExperience.TABLE_NAME;
        Log.d("QUERY:",query );
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount()>0) {
            experiences = new ArrayList<FishingExperience>();
            cursor.moveToFirst();
            do {
                String date = cursor.getString(4);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                Date parsed = new Date();
                java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());

                String time = cursor.getString(5);
                Time convertedTime;
                convertedTime = Time.valueOf(time);

                FishingExperience exp = new FishingExperience(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3), convertedDate, convertedTime, cursor.getString(6));
                experiences.add(exp);
            } while (cursor.moveToNext());
            db.close();

            return cursor;
        }else return null;
    }

    //TBA thread
    public Cursor getAllFishCatch() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FishCatch> fishes = null;
        String query = "SELECT " + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_EXPERIENCE + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_LENGTH + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_WEIGHT + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_IMAGE + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_NAME + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_REMARK + " FROM "
                + AucklandFishingDBTables.FishCatch.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            fishes = new ArrayList<FishCatch>();
            cursor.moveToFirst();
            do {
                FishCatch aFish = new FishCatch(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getBlob(4), cursor.getString(5), cursor.getString(6));
                fishes.add(aFish);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }
/*
    public Cursor getAllLocations() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Location> locations = null;
        String query = "SELECT " + AucklandFishingDBTables.Location.COLUMN_LOCATION_ID + ", "
                + AucklandFishingDBTables.Location.COLUMN_lOCATION_LATITUDE + ", "
                + AucklandFishingDBTables.Location.COLUMN_LOCATION_LONGITUDE + ", "
                + AucklandFishingDBTables.Location.COLUMN_LOCATION_N0TE + ", "
                + AucklandFishingDBTables.Location.COLUMN_LOCATION_NAME + " FROM "
                + AucklandFishingDBTables.Location.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            locations = new ArrayList<Location>();
            cursor.moveToFirst();
            do {

                Location location = new Location(cursor.getInt(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getString(3), cursor.getString(4));
                locations.add(location);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }*/

    //public NetRule(int rulesId, String description, String title, double penalty, byte[] image) {
    public Cursor getAllNetRules() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NetRule> netrules = null;
        String query = "SELECT " + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID + ", "
                + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION + ", "
                + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE + ", "
                + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY + ", "
                + AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE + " FROM "
                + AucklandFishingDBTables.NetRules.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            netrules = new ArrayList<NetRule>();
            cursor.moveToFirst();
            do {
                NetRule netRule = new NetRule(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getBlob(4));
                netrules.add(netRule);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }

    public FishingExperience findFishingExperience(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishingExperience.TABLE_NAME, AucklandFishingDBTables.FishingExperience.ALL_COLUMNS,
                AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + "=?", new String[]{date}, null, null, null, null);
        FishingExperience exp = null;
        if (cursor != null) {
            cursor.moveToFirst();
            String dateEx = cursor.getString(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            Date parsed = new Date();
            java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());

            String time = cursor.getString(3);
            Time convertedTime;
            convertedTime = Time.valueOf(time);

            exp = new FishingExperience(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),convertedDate, convertedTime, cursor.getString(6));
            db.close();
        }
        return exp;
    }

    public int findLatestXPId(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT max( "+AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID+" ) from "
                    + AucklandFishingDBTables.FishingExperience.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("XP BG",cursor.getCount()+"");
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            Log.d("XP",cursor.getInt(0)+"");
            return cursor.getInt(0);
        }else {
            Log.d("XP",cursor.getCount()+"");
            return 0;
        }
    }

    public FishingExperience findFishingExperience(String date, String locationName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishingExperience.TABLE_NAME,
                                 AucklandFishingDBTables.FishingExperience.ALL_COLUMNS,
                                 AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + "=? AND"
                                +AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_NAME + "=?",
                                new String[]{date,locationName}, null, null, null, null);
        FishingExperience exp = null;
        if (cursor != null) {
            cursor.moveToFirst();
            String dateEx = cursor.getString(2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            Date parsed = new Date();
            java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());

            String time = cursor.getString(3);
            Time convertedTime;
            convertedTime = Time.valueOf(time);

            exp = new FishingExperience(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),convertedDate, convertedTime, cursor.getString(6));
            db.close();
        }
        return exp;
    }

    public int findFishingExperienceId(String date, String locationName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishingExperience.TABLE_NAME,
                                new String[]{AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID},
                            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE+ "=? AND"
                        +   AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_NAME+ "=?" ,
                            new String[]{date,locationName}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();


            int id = cursor.getInt(0);
            db.close();
            return id;
        }
        return 0;
    }

    public Cursor findCatches(String fExId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishCatch.TABLE_NAME, AucklandFishingDBTables.FishCatch.ALL_COLUMNS,
                AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_EXPERIENCE + "=?", new String[]{fExId}, null, null, null, null);
        ArrayList<FishCatch> fishes = null;
        if (cursor != null) {
            fishes = new ArrayList<FishCatch>();
            cursor.moveToFirst();
            do {
                FishCatch aFish = new FishCatch(cursor.getInt(0), cursor.getInt(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getBlob(4), cursor.getString(5), cursor.getString(6));
                fishes.add(aFish);
            } while (cursor.moveToNext());
            db.close();
        }
        return cursor;
    }

    public int findLatestFCId(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT max( "+AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID+" ) from "
                + AucklandFishingDBTables.FishCatch.TABLE_NAME;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("XP BG",cursor.getCount()+"");
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            Log.d("XP",cursor.getInt(0)+"");
            return cursor.getInt(0);
        }else {
            Log.d("XP",cursor.getCount()+"");
            return 0;
        }
    }


    public Category findCategory(String categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Category.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Category.COLUMN_CAT_ID,
                        AucklandFishingDBTables.Category.COLUMN_CAT_NAME,
                        AucklandFishingDBTables.Category.COLUMN_CAT_DESC},
                        AucklandFishingDBTables.Category.COLUMN_CAT_NAME + "=?",
                        new String[]{categoryId}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Category aCat = new Category(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            db.close();
            return aCat;
        }
        return null;
    }

      public int findCategoryId(String catName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Category.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Category.COLUMN_CAT_ID},
                AucklandFishingDBTables.Category.COLUMN_CAT_NAME + "=?",
                new String[]{catName}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int id =cursor.getInt(0);
            db.close();
            return id;
        }
        return 0;
      }

    public Checklist findChecklist(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION + ", "
                + AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE + " FROM "
                + AucklandFishingDBTables.CheckList.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Checklist aCheckList = new Checklist(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3));
            db.close();
            return aCheckList;
        }
       return null;
    }

    public Faq findFaq(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Faq.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Faq.COLUMN_FAQ_ID,
                        AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION,
                        AucklandFishingDBTables.Faq.COLUMN_FAQ_ANSWER},
                        AucklandFishingDBTables.Faq.COLUMN_FAQ_ID+ "=?",
                         new String[]{id}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Faq aFaq = new Faq(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            db.close();
            return aFaq;
        }

        return null;
    }

    public int findChecklistId(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.CheckList.TABLE_NAME,
                new String[]{AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID},
                AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE + "=?",
                new String[]{title}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int id =cursor.getInt(0);
            db.close();
            return id;
        }
        return 0;
    }

    public int findFaqId(String question) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Faq.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Faq.COLUMN_FAQ_ID},
                AucklandFishingDBTables.Faq.COLUMN_FAQ_QUESTION+ "=?",
                new String[]{question}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int id =cursor.getInt(0);
            db.close();
            return id;
        }
        return 0;
    }

    public FishCatch findFishCatch(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishCatch.TABLE_NAME,
                new String[]{AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_EXPERIENCE,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_LENGTH,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_WEIGHT,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_IMAGE,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_NAME,
                            AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_REMARK},
                AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID+ "=?",
                new String[]{id}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            FishCatch aFishCatch = new FishCatch(cursor.getInt(0),cursor.getInt(1),cursor.getDouble(2),cursor.getDouble(3),cursor.getBlob(4),cursor.getString(5),cursor.getString(6));
            db.close();
            return aFishCatch;
        }
        return null;
    }

    public Fish findFish(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Fish.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Fish.COLUMN_FISH_ID,
                        AucklandFishingDBTables.Fish.COLUMN_FISH_NAME,
                        AucklandFishingDBTables.Fish.COLUMN_FISH_DESC,
                        AucklandFishingDBTables.Fish.COLUMN_FISH_IMAGE,
                        AucklandFishingDBTables.Fish.COLUMN_FISH_CAT,
                        AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_LENGTH_CM,
                        AucklandFishingDBTables.Fish.COLUMN_MIN_FISH_MAX_DAILY_LIMIT,
                        AucklandFishingDBTables.Fish.COLUMN_IS_COMBINED_BAG},
                AucklandFishingDBTables.Fish.COLUMN_FISH_ID+ "=?",
                new String[]{id}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Fish aFish = new Fish(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7));
            db.close();
            return aFish;
        }
        return null;
    }

    public int findFishId(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.Fish.TABLE_NAME,
                new String[]{AucklandFishingDBTables.Fish.COLUMN_FISH_ID},
                             AucklandFishingDBTables.Fish.COLUMN_FISH_NAME+ "=?",
                new String[]{name}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            db.close();

            return id;
        }
        return 0;
    }

    public NetRule findNetRule(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.NetRules.TABLE_NAME,
                new String[]{AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID,
                        AucklandFishingDBTables.NetRules.COLUMN_NETRULES_DESCRIPTION,
                        AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE,
                        AucklandFishingDBTables.NetRules.COLUMN_NETRULES_PENALTY,
                        AucklandFishingDBTables.NetRules.COLUMN_NETRULES_IMAGE,
                        },
                AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID+ "=?",
                new String[]{id}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            NetRule aRule = new NetRule(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3),cursor.getBlob(4));
            db.close();
            return aRule;
        }
        return null;
    }
    public int findNetRuleId(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.NetRules.TABLE_NAME,
                new String[]{AucklandFishingDBTables.NetRules.COLUMN_NETRULES_ID},
                AucklandFishingDBTables.NetRules.COLUMN_NETRULES_TITLE+ "=?",
                new String[]{title}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            db.close();

            return id;
        }
        return 0;
    }
}
