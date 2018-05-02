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

deleteSpan.onclick = function() {
    deleteModal.style.display = "none";
}

window.onclick = function(event){
    if(event.target == deleteModal){
        deleteModal.style.display = "none";
    }
}

function displayDeleteModal(wordId){
    deleteModal.style.display = "block";
    document.getElementById("hidenTextDelete").value = wordId;
}

