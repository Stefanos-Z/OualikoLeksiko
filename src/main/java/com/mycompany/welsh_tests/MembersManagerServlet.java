package com.mycompany.welsh_tests;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

public class MembersManagerServlet extends HttpServlet {

    
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
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/modals.css\"/>");
        out.println("</head>");
        out.println("<body>");
        
        /* DISPLAY MENU BAR */
        User thisUser = CookieAndSessionManager.getUserFromSession(request);
        String menuBar = CookieAndSessionManager.getMenuBar(thisUser.getUserType());
        out.println(menuBar);
        
        /* ADD USER BUTTON - MODAL */
        out.println("<input id=\"addMemberButton\" type=\"button\" value=\"+ Add a Member\"/>");
        
        String addModal = Modals.getAddMemberModal();
        out.println(addModal);
        
        /* MANAGE MODAL WITH JAVASCRIPT */
        out.println("<script src=\"js/addMemberModal.js\"></script>");
        
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
                    
                    
                        out.println("<img id=\"editMemberButton\" class=\"editImage\" src=\"images/editWord.png\"/>");
                        String editModal = Modals.getEditMemberModal();
                        out.println(editModal);
                        
                        
                        out.println("<img id=\"deleteMemberButton\" class=\"deleteImage\" src=\"images/deleteWord.png\"/>");
                        String deleteModal = Modals.getDeleteMemberModal();
                        out.println(deleteModal);
                        
                    out.println("</td>");
                out.println("</tr>");

        }
        out.println("<script src=\"js/editMemberModal.js\"></script>");
        out.println("<script src=\"js/deleteMemberModal.js\"></script>");
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