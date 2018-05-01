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
import models.Question;
import models.TestsResults;
import models.User;

/**
 *
 * @author oneZt
 */
public class TakeTestServlet extends HttpServlet {
    
    private int numberOfQuestions = 5;
    private ArrayList<Question> allQuestions;
    
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
        
        allQuestions = inter.createAndGetQuestions(numberOfQuestions);
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Take a Test</title>");
        out.println("<link rel=\"icon\" href=\"images/take_test.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser.getUserType());
        out.println(menuBar);
        
        /* MANAGE MENU WITH JAVASCRIPT */
        out.println("<script src=\"js/displayHomePageElements.js\"></script>");
        
        /* DISPLAY TEST */
        out.println("<form method=\"post\" action=\"TakeTestServlet\">");
        for(int i = 0;i<numberOfQuestions;i++)
        {
            out.println("<label>"+ allQuestions.get(i).getFullQuestion()+"</label><br/>");
            if(allQuestions.get(i).getQuestionType()==0){
                out.println("<input name=\"answer"+i+"\" type=\"radio\" value=\"M\" checked=\"checked\" />Male<br/>");
                out.println("<input name=\"answer"+i+"\" type=\"radio\" value=\"F\" />Female<br/>");
            }
            else
                out.println("<input name=\"answer"+i+"\" type=\"text\" placeholder=\"Enter Answer\"/>");
            out.println("<br/><br/>");
            
        }
        
        /* SUBMIT BUTTON */
        out.println("<input type=\"submit\" value\"Submit\" />");
        
        out.println("</form>");
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
        
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        int score=0;
        
        
        for(int i = 0;i<numberOfQuestions;i++){
            String thisAnswer = request.getParameter("answer"+i);
            if(thisAnswer.equalsIgnoreCase(allQuestions.get(i).getCorrectAnswer()))
                score++;
        }

        User thisStudent = CookieAndSessionManager.getUserFromSession(request);
        TestsResults newTestResult = new TestsResults(thisStudent.getUserName(), score);
        inter.addToNewTable(newTestResult);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Take a Test</title>");
        out.println("<link rel=\"icon\" href=\"images/take_test.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/wordsManager.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser.getUserType());
        out.println(menuBar);
        
        /* DISPLAY SCORE TO THE USER */
        out.println("<h2>Your score is"+score+"</h2>");
        
        /* RETURN BUTTON */
        
        
        
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
