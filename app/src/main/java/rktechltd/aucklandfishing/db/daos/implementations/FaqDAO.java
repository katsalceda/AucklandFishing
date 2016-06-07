package rktechltd.aucklandfishing.db.daos.implementations;

import android.content.Context;

import java.util.List;

import rktechltd.aucklandfishing.db.AucklandFishingDBHelper;
import rktechltd.aucklandfishing.db.daos.interfaces.FaqDAOInterface;
import rktechltd.aucklandfishing.models.Faq;

/**
 * Created by romelyn on 30/05/2016.
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
    public List<Faq> getAllFaqs() {
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
