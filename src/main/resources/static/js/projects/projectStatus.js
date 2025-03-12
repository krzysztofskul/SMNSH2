/**
 * Module which is used to set a functionality of project status buttons when edit page is active
 */

const projectStatus = () => {
	
		const inputIdStatus = document.getElementById('inputStatus');
		/*if (inputIdStatus) {
			inputIdStatus.hidden = false;
		}*/
		
		//console.log(inputIdStatus);
		
		const buttons = Array.from(document.getElementsByTagName('button')).filter(button => button.type == 'button');
		//const buttons = document.getElementsByTagName('button');
		//console.log(buttons);	
		
		for (const btn of buttons) {
			if (btn.id.toString().includes("btn-")) {
				//console.log(btn.id);
				if (btn.disapbled = "disdabled") {
					btn.removeAttribute("disabled");
				}
				btn.addEventListener("click", () => {
					//alert(btn.dataset.status);
					inputIdStatus.value = btn.dataset.status;
					
					for (const btn of buttons) {
						btn.classList.remove('btn-outline-success');
						btn.classList.remove('btn-success');
						console.log(btn);
					}				
					
					btn.classList.remove('btn-outline-dark');		
					btn.classList.add('btn-success');
					
				})
			}
			
		}
	
}

function x() {
	
}

export default projectStatus;
 
 