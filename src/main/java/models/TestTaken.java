/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class TestTaken {
    
    private int testID;
    private int userID;
    private double grade;
    private Date dateSubmitted;

    public TestTaken(int testID, int userID, double grade, Date dateSubmitted) {
        this.testID = testID;
        this.userID = userID;
        this.grade = grade;
        this.dateSubmitted = dateSubmitted;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}
