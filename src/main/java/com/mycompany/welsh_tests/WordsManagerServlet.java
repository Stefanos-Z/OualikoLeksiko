package com.mycompany.welsh_tests;

/* Libraries Declaration */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import models.WelshWord;

/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */
public class WordsManagerServlet extends HttpServlet {

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
        if(!CookieAndSessionManager.checkValidation(request)){
            response.sendRedirect("login.xhtml");
        }
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        /* NEEDED VARIABLES */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        ArrayList<WelshWord> allWords = inter.getWelshWords();
        response.setContentType("text/html;charset=UTF-8");
                
        /* Create responsive and dynamic Servlet */
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("<title>Words Manager</title>");
        out.println("<link rel=\"icon\" href=\"images/words_manager.png\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/menu_and_background.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tables.css\"/>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/modals.css\"/>");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
        
        out.println("<script src=\"js/deleteHistoryPost.js\"></script>");
        out.println("<script src=\"js/wordManagerjs.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        
        /* GET USER TYPE AND DISPLAY APPROPRIATE MENU BAR */
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser);
        out.println(menuBar);
        
        /* Create the button that will display the modal (add word)*/
        out.println("<input id=\"addWordButton\" type=\"button\" value=\"+ Add a Word\"/>");
        
        /* Create the modals */
        String addModal = Modals.getAddWordModal();
        out.println(addModal);
        String editModal = Modals.getEditWordModal();
        out.println(editModal);
        String deleteModal = Modals.getDeleteWordModal();
        out.println(deleteModal);
        
        /* CREATE THE LIST OF WORDS */
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
            out.println("<tr id=\"wordTableRow"+i+"\" class=\"columnRow\">");
                out.println("<td id=\"WW"+i+"\" class=\"columnData \">"+allWords.get(i).getWelshWord()+"</td>");
                out.println("<td id=\"EW"+i+"\" class=\"columnData\">"+allWords.get(i).getEnglishMeaning()+"</td>");
                out.println("<td id=\"G"+i+"\" class=\"columnData\">"+allWords.get(i).getGender()+"</td>");
                out.println("<td id=\"columnData\">"); //add modal on last column in every row

                out.println("<input id=\"editWordButton\" type=\"image\" onclick=\"displayModal("+i+","+allWords.get(i).getWordID() +
                                ")\" class=\"editImage\" src=\"images/editWord.png\"/>");
                out.println("<input id=\"deleteWordButton\" type=\"image\" onclick=\"displayDeleteModal("+allWords.get(i).getWordID()+")\" class=\"deleteImage\" src=\"images/deleteWord.png\"/>");
                

                out.println("</td>");
            out.println("</tr>");
        }
        /* MANAGE MODAL WITH JAVASCRIPT */
        out.println("<script src=\"js/addWordModal.js\"></script>");
        out.println("<script src=\"js/editWordModal.js\"></script>");
        out.println("<script src=\"js/deleteWordModal.js\"></script>");
        
        out.println("</table>"); //End of table (words)
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
        
        /* GET CONNECTION WITH THE DATABASE */
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        /* Needed Variables */
        String wordId = request.getParameter("wordId");
        System.out.println(wordId);
        
        /* DELETE THE WORD FROM THE DATABASE */
        inter.deleteWelshWord(wordId);
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
