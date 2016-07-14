package rktechltd.aucklandfishing.db;
import android.provider.BaseColumns;

/**
 * This class is an implementation of the database designed for the Auckland Fishing App
 * Auckland Fishing
 * @version 12/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */
public final class AucklandFishingDBTables {
    /**
     * A constructor for AKLFishingDBTables
     */
    public AucklandFishingDBTables(){    }

    /**
     * A subclass which is an implementation of the FishCategory table
     */
    public static abstract class Category implements BaseColumns {
        public static final String TABLE_NAME = "category"; //table name category
        public static final String COLUMN_CAT_ID = "categoryId";//implementation of the categoryId column
        public static final String COLUMN_CAT_NAME = "categoryName";//implementation of the name column
        public static final String COLUMN_CAT_DESC = "categoryDescription"; //implementation of the description column
        public static final String PRIMARY_KEY="PRIMARY KEY("+ COLUMN_CAT_ID+")";//implementation of the categoryId as primary key
        public static final String[] ALL_COLUMNS ={COLUMN_CAT_ID,COLUMN_CAT_NAME,COLUMN_CAT_DESC};
    }

    /**
     * A subclass which is an implementation of the SafetyCheckList table
     */
    public static abstract class CheckList implements BaseColumns {
        public static final String TABLE_NAME = "checkList";//table name category
        public static final String COLUMN_CHECKLIST_ID = "checkListId"; //implementation of the checklistId column
        public static final String COLUMN_CHECKLIST_TITLE = "title";//implementation of the title column
        public static final String COLUMN_CHECKLIST_DESCRIPTION = "description";//implementation of the descriptiom column
        public static final String COLUMN_CHECKLIST_IMAGE = "image";////implementation of the image column
        public static final String COLUMN_CHECKLIST_INDEX = "ind";////implementation of the image column
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_CHECKLIST_ID + ")"; //implementation of the checklistId as primary key
        public static final String[] ALL_COLUMNS = {COLUMN_CHECKLIST_ID,COLUMN_CHECKLIST_TITLE, COLUMN_CHECKLIST_DESCRIPTION, COLUMN_CHECKLIST_IMAGE,COLUMN_CHECKLIST_INDEX};
    }

    /**
     * A sublcass which is an implementation of the FAQ table
     */
    public static abstract class Faq implements BaseColumns {
        public static final String TABLE_NAME = "faq";//table name faq
        public static final String COLUMN_FAQ_ID = "faqId";//implementation of the faqId column
        public static final String COLUMN_FAQ_QUESTION = "question";//implementation of the question column
        public static final String COLUMN_FAQ_ANSWER = "answer";//implementation of the answer column
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_FAQ_ID + ")";//implementation of the faqId as primary key
        public static final String[] ALL_COLUMNS = {COLUMN_FAQ_ID, COLUMN_FAQ_QUESTION, COLUMN_FAQ_ANSWER};
    }

    /**
     * A subclass which is an implementation of the Fish table
     */
    public static abstract class Fish implements BaseColumns{
        public static final String TABLE_NAME = "fish";//table name fish
        public static final String COLUMN_FISH_ID = "fishId";//implementation of the fishId column
        public static final String COLUMN_FISH_NAME = "fishName";//implementation of the name column
        public static final String COLUMN_FISH_DESC = "fishDescription";//implementation of the description column
        public static final String COLUMN_FISH_IMAGE = "fishImage";//implementation of the image column
        public static final String COLUMN_FISH_CAT ="categoryId";//implementation of the categoryId reference to the category table
        public static final String COLUMN_MIN_FISH_LENGTH_CM ="minFishLengthCm";//implementation of the minfishlengthcm column
        public static final String COLUMN_MIN_FISH_MAX_DAILY_LIMIT="maxDailyLimit";//implementation of the maxdailylimit column
        public static final String COLUMN_IS_COMBINED_BAG="isCombinedBag";//implementation of the isCombinedaBag column
        public static final String COLUMN_FISH_INDEX="ind";//implementation of the isCombinedaBag column
        public static final String PRIMARY_KEY="PRIMARY KEY("+ COLUMN_FISH_ID+")";//implementation of the fishId as primary key
        public static final String[] ALL_COLUMNS ={COLUMN_FISH_ID,COLUMN_FISH_NAME,COLUMN_FISH_DESC,COLUMN_FISH_IMAGE,COLUMN_FISH_CAT,COLUMN_MIN_FISH_LENGTH_CM,COLUMN_MIN_FISH_MAX_DAILY_LIMIT,COLUMN_IS_COMBINED_BAG,COLUMN_FISH_INDEX};
    }

