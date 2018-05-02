/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */

/**This java script is for "locking" the sites if the user tries to open the 
 * sites without credentials because home pages of the users are xhtml and not 
 * servlets.So it's check if there are any cookies (that are being created when
 *  you logon with your credentials) exist.If not it's just delete the content
 *  else it allow the access
*/
function getCookie() {
    var myCookie = document.cookie;//get the page's current cookie status
    //document.getElementById("lockform").innerHTML=myCookie;//"Cookie Have Expired";
   
   if (myCookie == "") {
        // do cookie doesn't exist stuff;
      
        document.getElementById("lockform").innerHTML="";
        document.location.href="login.xhtml";
    }
    else {
        // do cookie exists stuff
   
        document.getElementById("lockform").innerHTML="";
    }
} 