// Get the modal
var deleteModal = document.getElementById('deleteMemberModal');

// Get the button that opens the modal
var deleteButton = document.getElementById("deleteMemberButton");

// Get the <span> element that closes the modal
var deleteSpan = document.getElementsByClassName("deleteMemberClose")[0];

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