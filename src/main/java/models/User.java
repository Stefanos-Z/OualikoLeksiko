package models;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class User {
    
    /* Variables Declaration */
    private String userName;
    private String userPassword;
    private String userEmail; 
    private String userType;
    
    /**
     * Constructor
     * @param userName references the username
     * @param userPassword references the user's password
     * @param userEmail references the user's email
     * @param userType references the user type
     */
    public User(String userName, String userPassword, String userEmail, String userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userType = userType;
    }

    /**
     * Returns the username
     * @return a string representation of the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username
     * @param userName 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**.
     * Returns the password
     * @return a string representation of the user's password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Returns the user Type
     * @return a string representation of the user Type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user's password 
     * @param userPassword is the new given password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Returns the user email
     * @return a string representation of the user's email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets a new Email address
     * @param userEmail is the new given email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}