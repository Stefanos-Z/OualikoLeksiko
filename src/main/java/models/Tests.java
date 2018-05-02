package models;

/* Libraries Declaration */
import java.sql.Date;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class Tests {
    
    /* Variables Declaration */
    private int testID;
    private String testTitle;
    private Date dateCreated;
    private String userName;

    /**
     * Constructor
     * @param testID is ID of the current Test
     * @param testTitle is the title of the current Test
     * @param dateCreated is the date created of the current Test
     * @param userName is the username that will take the Test
     */
    public Tests(int testID, String testTitle, Date dateCreated, String userName) {
        this.testID = testID;
        this.testTitle = testTitle;
        this.dateCreated = dateCreated;
        this.userName = userName;
    }

    /**
     * Returns the current Test ID
     * @return integer of the test ID
     */
    public int getTestID() {
        return testID;
    }

    /**
     * Sets the test ID
     * @param testID is the new given ID
     */
    public void setTestID(int testID) {
        this.testID = testID;
    }

    /**
     * Gets the Test Title
     * @return a string representation of the test Title
     */
    public String getTestTitle() {
        return testTitle;
    }

    /**
     * Sets the test title
     * @param testTitle is the new given title
     */
    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    /**
     * Gets the creation Date of the Test
     * @return a Date format of date created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the Date
     * @param dateCreated is the new given Date
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Returns the user Name
     * @return the current userName 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username
     * @param userName is the given username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
