/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    
    // Get the modal
    var addModal = document.getElementById('addMemberModal');

    // Get the button that opens the modal
    var addButton = document.getElementById('addMemberButton');

    // Get the <span> element that closes the modal
    var addSpan = document.getElementsByClassName("addMemberClose")[0];

    addButton.onclick = function() {
        addModal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    addSpan.onclick = function() {
        addModal.style.display = "none";
    };

    window.onclick = function(event){
        if(event.target === addModal){
            addModal.style.display = "none";
        }
    };
    
    $("#addUserDB").click(function() {
        
        var uName = $("#userNameAdd").val();
        var pWord = $("#passwordAdd").val();
        var uEmail = $("#emailAdd").val();
        var uTupe = $('input[name=userTypeAdd]:checked').val();
        console.log(uName);
        console.log(pWord);
        console.log(uEmail);
        console.log(uTupe);
        var data = {"username":""+uName,"password":pWord, "email":uEmail, "userType":uTupe};
        
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/AddUserServlet",
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