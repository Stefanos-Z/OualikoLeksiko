package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Sessions;
import models.User;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class LoginServlet extends HttpServlet {
     
    private String userType;
    /**
     * Called by the server (via the service method) 
     * to allow a servlet to handle a GET request.
     * @param request an object that contains the request the client has made of the servlet
     * @param response an object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteCookiesOnPage(request, response);
        response.sendRedirect("login.xhtml");
        
    }

    /**
     * Called by the server (via the service method) to 
     * allow a servlet to handle a POST request. 
     * @param request an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
//        System.out.println("username = " + username);
//        System.out.println("password  = " + password);
        
        User user= inter.verificationUserID(username, password);
        
        if(user!=null)
        {
            if(user.getUserPassword().equals(password))
            {
                Sessions newSession = new Sessions(user.getUserName());
                inter.createSession(newSession);
                
                userType = user.getUserType();
                
                Cookie myCookie = new Cookie(user.getUserName(), newSession.getSessionID());
                myCookie.setMaxAge(60*60);//sets the the lifespan of the cooki in seconds
                myCookie.setPath("/");
                
                response.addCookie(myCookie);
                
                System.out.println("\n\nuserType = " + userType);
                
                if(userType.equals("Administrator")){
                    response.sendRedirect("adminsLandingPage.xhtml");
                }
                else if(userType.equals("Instructor")){
                    response.sendRedirect("instructorsLandingPage.xhtml");
                }
                else if(userType.equals("Student")){
                    response.sendRedirect("studentsLandingPage.xhtml");
                }
            }
            else
                response.sendRedirect("login.xhtml");
        }else
            response.sendRedirect("login.xhtml");
    }
    
    /**
     * Returns information about the servlet, such as author, 
     * version, and copyright; By default, this method returns an 
     * empty string; Override this method to have it return a 
     * meaningful value; See Servlet.getServletInfo().
     */
    @Override
    public String getServletInfo() {
        return "info";
    }

    /**
     * Gets the type of the user currently logged-in
     * @return a String representation of the user Type
     */
    public String getUserType() {
        System.out.println("user Type = " + userType);
        return userType;
    }
    
    
    
    
    
    
    private void deleteCookiesOnPage(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] allCookies = request.getCookies();
        if (allCookies != null)
        {
            for(Cookie thisCookie: allCookies)
            {
                Cookie cookie = new Cookie(thisCookie.getName(), null);
                cookie.setPath("/");
                cookie.setMaxAge(0);
                
                response.addCookie(cookie);
            }
        }
    }   
}