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
        
        if(!CookieAndSessionManager.checkValidation(request))
            response.sendRedirect("login.xhtml");
        
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        ArrayList<WelshWord> allWords = inter.getWelshWords();
        response.setContentType("text/html;charset=UTF-8");
                
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<link rel=\"icon\" href=\"images/add_remove_words.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("<script src=\"js/displayMenuButtons.js\"></script>");
        out.println("<script src=\"js/addWordModal.js\"></script>");
        

        out.println("</head>");
        out.println("<body>");
        
        out.println("<div class=\"menuBar\">" +
            "<ul id=\"listHolder\">" +
                "<li><a class=\"type_any\" id=\"link\" href=\"/OualikoLeksiko/homePage.xhtml\">Home</a></li>" +
                "<li><a class=\"type_any\" id=\"link\" href=\"EditWordsServlet\">Words Manager</a></li>" +
                "<li><a class=\"type_any\" id=\"link\" href=\"TakeTestServlet\">Take a Test</a></li>" +
                "<li><a class=\"type_any\" id=\"link\" href=\"/OualikoLeksiko/history.xhtml\">View History Search</a></li>" +
                "<li><a class=\"type_administrators\" id=\"link\" href=\"/OualikoLeksiko/addMembers.xhtml\">Add Members</a></li>" +
                "<li id=\"logoutButton\"><a class=\"type_any\" id=\"link\" href=\"/OualikoLeksiko/login.xhtml\">Logout</a></li>" +
            "</ul>" +
        "</div>");
        
        out.println("<input class=\"addWordButton\" type=\"button\" value=\" + Add a Word\"/>");

        out.println("<button id=\"myBtn\">Open Modal</button>"+
                    "<div id=\"myModal\" class=\"modal\">" +
                        "<div class=\"modal-content\">" +   
                            "<span class=\"close\">&times;</span>" +
                            "<p>Some text in the Modal..</p>\n" +
                        "</div>" +
                    "</div>");
        
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
