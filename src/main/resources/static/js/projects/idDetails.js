/**
 * 
 */

$(document).ready(function() {
	
	//alert("test project details page"); //ok
	
		let btnStatus = $(".btn-status");
		let projectId = $("#projectId").text();
	
		btnStatus.on("click", function() {
			//alert("projectId: "+projectId+"\nbtn-status id attr.: "+$(this).attr("id"));
			$.ajax({
			    type: 'POST',
			    url: '/smnsh2/restapi/projects/'+projectId,
			    data: { 
			        'btn-status': $(this).attr("id") 
			    }
			}).always(function() {
				location.reload();
			});

		});
	
		
	
});