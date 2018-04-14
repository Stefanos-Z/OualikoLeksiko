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
    private String userName;

    public Tests(int testID, String testTitle, Date dateCreated, String userName) {
        this.testID = testID;
        this.testTitle = testTitle;
        this.dateCreated = dateCreated;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
