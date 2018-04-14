package models;


import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class Sessions {
    
    private String sessionID;
    private int userID;
    private Date expirationDate;    

    public Sessions(int userID) {
        this.sessionID = UUID.randomUUID().toString();
        this.userID = userID;
        Date rightNow = new Date();
        rightNow.setHours(rightNow.getHours()+1);
        Timestamp sss = new Timestamp(rightNow.getTime());
        expirationDate=sss;
        
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

    public void setExpirationDate() {
        expirationDate.setHours(expirationDate.getHours()+1);
    }

    
}
