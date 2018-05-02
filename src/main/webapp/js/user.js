/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getuserID(){
 var value = document.cookie;
 userID = value.substring(0, value.indexOf('='));
 document.getElementById("logeduser").innerHTML="User: "+userID;
  }