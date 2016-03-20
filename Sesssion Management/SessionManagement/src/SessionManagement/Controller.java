package SessionManagement;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Session> sessionMap;

    private static String COOKIE_NAME = "SESSIONCONTROLLER_HONGFEI";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        sessionMap = new HashMap<>();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ipAddress = request.getRemoteAddr();
        Cookie[] cookies = request.getCookies();

        Session session = getSessionFromCookies(cookies);

        if (session == null) { // session not found in cookie
            session = Session.createNewSession(ipAddress);
        } else {
            Session serverSession = sessionMap.get(session.sessionID);
            
            Date currentTime = new Date();
            if (serverSession == null || serverSession.expirationTime.before(currentTime)) {
                session = Session.createNewSession(ipAddress);
            } else {
                
                if(request.getParameter("Replace") != null) { 
                    // update message
                    serverSession.message = request.getParameter("input");
                } 
                
                session = serverSession;
               
                session.version += 1;
                session.expirationTime = Utils.getNextExpirationTime();
            }
        }

        sessionMap.put(session.sessionID, session);

        Cookie c = new Cookie(COOKIE_NAME, session.toString());
        response.addCookie(c);
        
        request.setAttribute("sessionID", session.sessionID);
        request.setAttribute("sessionVersion", session.version);
        request.setAttribute("sessionMessage", session.message);
        request.setAttribute("sessinoExpirationDate", session.expirationTime);
        request.getRequestDispatcher("session.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cookie[] cookies = request.getCookies();

        if(request.getParameter("Refresh") != null){
            doGet(request, response);
            
        } else if (request.getParameter("Replace") != null) {

            doGet(request, response);
            
        } else if (request.getParameter("Logout") != null){

            Session s = getSessionFromCookies(cookies);
            if(s!= null) {
                sessionMap.remove(s.sessionID);
            }
            
            doGet(request,response);
        }
        
    }

    private Session getSessionFromCookie(Cookie c) {
        String[] values = c.getValue().split(",");

        String sessionID        = values[0];
        int version             = Integer.parseInt(values[1]);
        String message          = values[2];
        Date expirationTime     = new Date(Long.parseLong(values[3]));

        Session s = new Session(sessionID, version, message, expirationTime);

        return s;
    }
    
    private Session getSessionFromCookies(Cookie[] cookies) {
        for (Cookie c : cookies) {
            if (c.getName().equals(COOKIE_NAME)) {
                return getSessionFromCookie(c);
            }
        }
        
        return null;
    }
}
