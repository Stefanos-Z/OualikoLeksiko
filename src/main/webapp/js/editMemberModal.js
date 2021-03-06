/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */


// Get the modal
var editModal = document.getElementById('editMemberModal');

// Get the button that opens the modal
var editButton = document.getElementById('editMemberButton');

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editMemberClose")[0];

// When the user clicks the button, open the modal 
//editButton.onclick = function() {
//    editModal.style.display = "block";
//}

// When the user clicks on <span> (x), close the modal
editSpan.onclick = function() {
    editModal.style.display = "none";
}
//do not close the modal if the window is being clicked
window.onclick = function(event){
    if(event.target == editModal){
        editModal.style.display = "none";
    }
}

//display the user's information on the corresponding place when the edit user appear
function displayEditUserModal(index){
    editModal.style.display = "block";
    var userName = document.getElementById("uName"+index).innerHTML;
    var pWord = document.getElementById("pWord"+index).innerHTML;
    var email = document.getElementById("uEmail"+index).innerHTML;
    var userType = document.getElementById("uType"+index).innerHTML;
  
    document.getElementById("hidenTextEditUserType").value = userType;
    document.getElementById("hidenTextEditUsername").value = userName;
    document.getElementById("userNameEdit").value = userName;
    document.getElementById("passwordEdit").value = pWord;
    document.getElementById("emailEdit").value = email;


}
