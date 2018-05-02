// Get the modal
var addMemberModal = document.getElementById('addMemberModal');

// Get the button that opens the modal
var addMemberButton = document.getElementById('addMemberButton');

// Get the <span> element that closes the modal
var addMemberSpan = document.getElementsByClassName("addMemberClose");

 // When the user clicks on <span> (x), close the modal
addMemberSpan.onclick = function() {
    addMemberModal.style.display = "none";
}

addMemberButton.onclick = function() {
    addMemberModal.style.display = "block";
}

function displayModal(){
    
    addMemberModal.style.display = "block";
    console.log("=====");
    /*
    var welshWord = document.getElementById("WW"+index).innerHTML;
    var englishWord = document.getElementById("EW"+index).innerHTML;
    var gender = document.getElementById("G"+index).innerHTML;
    
    
    document.getElementById("wordWelshInput").value = welshWord;
    document.getElementById("wordEnglishInput").value = englishWord;
    
    if(gender == "M"){
        document.getElementById("radio1").checked = true;
        document.getElementById("radio2").checked = false;
    }else if(gender == "F"){
        document.getElementById("radio1").checked = false;
        document.getElementById("radio2").checked = true;
    }*/

}

window.onclick = function(event){
    if(event.target == addMemberModal){
        addMemberModal.style.display = "none";
    }
}