package models;

import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class Sessions {
    
    private String sessionID;
    private String userName;
    private Date expirationDate;    

    public Sessions(String sessionID, String userName, Date expirationDate) {
        this.sessionID = UUID.randomUUID().toString();
        this.userName = userName;
        this.expirationDate = expirationDate;
    }
    
    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    
}
