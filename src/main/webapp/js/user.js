/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */

/**
 * This is where substract the name of the user from the current cookie. And place 
 * it on the menu bar at the top right side
 */

function getuserID(){
 var value = document.cookie;
 userID = value.substring(0, value.indexOf('='));
 document.getElementById("logeduser").innerHTML="User: "+userID;
  }