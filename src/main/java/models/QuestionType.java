package models;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class QuestionType {
    
    private int questionID;
    private String questionText;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType(int questionID, String questionText) {
        this.questionID = questionID;
        this.questionText = questionText;
    }
}
