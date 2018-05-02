/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import models.QuestionType;
import models.Sessions;
import models.TestsResults;
import models.User;
import models.WelshWord;


public class DatabaseManager {
    private static String url;
    private static String username;
    private static String password;
    

    
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
     * Decides the formated message and titles to add in the array list returned
     * @param thisMap the map corresponding to the table
     * @param userChoice corresponding to column titles based on the users choice
     * @return an array list of rows
     * @throws SQLException if the connection or query is faulty
     */
    public List<String[]> list(Map<String, String> thisMap, int userChoice) throws SQLException
    {
        List<String[]> listOfContent = null;
        Connection conn = DriverManager.getConnection(url, username, password);//establish a connection
        String query = "SELECT * FROM "+ thisMap.get("Table");//formated query
        PreparedStatement pstat = conn.prepareStatement(query);//prepared statement for the query

        ResultSet rs = pstat.executeQuery();
        //gets a result set
        
        
        //for each case the firs row is the desired format
        //the second is the coloumn titles
        //the third is the column underline
        switch(userChoice)
        {
            case 1:
                listOfContent = getRows(rs);
                listOfContent.add(0,new String[]{"%-22s","%-22s","%-22s"});
                listOfContent.add(1,new String[]{"Student ID","Student Name","Scheme"});
                listOfContent.add(2,new String[]{"**********","************","******"});
                break;
            case 2:
                listOfContent = getRows(rs);
                listOfContent.add(0,new String[]{"%-22s","%-22s","%-22s"});
                listOfContent.add(1,new String[]{"Staff ID","Staff Name","Staff Grade"});
                listOfContent.add(2,new String[]{"*************","******************","********************"});
                break;
            case 3:
                listOfContent = getRows(rs);
                listOfContent.add(0,new String[]{"%-22s","%-33s","%-22s"});
                listOfContent.add(1,new String[]{"Module ID","Module Name","Credits"});
                listOfContent.add(2,new String[]{"*************","******************","*******"});
                break;
            case 4:
                listOfContent = getRows(rs);
                listOfContent.add(0,new String[]{"%-20s","%-20s"});
                listOfContent.add(1,new String[]{"Student ID","Module ID"});
                listOfContent.add(2,new String[]{"**********","*********"});
                break;
            case 5:
                listOfContent = getRows(rs);
                listOfContent.add(0,new String[]{"%-20s","%-20s"});
                listOfContent.add(1,new String[]{"Staff ID","Module ID"});
                listOfContent.add(2,new String[]{"**********","*********"});
                break;
        }
        rs.close();
        pstat.close();
        conn.close();

        return listOfContent;
    }



    /**
     * Deletes a student from the student table
     * @param id the id of the student
     */
    public void deleteStudentbyId(String id) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "DELETE FROM student WHERE student_id = ?";
        
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, id);
        pstat.executeUpdate();
        pstat.close();
        conn.close();
    }

    /**
     * Updates a row in the student table
     * @param oldId the old Id which we needs to be found in order to update
     * @param newId the Students new ID
     * @param name the students new name
     * @param scheme the students new scheme
     */
    public void updateStudentbyId(String oldId, String newId, String name, String scheme) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "UPDATE student SET student_id = ?, student_name = ?, student_scheme = ? WHERE student_id = ?";
        PreparedStatement pstat = conn.prepareStatement(query);
        
        pstat.setString(1, newId);
        pstat.setString(2, name);
        pstat.setString(3, scheme);
        pstat.setString(4, oldId);
        pstat.executeUpdate();
        
        pstat.close();
        conn.close();
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
        System.out.println(query);
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
        System.out.println(query);
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
        
        //Example: Student_id = 'foo'
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
     * finds the teachers who teach more than 1 module
     * @return a formated list of rows
     * @throws SQLException 
     */
    public List<String[]> findTeachesWhoTeachMoreThanOne() throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT s.staff_Id, s.staff_name " +
            "FROM staff s INNER JOIN teaches t ON s.staff_Id = t.staff_Id "+
            "GROUP BY t.staff_Id "+
            "HAVING COUNT(t.staff_Id) > 1;";
        PreparedStatement pstat = conn.prepareStatement(query);
        ResultSet rs = pstat.executeQuery();
        List<String[]> queryResults = getRows(rs);
        
        queryResults.add(0, new String[]{"%-20s","%-20s"});//add a formater
        queryResults.add(1, new String[]{"Staff ID","Staff Name"});//coloumn titles of the query
        queryResults.add(2, new String[]{"********","**********"});//Coloumn title underline
        
        pstat.close();
        rs.close();
        conn.close();
        return queryResults;
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
     * Gets the results from  
     * @param rs the result set 
     * @return a formated array list of string arrays
     */
    private List<String[]> getRows(ResultSet rs) throws SQLException
    {
        List<String[]> resultList = new ArrayList<String[]>();//creates a new list
        
        ResultSetMetaData metaData = rs.getMetaData();
        
        
        Integer columnCount = metaData.getColumnCount();

        
        while (rs.next()) 
        {
            String[] row = new String[columnCount];//creates new String array
            for (int i = 1; i <= columnCount; i++) {
                row[i-1] = rs.getString(i);
            }
            resultList.add(row);
        }
        
        return resultList;
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

    public int getNumberOfRecords(Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT COUNT("+ findPrimaryKey(thisMap)+") FROM "+thisMap.get("Table")+";";
        PreparedStatement pstat = conn.prepareStatement(query);
        ResultSet rs = pstat.executeQuery();
        rs.next();
        
        int numberOfRecords = rs.getInt(1);
        
        pstat.close();
        conn.close();
        return numberOfRecords;
    }
    
    

    String getQuestionText(int questionTypeID, Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM "+thisMap.get("Table")+" WHERE "+findPrimaryKey(thisMap)+" = "+questionTypeID+";";
        System.out.println("this is the query  "+query);
        PreparedStatement pstat = conn.prepareStatement(query);
        System.out.println("hello 0");
        ResultSet rs = pstat.executeQuery();
        rs.next();
        System.out.println("hello 1 ");
        String questionText = rs.getString(2);
        
        System.out.println("hello 2");
        pstat.close();
        conn.close();
        return questionText;
    }

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
