
/* Variables Declaration */
var currentType = "ALL";


displayHomePageElements(currentType);


function displayHomePageElements(currentType){
    
    if(currentType === "Student"){
        /* Display Elements in Menu Bar */
        document.getElementById("userType_HOME").style.display = 'block';
        document.getElementById("userType_STUDENT").style.display = 'block';
        document.getElementById("userType_HISTORY").style.display = 'block';
        document.getElementById("userType_LOGOUT").style.display = 'block';

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuInstructor").style.display = 'none';
        document.getElementById("menuAdministrator").style.display = 'none';

    }
    else if(currentType === "Instructor"){
        /* Display Elements in Menu Bar */
        document.getElementById("userType_HOME").style.display = 'block';
        document.getElementById("userType_INSTRUCTOR").style.display = 'block';
        document.getElementById("userType_HISTORY").style.display = 'block';
        document.getElementById("userType_LOGOUT").style.display = 'block';

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuStudent").style.display = 'none';
        document.getElementById("menuAdministrator").style.display = 'none';
    }
    else if(currentType === "Administrator"){
        /* Display Elements in Menu Bar */
        document.getElementById("userType_HOME").style.display = 'block';
        document.getElementById("userType_HISTORY").style.display = 'block';
        document.getElementById("userType_ADMINISTRATOR").style.display = 'block';
        document.getElementById("userType_LOGOUT").style.display = 'block';    

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuStudent").style.display = 'none';
        document.getElementById("menuInstructor").style.display = 'none';
    }
    else if(currentType === "ALL" || currentType === ""){
        /* Display Elements in Menu Bar */
        document.getElementById("userType_HOME").style.display = 'block';
        document.getElementById("userType_INSTRUCTOR").style.display = 'block';
        document.getElementById("userType_STUDENT").style.display = 'block';
        document.getElementById("userType_HISTORY").style.display = 'block';
        document.getElementById("userType_ADMINISTRATOR").style.display = 'block';
        document.getElementById("userType_LOGOUT").style.display = 'block';
        
        /* Display Main Elements in the middle of the Screen */
        //Display All - Dont take action.
    }
}