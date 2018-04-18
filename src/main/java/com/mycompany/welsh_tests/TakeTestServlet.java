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

/**
 *
 * @author oneZt
 */
public class TakeTestServlet extends HttpServlet {
    private final int numberOfQuestions = 20;
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
        out.println("</head>");
        out.println("<body>");
        out.println("<form method=\"post\" action=\"TakeTestServlet\">");
        for(int i = 0;i<numberOfQuestions;i++)
        {
            //<input type="radio" name="deliveryOption" value="Same Day" checked="checked"/>Same Day<br/>
            out.println("<label>"+ allQuestions.get(i).getFullQuestion()+"</label><br/>");
            if(allQuestions.get(i).getQuestionType()==1){
                out.println("<input name=\"answer"+i+"\" type=\"radio\" value=\"Male\" checked=\"checked\" />Male<br/>");
                out.println("<input name=\"answer"+i+"\" type=\"radio\" value=\"Female\" />Female<br/>");
            }
            else
                out.println("<input name=\"answer"+i+"\" type=\"text\" placeholder=\"Enter Answer\"/>");
            out.println("<br/><br/>");
            
        }
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
        int score=0;
        for(int i = 0;i<numberOfQuestions;i++){
            String thisAnswer = request.getParameter("answer"+i);
            if(thisAnswer.equalsIgnoreCase(allQuestions.get(i).getCorrectAnswer()))
                score++;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Your score is"+score+"</h2>");
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
