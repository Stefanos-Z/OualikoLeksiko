// Get the modal
var modal = document.getElementById('addMemberModal');

// Get the button that opens the modal
var modalButton = document.getElementById('myButton');

// Get the <span> element that closes the modal
var closeButton = document.getElementsByClassName('close');

modalButton.addEventListener("click", openModal);

function openModal(){
    
    console.log(123);
}
/*
// When the user clicks on the button, open the modal 
modalButton.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
closeButton.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
 */