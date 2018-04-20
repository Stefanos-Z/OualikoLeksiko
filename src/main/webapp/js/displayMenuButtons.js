//REFERENCE : https://support.zendesk.com/hc/en-us/articles/203661316-Hide-or-show-HTML-based-on-user-s-role-or-group
//
//Show div html based on role
if(LoginServlet.getType === "student"){
    $("div.type_any").show();
}

if(LoginServlet.getType === "instructor"){
    $("div.type_any").show();
    $("div.type_instructor").show();
}

if(LoginServlet.getType === "administrator"){
    $("div.type_any").show();
    //$("div.type_instructor").show();
    $("div.type_administrator").show();
}
