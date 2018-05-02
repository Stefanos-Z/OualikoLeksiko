/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */

// Get the modal
var deleteModal = document.getElementById('deleteWordModal');

// Get the button that opens the modal
var deleteButton = document.getElementById('deleteWordButton');

var deleteCancelButton = document.getElementById('deleteModal-cancel-button');

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteWordClose")[0];

// When the user clicks the button, open the modal 
//deleteButton.onclick = function() {
//    deleteModal.style.display = "block";
//}

//// When the user clicks on <span> (x), close the modal
//function hideModalOnExit() {
//    deleteModal.style.display = "none";
//}

deleteCancelButton.onclick = function() {
    deleteModal.style.display = "none";
}
//when click on the delete button do not close the modal
deleteSpan.onclick = function() {
    deleteModal.style.display = "none";
}
//do not close the modal if the window is being clicked
window.onclick = function(event){
    if(event.target == deleteModal){
        deleteModal.style.display = "none";
    }
}
//when the modal is appeard show it in block with the correct value
function displayDeleteModal(wordId){
    deleteModal.style.display = "block";
    document.getElementById("hidenTextDelete").value = wordId;
}

