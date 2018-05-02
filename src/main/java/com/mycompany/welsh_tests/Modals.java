package com.mycompany.welsh_tests;

import java.util.Date;

/**
 *
 * @author loizo
 */
public class Modals {
    
    public static String getAddWordModal() {
        
        String modal = "<div id=\"addWordModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"addModal-header\">"+
                    "<span class=\"addWordClose\">&times;</span>" + //Close Button
                    "<h1>Enter the following data to create a Word</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelshAdd\" type=\"text\" placeholder=\"Word in Welsh\"/>"+
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglishAdd\" type=\"text\" placeholder=\"Word in English\"/>"+
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"male\" checked>" +
                            "<span> Male </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"female\">" +
                            "<span>Female </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input id=\"AddWordToDB\" class=\"addModal-submit-button\" type=\"submit\" value=\"Create Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
    
        return modal;
    }
    
    public static String getEditWordModal() {
        
        String modal = "<div id=\"editWordModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"editModal-header\">"+
                    "<span class=\"editWordClose\">&times;</span>" + //Close Button
                    "<h1>Modify the following data to edit the Word</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form id=\"formModal\" class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelshInput\" type=\"text\" placeholder=\"Word in Welsh\"/>" +
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglishInput\" type=\"text\" placeholder=\"Word in English\" value\"\"\"/>" +
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input id=\"radio1\" class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"male\" checked=\"checked\">"+
                            "<span> Male </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input id=\"radio2\" class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"female\">"+
                            "<span>Female </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input class=\"editModal-submit-button\" type=\"submit\" value=\"Edit Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteWordModal() {
        
        String modal = "<div id=\"deleteWordModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following Word?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" +
                    "<input class=\"modal-textField\" name=\"wordWelshInput\" id=\"wordWelsh\" type=\"text\" disabled=\"disabled\"/>" +
                    "<input class=\"modal-textField\" name=\"wordEnglishInput\" id=\"wordEnglish\" type=\"text\" disabled=\"disabled\"/>" +
                    "<input class=\"modal-textField\" name=\"wordGenderInput\" id=\"wordGender\" type=\"text\" disabled=\"disabled\"/>" +
                    "<br/>" +

                    "<br/>" +
                    "<input class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getAddMemberModal() {
        
        String modal = "<div id=\"addMemberModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"addModal-header\">"+
                    "<span class=\"addMemberClose\">&times;</span>" + //Close Button
                    "<h1>Enter the following data to add a Member</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/AddUserServlet\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userName\" type=\"text\" placeholder=\"Enter username\"/>"+
                    "<input class=\"modal-textField\" name=\"password\" id=\"password\" type=\"password\" placeholder=\"Enter password\"/>"+
                    "<input class=\"modal-textField\" name=\"email\" id=\"email\" type=\"text\" placeholder=\"Enter email address\"/>"+
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Administrator\" checked>" +
                            "<span> Administrator </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Instructor\">" +
                            "<span> Instructor </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Student\">" +
                            "<span> Student </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input class=\"addModal-submit-button\" type=\"submit\" value=\"Create Member\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        
        return modal;
    }
    public static String getEditMemberModal(String username, String password, String email, String userType) {
        
        String userTypeADMINISTRATOR = "";
        String userTypeINSTRACTOR = "";
        String userTypeSTUDENT = "";
        
        if(userType.equals("Administrator")){ //Male
            userTypeADMINISTRATOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Administrator\" checked>";
            userTypeINSTRACTOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Instructor\">";
            userTypeSTUDENT = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Student\">";
        }else if(userType.equals("Instructor")){
            userTypeADMINISTRATOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Administrator\">";
            userTypeINSTRACTOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Instructor\" checked>";
            userTypeSTUDENT = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Student\">";
        }else if(userType.equals("Student")){
            userTypeADMINISTRATOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Administrator\">";
            userTypeINSTRACTOR = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Instructor\">";
            userTypeSTUDENT = "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Student\" checked>";
        }
        
       String modal = "<div id=\"editMemberModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"editModal-header\">"+
                    "<span class=\"editClose\">&times;</span>" + //Close Button
                    "<h1>Modify the following data to edit a Member</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/AddUserServlet\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userName\" type=\"text\" value=\" "+username+" \"/>"+
                    "<input class=\"modal-textField\" name=\"password\" id=\"password\" type=\"password\" value=\" "+password+" \"/>"+
                    "<input class=\"modal-textField\" name=\"email\" id=\"email\" type=\"text\" value=\" "+email+" \"/>"+
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            userTypeADMINISTRATOR +
                            "<span> Administrator </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            userTypeINSTRACTOR +
                            "<span> Instructor </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            userTypeSTUDENT +
                            "<span> Student </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input class=\"editModal-submit-button\" type=\"submit\" value=\"Edit Member\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteMemberModal(String username, String password, String email, String userType) {
        
        String modal = "<div id=\"deleteMemberModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following Member?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/AddUserServlet\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userName\" type=\"text\" value=\" "+username+" \"/>"+
                    "<input class=\"modal-textField\" name=\"password\" id=\"password\" type=\"password\" value=\" "+password+" \"/>"+
                    "<input class=\"modal-textField\" name=\"email\" id=\"email\" type=\"text\" value=\" "+email+" \"/>"+
                    "<input class=\"modal-textField\" name=\"userType\" id=\"userType\" type=\"text\" value=\" "+userType+" \"/>"+
                    "<br/>" +
                    "<input class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete Member\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteHistoryModal(String studentName, double grade, Date dateSubmitted) {
        
        String modal = "<div id=\"deleteHistoryModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following History?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/AddUserServlet\">" +
                    "<input class=\"modal-textField\" name=\"studentName\" id=\"studentName\" type=\"text\" value=\" "+studentName+" \"/>"+
                    "<input class=\"modal-textField\" name=\"grade\" id=\"grade\" type=\"number\" value=\" "+grade+" \"/>"+
                    "<input class=\"modal-textField\" name=\"dateSubmited\" id=\"dateSubmitted\" type=\"date\" value=\" "+dateSubmitted+" \"/>"+
                    "<br/>" +
                    "<input class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete History\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
}
