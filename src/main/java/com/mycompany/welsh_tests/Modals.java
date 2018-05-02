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
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"genderForAdd\" value=\"male\" checked>" +
                            "<span> Male </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"genderForAdd\" value=\"female\">" +
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
                "<form id=\"formModal\" class=\"modal-form\">" +
                    "<input class=\"modal-textField\" name=\"wordWelsh\" id=\"wordWelshInput\" type=\"text\" placeholder=\"Word in Welsh\"/>" +
                    "<input class=\"modal-textField\" name=\"wordEnglish\" id=\"wordEnglishInput\" type=\"text\" placeholder=\"Word in English\"/>" +
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input id=\"radio1\" class=\"modal-radio-button\" type=\"radio\" name=\"genderForEdit\" value=\"male\" checked=\"checked\">"+
                            "<span> Male </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input id=\"radio2\" class=\"modal-radio-button\" type=\"radio\" name=\"genderForEdit\" value=\"female\">"+
                            "<span>Female </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input id=\"hidenTextEdit\" class=\"hidenTextFields\" name=\"hidenTextField\" type=\"text\" />"+
                    "<br/>" +
                    "<input id=\"editWordDB\"class=\"editModal-submit-button\" type=\"submit\" value=\"Edit Word\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteWordModal() {
        
        String modal = "<div id=\"deleteWordModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteWordClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following Word?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\">" +
                    
                    "<input id=\"hidenTextDelete\" class=\"hidenTextFields\" name=\"hidenTextField\" type=\"text\" />"+
                    "<br/>" +
                    "<input id=\"deleteWordDB\" class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete Word\"/>" +
                    "<br/>" +
                
                    "<input class=\"deleteModal-submit-button\" id=\"deleteModal-cancel-button\" type=\"button\" value=\"Cancel\"/>" +
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
                "<form class=\"modal-form\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userNameAdd\" type=\"text\" placeholder=\"Enter username\"/>"+
                    "<input class=\"modal-textField\" name=\"password\" id=\"passwordAdd\" type=\"password\" placeholder=\"Enter password\"/>"+
                    "<input class=\"modal-textField\" name=\"email\" id=\"emailAdd\" type=\"text\" placeholder=\"Enter email address\"/>"+
                    "<br/>" +
                    "<div class=\"modal-radio-buttons-div\">" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userTypeAdd\" value=\"Administrator\" checked>" +
                            "<span> Administrator </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userTypeAdd\" value=\"Instructor\">" +
                            "<span> Instructor </span>" +
                        "</label>" +
                        "<label class=\"modal-radio-label\">" +
                            "<input class=\"modal-radio-button\" type=\"radio\" name=\"userTypeAdd\" value=\"Student\">" +
                            "<span> Student </span>" +
                        "</label>" +
                    "</div>"+
                    "<br/>" +
                    "<input id=\"addUserDB\"class=\"addModal-submit-button\" type=\"submit\" value=\"Create Member\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getEditMemberModal() {
        
                String modal = "<div id=\"editMemberModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" +
                "<header class=\"editModal-header\">" +
                    "<span class=\"editMemberClose\">&times;</span>" +
                    "<h1>Modify the following data to edit a Member</h1>" +
                "</header>" +
                "<form class=\"modal-form\">" +
                    "<input class=\"modal-textField\" name=\"username\" id=\"userNameEdit\" type=\"text\">" +
                    "<input class=\"modal-textField\" name=\"password\" id=\"passwordEdit\" type=\"password\">" +
                    "<input class=\"modal-textField\" name=\"email\" id=\"emailEdit\" type=\"text\">" +
                    "<br>" +
                    "<input id=\"hidenTextEditUserType\" class=\"hidenTextFields\" name=\"hidenTextField\" type=\"text\" />"+
                    "<input id=\"hidenTextEditUsername\" class=\"hidenTextFields\" name=\"hidenTextField\" type=\"text\" />"+
                    "<input id=\"editUserDB\" class=\"editModal-submit-button\" type=\"submit\" value=\"Edit Member\">" +
                "</form>" +
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteMemberModal() {
        
        String modal = "<div id=\"deleteMemberModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteMemberClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following Member?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\">" +
                    "<input id=\"hidenTextDeleteUser\" class=\"hidenTextFields\" name=\"hidenTextField\" type=\"text\" />"+
                    "<input id=\"deleteUserDB\" class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete Member\"/>" +
                    "<input class=\"deleteModal-submit-button\" id=\"deleteModal-cancel-button\" type=\"button\" value=\"Cancel\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
    
    public static String getDeleteHistoryModal() {
        
        String modal = "<div id=\"deleteHistoryModal\" class=\"modal\">" +
            "<div class=\"modal-content\">" + 
                "<header class=\"deleteModal-header\">"+
                    "<span class=\"deleteHistoryClose\">&times;</span>" + //Close Button
                    "<h1>Are you sure you want to delete the following History?</h1>" + 
                "</header>" + //End of MODAL <header>
                "<form class=\"modal-form\" method=\"post\" action=\"HistoryServlet\">" +
                    "<input class=\"modal-textField\" name=\"studentName\" id=\"studentName\" type=\"text\" value=\"\"/>"+
                    "<input class=\"modal-textField\" name=\"grade\" id=\"grade\" type=\"number\" value=\"\"/>"+
                    "<input class=\"modal-textField\" name=\"dateSubmited\" id=\"dateSubmitted\" type=\"date\" value=\"\"/>"+
                    "<br/>" +
                    "<input class=\"deleteModal-submit-button\" type=\"submit\" value=\"Delete History\"/>" +
                "</form>" + //End of MODAL <form>
            "</div>" + //End of MODAL CONTENT <div>
        "</div>"; //End of MODAL <div>
        
        return modal;
    }
}
