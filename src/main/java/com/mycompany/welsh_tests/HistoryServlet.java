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
        
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User user= inter.getUseByUserName(username);
//        String userType = user.getUserType();
//        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        /* DISPLAY MENU BAR */
        if(thisUser.getUserType().equals("Administrator")){

            //String test = showMenu(userType);
            
            out.println("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"/OualikoLeksiko/adminsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"/OualikoLeksiko/HistoryServlet\">View History</a></li>" +
                "<li><a id=\"menuBar_ADMINISTRATOR\" class=\"link\" href=\"/OualikoLeksiko/AddUserServlet\">Add Users</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
        else if(thisUser.getUserType().equals("Instructor")){

            out.println("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"/OualikoLeksiko/instructorsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_INSTRUCTOR\" class=\"link\" href=\"WordsManagerServlet\">Words Manager</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"/OualikoLeksiko/HistoryServlet\">View History</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
        else if(thisUser.getUserType().equals("Student")){

            out.println("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"/OualikoLeksiko/studentsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_STUDENT\" class=\"link\" href=\"TakeTestServlet\">Take a Test</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"/OualikoLeksiko/HistoryServlet\">View History</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
        
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
                        out.println("<img class=\"deleteWordImage\" src=\"images/deleteWord.png\"/>");
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
