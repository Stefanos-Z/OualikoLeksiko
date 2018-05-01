// Get the modal
var editModal = document.getElementById('editWordModal');

// Get the button that opens the modal
var editButton = document.getElementById("editWordButton");

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editClose")[0];

// When the user clicks on <span> (x), close the modal
function hideModalOnExit() {
    editModal.style.display = "none";
}

function displayModal(index, wordID){
    editModal.style.display = "block";
    console.log("num = "+index);
    var welshWord = document.getElementById("WW"+index).innerHTML;
    var englishWord = document.getElementById("EW"+index).innerHTML;
    var gender = document.getElementById("G"+index).innerHTML;
    
    console.log("ID EQUALS +======> " + wordID);
    
    document.getElementById("wordWelshInput").value = welshWord;
    document.getElementById("wordEnglishInput").value = englishWord;
    
    if(gender == "M"){
        document.getElementById("radio1").checked = true;
        document.getElementById("radio2").checked = false;
    }else if(gender == "F"){
        document.getElementById("radio1").checked = false;
        document.getElementById("radio2").checked = true;
    }

}

window.onclick = function(event){
    if(event.target == editModal){
        editModal.style.display = "none";
    }
}