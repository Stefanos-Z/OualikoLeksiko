package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/* Reference Models */
import models.Question;
import models.QuestionType;
import models.Sessions;
import models.TestsResults;
import models.User;
import models.WelshWord;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class DatbaseInterface {
    
    /* Variables Declaration */
    private DatabaseManager manager = null;
    private Random r = new Random();
    
    
    Map<String, String> users = new HashMap<>();//map for the users table
    Map<String, String> questionType = new HashMap<>();//map for the question type table
    Map<String, String> welshWords = new HashMap<>();//map for the welsh words table
    //Map<String, String> tests = new HashMap<>();//map for the tests table
    Map<String, String> testResults = new HashMap<>();//map for the testResults table
    Map<String, String> sessions = new HashMap<>();//map for the teaches table
    Map<String, String> testQuestions = new HashMap<>();//map for the teaches table
    
    /**
     * Constructor instantiate Maps
     */
    public DatbaseInterface()
    {
        intatiateMaps();
    }
    
    /**
     * Checks for a valid connection
     * @return true/false if connection is valid or not
     */
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
    
    /**
     * Creates the Session of a user
     * @param session manages Sessions
     */
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
    
    /**
     * initializes the maps that correspond to the database
     */
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

        
        testResults.put("Table","TestsResults");
        testResults.put("user_name","");
        testResults.put("grade","");
        testResults.put("date_submitted","");
        testResults.put("PRIMARY KEY1","test_id");
        
        
        
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
        
    }


    /**
     * Gets the user by a given username
     * @param username the username of the user
     * @return the user
     */
    public User getUseByUserName(String username) {
        try {
            User thisUser = manager.getUser(username, users);
            return thisUser;
        } catch (SQLException ex) {
            System.out.println("!!!!!!!!!No User Found!!!!!!");
        }
        return null;
    }
    
    
    /**
     * Returns a list with questions and their answers
     * @param numOfQuestions the amount of questions 
     * @return the list
     */
    public ArrayList<Question> createAndGetQuestions(int numOfQuestions)
    {
        ArrayList<Question> allQuestions = new ArrayList<>();
        try {
            ArrayList<QuestionType> allQT = manager.getAllQuestionTypes(questionType);
            ArrayList<WelshWord> allWelshWords = manager.getAllWelshWords(welshWords);
//            int numOfWelshWords = allWelshWords.size();
            int numOfQuestionType = allQT.size();
            java.util.Collections.shuffle(allWelshWords);
            for(int i =0; i<numOfQuestions;i++)
            {
                
                int questionTypeID = r.nextInt(numOfQuestionType);
                testQuestions.put("question_id",""+questionTypeID);
                
                //WelshWord thisWord = manager.getWelshWord(welshWords, welshWordID);
                //String questionText = manager.getQuestionText(questionTypeID,questionType);
                
                WelshWord thisWord = allWelshWords.get(i);
                String questionText = allQT.get(questionTypeID).getQuestionText();
                String answer = "";
                switch (questionTypeID){
                    case 0:
                        questionText= questionText.replaceAll("<>", thisWord.getWelshWord());
                        answer = thisWord.getGender();
                    break;
                    case 1:
                        questionText = questionText.replaceAll("<>", thisWord.getWelshWord());
                        answer = thisWord.getEnglishMeaning();
                        break;
                    case 2:
                        questionText= questionText.replaceAll("<>", thisWord.getEnglishMeaning());
                        answer = thisWord.getWelshWord();
                        break;
                }
                allQuestions.add(new Question(questionText,answer,questionTypeID));
                //manager.addNewRowToTable(testQuestions);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allQuestions;
        
        
        
        
    }
    
    /**
     * Adds a welsh word to the database
     * @param welshMeaning the welsh meaning of the word
     * @param englishMeaning the English meaning of the word
     * @param gender the gender of the word
     */
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
    
    /**
     * gets a list with all welsh words from the database
     * @return the list with the words
     */
    public ArrayList<WelshWord> getWelshWords()
    {
        ArrayList<WelshWord> allWords = null;
        try {
            allWords = manager.getAllWelshWords(welshWords);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allWords;
    }

    
    /**
     * updates the session coresponding to the user logged on
     * @param session of the user
     */
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

    /**
     * gets the session
     * @param sessionID the session id
     * @return  the session
     */
    public Sessions getSessionbyID(String sessionID) {
        Sessions thisSession = null;
        
        try {
            thisSession = manager.getSessionByID(sessions, sessionID);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return thisSession;
                 
    }

    /**
     * Adds a new test to the database
     * @param newTestResult the new test result
     */
    public void addTestResult(TestsResults newTestResult) {
        testResults.put("user_name",newTestResult.getUserName());
        testResults.put("grade",""+newTestResult.getGrade());
        testResults.put("date_submitted",""+newTestResult.getDateSubmitted());
        //System.out.println("****************************"+newTestResult.getDateSubmitted());
        try {
            manager.addNewRowToTable(testResults);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a new test to the database
     * @param newTestResult the new test result
     */
    void addToNewTable(TestsResults newTestResult) {
        testResults.put("user_name",newTestResult.getUserName());
        testResults.put("grade",""+newTestResult.getGrade());
        testResults.put("date_submitted",""+newTestResult.getDateSubmitted());
        try {
            manager.addNewRowToTable(testResults);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets a list with the test results
     * @param thisUser is the user requesting the results
     * @return the list with the results
     */
    public ArrayList<TestsResults> getTestHistory(User thisUser) {
        ArrayList<TestsResults> results = null;
        try {
            if(thisUser.getUserType().equals("Student"))
            {
                results = manager.getTestResultsForStudent(testResults, thisUser.getUserName());
            }else
            {
                results = manager.getTestAllResults(testResults);
            }
        } catch (SQLException ex) {
            System.out.println("sql exception");
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
    }
    
    
    /**
     * gets all users in the database
     * @return the list of users
     */
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = null;
        
        try {
            allUsers = manager.getAllUsers(users);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    /**
     * updates a welsh word in the database
     * @param wordId the words unique id
     * @param wWord the welsh meaning of the word
     * @param eWord the English meaning of the word
     * @param gender the gender of the word 
     */
    public void updateWelshWord(String wordId, String wWord, String eWord, String gender) {
        try {
            welshWords.put("welsh_word",wWord);
            welshWords.put("english_meaning",eWord);
            welshWords.put("gender",gender);
            ArrayList<String> pk = new ArrayList<>();
            pk.add(wordId);
            manager.updateRowFromTable(welshWords, pk);
        } catch (SQLException ex) {
            System.out.println("!!Error in update word");
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes the welsh word
     * @param wordId the id of the welsh word
     */
    public void deleteWelshWord(String wordId) {
        
        try {
            ArrayList<String> pk = new ArrayList<>();
            pk.add(wordId);
            manager.deleteRowFromTable(welshWords, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a new user to the database
     * @param uName the username of the user
     * @param pWord the password of the user
     * @param email the email of the user
     * @param type the user type
     */
    void addUser(String uName, String pWord, String email, String type) {
        try {
            users.put("user_name",uName);
            users.put("user_password",pWord);
            users.put("user_email",email);
            users.put("user_type",type);
            
            manager.addNewRowToTable(users);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Updates the user in the database
     * @param newUName the new username of the user
     * @param pWord the password of the user
     * @param email the email of the user
     * @param uType the user type
     * @param oldUName 
     */
    public void editUser(String newUName, String pWord, String email,String uType, String oldUName) {
        try {
            users.put("user_name",newUName);
            users.put("user_password",pWord);
            users.put("user_email",email);
            users.put("user_type",uType);
            ArrayList<String> pk = new ArrayList<>();
            pk.add(oldUName);
            manager.updateRowFromTable(users, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes the user
     * @param username the username of the user
     */
    public void deleteUser(String username) {
        try {
            ArrayList<String> pk = new ArrayList<>();
            pk.add(username);
            manager.deleteRowFromTable(users, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * deletes a history record in the database
     * @param testID the id of the test to be deleted
     */
    public void deleteHistoryByTestID(String testID) {
        try {
            ArrayList<String> pk = new ArrayList<>();
            pk.add(testID);
            manager.deleteRowFromTable(testResults, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
