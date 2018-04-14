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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


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
    
    public void createTable() throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);//establish a connection
        Statement stmt = conn.createStatement();
        String sql = "CREATE TABLE REGISTRATION " +
                   "(id INTEGER not NULL, " +
                   " first VARCHAR(255), " + 
                   " last VARCHAR(255), " + 
                   " age INTEGER, " + 
                   " PRIMARY KEY ( id ))"; 
        
        
        stmt.executeUpdate(sql);
        stmt.close();
        conn.close();
        
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
     * Adds student to student table
     * @param id the new student's ID
     * @param name the new student's name
     * @param scheme the new student's scheme
     * @throws SQLException if the connection is faulty or the PK is already in the table or
     * the inputed values are null or invalid type 
     */
    public void addStudent(String id, String name, String scheme) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "INSERT INTO student VALUES (?, ?, ?)";
        
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, id);
        pstat.setString(2, name);
        pstat.setString(3, scheme);
        
        pstat.executeUpdate();
        
        pstat.close();
        conn.close();
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
    public int deleteRowFromTable(Map<String, String> thisMap) throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "DELETE FROM "+thisMap.get("Table")+" WHERE ";
        
        for (Map.Entry<String, String> e : thisMap.entrySet())//for every entry in the map
        {
            if (checkIfPrimaryKey(e.getKey()))//checks if the key is primary key
            {
                
                query=query + e.getValue()+"=" +"'"+ thisMap.get(e.getValue()) +"'" + " AND ";
            }
        }
        query = query.substring(0,query.length()-5);//deletes the last AND
        PreparedStatement pstat = conn.prepareStatement(query);      

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
    public int UpdateRowFromTable(Map<String, String> thisMap, ArrayList<String> thesePrimaryKeyValues) throws SQLException {
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
     * Set query- finds the modules taught by
     * @param staffID the staff needed to search
     * @return a list with the results of the query
     * @throws SQLException 
     */
    public List<String[]> findModulesTaughtBy(String staffID) throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);
        //specific Query
        String query = "SELECT m.module_Id, m.module_name " +
            "FROM module m INNER JOIN teaches t ON m.module_Id = t.module_Id "+
            "WHERE t.staff_Id = ?";
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, staffID);
        
        ResultSet rs = pstat.executeQuery();
        
        //gets the list from the result set
        List<String[]> queryResults = getRows(rs);
        
        queryResults.add(0, new String[]{"%-20s","%-20s"});//add a formater
        queryResults.add(1, new String[]{"Module ID","Module Name"});//coloumn titles of the query
        queryResults.add(2, new String[]{"*********","***********"});//Coloumn title underline
        
        rs.close();
        pstat.close();
        conn.close();
        return queryResults;
    }
    
    /**
     * Finds student who are registered in the 
     * @param moduleID specified 
     * @return a formated list with the results of the query
     * @throws SQLException 
     */
    public List<String[]> findStudentsRegisteredIn(String moduleID) throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);
        //specific Query
        String query = "SELECT s.student_Id, s.student_name " +
            "FROM student s INNER JOIN registered r ON s.student_Id = r.student_Id "+
            "WHERE r.module_Id = ?";
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, moduleID);
        ResultSet rs = pstat.executeQuery();
        List<String[]> queryResults = getRows(rs);
        queryResults.add(0, new String[]{"%-20s","%-20s"});//add a formater
        queryResults.add(1, new String[]{"Student ID","Student Name"});//coloumn titles of the query
        queryResults.add(2, new String[]{"**********","************"});//Coloumn title underline
        conn.close();
        return queryResults;
    }
    
    /**
     * Finds the staff and the module they teach to a specified student 
     * @param studentID the student's ID
     * @return a formated list with the results of the query
     * @throws SQLException 
     */
    public List<String[]> findStaffWhoTeach(String studentID) throws SQLException
    {
        Connection conn = DriverManager.getConnection(url, username, password);
        String query = "SELECT s.staff_Id, s.staff_name, t.module_Id " +
            "FROM staff s INNER JOIN teaches t ON s.staff_Id = t.staff_Id "+
            "INNER JOIN registered r ON r.module_Id = t.module_Id "+
            "WHERE r.student_Id = ?";
        PreparedStatement pstat = conn.prepareStatement(query);
        pstat.setString(1, studentID);
        ResultSet rs = pstat.executeQuery();
        
        List<String[]> queryResults = getRows(rs);
        queryResults.add(0, new String[]{"%-20s","%-20s","%-20s"});//add a formater
        queryResults.add(1, new String[]{"Staff ID","Staff Name","Module ID"});//coloumn titles of the query
        queryResults.add(2, new String[]{"********","**********","*********"});//Coloumn title underline
        
        rs.close();
        pstat.close();
        conn.close();
        return queryResults;
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
}