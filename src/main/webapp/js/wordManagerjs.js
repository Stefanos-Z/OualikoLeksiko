/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */


    
$(document).ready(function(){
    
    console.log('hello');
    // Get the modal
    var addModal = document.getElementById('addWordModal');

    // Get the button that opens the modal
    var addButton = document.getElementById("addWordButton");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("addWordClose")[0];

    // When the user clicks the button, open the modal 
    addButton.onclick = function() {
        addModal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        addModal.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target === addModal) {
            addModal.style.display = "none";
        }
    };
    
     //When deleting a world then update the table and the database
    
    $("#deleteWordDB").click(function() {
        var wordID = $("#hidenTextDelete").val();
        var data = {"wordId":""+wordID};
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/WordsManagerServlet",
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
    
    
    
    //When edit a world then update the table and the database
    
    $("#editWordDB").click(function() {
        var wordID = $("#hidenTextEdit").val();
        var wWord = $("#wordWelshInput").val();
        var eWord = $("#wordEnglishInput").val();
        var gender = $('input[name=genderForEdit]:checked').val();
        console.log(wordID);
        console.log(wWord);
        console.log(eWord);
        console.log(gender);
        var data = {"wordId":""+wordID,"welshWord":wWord, "englishWord":eWord, "gender":gender};
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/EditWordServlet",
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
    
    
    
    
    
    
    
    
    
    
     //When adding a world then update the table and the database
    
    
    
    
    $("#AddWordToDB").click(function() {
        var wWord = $("#wordWelshAdd").val();
        var eWord = $("#wordEnglishAdd").val();
        var gender = $('input[name=genderForAdd]:checked').val();
        console.log(wWord);
        
        var data = {"welshWord":wWord, "englishWord":eWord, "gender":gender};
        $.ajax({
            type : 'POST',
            contentType : "application/x-www-form-urlencoded",
            url : "/Welsh_tests/AddWordServlet",
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
