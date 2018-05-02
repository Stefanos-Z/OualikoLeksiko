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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Random r = new Random();
    Map<String, String> users = new HashMap<>();//map for the student table
    Map<String, String> questionType = new HashMap<>();//map for the staff table
    Map<String, String> welshWords = new HashMap<>();//map for the module table
    Map<String, String> tests = new HashMap<>();//map for the registration table
    Map<String, String> testResults = new HashMap<>();//map for the teaches table
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
     * Instantiate the Maps with adding elements in the tables
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
        //testQuestions.put("PRIMARY KEY1","respondent_question_id");
        
    }

    /**
     * Gets the 
     * @param username
     * @return 
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
//                int welshWordID = r.nextInt(numOfWelshWords);
//                testQuestions.put("word_id",""+welshWordID);
                
                //WelshWord thisWord = manager.getWelshWord(welshWords, welshWordID);
                //String questionText = manager.getQuestionText(questionTypeID,questionType);
                
                WelshWord thisWord = allWelshWords.get(i);
                String questionText = allQT.get(questionTypeID).getQuestionText();
                String answer = "";
                switch (questionTypeID){
                    case 0:
                        questionText= questionText.replaceAll("<>", thisWord.getWelshWord());
                        testQuestions.put("correct_answer",thisWord.getGender());
                        answer = thisWord.getGender();
                    break;
                    case 1:
                        questionText = questionText.replaceAll("<>", thisWord.getWelshWord());
                        testQuestions.put("correct_answer",thisWord.getEnglishMeaning());
                        answer = thisWord.getEnglishMeaning();
                        break;
                    case 2:
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
    
    
    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = null;
        
        try {
            allUsers = manager.getAllUsers(users);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    public void updateWelshWord(String wordId, String wWord, String eWord, String string) {
        try {
            welshWords.put("welsh_word",wWord);
            welshWords.put("english_meaning",eWord);
            welshWords.put("gender",string);
            ArrayList<String> pk = new ArrayList<>();
            pk.add(wordId);
            manager.updateRowFromTable(welshWords, pk);
        } catch (SQLException ex) {
            System.out.println("!!Error in update word");
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteWelshWord(String wordId) {
        
        try {
            ArrayList<String> pk = new ArrayList<>();
            pk.add(wordId);
            manager.deleteRowFromTable(welshWords, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public void deleteUser(String username) {
        try {
            ArrayList<String> pk = new ArrayList<>();
            pk.add(username);
            manager.deleteRowFromTable(users, pk);
        } catch (SQLException ex) {
            Logger.getLogger(DatbaseInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
