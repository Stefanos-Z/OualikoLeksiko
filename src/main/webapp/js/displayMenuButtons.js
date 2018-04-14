//REFERENCE : https://support.zendesk.com/hc/en-us/articles/203661316-Hide-or-show-HTML-based-on-user-s-role-or-group
//
//Show div html based on role
if (PersonLogedIn.getType.role==="student"){
    $("div.type_any").show();
}

if (HelpCenter.user.role==="instructor"){
    $("div.type_any").show();
    $("div.type_instructor").show();
}

if (HelpCenter.user.role==="administrator"){
    $("div.type_any").show();
    //$("div.type_instructor").show();
    $("div.type_administrator").show();
}
