package models;

/* Libraries Declaration */
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class Sessions {
    
    /* Variables Declaration */
    private String sessionID;
    private String userName;
    private Date expirationDate;    

    /**
     * Constructor
     * @param sessionID references the session ID
     * @param userName references the username
     * @param expirationDate references the expiration Date
     */
    public Sessions(String sessionID, String userName, Date expirationDate) {
        this.sessionID = sessionID;
        this.userName = userName;
        this.expirationDate = expirationDate;
    }

    public Sessions(String userName) {
        this.sessionID = UUID.randomUUID().toString();

        Date rightNow = new Date();
        rightNow.setHours(rightNow.getHours()+1);
        Timestamp sss = new Timestamp(rightNow.getTime());
        expirationDate=sss;
        
        this.userName = userName;
    }
    
    /**
     * Returns the current session ID
     * @return a string representation of the session ID
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the session ID
     * @param sessionID is the given session ID for the current session
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Returns the username of the current logged-in user
     * @return a string representation of the username
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

    /**
     * Returns the date of the current Session
     * @return a Date format of the current Session
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets a new Expiration Date for the Session
     * @param newDate is the new Date given
     */
    public void setExpirationDate(Date newDate) {
        this.expirationDate = newDate;
    }   
}