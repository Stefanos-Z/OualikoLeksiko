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
    private String userName;
    private Date expirationDate;    

    public Sessions(String userName) {
        this.sessionID = UUID.randomUUID().toString();

        Date rightNow = new Date();
        rightNow.setHours(rightNow.getHours()+1);
        Timestamp sss = new Timestamp(rightNow.getTime());
        expirationDate=sss;
        
        this.userName = userName;

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

    public void setExpirationDate() {
        expirationDate.setHours(expirationDate.getHours()+1);
    }

    
}
