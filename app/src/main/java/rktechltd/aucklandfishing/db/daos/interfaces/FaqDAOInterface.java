package rktechltd.aucklandfishing.db.daos.interfaces;
import android.database.Cursor;
import rktechltd.aucklandfishing.models.Faq;

/**
 * An interface for Faq Data Access Object
 * Auckland Fishing
 * @version 16/05/2016
 * @author Romelyn Ungab and Katrina Salceda
 */

public interface FaqDAOInterface {
    Faq getFaqs(int faqId);
    Cursor getAllFaqs();
    boolean addFaq(Faq faq);
    int getFaqId(String question);
}
