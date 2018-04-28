package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

public class AddUserServlet extends HttpServlet {

    
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
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        ArrayList<User> users = inter.getAllUsers();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Add Users</title>");
        out.println("<link rel=\"icon\" href=\"images/add_member.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        /* DISPLAY MENU BAR */
        if(thisUser.getUserType().equals("Administrator")){

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
        
        /* MANAGE MENU WITH JAVASCRIPT */
        out.println("<script src=\"js/displayHomePageElements.js\"></script>");
        
        /* ADD USER BUTTON - MODAL */
        out.println("<input id=\"addWordButton\" type=\"button\" value=\"+ Add a Member\"/>");
        
        out.println("<div id=\"myModal\" class=\"modal\">" +
                        "<div class=\"modal-content\">" +   
                            "<span class=\"close\">&times;</span>" +
                            "<label>Enter the following data to add a Member</label>" + 
                            "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelsh\" type=\"text\" placeholder=\"username\"/>"+
                            "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglish\" type=\"password\" placeholder=\"password\"/>"+
                            "<select>" +
                            "<option>Choose Gender</option>" +
                            "<option name=\"wordGender\" value=\"gender\" name=\"male\" >Male</option>" +
                            "<option name=\"wordGender\" value=\"gender\" name=\"female\" >Female</option>" +
                            "</select>" +
                            "<form method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" + 
                            "<input class=\"modal-button\" type=\"submit\" value=\"Create Word\"/>" +
                            "</form>" +
                            "<form>" +
                            "<input class=\"modal-button\" type=\"submit\" value=\"Cancel\"/>" +
                            "</form>" +
                        "</div>");
        out.println("</div>"); //End of MODAL DIV
        
        /* MANAGE MODAL WITH JAVASCRIPT */
        out.println("<script src=\"js/addWordModal.js\"></script>");
        
        /* DISPLAY TABLE OF USERS FROM DATABASE */
        out.println("<table class=\"wordsTable\">");
            out.println("<tr>");
                out.println("<th align=\"center\" class=\"columnLabel\">Username</th>");
                out.println("<th class=\"columnLabel\">Password</th>");
                out.println("<th class=\"columnLabel\">Email</th>");
                out.println("<th class=\"columnLabel\">Type</th>");
                out.println("<th class=\"columnLabel\">Options</th>");
            out.println("</tr>");
        
        for(int i =0; i<users.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td id=\"columnData\">"+users.get(i).getUserName()+"</td>");
                    out.println("<td id=\"columnData\">"+users.get(i).getUserPassword()+"</td>");
                    out.println("<td id=\"columnData\">"+users.get(i).getUserEmail()+"</td>");
                    out.println("<td id=\"columnData\">"+users.get(i).getUserType()+"</td>");
                    out.println("<td id=\"columnData\">");
                        out.println("<img class=\"editImage\" src=\"images/editWord.png\"/>");
                        out.println("<img class=\"deleteImage\" src=\"images/deleteWord.png\"/>");
                    out.println("</td>");
                out.println("</tr>");

        }
        out.println("</table>");
        
        
        
        
        
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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