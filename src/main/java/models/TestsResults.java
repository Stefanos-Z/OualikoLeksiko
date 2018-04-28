/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.util.Date;


/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class TestsResults {
    
    private int testID;
    private String userName;
    private int grade;
    private Date dateSubmitted;

    public TestsResults(String userName, int grade) {
        this.userName = userName;
        this.grade = grade;
        dateSubmitted = new Timestamp(new Date().getTime());
    }

    public TestsResults(int testID, String userName, int grade, Date dateSubmitted) {
        this.testID = testID;
        this.userName = userName;
        this.grade = grade;
        this.dateSubmitted = dateSubmitted;
    }

    public int getTestID() {
        return testID;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
