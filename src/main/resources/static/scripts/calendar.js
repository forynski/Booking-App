// STICKY NAVBAR
// window.onscroll = function() {myFunction()};
//
// var navbar = document.getElementById("navbar");
// var sticky = navbar.offsetTop;
//
// function myFunction() {
//     if (window.pageYOffset >= sticky) {
//         navbar.classList.add("sticky")
//     } else {
//         navbar.classList.remove("sticky");
//     }
// }

// alert("I'm active");

$(function () {
    $('#checkin').datepicker();
});

$(function () {
    $('#checkout').datepicker();
});


    var date = new Date();
    $('#checkin').datepicker({
    format:'yyyy-mm-dd',
    todayBtn:true,
    autoclose:true,
    todayHighlight: true,
    startDate:date
}).on("changeDate", function(e) {
    var checkInDate = e.date, $checkOut = $("#checkout");
    checkInDate.setDate(checkInDate.getDate() + 1);
    $checkOut.datepicker("setStartDate", checkInDate);
    $checkOut.datepicker("setDate", checkInDate).focus();
});

    $("#checkout").datepicker({
    format: "yyyy-mm-dd",
    todayBtn: true,
    autoclose: true,
    todayHighlight: true,
});

