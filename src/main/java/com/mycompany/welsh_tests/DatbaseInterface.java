/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Question;
import models.QuestionType;
import models.Sessions;
import models.User;
import models.WelshWord;

/**
 *
 * @author oneZt
 */
public class DatbaseInterface {
    private DatabaseManager manager = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Random r = new Random();
    
    
    Map<String, String> users = new HashMap<>();//map for the student table
    Map<String, String> questionType = new HashMap<>();//map for the staff table
    Map<String, String> welshWords = new HashMap<>();//map for the module table
    Map<String, String> tests = new HashMap<>();//map for the registration table
    Map<String, String> testsTaken = new HashMap<>();//map for the teaches table
    Map<String, String> sessions = new HashMap<>();//map for the teaches table
    Map<String, String> testQuestions = new HashMap<>();//map for the teaches table
    
    public DatbaseInterface()
    {
        intatiateMaps();
    }
    
    public boolean getConection(){
        try {
            manager = new DatabaseManager();
        } catch (IOException ex) {
            System.out.println("error IO");
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public void createSession(Sessions session)
    {
        sessions.put("session_id",session.getSessionID());
        sessions.put("user_name",session.getUserName());
        sessions.put("expiration_date",""+session.getExpirationDate());
        try {
            manager.addNewRowToTable(sessions);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    private void intatiateMaps() {
        users.put("Table","Users");
        users.put("user_name","");
        users.put("user_password","");
        users.put("user_email","");
        users.put("user_type","");
        users.put("PRIMARY KEY1","user_name");
        
        
        questionType.put("Table","QuestionType");
        questionType.put("question_id","");
        questionType.put("question_text","");
        questionType.put("PRIMARY KEY1","question_id");
        
        welshWords.put("Table","WelshWords");
        welshWords.put("welsh_word","");
        welshWords.put("english_meaning","");
        welshWords.put("gender","");
        welshWords.put("PRIMARY KEY1","word_id");
        
        tests.put("Table", "Test");
        tests.put("test_id", "");
        tests.put("test_title", "");
        tests.put("date_created", "");
        tests.put("user_name", "");
        tests.put("PRIMARY KEY1", "test_id");
        
        
        testsTaken.put("Table","TestsTaken");
        testsTaken.put("test_id","");
        testsTaken.put("user_name","");
        testsTaken.put("grade","");
        testsTaken.put("date_submitted","");
        testsTaken.put("PRIMARY KEY1","test_id");
        testsTaken.put("PRIMARY KEY2","user_name");
        
        
        sessions.put("Table", "Sessions");
        sessions.put("session_id", "");
        sessions.put("user_name", "");
        sessions.put("expiration_date", "");
        sessions.put("PRIMARY KEY1", "session_id");
        
        testQuestions.put("Table","TestQuestions");
        testQuestions.put("question_id","");
        testQuestions.put("word_id","");
        testQuestions.put("question_full","");
        testQuestions.put("correct_answer","");
        //testQuestions.put("PRIMARY KEY1","respondent_question_id");
        
    }
//    public static void main(String[] args) {
//        DatbaseInterface ins = new DatbaseInterface();
//        ins.getConection();
//        //ins.createAndGetQuestions(5);
//        
//    }

    public User getUseByUserName(String username) {
        try {
            User thisUser = manager.getUser(username, users);
            return thisUser;
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!No User Found!!!!!!");
        }
        return null;
    }
    
    public ArrayList<Question> createAndGetQuestions(int numOfQuestions)
    {
        ArrayList<Question> allQuestions = new ArrayList<>();
        try {
            ArrayList<QuestionType> allQT = manager.getAllQuestionTypes(questionType);
            ArrayList<WelshWord> allWelshWords = manager.getAllWelshWords(welshWords);
            int numOfWelshWords = allWelshWords.size();
            int numOfQuestionType = allQT.size();
            
            for(int i =0; i<numOfQuestions;i++)
            {
                int questionTypeID = r.nextInt(numOfQuestionType)+1;
                testQuestions.put("question_id",""+questionTypeID);
                int welshWordID = r.nextInt(numOfWelshWords)+1;
                testQuestions.put("word_id",""+welshWordID);
                
                //WelshWord thisWord = manager.getWelshWord(welshWords, welshWordID);
                //String questionText = manager.getQuestionText(questionTypeID,questionType);
                
                WelshWord thisWord = allWelshWords.get(welshWordID);
                String questionText = allQT.get(questionTypeID).getQuestionText();
                String answer = "";
                switch (questionTypeID){
                    case 1:
                        questionText= questionText.replaceAll("<>", thisWord.getWelshWord());
                        testQuestions.put("correct_answer",thisWord.getGender());
                        answer = thisWord.getGender();
                    break;
                    case 2:
                        questionText = questionText.replaceAll("<>", thisWord.getWelshWord());
                        testQuestions.put("correct_answer",thisWord.getEnglishMeaning());
                        answer = thisWord.getEnglishMeaning();
                    case 3:
                        questionText= questionText.replaceAll("<>", thisWord.getEnglishMeaning());
                        testQuestions.put("correct_answer",thisWord.getWelshWord());
                        answer = thisWord.getWelshWord();
                        break;
                }
                testQuestions.put("question_full",questionText);
                allQuestions.add(new Question(questionText,answer,questionTypeID));
                //manager.addNewRowToTable(testQuestions);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allQuestions;
        
        
        
        
    }
    
    //MODAL
    public void addWelshWord(String welshMeaning, String englishMeaning, String gender)
    {
        welshWords.put("welsh_word",welshMeaning);
        welshWords.put("english_meaning",englishMeaning);
        welshWords.put("gender",gender);
        
        try {
            manager.addNewRowToTable(welshWords);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    public ArrayList<WelshWord> getWelshWords()
    {
        ArrayList<WelshWord> allWords = null;
        try {
            allWords = manager.getAllWelshWords(welshWords);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(WelshWord e: allWords)
            System.out.println(e.getWordID()+"     "+ e.getEnglishMeaning()+"       "+e.getWelshWord()+"       "+e.getGender() );
        return allWords;
    }

    public void updateSession(Sessions session) {
        sessions.put("session_id",session.getSessionID());
        sessions.put("user_name",session.getUserName());
        sessions.put("expiration_date",""+session.getExpirationDate());
        try {
            ArrayList<String> thisPrimaryKey = new ArrayList<>();
            thisPrimaryKey.add(""+session.getExpirationDate());
            manager.updateRowFromTable(sessions, thisPrimaryKey);
            
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Sessions getSessionbyID(String sessionID) {
        Sessions thisSession = null;
        
        try {
            thisSession = manager.getSessionByID(sessions, sessionID);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisSession;
                 
    }
    
    


    
}
