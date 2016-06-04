package rktechltd.aucklandfishing.db.daos.interfaces;

import java.util.List;

import rktechltd.aucklandfishing.models.Faq;

/**
 * Created by romelyn on 30/05/2016.
 */
public interface FaqDAOInterface {
    Faq getFaqs(int faqId);
    List<Faq> getAllFaqs();
    boolean addFaq(Faq faq);
    int getFaqId(String question);
}
