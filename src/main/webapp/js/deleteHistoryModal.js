/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */


// Get the modal
var deleteModal = document.getElementById('deleteHistoryModal');

// Get the button that opens the modal
var deleteButton = document.getElementById("deleteHistoryButton");

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteHistoryClose")[0];

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

 //when the modal is appeard show it in block with the correct value and also keep a log
function displayDeleteHistoryModal(testID){
    deleteModal.style.display = "block";
    console.log(testID);
    document.getElementById("hidenTextDeleteHistory").value = testID;
}