package models;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class QuestionType {
    
    /* Variables Declaration */
    private int questionID;
    private String questionText;

    /**
     * Constructor
     * @param questionID references the question ID
     * @param questionText references the question text
     */
    public QuestionType(int questionID, String questionText) {
        this.questionID = questionID;
        this.questionText = questionText;
    }
    
    /**
     * Returns the question ID
     * @return an integer representing the question ID
     */
    public int getQuestionID() {
        return questionID;
    }

    /**
     * Sets the question ID
     * @param questionID is the question ID given
     */
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    /**
     * Returns the Question 
     * @return a string representation of the question 
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Sets the question Text
     * @param questionText is the question text given
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}