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

function displayModal(id){
    editModal.style.display = "block";
    console.log("num = "+id);
    var welshWord = document.getElementById("WW"+id).innerHTML;
    var englishWord = document.getElementById("EW"+id).innerHTML;
    var gender = document.getElementById("G"+id).innerHTML;
    
    console.log(welshWord);
    console.log(englishWord);
    console.log(gender);

    document.getElementById("wordWelshInput").value = welshWord;
    document.getElementById("wordEnglishInput").value = englishWord;
    //document.getElementById("wordWelsh").innerHTML = 'something something';
    
    //document.getElementById('wordEnglish').setAttribute("value", englishWord);

}

window.onclick = function(event){
    if(event.target == editModal){
        editModal.style.display = "none";
    }
}
