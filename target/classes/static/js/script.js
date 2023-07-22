function dropdown() {
    document.querySelector('#pomenu').classList.toggle('hidden');
    document.querySelector('#arrowdown').classList.toggle('rotate-0');

}
var icon = document.getElementById("myIcon");

// Add a click event listener
icon.addEventListener("click", function() {
    // Display a message
    alert("You clicked the icon!");
});