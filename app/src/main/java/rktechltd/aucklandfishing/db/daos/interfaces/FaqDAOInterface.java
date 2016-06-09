package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Faq;

/**
<<<<<<< HEAD
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
=======
 * An interface for Faq Data Access Object
 * Created by romelyn on 30/05/2016.
>>>>>>> origin/master
 */

public interface FaqDAOInterface {
    Faq getFaqs(int faqId);
    Cursor getAllFaqs();
    boolean addFaq(Faq faq);
    int getFaqId(String question);
}
