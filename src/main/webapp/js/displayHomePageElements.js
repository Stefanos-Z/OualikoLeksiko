//REFERENCE : https://support.zendesk.com/hc/en-us/articles/203661316-Hide-or-show-HTML-based-on-user-s-role-or-group
//
//Show div html based on role

var currentType = "ALL";

if(currentType === "Student"){
    document.getElementById("userType_HOME").style.display = 'block';
    document.getElementById("userType_STUDENT").style.display = 'block';
    document.getElementById("userType_HISTORY").style.display = 'block';
    document.getElementById("userType_LOGOUT").style.display = 'block';
    
    document.getElementById("menuInstructor").style.display = 'none';
    document.getElementById("menuAdministrator").style.display = 'none';

}
else if(currentType === "Instructor"){
    document.getElementById("userType_HOME").style.display = 'block';
    document.getElementById("userType_INSTRUCTOR").style.display = 'block';
    document.getElementById("userType_HISTORY").style.display = 'block';
    document.getElementById("userType_LOGOUT").style.display = 'block';
    
    document.getElementById("menuStudent").style.display = 'none';
    document.getElementById("menuAdministrator").style.display = 'none';
}
else if(currentType === "Administrator"){
    document.getElementById("userType_HOME").style.display = 'block';
    document.getElementById("userType_HISTORY").style.display = 'block';
    document.getElementById("userType_ADMINISTRATOR").style.display = 'block';
    document.getElementById("userType_LOGOUT").style.display = 'block';    
    
    document.getElementById("menuStudent").style.display = 'none';
    document.getElementById("menuInstructor").style.display = 'none';
}
else if(currentType === "ALL" || currentType === ""){
    document.getElementById("userType_HOME").style.display = 'block';
    document.getElementById("userType_INSTRUCTOR").style.display = 'block';
    document.getElementById("userType_STUDENT").style.display = 'block';
    document.getElementById("userType_HISTORY").style.display = 'block';
    document.getElementById("userType_ADMINISTRATOR").style.display = 'block';
    document.getElementById("userType_LOGOUT").style.display = 'block';
    
}