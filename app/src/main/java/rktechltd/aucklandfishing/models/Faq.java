package rktechltd.aucklandfishing.models;

/**
 * Created by romelyn on 30/05/2016.
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
     *
     * @param faqId
     */
    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

