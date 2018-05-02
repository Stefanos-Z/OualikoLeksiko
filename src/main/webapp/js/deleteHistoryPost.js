/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */


//delete the history from the table and also from the database...keep log
$(document).ready(function(){
    
    
    $("#deleteHistoryDB").click(function() {
        console.log("hello");
        var testID = $("#hidenTextDeleteHistory").val();
        var data = {"testID":""+testID};
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/HistoryServlet",
            data : data,
            dataType : 'text',
            success : function(response) {
                console.log("Success!");
            },
            error : function(e) {
                    alert("ERROR");
                    
            }
        });
        
        
        
    });
});