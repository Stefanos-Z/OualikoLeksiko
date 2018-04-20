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
}
