/**
 * 
 */

function editStatusFunc() {
		//alert("test project and milestones page"); //ok
	
		let btnStatus = $(".btn-status");
		let btnDelete = $(".btn-delete");
		let projectId = $("#projectId").text();
		let inputNamePl = $(".inputNamePl");
		let inputNameEn = $(".inputNameEn");
		let inputDeadline = $(".inputDeadline")

			//remove href attribute
			btnStatus.each(function() {
				$(this).removeAttr("href");
			});
			//add event listener for click on btnStatus
			btnStatus.on("click", function() {
				//alert("projectId: "+projectId+"\nmilestoneId: "+$(this).attr("id").split("-")[1]+"\nbtn-status id attr.: "+$(this).attr("id").split("-")[2]);
				$.ajax({
				    type: 'POST',
				    url: '/smnsh/restapi/projects/'+projectId+'/milestones/'+$(this).attr("id").split("-")[1]+'/set-status',
				    data: { 
				        'status': $(this).attr("id").split("-")[2]
				    }
				}).always(function() {
					location.reload();
				});
	
			});
			//add event listener for click on btnDelete
			btnDelete.on("click", function() {
				//alert("projectId: "+projectId+"\nmilestoneId: "+$(this).attr("id").split("-")[1]+"\nbtn-delete id attr.: "+$(this).attr("id").split("-")[2]);
				$.ajax({
					type: 'DELETE',
					url: '/smnsh/restapi/milestones/'+$(this).attr("id").split("-")[1],
				}).always(function() {
					location.reload();
				});
				
				;
			});
			//TODO: add event listener for input deadline 
			//...
			
			//edd event listener for input namePl/nameEn
			inputNamePl.on("blur", function() {
				//console.log($(this).val());
				//console.log("milestone id: "+$(this).data("milestoneid"));
				$.ajax({
				    type: 'PUT',
				    url: '/smnsh/restapi/milestones/'+$(this).data("milestoneid"),
				    data: { 
				        'namePl': $(this).val()
				    }
				}).always(function() {
					location.reload();
				});
			})
			inputNameEn.on("blur", function() {
				$.ajax({
				    type: 'PUT',
				    url: '/smnsh/restapi/milestones/'+$(this).data("milestoneid"),
				    data: { 
				        'nameEn': $(this).val()
				    }
				}).always(function() {
					location.reload();
				});
			})
			inputDeadline.on("change", function() {
				//console.log($(this).val());
				//console.log("milestone id: "+$(this).data("milestoneid"));
				$.ajax({
				    type: 'PUT',
				    url: '/smnsh/restapi/milestones/'+$(this).data("milestoneid"),
				    data: { 
				        'deadline': $(this).val()
				    }
				}).always(function() {
					location.reload();
				});
			})
}

function editNewMilestoneFunc() {
	
	//buttons for a new milestone's status
	let buttons = Array.from(document.getElementsByTagName('button')).filter(tag => tag.dataset.status);
	let inputStatus = document.getElementById('inputStatus');
	
	for(const btn of buttons) {
		btn.addEventListener("click", function() {
			inputStatus.value = btn.dataset.status;
			for (const btn of buttons) {
				btn.classList.remove('btn-outline-success');
			}
			btn.classList.add('btn-outline-success');
			
		});		
	}
	

	
}

function editExistingMilestoneFunc() {
	
}

$(document).ready(function() {
	
	let paramEdit = document.getElementById('paramEdit').innerText;
	//console.log("edit: "+paramEdit);
	
	if (paramEdit === 'true') {		
		editNewMilestoneFunc();	
	} else {
	editStatusFunc();
	}

	
});