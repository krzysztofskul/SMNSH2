/**
 * 
 */

$(document).ready(function() {
	
	//alert("test project details page"); //ok
	
		let btnStatus = $(".btn-status");
		let projectId = $("#projectId").text();
		
		btnStatus.each(function() {
			$(this).removeAttr("href");
		});
	
		btnStatus.on("click", function() {
			//alert("projectId: "+projectId+"\nbtn-status id attr.: "+$(this).attr("id"));
			$.ajax({
			    type: 'PUT',
			    url: '/smnsh/restapi/projects/'+projectId,
			    data: { 
			        'btn-status': $(this).attr("id") 
			    }
			}).always(function() {
				location.reload();
			});

		});
	
		
	
});