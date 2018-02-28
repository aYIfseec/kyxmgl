// JavaScript Document
function navDispaly() {
	var url = window.location.href;
	//alert(url);
	var arr = $('.top-ul').children();
	for (var i = 0; i < arr.length; i++) {
		//alert(arr[i].firstChild.href);
		if (url.indexOf(arr[i].firstChild.href) >= 0) {
			arr[i].firstChild.className = 'active';
		}
	}
}

$(document).ready(function() {
	// Store variables
	var accordion_head = $('.accordion > li > a'),
		accordion_body = $('.accordion li > .sub-menu');
	// Open the first tab on load
	accordion_head.first().addClass('active').next().slideDown('normal');
	// Click function
	accordion_head.on('click', function(event) {
		// Disable header links
		event.preventDefault();
		// Show and hide the tabs on click
		if ($(this).attr('class') != 'active'){
			accordion_body.slideUp('normal');
			$(this).next().stop(true,true).slideToggle('normal');
			accordion_head.removeClass('active');
			$(this).addClass('active');
		}
	});
});