    /**
     * A subclass which is an implementation of the FishingExperience table
     */
    public static abstract class FishingExperience implements BaseColumns{
        public static final String TABLE_NAME = "fishingExperience";//table name fishingExperience
        public static final String COLUMN_FISHING_EXPERIENCE_ID ="fishingExperienceId";//implementation of the categoryId column
        public static final String COLUMN_FISHING_EXPERIENCE_NAME = "locationName";//implementation of the name column
        public static final String COLUMN_FISHING_EXPERIENCE_LATITUDE = "locationLatitude";//implementation of the latitude column
        public static final String COLUMN_FISHING_EXPERIENCE_LONGITUDE = "locationLongitude";//implementation of the longitude column
        public static final String COLUMN_FISHING_EXPERIENCE_DATE ="dateEx";//implementation of the date column
        public static final String COLUMN_FISHING_EXPERIENCE_TIME ="timeEx";//implementation of the time column
        public static final String COLUMN_FISHING_EXPERIENCE_REMARK ="remark";//implementation of the remark column
        public static final String PRIMARY_KEY="PRIMARY KEY ("+COLUMN_FISHING_EXPERIENCE_ID+")";
        public static final String[] ALL_COLUMNS ={COLUMN_FISHING_EXPERIENCE_ID,COLUMN_FISHING_EXPERIENCE_NAME,COLUMN_FISHING_EXPERIENCE_LATITUDE,COLUMN_FISHING_EXPERIENCE_LONGITUDE, COLUMN_FISHING_EXPERIENCE_DATE,COLUMN_FISHING_EXPERIENCE_TIME,COLUMN_FISHING_EXPERIENCE_REMARK};
    }

    /**
     * A subclass which is an implementation of the FishCatch table
     */
    public static abstract class FishCatch implements BaseColumns{
        public static final String TABLE_NAME = "fishCatch";//table name fishCatch
        public static final String COLUMN_FISH_CATCH_ID ="fishCatchId";////implementation of the fishCatchId column
        public static final String COLUMN_FISH_CATCH_EXPERIENCE ="experienceId";//implementation of the experienceId column a reference to the fishingExperience table
        public static final String COLUMN_FISH_CATCH_LENGTH ="fishLength";//implementation of the length column
        public static final String COLUMN_FISH_CATCH_WEIGHT ="fishWeight";//implementation of the weight column
        public static final String COLUMN_FISH_CATCH_REMARK ="remark";//implementation of the remark column
        public static final String COLUMN_FISH_CATCH_NAME ="catchName";//implementation of the catchName column
        public static final String COLUMN_FISH_CATCH_IMAGE ="image";//implementation of the image column
        public static final String PRIMARY_KEY="PRIMARY KEY ("+COLUMN_FISH_CATCH_ID+")";//implementation of the fishCatchId column as primary key
        public static final String[] ALL_COLUMNS ={COLUMN_FISH_CATCH_ID,COLUMN_FISH_CATCH_EXPERIENCE,COLUMN_FISH_CATCH_LENGTH,COLUMN_FISH_CATCH_WEIGHT,COLUMN_FISH_CATCH_REMARK,COLUMN_FISH_CATCH_NAME};
    }

    /**
     * A subclass which is an implementation of the NetRule table
     */
    public static abstract class NetRules implements BaseColumns {
        public static final String TABLE_NAME = "netRules";// table name netRules
        public static final String COLUMN_NETRULES_ID = "netRulesId";//implementation of the netruleId column
        public static final String COLUMN_NETRULES_TITLE = "title";//implementation of the title column
        public static final String COLUMN_NETRULES_DESCRIPTION = "description";//implementation of the description column
        public static final String COLUMN_NETRULES_PENALTY = "penalty";//implementation of the penalty column
        public static final String COLUMN_NETRULES_IMAGE = "image";//implementation of the image column
        public static final String COLUMN_NETRULES_INDEX = "ind";//implementation of the image column
        public static final String PRIMARY_KEY = "PRIMARY KEY (" + COLUMN_NETRULES_ID + ")";//implementation of the netrulesId column as primary key
        public static final String[] ALL_COLUMNS = {COLUMN_NETRULES_ID, COLUMN_NETRULES_TITLE, COLUMN_NETRULES_DESCRIPTION,COLUMN_NETRULES_PENALTY, COLUMN_NETRULES_IMAGE,COLUMN_NETRULES_INDEX};
    }
}

