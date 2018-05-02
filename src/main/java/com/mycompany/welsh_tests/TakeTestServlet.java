package com.mycompany.welsh_tests;

/* Libraries Declaration */
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
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class TakeTestServlet extends HttpServlet {
    
    /* Variables Declaration */
    private int numberOfQuestions = 20;
    private ArrayList<Question> allQuestions;
    
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
        allQuestions = inter.createAndGetQuestions(numberOfQuestions);
        
        /* Create responsive and dynamic Servlet */
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Take a Test</title>");
        out.println("<link rel=\"icon\" href=\"images/take_test.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/testStyles.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser);
        out.println(menuBar);
        
        /* MANAGE MENU WITH JAVASCRIPT */
        out.println("<script src=\"js/displayHomePageElements.js\"></script>");
        
        /* DISPLAY TEXT TITLE */
        out.println("<br/><br/>");
        out.println("<h1 class=\"scoreText\">Please fill the test, good luck!</h1>");
        out.println("<br/>");
        
        /* DISPLAY TEST (LIST OF QUESTIONS) */
        out.println("<form  id=\"questionView\" method=\"post\" action=\"TakeTestServlet\">");
        for(int i = 0;i<numberOfQuestions;i++)
        {
            out.println("<label>"+ allQuestions.get(i).getFullQuestion()+"</label><br/>");
            if(allQuestions.get(i).getQuestionType()==0){
                out.println("<input  name=\"answer"+i+"\" type=\"radio\" value=\"M\" checked=\"checked\" />Male<br/>");
                out.println("<input  name=\"answer"+i+"\" type=\"radio\" value=\"F\" />Female<br/>");
            }
            else
                out.println("<input  name=\"answer"+i+"\" type=\"text\" placeholder=\"Enter Answer\"/>");
            out.println("<br/><br/>");
            
        }
        
        /* SUBMIT BUTTON */
        out.println("<input type=\"submit\" value\"Submit\" />");
        
        out.println("</form>"); //End of form 
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
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/testStyles.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser);
        out.println(menuBar);
        
        /* DISPLAY SCORE TO THE USER */
        out.println("<br/><br/>");
        out.println("<h1 class=\"scoreText\">Well Done! You have completed a test.</h1>");
        out.println("<br/>");
        out.println("<h2 class=\"scoreText\">Your score is " + score + "</h2>");
        out.println("<br/><br/>");
        
        /* DISPLAY TABLE OF HISTORY FROM DATABASE */
        ArrayList<TestsResults> testResults = inter.getTestHistory(thisUser);
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
        }
        
        out.println("</table>"); //End of 
        
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
