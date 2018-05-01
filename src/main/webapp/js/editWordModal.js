// Get the modal
var editModal = document.getElementById('editModal');

// Get the button that opens the modal
var editButton = document.getElementById("editWordButton");

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editClose")[0];

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