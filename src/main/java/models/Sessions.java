package models;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class Sessions {
    
    private String sessionID;
    private int userID;
    private Date expirationDate;    

    public Sessions(String sessionID, int userID, Date expirationDate) {
        this.sessionID = UUID.randomUUID().toString();
        this.userID = userID;
        this.expirationDate = expirationDate;
    }
    
    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
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
