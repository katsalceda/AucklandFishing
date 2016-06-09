package rktechltd.aucklandfishing.db.daos.implementations;
import android.content.Context;
import android.database.Cursor;
import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FaqDAOInterface;
import rktechltd.aucklandfishing.models.Faq;

/**
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public class FaqDAO implements FaqDAOInterface  {
    private AucklandFishingDBHelper db ;
    public FaqDAO(Context context){
        db = new AucklandFishingDBHelper(context);//initialize database with context
    }
    @Override
    public Faq getFaqs(int faqId) {
        return db.findFaq(""+faqId);
    }

    @Override
    public Cursor getAllFaqs() {
      return db.getAllFaqs();
    }

    @Override
    public boolean addFaq(Faq faq) {
       return db.saveFaq(faq);
    }

    @Override
    public int getFaqId(String question) {
      return db.findFaqId(question);
    }
}
