/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author oneZt
 */
public class Question {
    private String fullQuestion;
    private String correctAnswer;
    private int questionType;

    public Question(String fullQuestion, String correctAnswer, int questionType) {
        this.fullQuestion = fullQuestion;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
    }

    public String getFullQuestion() {
        return fullQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setFullQuestion(String fullQuestion) {
        this.fullQuestion = fullQuestion;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
    
    
}
