package models;

/* Libraries Declaration */
import java.sql.Timestamp;
import java.util.Date;


/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class TestsResults {
    
    /* Variables Declaration */
    private int testID;
    private String userName;
    private int grade;
    private Date dateSubmitted;

    /**
     * Constructor
     * @param userName references the username
     * @param grade references the grade
     */
    public TestsResults(String userName, int grade) {
        this.userName = userName;
        this.grade = grade;
        dateSubmitted = new Timestamp(new Date().getTime());
    }

    /**
     * Constructor
     * @param testID references the test ID
     * @param userName references the username
     * @param grade references the grade
     * @param dateSubmitted references the date submitted
     */
    public TestsResults(int testID, String userName, int grade, Date dateSubmitted) {
        this.testID = testID;
        this.userName = userName;
        this.grade = grade;
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * Returns the Test ID
     * @return a string representation of the test ID
     */
    public int getTestID() {
        return testID;
    }

    /**
     * Returns the User name
     * @return a string representation of the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the Grade
     * @return the double number of the grade
     */
    public double getGrade() {
        return grade;
    }
    
    /**
     * Sets the user name
     * @param userName is the given username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the Grade
     * @param grade is the new given grade
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Gets the date submitted 
     * @return a Date format of the test submission date
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * Sets the date Submitted
     * @param dateSubmitted is the new Date submitted
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
}