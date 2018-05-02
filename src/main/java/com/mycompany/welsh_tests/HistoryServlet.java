package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Sessions;
import models.TestsResults;
import models.User;


public class HistoryServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        ArrayList<TestsResults> testResults = inter.getTestHistory(thisUser);
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<link rel=\"icon\" href=\"images/history.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("</head>");
        out.println("<body>");

        /* DISPLAY MENU BAR */
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser);
        out.println(menuBar);
        
        /* DISPLAY TABLE OF HISTORY FROM DATABASE */
        out.println("<table class=\"wordsTable\">");
        if(thisUser.getUserType().equals("Student")){
        
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
        }else{
            
            out.println("<tr>");
                out.println("<th class=\"columnLabel\">Student Name</th>");
                out.println("<th class=\"columnLabel\">Grade</th>");
                out.println("<th class=\"columnLabel\">Date Submitted</th>");
                out.println("<th class=\"columnLabel\">Settings</th>");
            out.println("</tr>");

            for(int i =0; i<testResults.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getUserName()+"</td>");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getGrade()+"</td>");
                    out.println("<td id=\"columnData\">"+testResults.get(i).getDateSubmitted()+"</td>");
                    out.println("<td id=\"columnData\">");
                        out.println("<img class=\"deleteHistoryImage\" src=\"images/deleteWord.png\"/>");
                        String deleteHistoryModal = Modals.getDeleteHistoryModal(testResults.get(i).getUserName(),
                                testResults.get(i).getGrade(), testResults.get(i).getDateSubmitted());
                        out.println(deleteHistoryModal);
                        out.println("<script src=\"js/deleteHistoryModal.js\"></script>");
                        
                    out.println("</td>");
                out.println("</tr>");
            }
        }
        out.println("</table>");
        
        
        
        
        
        
        out.println("</body>");
        out.println("</html>");
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Empty Abstract Method
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
