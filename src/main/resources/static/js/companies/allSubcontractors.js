/*document.addEventListener("DOMContentLoaded", () => {
  //alert("js file test!");
});*/

$(document).ready(function() {
     //alert("js with jQuery file test!"); //ok

	/*
	* <a> tags with class=labelEnum
	*/
	var labelEnums = $(".labelEnum");
	
	/*
	labelEnums.on("click", function() {
		var attrId = $(this).attr('id');
		var companyId = attrId.split('-')[1];
		var labelEnumString = "LabelEnum."+attrId.split('Enum')[1].split('For')[0].toUpperCase();
		alert("companyId: "+companyId+"\nlabel: "+labelEnumString);//ok
		var data = {
			'labelEnumString': labelEnumString,
		}

		$.ajax({
			url: "/smnsh/companies/rest/"+companyId,
			type: "POST",
			data: {
				'labelEnumString': labelEnumString
			}
			
		}).done(function() {
				alert("success");
		}).fail(function() {
				alert("errror");
		});

		$.post(
			"/smnsh/companies/rest/"+companyId,
			{
				labelEnumString : labelEnumString
			},
			function(data, status){
				alert("Data: " + data + "\nStatus: " + status);
			}
		);
	}
	*/

});