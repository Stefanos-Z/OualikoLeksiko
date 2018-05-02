/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    console.log('hello');
    
    
    $("#AddWordToDB").click(function() {
        var wWord = $("#wordWelshAdd").val();
        console.log(wWord);
        
        var data = {};
        $.ajax({
            type : "POST",
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_test/AddWordServlet",
            data : word,
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
