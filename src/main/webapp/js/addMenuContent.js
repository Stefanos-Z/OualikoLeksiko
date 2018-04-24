var html = '<div class="menuBar">'; 
    html += '<ul id="listHolder">';
        html += '<li><a id="userType_HOME" class="link" href="/OualikoLeksiko/homePage.xhtml">Home</a></li>';
        html += '<li><a id="userType_INSTRUCTOR" class="link" href="WordsManagerServlet">Words Manager</a></li>';
        html += '<li><a id="userType_STUDENT" class="link" href="TakeTestServlet">Take a Test</a></li>';
        html += '<li><a id="userType_HISTORY" class="link" href="HistoryServlet">View History</a></li>';
        html += '<li><a id="userType_ADMINISTRATOR" class="link" href="AddUserServlet">Add Users</a></li>';

        html += '<li id="logoutButton"><a id="userType_LOGOUT" class="link" href="LoginServlet">Logout</a></li>';
    html += '</ul>';
html += '</div>';

$("div").append(html);