package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class AddUserServlet extends HttpServlet {

    /**
     * Called by the server (via the service method) 
     * to allow a servlet to handle a GET request.
     * @param request an object that contains the request the client has made of the servlet
     * @param response an object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /* GET CONNECTION WITH THE DATABASE */
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        /* Needed Variables */
        String uName = request.getParameter("username");
        String pWord = request.getParameter("password");
        String email = request.getParameter("email");
        String uType = request.getParameter("userType");
        
        /* ADD THE USER IN THE DATABASE */
        inter.addUser(uName, pWord, email, uType);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}