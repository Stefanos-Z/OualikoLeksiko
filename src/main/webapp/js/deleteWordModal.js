// Get the modal
var deleteModal = document.getElementById('deleteModal');

// Get the button that opens the modal
var deleteButton = document.getElementById("deleteWordButton");

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteClose")[0];

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

