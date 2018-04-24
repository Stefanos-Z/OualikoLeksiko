/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.WelshWord;

/**
 *
 * @author oneZt
 */
public class WordsManagerServlet extends HttpServlet {



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
        
        if(!CookieAndSessionManager.checkValidation(request)){
            response.sendRedirect("login.xhtml");
        }
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        ArrayList<WelshWord> allWords = inter.getWelshWords();
        response.setContentType("text/html;charset=UTF-8");
                
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Words Manager</title>");
        out.println("<link rel=\"icon\" href=\"images/words_manager.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU */
        out.println("<div class=\"menuBar\">" +
                    "<ul id=\"listHolder\">" +
                    "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"/OualikoLeksiko/homePage.xhtml\">Home</a></li>" +
                    "<li><a id=\"menuBar_INSTRUCTOR\" class=\"link\" href=\"WordsManagerServlet\">Words Manager</a></li>" +
                    "<li><a id=\"menuBar_STUDENT\" class=\"link\" href=\"TakeTestServlet\">Take a Test</a></li>" +
                    "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"/OualikoLeksiko/HistoryServlet\">View History</a></li>" +
                    "<li><a id=\"menuBar_ADMINISTRATOR\" class=\"link\" href=\"/OualikoLeksiko/AddUserServlet\">Add Users</a></li>" +
                    "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                    "</ul>" +
                    "</div>");
        
        /* MANAGE MENU WITH JAVASCRIPT */
        out.println("<script src=\"js/displayHomePageElements.js\"></script>");
        
        /* Create the button that will display the modal */
        out.println("<input id=\"myButton\" type=\"button\" value=\"+ Add a Word\"/>");
        
        out.println("<div id=\"myModal\" class=\"modal\">" +
                        "<div class=\"modal-content\">" +   
                            "<span class=\"close\">&times;</span>" +
                            "<label>Enter the following data to create a word</label>" + 
                            "<input name=\"wordWelsh\" id=\"wordWelsh\" type=\"text\" placeholder=\"Word in Welsh\"/>"+
                            "<input name=\"wordEnglish\" id=\"wordEnglish\" type=\"text\" placeholder=\"Word in English\"/>"+
                            "<select>" +
                            "<option>Choose Gender</option>" +
                            "<option name=\"wordGender\" value=\"gender\" name=\"male\" >Male</option>" +
                            "<option name=\"wordGender\" value=\"gender\" name=\"female\" >Female</option>" +
                            "</select>" +
                            "<form method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" + 
                            "<input type=\"submit\" value=\"Create Word\"/>" +
                            "</form>" +
                            "<form>" +
                            "<input type=\"submit\" value=\"Cancel\"/>" +
                            "</form>" +
                        "</div>");
        out.println("</div>"); //End of MODAL DIV
        
        /* GET  VALUES FROM MODAL */
        int wordID = inter.getWelshWords().size()+1;
        String welshMeaning = request.getParameter("wordWelsh");
        String englishMeaning = request.getParameter("wordEnglish");
        String gender = request.getParameter("wordGender");
        
        System.out.println("\n*****\n******\n*****\nMYMYMYMYMYMYMYMY");
        System.out.println("word info " + wordID);
        System.out.println("word info " + welshMeaning);
        System.out.println("word info " + englishMeaning);
        System.out.println("word info " + gender);
        
        /* MANAGE MODAL WITH JAVASCRIPT */
        out.println("<script src=\"js/addWordModal.js\"></script>");
        

                
        out.println("<table class=\"wordsTable\">");
            out.println("<tr>");
                out.println("<th align=\"center\" class=\"columnLabel\">");
                    out.println("Welsh Language <img class=\"columnDataImage\" src=\"images/welsh-flag.png\"/>");
                out.println("</th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("English Language <img class=\"columnDataImage\" src=\"images/uk-flag.png\"/></th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("Gender of the word <img class=\"columnDataImage\" src=\"images/gender.png\"/></th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("Options<img class=\"columnDataImage\" src=\"images/option.png\"/></th>");
                out.println("</tr>");
        
        for(int i =0; i<allWords.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getWelshWord()+"</td>");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getEnglishMeaning()+"</td>");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getGender()+"</td>");
                    out.println("<td id=\"columnData\">");
                        out.println("<img class=\"editWordImage\" src=\"images/editWord.png\"/>");
                        out.println("<img class=\"deleteWordImage\" src=\"images/deleteWord.png\"/>");
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
        if(!CookieAndSessionManager.checkValidation(request)){
            response.sendRedirect("login.xhtml");
        }
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        response.setContentType("text/html;charset=UTF-8");
                
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Words Manager</title>");
        out.println("<link rel=\"icon\" href=\"images/words_manager.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU */
        out.println("<div class=\"menuBar\">" +
                    "<ul id=\"listHolder\">" +
                    "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"/OualikoLeksiko/homePage.xhtml\">Home</a></li>" +
                    "<li><a id=\"menuBar_INSTRUCTOR\" class=\"link\" href=\"WordsManagerServlet\">Words Manager</a></li>" +
                    "<li><a id=\"menuBar_STUDENT\" class=\"link\" href=\"TakeTestServlet\">Take a Test</a></li>" +
                    "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"/OualikoLeksiko/HistoryServlet\">View History</a></li>" +
                    "<li><a id=\"menuBar_ADMINISTRATOR\" class=\"link\" href=\"/OualikoLeksiko/AddUserServlet\">Add Users</a></li>" +
                    "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                    "</ul>" +
                    "</div>");
        
        /* MANAGE MENU WITH JAVASCRIPT */
        out.println("<script src=\"js/displayHomePageElements.js\"></script>");
        
        
        
        /* GET  VALUES FROM MODAL */
        int wordID = inter.getWelshWords().size()+1;
        String welshMeaning = request.getParameter("wordWelsh");
        String englishMeaning = request.getParameter("wordEnglish");
        String gender = request.getParameter("wordGender");
        
        System.out.println("\n*****\n******\n*****\nMYMYMYMYMYMYMYMY");
        System.out.println("word info " + wordID);
        System.out.println("word info " + welshMeaning);
        System.out.println("word info " + englishMeaning);
        System.out.println("word info " + gender);
        /* Add Words to the Database Table */
        inter.addWelshWord(wordID, welshMeaning, englishMeaning, gender);
        
        ArrayList<WelshWord> allWords = inter.getWelshWords();
        
        /* DIPSLAY NEW TABLE */
        out.println("<table class=\"wordsTable\">");
            out.println("<tr>");
                out.println("<th align=\"center\" class=\"columnLabel\">");
                    out.println("Welsh Language <img class=\"columnDataImage\" src=\"images/welsh-flag.png\"/>");
                out.println("</th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("English Language <img class=\"columnDataImage\" src=\"images/uk-flag.png\"/></th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("Gender of the word <img class=\"columnDataImage\" src=\"images/gender.png\"/></th>");
                out.println("<th class=\"columnLabel\">");
                    out.println("Options<img class=\"columnDataImage\" src=\"images/option.png\"/></th>");
                out.println("</tr>");
        System.out.println("\n\n\n\n\n\n\n\n****************************************************"
            + "*********************************************************************************");
        for(int i =0; i<allWords.size();i++){
                out.println("<tr class=\"columnRow\">");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getWelshWord()+"</td>");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getEnglishMeaning()+"</td>");
                    out.println("<td id=\"columnData\">"+allWords.get(i).getGender()+"</td>");
                    out.println("<td id=\"columnData\">");
                        out.println("<img class=\"editWordImage\" src=\"images/editWord.png\"/>");
                        out.println("<img class=\"deleteWordImage\" src=\"images/deleteWord.png\"/>");
                    out.println("</td>");
                out.println("</tr>");
                
            System.out.println(i+" , "+allWords.get(i).getWelshWord()+", "+
                    allWords.get(i).getEnglishMeaning()+", " + allWords.get(i).getGender());
        }
        out.println("</table>");
        
        out.println("</body>");
        out.println("</html>");
        out.close();    
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