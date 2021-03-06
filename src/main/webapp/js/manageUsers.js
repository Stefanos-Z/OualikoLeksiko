/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
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
    
    //Adding the new user to the database server and also keep a log of the new update
    $("#addUserDB").click(function() {
        
        var uName = $("#userNameAdd").val();
        var pWord = $("#passwordAdd").val();
        var uEmail = $("#emailAdd").val();
        var uTupe = $('input[name=userTypeAdd]:checked').val();
        var data = {"username":uName,"password":pWord, "email":uEmail, "userType":uTupe};
        
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
    $("#editUserDB").click(function() {
        
        var newUserName = $("#userNameEdit").val();
        
        var oldUserName = $("#hidenTextEditUsername").val();
        var userType = $("#hidenTextEditUserType").val();
        var pWord = $("#passwordEdit").val();
        var uEmail = $("#emailEdit").val();
        console.log(newUserName);
        console.log(userType);
        console.log(pWord);
        console.log(uEmail);
        
        var data = {"newUsername":newUserName,"password":pWord, "email":uEmail, "oldUsername":oldUserName, "userType":userType};
        
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/EditUserServlet",
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
    
    
    $("#deleteUserDB").click(function() {
        
        var uName = $("#hidenTextDeleteUser").val();
        var data = {"username":uName};
        
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/MembersManagerServlet",
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





