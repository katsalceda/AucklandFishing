package rktechltd.aucklandfishing.models;

/**
 * Class representing Faq
 * Auckland Fishing
 * @version 16/05/2016
 * @author Katrina Salceda and Romelyn Ungab
 */

public class Faq {

    private int faqId; //FAQ id
    private String question; //The question
    private String answer; //The answer

    //Constructors
    public Faq(){} //default constructor

    /**
     * FAQ constructor
     * @param faqId an integer represening the ID of the FAQ
     * @param question String representing the question
     * @param answer String representing the answer
     */
    public Faq(int faqId, String question, String answer) {
        this.faqId = faqId;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Gets the Faq Id
     * @return integer FAQ id
     */
    public int getFaqId() {
        return faqId;
    }

    /**
     *Sets the faq id
     * @param faqId
     */
    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    /**
     * Gets the faq question
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the faq question
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the faq answer
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the faq answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

