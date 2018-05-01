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
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/modals.css\"/>");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
        
        out.println("<script src=\"js/wordManagerjs.js\"></script>");
        out.println("</head>");
        out.println("<body>");
        
        /* GET USER TYPE AND DISPLAY APPROPRIATE MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser.getUserType());
        out.println(menuBar);
        
        /* Create the button that will display the modal */
        out.println("<input id=\"addWordButton\" type=\"button\" value=\"+ Add a Word\"/>");
        
        /* Create the modal for adding a new word */
        String addModal = Modals.getAddWordModal();
        out.println(addModal);
        
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
                        
                        out.println("<img id=\"editWordButton\" class=\"editImage\" src=\"images/editWord.png\"/>");
                        String editModal = Modals.getEditWordModal(allWords.get(i).getWelshWord(),
                                allWords.get(i).getEnglishMeaning(),allWords.get(i).getGender());
                        out.println(editModal);
                        
                        
                        out.println("<img id=\"deleteWordButton\" class=\"deleteImage\" src=\"images/deleteWord.png\"/>");
                        String deleteModal = Modals.getDeleteWordModal(allWords.get(i).getWelshWord(),
                                allWords.get(i).getEnglishMeaning(),allWords.get(i).getGender());
                        out.println(deleteModal);
                        
        
                    out.println("</td>");
                out.println("</tr>");

        }
        
        out.println("<script src=\"js/editWordModal.js\"></script>");
        out.println("<script src=\"js/deleteWordModal.js\"></script>");
        
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
