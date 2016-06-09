package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FaqDAOInterface;
import rktechltd.aucklandfishing.models.Faq;

/**
 * A Faq Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FaqDAO implements FaqDAOInterface  {
    private AucklandFishingDBHelper db ;
    /**
     * Constructor
     * @param context
     */
    public FaqDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    /**
     * gets the Faq given the id
     * @param faqId
     * @return
     */
    @Override
    public Faq getFaqs(int faqId) {
        return db.findFaq(""+faqId);
    }
    /**
     * Gets all Faq
     * @return Cursor
     */
    @Override
    public Cursor getAllFaqs() {
      return db.getAllFaqs();
    }
    /**
     * adds new Faq
     * @param faq
     * @return
     */
    @Override
    public boolean addFaq(Faq faq) {
       return db.saveFaq(faq);
    }

    /**
     * Gets the id given a question
     * @param question
     * @return
     */
    @Override
    public int getFaqId(String question) {
      return db.findFaqId(question);
    }
}
