/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oneZt
 */
public class DatbaseInterface {
    private DatabaseManager manager = null;
    Map<String, String> users = new HashMap<>();//map for the student table
    Map<String, String> questionType = new HashMap<>();//map for the staff table
    Map<String, String> welshWords = new HashMap<>();//map for the module table
    Map<String, String> tests = new HashMap<>();//map for the registration table
    Map<String, String> testsTaken = new HashMap<>();//map for the teaches table
    Map<String, String> sessions = new HashMap<>();//map for the teaches table
    
    public DatbaseInterface()
    {
        intatiateMaps();
    }
    
    public boolean getConection(){
        try {
            manager = new DatabaseManager();
            manager.createTable();
        } catch (IOException ex) {
            System.out.println("error IO");
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    //public createSession()
    

    private void intatiateMaps() {
        users.put("Table","Users");
        users.put("user_id","");
        users.put("user_name","");
        users.put("user_password","");
        users.put("user_email","");
        users.put("user_type","");
        users.put("PRIMARY KEY1","user_id");
        
        
        questionType.put("Table","QuestionType");
        questionType.put("question_id","");
        questionType.put("question_text","");
        questionType.put("PRIMARY KEY1","question_id");
        
        welshWords.put("Table","WelshWords");
        welshWords.put("word_id","");
        welshWords.put("welsh_word","");
        welshWords.put("english_meaning","");
        welshWords.put("gender","");
        welshWords.put("PRIMARY KEY1","word_id");
        
        tests.put("Table", "Test");
        tests.put("test_id", "");
        tests.put("test_title", "");
        tests.put("date_created", "");
        tests.put("user_id", "");
        tests.put("PRIMARY KEY1", "test_id");
        
        
        testsTaken.put("Table","TestsTaken");
        testsTaken.put("test_id","");
        testsTaken.put("user_id","");
        testsTaken.put("grade","");
        testsTaken.put("date_submitted","");
        testsTaken.put("PRIMARY KEY1","test_id");
        testsTaken.put("PRIMARY KEY2","user_id");
        
        
        sessions.put("Table", "Sessions");
        sessions.put("session_id", "");
        sessions.put("user_id", "");
        sessions.put("expiration_date", "");
        sessions.put("PRIMARY KEY1", "session_id");
        
        
        
    }
    
    
}
