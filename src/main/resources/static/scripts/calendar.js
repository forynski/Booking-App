// $( function() {
//     $( "#checkin" ).datepicker({ minDate: "now" });
// } );
//
// $( function() {
//     $( "#checkout" ).datepicker({ minDate: 1 });
// } );
//
// $('.input-group.date').datepicker({
//     format: 'yyyy-mm-dd',
//     autoclose: true,
//     todayHighlight: true
// });

//TODO: check scripts

// STICKY NAVBAR
window.onscroll = function() {myFunction()};

var navbar = document.getElementById("navbar");
var sticky = navbar.offsetTop;

function myFunction() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}
