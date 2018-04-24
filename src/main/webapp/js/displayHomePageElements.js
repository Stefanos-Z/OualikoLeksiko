
/* Variables Declaration */
var currentTypeLoggedIn = "Student";

displayHomePageElements(currentTypeLoggedIn);


function displayHomePageElements(currentTypeLoggedIn){

    document.getElementById("menuBar_HISTORY").style.display = 'block';
    document.getElementById("menuBar_LOGOUT").style.display = 'block';
        
    if(currentTypeLoggedIn === "Student"){
        
        /* Display Elements in Menu Bar */
        document.getElementById("menuBar_STUDENT").style.display = 'block';

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuInstructor").style.display = 'none';
        document.getElementById("menuAdministrator").style.display = 'none';

    }
    else if(currentTypeLoggedIn === "Instructor"){
        
        /* Display Elements in Menu Bar */
        document.getElementById("menuBar_HOME").style.display = 'block';
        document.getElementById("menuBar_INSTRUCTOR").style.display = 'block';
        document.getElementById("menuBar_HISTORY").style.display = 'block';
        document.getElementById("menuBar_LOGOUT").style.display = 'block';

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuStudent").style.display = 'none';
        document.getElementById("menuAdministrator").style.display = 'none';
    }
    else if(currentTypeLoggedIn === "Administrator"){
        
        /* Display Elements in Menu Bar */
        document.getElementById("menuBar_HOME").style.display = 'block';
        document.getElementById("menuBar_HISTORY").style.display = 'block';
        document.getElementById("menuBar_ADMINISTRATOR").style.display = 'block';
        document.getElementById("menuBar_LOGOUT").style.display = 'block';    

        /* Display Main Elements in the middle of the Screen */
        document.getElementById("menuStudent").style.display = 'none';
        document.getElementById("menuInstructor").style.display = 'none';
    }
    else if(currentTypeLoggedIn === "ALL" || currentTypeLoggedIn === ""){
        
        /* Display Elements in Menu Bar */
        document.getElementById("menuBar_HOME").style.display = 'block';
        document.getElementById("menuBar_INSTRUCTOR").style.display = 'block';
        document.getElementById("menuBar_STUDENT").style.display = 'block';
        document.getElementById("menuBar_HISTORY").style.display = 'block';
        document.getElementById("menuBar_ADMINISTRATOR").style.display = 'block';
        document.getElementById("menuBar_LOGOUT").style.display = 'block';
        
        /* Display Main Elements in the middle of the Screen */
        //Display All - Dont take action.
    }
}