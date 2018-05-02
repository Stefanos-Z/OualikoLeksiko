/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import models.Sessions;
import models.User;

/**
 *
 * @author oneZt
 */
public class CookieAndSessionManager {
    
    
    public static boolean checkValidation(HttpServletRequest request)
    {
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        Cookie[] c = request.getCookies();
        if(c == null)
        {
            System.out.println("no cookies found");
            return false;
        }
        
        
        //System.out.println(c.length);
        Cookie myCookie = c[0];

        Sessions session = inter.getSessionbyID(myCookie.getValue());
        
        //Find user role
        //User user = inter.findByUserId(session.getUserId());
        
        boolean isValid = false;
        
        if(session.getExpirationDate().equals(new Date()))
            System.out.println(session.getExpirationDate()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        //true if valid
         if(session.getExpirationDate().before(new Date()))
             System.out.println("hellllllllllooooooooooooooooooossssssssssssssseeeeeeeeeeeexyyyyyyyyyy");
        if(session.getExpirationDate().after(new Date()))
        {
            isValid = true;
        }
        
        //extends the validity of the page
        if(isValid)
        {
            System.out.println("is VAlid!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Date newExpiryDate = new Date();//create a new date refference
            newExpiryDate.setHours(newExpiryDate.getHours()+1);//adds an hour to the expiry date
            session.setExpirationDate(newExpiryDate);//updates the new expiry date
            inter.updateSession(session);
            myCookie.setMaxAge(60*60);
        }else   
            System.out.println("notVALID!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        
        return isValid;
    }
    
    
    public static User getUserFromSession(HttpServletRequest request)
    {
        DatbaseInterface inter = new DatbaseInterface();
        inter.getConection();
        
        Cookie[] c = request.getCookies();
        if(c == null)
        {
            System.out.println("no cookies found");
            return null;
        }
        Cookie myCookie = c[0];

        Sessions session = inter.getSessionbyID(myCookie.getValue());
        String username = session.getUserName();
        return inter.getUseByUserName(username);
    }

    static String getMenuBar(String userType) {
        if(userType.equals("Administrator")){

            return ("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"adminsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"HistoryServlet\">View History</a></li>" +
                "<li><a id=\"menuBar_ADMINISTRATOR\" class=\"link\" href=\"MembersManagerServlet\">Add Users</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
        else if(userType.equals("Instructor")){

            return ("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"instructorsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_INSTRUCTOR\" class=\"link\" href=\"WordsManagerServlet\">Words Manager</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"HistoryServlet\">View History</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
        else {

            return ("<div class=\"menuBar\">" +
                "<ul id=\"listHolder\">" +
                "<li><a id=\"menuBar_HOME\" class=\"link\" href=\"studentsLandingPage.xhtml\">Home</a></li>" +
                "<li><a id=\"menuBar_STUDENT\" class=\"link\" href=\"TakeTestServlet\">Take a Test</a></li>" +
                "<li><a id=\"menuBar_HISTORY\" class=\"link\" href=\"HistoryServlet\">View History</a></li>" +
                "<li id=\"logoutButton\"><a id=\"menuBar_LOGOUT\" class=\"link\" href=\"LoginServlet\">Logout</a></li>" +
                "</ul>" +
                "</div>");
        }
    }
}
