/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */

// Get the modal
var deleteModal = document.getElementById('deleteMemberModal');

// Get the button that opens the modal
var deleteButton = document.getElementById('deleteMemberButton');

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteMemberClose")[0];


// When the user clicks on <span> (x), close the modal
deleteSpan.onclick = function() {
    deleteModal.style.display = "none";
}
//do not close the modal if the window is being clicked
window.onclick = function(event){
    if(event.target == deleteModal){
        deleteModal.style.display = "none";
    }
}
//when you going to delete user display the modal with the name and his info
function displayUserDeleteModal(index){
    deleteModal.style.display = "block";
    var userName = document.getElementById("uName"+index).innerHTML;
    document.getElementById("hidenTextDeleteUser").value = userName;
}