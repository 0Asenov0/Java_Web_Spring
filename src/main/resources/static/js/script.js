const menu = document.querySelector('#mobile-menu');
const menuLinks = document.querySelector('.navbar_menu');
let mybutton = document.getElementById("myBtn");



menu.addEventListener('click',function(){
    menu.classList.toggle('is-active');
    menuLinks.classList.toggle('active');
})
// // When the user scrolls down 20px from the top of the document, show the button
// window.onscroll = function() {scrollFunction()};
//
// // function scrollFunction() {
// //     if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
// //         mybutton.style.display = "block";
// //     } else {
// //         mybutton.style.display = "none";
// //     }
// // }
// //
// // // When the user clicks on the button, scroll to the top of the document
// // function topFunction() {
// //     document.body.scrollTop = 0; // For Safari
// //     document.documentElement.scrollTop = 90; // For Chrome, Firefox, IE and Opera
// // }

