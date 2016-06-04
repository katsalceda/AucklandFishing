package rktechltd.aucklandfishing.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rktechltd.aucklandfishing.models.Category;
import rktechltd.aucklandfishing.models.Checklist;
import rktechltd.aucklandfishing.models.Faq;
import rktechltd.aucklandfishing.models.Fish;
import rktechltd.aucklandfishing.models.FishCatch;
import rktechltd.aucklandfishing.models.FishingExperience;
import rktechltd.aucklandfishing.models.Location;
import rktechltd.aucklandfishing.models.NetRule;
import rktechltd.aucklandfishing.utilities.ImageHelper;
import static rktechltd.aucklandfishing.db.DefaultValues.*;


/**
 * Created by romelyn on 13/05/2016.
 */
public class AucklandFishingDBHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase db;
    public static final String DB_NAME = "aklfishingdatabase.db";
    public static final int DB_VERSION = 25;

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

    private static final String CREATE_LOCATION_TABLE = "CREATE TABLE " + AucklandFishingDBTables.Location.TABLE_NAME + " (" +
            AucklandFishingDBTables.Location.COLUMN_LOCATION_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Location.COLUMN_LOCATION_NAME + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Location.COLUMN_lOCATION_LATITUDE + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Location.COLUMN_LOCATION_LONGITUDE + REAL_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Location.COLUMN_LOCATION_N0TE + TEXT_TYPE + COMMA_SEP +
            AucklandFishingDBTables.Location.PRIMARY_KEY +
            " )";

    private static final String CREATE_FISHEXPERIENCE_TABLE = "CREATE TABLE " + AucklandFishingDBTables.FishingExperience.TABLE_NAME + " (" +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID + NUMERIC_TYPE + COMMA_SEP +
            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LOCATION_ID + NUMERIC_TYPE + COMMA_SEP +
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
    private static final String DELETE_lOCATION_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Location.TABLE_NAME;
    private static final String DELETE_FISHEXPERIENCE_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.FishingExperience.TABLE_NAME;
    private static final String DELETE_FISHCATCH_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.FishCatch.TABLE_NAME;
    private static final String DELETE_CHECKLIST_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.CheckList.TABLE_NAME;
    private static final String DELETE_NETRULES_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.NetRules.TABLE_NAME;
    private static final String DELETE_FAQ_TABLE = "DROP TABLE IF EXISTS " + AucklandFishingDBTables.Faq.TABLE_NAME;


    public AucklandFishingDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
        db = getWritableDatabase();
        Log.d("AKLFishingDB","Successfully created DB");
        Log.d("AKLFishingDB",CREATE_CATEGORY_TABLE);

    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_TABLE);
        Log.d("AKLFishingDB", "CAT created");
        db.execSQL(CREATE_FISH_TABLE);
        Log.d("AKLFishingDB", "FISH created");
        db.execSQL(CREATE_LOCATION_TABLE);
        Log.d("AKLFishingDB", "LOCATION created");
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

        initializeTables();
    }

    public void initializeTables(){
        Log.d("AKLFishingDB", "initializing");
        //save existing category data
         saveCategory(CATEGORIES); //saving the data
        Log.d("AKLFishingDB", "cat data saved");
    }

      /*  //save existing checklist data
        for (Checklist c : CHECKLISTS) {
            saveCheckList(c);//saving the data
            Log.d("AKLFishingDB","ck data saved");
        }



       //save existing faq data
        for (Faq f : FAQS) {
            saveFaq(f); //saving the data
            Log.d("AKLFishingDB","faq data saved");
        }

        //save existing netrules data
        for (NetRule n : NETRULES) {
            saveNetRule(n);//saving the data
            Log.d("AKLFishingDB","net data saved");
        }

        //save existing fish data
        for (Fish f : FISHES) {
            saveFish(f);//saving the data
            Log.d("AKLFishingDB","fish data saved");
        }*/



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
        db.execSQL(DELETE_lOCATION_TABLE);
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
     * @param aCategoryList
     * @return boolean
     */
    //TBA thread
    public boolean saveCategory(List<Category> aCategoryList) {
        SQLiteDatabase db = this.getWritableDatabase();
     /*   ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.Category.COLUMN_CAT_ID, aCategory.getCategoryId());
        cv.put(AucklandFishingDBTables.Category.COLUMN_CAT_NAME, aCategory.getCategoryName());
        cv.put(AucklandFishingDBTables.Category.COLUMN_CAT_DESC, aCategory.getCategoryDescription());*/
    //  this.db.insert(AucklandFishingDBTables.Category.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB", "ADding Category");
        for(Category aCategory : aCategoryList) {
            String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Category.TABLE_NAME +
                    "( " + AucklandFishingDBTables.Category.COLUMN_CAT_ID + " , "
                    + AucklandFishingDBTables.Category.COLUMN_CAT_NAME + " , "
                    + AucklandFishingDBTables.Category.COLUMN_CAT_DESC + " )" +
                    " VALUES( " + aCategory.getCategoryId() + " , "
                    + aCategory.getCategoryName() + " , "
                    + aCategory.getCategoryDescription() + " )";
            Log.d("AKLFishingDB", insertSQL);
            db.execSQL(insertSQL);
            Log.d("AKLFishingDB", "Category added");
        }
        db.close();
        return true;
    }

    public boolean saveCategory(Category aCategory){
        String insertSQL = "INSERT INTO " + AucklandFishingDBTables.Category.TABLE_NAME +
                "( " + AucklandFishingDBTables.Category.COLUMN_CAT_ID + " , "
                + AucklandFishingDBTables.Category.COLUMN_CAT_NAME + " , "
                + AucklandFishingDBTables.Category.COLUMN_CAT_DESC + " )" +
                " VALUES( " + aCategory.getCategoryId() + " , "
                + aCategory.getCategoryName() + " , "
                + aCategory.getCategoryDescription() + " )";
        Log.d("AKLFishingDB", insertSQL);
        this.db.execSQL(insertSQL);
        Log.d("AKLFishingDB", "Category added");
        return true;
    }

    //TBA thread
    public boolean saveCheckList(Checklist aCheckList) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_ID, aCheckList.getCheckListId());
        cv.put(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_TITLE, aCheckList.getTitle());
        cv.put(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_DESCRIPTION, aCheckList.getDescription());
        cv.put(AucklandFishingDBTables.CheckList.COLUMN_CHECKLIST_IMAGE, aCheckList.getImage());
        Log.d("AKLFishingDB",cv.toString());
        db.insert(AucklandFishingDBTables.CheckList.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","Checklist added");
        db.close();
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
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LOCATION_ID, aFishExp.getLocation());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE, aFishExp.getDate().toString());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_TIME, aFishExp.getTime().toString());
        cv.put(AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_REMARK, aFishExp.getRemark());
        db.insert(AucklandFishingDBTables.FishingExperience.TABLE_NAME, null, cv);
        Log.d("AKLFishingDB","FX added");
        db.close();
        return true;
    }

    //TBA thread
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
    }

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
    public ArrayList<Faq> getAllFaqs() {
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
        return faqs;
    }

    //TBA thread
    public ArrayList<Fish> getAllFishes() {
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
                + AucklandFishingDBTables.Fish.TABLE_NAME;
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
        return fishes;
    }

    public ArrayList<FishingExperience> getAllFishingExperience() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FishingExperience> experiences = null;
        String query = "SELECT " + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LOCATION_ID + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_TIME + ", "
                + AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_REMARK + " FROM "
                + AucklandFishingDBTables.FishingExperience.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            experiences = new ArrayList<FishingExperience>();
            cursor.moveToFirst();
            do {
                String date = cursor.getString(2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
                Date parsed  = new Date();
                java.sql.Date convertedDate = new java.sql.Date(parsed.getTime());

                String time = cursor.getString(3);
                Time convertedTime;
                convertedTime = Time.valueOf(time);

                FishingExperience exp = new FishingExperience(cursor.getInt(0), cursor.getInt(1), convertedDate, convertedTime, cursor.getString(4));
                experiences.add(exp);
            } while (cursor.moveToNext());
            db.close();
        }
        return experiences;
    }

    //TBA thread
    public ArrayList<FishCatch> getAllFishCatch() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FishCatch> fishes = null;
        String query = "SELECT " + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_ID + ", "
                + AucklandFishingDBTables.FishCatch.COLUMN_FISH_CATCH_LENGTH + ", "
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
        return fishes;
    }

    public ArrayList<Location> getAllLocations() {
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
        return locations;
    }

    //public NetRule(int rulesId, String description, String title, double penalty, byte[] image) {
    public ArrayList<NetRule> getAllNetRules() {
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
        return netrules;
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

            exp = new FishingExperience(cursor.getInt(0), cursor.getInt(1), convertedDate, convertedTime, cursor.getString(4));
            db.close();
        }
        return exp;
    }

    public FishingExperience findFishingExperience(String date, String locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishingExperience.TABLE_NAME,
                                 AucklandFishingDBTables.FishingExperience.ALL_COLUMNS,
                                 AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE + "=? AND"
                                +AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LOCATION_ID + "=?",
                                new String[]{date,locationId}, null, null, null, null);
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

            exp = new FishingExperience(cursor.getInt(0), cursor.getInt(1), convertedDate, convertedTime, cursor.getString(4));
            db.close();
        }
        return exp;
    }

    public int findFishingExperienceId(String date, String locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AucklandFishingDBTables.FishingExperience.TABLE_NAME,
                                new String[]{AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_ID},
                            AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_DATE+ "=? AND"
                        +   AucklandFishingDBTables.FishingExperience.COLUMN_FISHING_EXPERIENCE_LOCATION_ID+ "=?" ,
                            new String[]{date,locationId}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();


            int id = cursor.getInt(0);
            db.close();
            return id;
        }
        return 0;
    }

    public List<FishCatch> findCatches(String fExId) {
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
        return fishes;
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
