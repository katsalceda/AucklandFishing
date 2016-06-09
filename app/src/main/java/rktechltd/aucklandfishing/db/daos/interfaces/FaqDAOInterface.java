package rktechltd.aucklandfishing.db.daos.interfaces;

import android.database.Cursor;

import java.util.List;

import rktechltd.aucklandfishing.models.Faq;

/**
 * An interface for Faq Data Access Object
 * Created by romelyn on 30/05/2016.
 */
public interface FaqDAOInterface {
    Faq getFaqs(int faqId);
    Cursor getAllFaqs();
    boolean addFaq(Faq faq);
    int getFaqId(String question);
}
