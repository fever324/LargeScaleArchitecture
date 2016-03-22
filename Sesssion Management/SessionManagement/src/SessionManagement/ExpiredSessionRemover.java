package SessionManagement;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class ExpiredSessionRemover implements Runnable {
    private Map<String, Session> sessionMap;
    
    public ExpiredSessionRemover(Map<String, Session> sessionMap) {
        this.sessionMap = sessionMap;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(180000); // sleep for 3 min
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Date currentTime = new Date();
        synchronized(sessionMap) {
            Iterator iterator = sessionMap.entrySet().iterator();
            while(iterator.hasNext()) {
               Map.Entry<String, Session> session = (Map.Entry)iterator.next();
               
               if(session.getValue().expirationTime.before(currentTime)) {
                   iterator.remove();
               }
            }
        }    
    }

}
