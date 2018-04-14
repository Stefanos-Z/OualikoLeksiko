package models;

import java.sql.Date;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class Sessions {
    
    private int sessionID;
    private int userID;
    private Date expirationDate;    

    public Sessions(int sessionID, int userID, Date expirationDate) {
        this.sessionID = sessionID;
        this.userID = userID;
        this.expirationDate = expirationDate;
    }
    
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    
}
