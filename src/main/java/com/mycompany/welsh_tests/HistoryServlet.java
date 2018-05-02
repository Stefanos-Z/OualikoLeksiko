package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.TestsResults;
import models.User;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class HistoryServlet extends HttpServlet {

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
        
        /* GET CONNECTION WITH THE DATABASE */
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        /* Needed Variables */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        ArrayList<TestsResults> testResults = inter.getTestHistory(thisUser);
        
        /* Create responsive and dynamic Servlet */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<link rel=\"icon\" href=\"images/history.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/modals.css\"/>");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
        out.println("</head>");
        out.println("<body>");

        /* DISPLAY MENU BAR */
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser);
        out.println(menuBar);
        
        /* DISPLAY MODAL FOR DELETING A HISTORY (ALL BUT STUDENT) */
        String deleteHistoryModal = Modals.getDeleteHistoryModal();
        out.println(deleteHistoryModal);
        out.println("<script src=\"js/deleteHistoryPost.js\"></script>");                
        out.println("<script src=\"js/deleteHistoryModal.js\"></script>");                
        /* DISPLAY TABLE OF HISTORY FROM DATABASE */
        out.println("<table class=\"wordsTable\">");
        if(thisUser.getUserType().equals("Student")){ //If Studnt
        
            out.println("<tr>");
                out.println("<th class=\"columnLabel\">Student Name</th>");
                out.println("<th class=\"columnLabel\">Grade</th>");
                out.println("<th class=\"columnLabel\">Date Submitted</th>");
            out.println("</tr>");

            for(int i =0; i<testResults.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getUserName()+"</td>");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getGrade()+"</td>");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getDateSubmitted()+"</td>");
                out.println("</tr>");
            }
        }else{ //If (Instructor or Administrator)
            
            out.println("<tr>");
                out.println("<th class=\"columnLabel\">Student Name</th>");
                out.println("<th class=\"columnLabel\">Grade</th>");
                out.println("<th class=\"columnLabel\">Date Submitted</th>");
                out.println("<th class=\"columnLabel\">Settings</th>");
            out.println("</tr>");

            for(int i =0; i<testResults.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td class=\"columnData\">"+testResults.get(i).getUserName()+"</td>");
                    out.println("<td class=\"columnData\">"+testResults.get(i).getGrade()+"</td>");
                    out.println("<td class=\"columnData\">"+testResults.get(i).getDateSubmitted()+"</td>");
                    out.println("<td class=\"columnData\">");
                        out.println("<img id=\"deleteHistoryButton\" onclick=\"displayDeleteHistoryModal("+testResults.get(i).getTestID()+")\" class=\"deleteHistoryImage\" src=\"images/deleteWord.png\"/>");
                    out.println("</td>");
                out.println("</tr>");
            }
        }
        
        /* Display Delete Senction only on Admin and Instructor */
        out.println("<script src=\"js/deleteHistoryModal.js\"></script>");
        
        out.println("</table>"); //End of History Table
        
        out.println("</body>");
        out.println("</html>");
        out.close();
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
        
        String testID = request.getParameter("testID");
        System.out.println("****************************************"+testID);
        inter.deleteHistoryByTestID(testID);
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
