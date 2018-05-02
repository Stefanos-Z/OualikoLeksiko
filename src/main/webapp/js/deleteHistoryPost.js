/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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