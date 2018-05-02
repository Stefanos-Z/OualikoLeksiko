package models;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class Question {
    
    /* Variables Declaration */
    private String fullQuestion;
    private String correctAnswer;
    private int questionType;

    /**
     * Constructor
     * @param fullQuestion references the questions
     * @param correctAnswer references the answer
     * @param questionType references the question type
     */
    public Question(String fullQuestion, String correctAnswer, int questionType) {
        this.fullQuestion = fullQuestion;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    /**
     * Returns the full question 
     * @return a string representation of the question
     */
    public String getFullQuestion() {
        return fullQuestion;
    }

    /**
     * Returns the correct answer
     * @return a string representation of the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the question type
     * @return a integer representing the question type
     */
    public int getQuestionType() {
        return questionType;
    }

    /**
     * Sets the full question 
     * @param fullQuestion is the full question given
     */
    public void setFullQuestion(String fullQuestion) {
        this.fullQuestion = fullQuestion;
    }

    /**
     * Sets the correct answer
     * @param correctAnswer is the correct answer given 
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Sets the question tyoe
     * @param questionType is the question type given
     */
    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}