package pl.krzysztofskul.sensit.smnsh.project.detailssls;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetialsSls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String code;
	
	private String companyInvestor;
	
	private String companyUser;
	
}
