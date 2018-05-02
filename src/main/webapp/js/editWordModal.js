
/**
 * Group        : 06
 * Module       : ICP-2152 (JAVA Technologies)
 * Project      : Programming Group Project
 * University   : Bangor University (United Kingdom)
 */

// Get the modal
var editModal = document.getElementById('editWordModal');

// Get the button that opens the modal
var editButton = document.getElementById("editWordButton");

// Get the <span> element that closes the modal
var editSpan = document.getElementsByClassName("editWordClose")[0];

// When the user clicks on <span> (x), close the modal
editSpan.onclick = function() {
    editModal.style.display = "none";
}

//editButton.onclick = function() {
//    editModal.style.display = "block";
//}


//set where the welsh and the english words are going to be appear and the gender as well
//
function displayModal(index, wordID){
    
    editModal.style.display = "block";
    console.log("num = "+index+"+======> " + wordID);
    
    var welshWord = document.getElementById("WW"+index).innerHTML;
    var englishWord = document.getElementById("EW"+index).innerHTML;
    var gender = document.getElementById("G"+index).innerHTML;
    
    document.getElementById("hidenTextEdit").value = wordID;
    document.getElementById("wordWelshInput").value = welshWord;
    document.getElementById("wordEnglishInput").value = englishWord;
    //if a words gender is f then set the m radio checked
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