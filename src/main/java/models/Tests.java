package models;

import java.sql.Date;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class Tests {
    
    private int testID;
    private String testTitle;
    private Date dateCreated;
    private int userID;

    public Tests(int testID, String testTitle, Date dateCreated, int userID) {
        this.testID = testID;
        this.testTitle = testTitle;
        this.dateCreated = dateCreated;
        this.userID = userID;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
