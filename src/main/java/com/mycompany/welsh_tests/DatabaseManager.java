package com.mycompany.welsh_tests;

/* Libaries Declaration */
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
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
public class DatabaseManager {
    
    /* Variables Declaration */
    private static String url;
    private static String username;
    private static String password;
    
    /**
     * Gives connection to the Web-App
     * @throws IOException if connection is not able
     * @throws ClassNotFoundException properties not found
     */
    public DatabaseManager() throws IOException, ClassNotFoundException
    {
        InputStream stream = DatabaseManager.class.getResourceAsStream("/database.properties");
        Properties props = new Properties();
        props.load(stream);
        
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        if (username == null) { username = ""; }
        password = props.getProperty("jdbc.password");
        if (password == null) { password = ""; }
        if (driver != null) { Class.forName(driver); }
    }
    



    
    /**
     * adds a new row to the table 
     * @param thisMap which corresponds to the table
     * with the keys of the map the tables columns and the map values 
     * correspond to the attribute values of the table
     * @throws SQLException if the values are incompatible (duplicate PK) invalid data type
     */
    public void addNewRowToTable(Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);

        //example INSERT INTO "student" (student_id,student_name, student_scheme)  VALUES
        String query = "INSERT INTO "+thisMap.get("Table")+"(";
        
        //example ('1234','stefan','Maths')
        String values = "(";
        for (Map.Entry<String, String> e : thisMap.entrySet())//for every entity
        {
            if (!(e.getKey().equals("Table") || checkIfPrimaryKey(e.getKey())))//check for correct keys
            {
                query=query+ e.getKey()+",";
                values = values + "'"+thisMap.get(e.getKey())+"',";
            }
        }
        
