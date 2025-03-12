/**
 * 
 */

import projectStatus from "./projectStatus.js";

$(document).ready(function() {
//addEventListener("DOMContentLoaded", (event) => {

	//alert("test project details page"); //ok
	
		let btnStatus = $(".btn-status");
		let projectId = $("#projectId").text();
		
		console.log(document.getElementById('paramEdit'));
		console.log(document.getElementById('paramEdit').innerText);
		if (!document.getElementById('paramEdit') || document.getElementById('paramEdit').innerText === 'false' || document.getElementById('paramEdit').innerText === '') {
			btnStatus.on("click", function() {
				//alert("projectId: "+projectId+"\nbtn-status id attr.: "+$(this).attr("id"));
				$.ajax({
				    type: 'PUT',
				    url: '/smnsh2/restapi/projects/'+projectId,
				    data: { 
				        'btn-status': $(this).attr("id") 
				    }
				}).always(function() {
					location.reload();
				});
	
			});		
		} else {
			projectStatus();			
		}
	
});