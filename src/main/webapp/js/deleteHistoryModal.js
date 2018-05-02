// Get the modal
var deleteModal = document.getElementById('deleteHistoryModal');

// Get the button that opens the modal
var deleteButton = document.getElementsByClassName("deleteHistoryButton");

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteHistoryClose")[0];

// When the user clicks the button, open the modal 
deleteButton.onclick = function() {
    deleteModal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
deleteSpan.onclick = function() {
    deleteModal.style.display = "none";
}

window.onclick = function(event){
    if(event.target == deleteModal){
        deleteModal.style.display = "none";
    }
}

