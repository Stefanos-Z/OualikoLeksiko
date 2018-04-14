package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Loizos Vasileiou (eeu905)
 */
public class LoginServlet extends HttpServlet {
    
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
        //Empty Abstract Method.
        System.out.println("\n\n................!!..............\n\n");
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
        
        System.out.println("user name = " + username);
        System.out.println("password  = " + password);
        
        User user= inter.verificationUserID(username, password);
        
        response.sendRedirect("homePage.xhtml");

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
}
