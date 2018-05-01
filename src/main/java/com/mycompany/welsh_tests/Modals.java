/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.welsh_tests;

/**
 *
 * @author loizo
 */
public class Modals {
    
    public static String getAddWordModal() {
        
        String modal = "<div id=\"addModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"addModal-header\">"+
                    "<span class=\"addClose\">&times;</span>" + //Close Button
                    "<h1>Enter the following data to create a word</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelsh\" type=\"text\" placeholder=\"Word in Welsh\"/>"+
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglish\" type=\"text\" placeholder=\"Word in English\"/>"+
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
                    "<input class=\"addModal-submit-button\" type=\"submit\" value=\"Create Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
    
        return modal;
    }
    
    public static String getEditWordModal(String wordWelsh, String wordEnglish, String gender) {
        
        System.out.println("GENDER + +++ ===== ==++++ = " + gender);
        
        String maleGenderInput = "";
        String femaleGenderInput = "";
        
        if(gender.equals("M")){ //Male
            maleGenderInput = "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"male\" checked>";
            femaleGenderInput = "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"female\">";
        }else if(gender.equals("F")){
            maleGenderInput = "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"male\">";
            femaleGenderInput = "<input class=\"modal-radio-button\" type=\"radio\" name=\"gender\" value=\"female\" checked>";
        }
        
        String modal = "<div id=\"editModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"editModal-header\">"+
                    "<span class=\"editClose\">&times;</span>" + //Close Button
                    "<h1>Validate the following data to edit the word</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelsh\" type=\"text\" placeholder=\"Word in Welsh\" value=\" "+wordWelsh+" \"/>" +
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglish\" type=\"text\" placeholder=\"Word in English\" value=\" "+wordEnglish+" \"/>" +
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            maleGenderInput+
                            "<span> Male </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            femaleGenderInput+
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
    
    public static String getDeleteWordModal(String wordWelsh, String wordEnglish, String gender) {
        
        String modal = "<div id=\"deleteModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following word?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/WordsManagerServlet\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelsh\" type=\"text\" disabled=\"disabled\" value=\" "+wordWelsh+" \"/>" +
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglish\" type=\"text\" disabled=\"disabled\" value=\" "+wordEnglish+" \"/>" +
                    "<input class=\"modal-textField\" name=\"wordGender\" id=\"wordGender\" type=\"text\" disabled=\"disabled\" value=\" "+gender+" \"/>" +
                    "<br/>" +

                    "<br/>" +
                    "<input class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getAddMemberModal() {
        String modal = "<div id=\"addModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"addModal-header\">"+
                    "<span class=\"addClose\">&times;</span>" + //Close Button
                    "<h1>Enter the following data to add a Memer</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"/OualikoLeksiko/AddUserServlet\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userName\" type=\"text\" placeholder=\"Enter username\"/>"+
                    "<input class=\"modal-textField\" name=\"password\" id=\"password\" type=\"password\" placeholder=\"Enter password\"/>"+
                    "<input class=\"modal-textField\" name=\"email\" id=\"email\" type=\"text\" placeholder=\"Enter email address\"/>"+
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"administrator\" checked>" +
                            "<span> Administrator </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"instructor\">" +
                            "<span> Instructor </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userType\" value=\"Student\">" +
                            "<span> Student </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input class=\"addModal-submit-button\" type=\"submit\" value=\"Create Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        
        return modal;
    }
    public static String getEditMemberModal() {
        
        String modal = "";
        
        return modal;
    }
    public static String getDeleteMemberModal() {
        
        String modal = "";
        
        return modal;
    }
}
