// Get the modal
var editModal = document.getElementById('editMemberModal');

// Get the button that opens the modal
var editButton = document.getElementById('editMemberButton');

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editMemberClose")[0];

// When the user clicks the button, open the modal 
editButton.onclick = function() {
    editModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
editSpan.onclick = function() {
    editModal.style.display = "none";
}

window.onclick = function(event){
    if(event.target == editModal){
        editModal.style.display = "none";
    }
}


function displayEditUserModal(index){
    editModal.style.display = "block";
    var userName = document.getElementById("uName"+index).innerHTML;
    var pWord = document.getElementById("pWord"+index).innerHTML;
    var email = document.getElementById("uEmail"+index).innerHTML;
//    editModal.style.display = "block";
//    console.log("num = "+index+"+======> " + wordID);
//    
//    var welshWord = document.getElementById("WW"+index).innerHTML;
//    var englishWord = document.getElementById("EW"+index).innerHTML;
//    var gender = document.getElementById("G"+index).innerHTML;
  
    document.getElementById("userNameEdit").value = userName;
    document.getElementById("passwordEdit").value = pWord;
    document.getElementById("emailEdit").value = email;
//    document.getElementById("hidenTextEdit").value = wordID;
//    document.getElementById("wordWelshInput").value = welshWord;
//    document.getElementById("wordEnglishInput").value = englishWord;
//    
//    if(gender == "M"){
//        document.getElementById("radio1").checked = true;
//        document.getElementById("radio2").checked = false;
//    }else if(gender == "F"){
//        document.getElementById("radio1").checked = false;
//        document.getElementById("radio2").checked = true;
//    }

}
