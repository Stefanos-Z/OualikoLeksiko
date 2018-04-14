package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author loizo
 */
public class ManageUserServlet extends HttpServlet {

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
   
        response.setContentType("text/html;charset=UTF-8");
        
        //Declare Needed ArrayList
        
        PrintWriter out = response.getWriter();//Declare print writer for HTML Parsing
        out.println("<html>");
        out.println("<head>");
        out.println("<title>List of Locations</title>"); //ADD HEAD TITLE
        out.println("<style> .backButton { font-size:25px; position:fixed; top:0; left:0; width:250px; height:70;}</style>");
        out.println("</head>"); //End of <head> tag
        out.println("<body style=\"text-align:center\">");

        /* Add a Return Button (SetTimeZone) */
        out.println("<form method=\"post\" action=\"SetTimeZone.xhtml\">");
        out.println("<input class=\"backButton\" type=\"submit\" value=\"← Set Time-Zone\">");
        out.println("</form>");

        /* Add Text Title */
        out.println("<h1 style=\"color:red;\" style=\"font-size:50px;\">"
                + "<b>List of Locations</b></h1>");
        
        /* Print the list of locations 
        for(int i=0; i<ids.length; i++){
            out.println("<h2 style=\"color:green;\">"+ids[i].toString()+"</h2>");
        }
        */
        
        out.println("</body>");//End of <body> tag
        out.println("</html>");//End of <html> tag
        out.close();//Close Print Writer
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
        //Empty Abstract Method.
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
