function getCookie() {
    var myCookie = document.cookie;
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