        query = query.substring(0,query.length()-1)+")";
        values = values.substring(0,values.length()-1)+")";
        query = query+" VALUES "+values;
        //System.out.println(query);
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.executeUpdate();
        pstat.close();
        conn.close();
    }
    
    /**
     * Deletes a row from the table 
     * @param thisMap corresponding with the table and the primary key of the 
     * row needed to be deleted
     * @throws SQLException 
     */
    public int deleteRowFromTable(Map<String, String> thisMap, ArrayList<String> primaryKeys) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "DELETE FROM "+thisMap.get("Table")+" WHERE ";
        int i =0;
        for (Map.Entry<String, String> e : thisMap.entrySet())//for every entry in the map
        {
            if (checkIfPrimaryKey(e.getKey()))//checks if the key is primary key
            {
                
                query=query + e.getValue()+"=" +"'"+ primaryKeys.get(i) +"'" + " AND ";
                i++;
            }
        }
        query = query.substring(0,query.length()-5);//deletes the last AND
        PreparedStatement pstat = conn.prepareStatement(query);      
        //System.out.println(query);
        int deletedrows = pstat.executeUpdate();
        
        pstat.close();
        conn.close();
        return deletedrows;
    }
    
    /**
     * Updates a row in a table
     * @param thisMap corresponding to the table name and new table column values
     * @param thesePrimaryKeyValues the values for the entry that needs to be updated
     * @throws SQLException 
     */
    public int updateRowFromTable(Map<String, String> thisMap, ArrayList<String> thesePrimaryKeyValues) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        
        //EXAMPLE: UPDATE student SET student_ID = '1234',student_name='stefanos', student_scheme = 'Maths'
        String query = "UPDATE " + thisMap.get("Table")+ " SET ";
        
        for (Map.Entry<String, String> e : thisMap.entrySet())
        {
            if (!(checkIfPrimaryKey(e.getKey()) || e.getKey().equals("Table")))
            {
                query=query + e.getKey()+"=" +"'"+ e.getValue() +"',";
            }
        }
        
        query = query.substring(0, query.length()-1) + " WHERE ";
        int i = 0;//index in the arraylist for the PK Values
        for (Map.Entry<String, String> e : thisMap.entrySet())
        {
            if (checkIfPrimaryKey(e.getKey()))
            {
                query=query + e.getValue()+"=" +"'"+ thesePrimaryKeyValues.get(i) +"'" + " AND ";
                i++;
            }
        }
        query = query.substring(0, query.length()-5);

        PreparedStatement pstat = conn.prepareStatement(query);
        int rows = pstat.executeUpdate();
        pstat.close();
        conn.close();
        return rows;
    }
    
    
    
    

    /**
     * checks if a string contains the sequence "PRIMARY KEY"
     * @param check it needs to check
     * @return true if it contains it
     */
    private boolean checkIfPrimaryKey(String check)
    {
        check = check.substring(0, check.length()-1);
        return check.equalsIgnoreCase("PRIMARY KEY");
    }
    
    
    
    
    /**
     * Searchers the database for a specific user 
     * @param usernameID the unique username
     * @param thisMap the map that represents the table
     * @return a User object with all parameters
     * @throws SQLException if user with provided username doesn't exist
     */
    User getUser(String usernameID, Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * " +
            "FROM "+thisMap.get("Table")+
            " WHERE "+findPrimaryKey(thisMap) + "='"+usernameID+"';";
        PreparedStatement pstat = conn.prepareStatement(query);

        ResultSet rs = pstat.executeQuery();


        rs.next();
        
        String user = rs.getString(1);
        String pass = rs.getString(2);
        String email = rs.getString(3);
        String type = rs.getString(4);
        
        pstat.close();
        conn.close();
        return new User(user, pass, email, type);
    }
    
    /**
     * Finds the primary key of an entry
     * @param thisMap is the Map needed to get the entries
     * @return the string representation of the key
     */
    private String findPrimaryKey(Map<String, String> thisMap)
    {
        for (Map.Entry<String, String> e : thisMap.entrySet())
        {
            if (checkIfPrimaryKey(e.getKey()))
            {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * Gets the Word in Welsh
     * @param thisMap is the Map to find the entries
     * @param wordID is the current word ID need to be found
     * @return a Welsh Word Object
     * @throws SQLException if there is an error in the query
     */
    WelshWord getWelshWord(Map<String, String> thisMap, int wordID) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM "+thisMap.get("Table")+" WHERE "+findPrimaryKey(thisMap)+" = "+wordID+";";
        PreparedStatement pstat = conn.prepareStatement(query);
        ResultSet rs = pstat.executeQuery();
        rs.next();
        WelshWord thisWord = new WelshWord(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
        
        
        pstat.close();
        conn.close();
        
        return thisWord;
    }

    /**
     * Get the list of all Welsh Words
     * @param thisMap is the Map needed to find entries
     * @return an Array List of All Welsh Words as Objects
     * @throws SQLException if there is an error in the query
     */
    public ArrayList<WelshWord> getAllWelshWords(Map<String, String> thisMap) throws SQLException {
        ArrayList<WelshWord> allWords = new ArrayList<>();
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM "+thisMap.get("Table")+";";
        PreparedStatement pstat = conn.prepareStatement(query);
        ResultSet rs = pstat.executeQuery();
        while(rs.next()){
            
            WelshWord thisWord = new WelshWord(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            allWords.add(thisWord);
        }
        
        pstat.close();
        conn.close();
        
        
        return allWords;
        
    }
    
    /**
     * Get the list of all Question Types
     * @param thisMap is the Map needed to find entries
     * @return an Array List of All Question Types as Objects
     * @throws SQLException if there is an error in the query
     */
    public ArrayList<QuestionType> getAllQuestionTypes(Map<String, String> thisMap) throws SQLException {
        ArrayList<QuestionType> allQuestionTypes = new ArrayList<>();
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM "+thisMap.get("Table")+";";
        PreparedStatement pstat = conn.prepareStatement(query);
        ResultSet rs = pstat.executeQuery();
        while(rs.next()){
            
            QuestionType thisQuestionType = new QuestionType(rs.getInt(1),rs.getString(2));
            allQuestionTypes.add(thisQuestionType);
        }
        
        pstat.close();
        conn.close();
        
        
        return allQuestionTypes;
        
    }

    /**
     * Gets the sessions by checking the id
     * @param thisMap is the Map needed to find entries
     * @return a Session Object
     * @throws SQLException if there is an error in the query
     */
    public Sessions getSessionByID(Map<String, String> thisMap, String sessionID) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * " +
            "FROM "+thisMap.get("Table")+
            " WHERE "+findPrimaryKey(thisMap) + "='"+sessionID+"';";
        PreparedStatement pstat = conn.prepareStatement(query);

        ResultSet rs = pstat.executeQuery();


        rs.next();
        
        String thisSessionID = rs.getString(1);
        String username = rs.getString(2);
        //Date thisDate = rs.getDate(3);
        Timestamp thisDate = rs.getTimestamp(3);

        
        pstat.close();
        conn.close();
        return new Sessions(thisSessionID, username, thisDate);
       
    }

    /**
     * Gets an ArrayList Test Results
     * @param thisMap is the Map needed to find entries
     * @return an Array List of All TestsResults as Objects
     * @throws SQLException if there is an error in the query
     */
    public ArrayList<TestsResults> getTestAllResults(Map<String, String> thisMap) throws SQLException {
        
        Connection conn = DriverManager.getConnection(url, username, password);
        ArrayList<TestsResults> results = new ArrayList<>();
        String query = "SELECT * " +
            "FROM "+thisMap.get("Table")+";";
        PreparedStatement pstat = conn.prepareStatement(query);

        ResultSet rs = pstat.executeQuery();


        while(rs.next()){
            TestsResults thisResult = new TestsResults(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getTimestamp(4));
            results.add(thisResult);
        }


        
        pstat.close();
        conn.close();
        return results;
    }

    /**
     * 
     /**
     * Get the list of all Test Results
     * @param thisMap is the Map needed to find entries
     * @param name is the current Student taking a test
     * @return an Array List of All Test Results as Objects
     * @throws SQLException if there is an error in the query
     */
    public ArrayList<TestsResults> getTestResultsForStudent(Map<String, String> thisMap, String name) throws SQLException {
        
        Connection conn = DriverManager.getConnection(url, username, password);
        ArrayList<TestsResults> results = new ArrayList<>();
        String query = "SELECT * " +
            "FROM "+thisMap.get("Table")+
            " WHERE user_name" + "='"+name+"';";
        PreparedStatement pstat = conn.prepareStatement(query);

        ResultSet rs = pstat.executeQuery();


        while(rs.next()){
            TestsResults thisResult = new TestsResults(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getTimestamp(4));
            results.add(thisResult);
        }

        pstat.close();
        conn.close();
        return results;
    }

    /**
     * Gets an array List of All Users
     * @param thisMap is the Map needed to find entries
     * @return an Array List of All Users as Objects
     * @throws SQLException if there is an error in the query
     */
    public ArrayList<User> getAllUsers(Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        ArrayList<User> allUsers = new ArrayList<>();
        String query = "SELECT * " +
            "FROM "+thisMap.get("Table")+";";
        PreparedStatement pstat = conn.prepareStatement(query);

        ResultSet rs = pstat.executeQuery();

        while(rs.next()){
            User thisUser = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            allUsers.add(thisUser);
        }
        
        pstat.close();
        conn.close();
        return allUsers;
    }
}