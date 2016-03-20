package SessionManagement;

import java.util.Calendar;
import java.util.Date;

public class Utils {
    public static Date getNextExpirationTime() {
        Calendar cal = Calendar.getInstance(); 
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime(); 
    }
}