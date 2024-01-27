/**
 * 
 */

$(document).ready(function() {
	
	//alert("test project and milestones page"); //ok
	
		let btnStatus = $(".btn-status");
		let btnDelete = $(".btn-delete");
		let projectId = $("#projectId").text();
	
		btnStatus.each(function() {
			$(this).removeAttr("href");
		});

		btnStatus.on("click", function() {
			//alert("projectId: "+projectId+"\nmilestoneId: "+$(this).attr("id").split("-")[1]+"\nbtn-status id attr.: "+$(this).attr("id").split("-")[2]);
			$.ajax({
			    type: 'POST',
			    url: '/smnsh2/restapi/projects/'+projectId+'/milestones/'+$(this).attr("id").split("-")[1]+'/set-status',
			    data: { 
			        'status': $(this).attr("id").split("-")[2]
			    }
			}).always(function() {
				location.reload();
			});

		});
	
		btnDelete.on("click", function() {
			//alert("projectId: "+projectId+"\nmilestoneId: "+$(this).attr("id").split("-")[1]+"\nbtn-delete id attr.: "+$(this).attr("id").split("-")[2]);
			$.ajax({
				type: 'DELETE',
				url: '/smnsh2/restapi/milestones/'+$(this).attr("id").split("-")[1],
			}).always(function() {
				location.reload();
			});
			
			;
		});
	
});