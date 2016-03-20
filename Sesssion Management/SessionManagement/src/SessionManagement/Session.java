package SessionManagement;

import java.util.Date;
import java.util.UUID;

public class Session {
    String sessionID;
    int version;
    String message;
    Date expirationTime;
    

    public Session(String sessionID, int version, String message, Date expirationTime) {
        super();
        this.sessionID = sessionID;
        this.version = version;
        this.message = message;
        this.expirationTime = expirationTime;
    }
    
    
    public static Session createNewSession(String ip) {
        String sessionID    = UUID.randomUUID().toString() + ip;
        int version         = 0;
        String message      = "";
        Date expirationTime = Utils.getNextExpirationTime();
        
        return new Session(sessionID, version, message, expirationTime);
    }


  

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expirationTime == null) ? 0 : expirationTime.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((sessionID == null) ? 0 : sessionID.hashCode());
        result = prime * result + version;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (expirationTime == null) {
            if (other.expirationTime != null)
                return false;
        } else if (!expirationTime.equals(other.expirationTime))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (sessionID == null) {
            if (other.sessionID != null)
                return false;
        } else if (!sessionID.equals(other.sessionID))
            return false;
        if (version != other.version)
            return false;
        return true;
    }





    @Override
    public String toString() {
        return sessionID + "," + version + "," + message + "," + expirationTime.getTime();
    }
    
}
