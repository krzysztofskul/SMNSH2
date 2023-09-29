/**
 * 
 */

$(document).ready(function() {

	//alert("login js");
	
	let usernames = $("a.dropdown-item");
	let labelUsername = $("#username");
	
	
	usernames.each(function() {
		console.log($(this).text());
		$(this).on("click", function() {
			//alert($(this).text());
			labelUsername.val($(this).text());
		});
	});
	
});