package pl.krzysztofskul.sensit.smnsh.project.detailssls;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "detailssls")
public class DetialsSls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String code;
	
	private String companyInvestor;
	
	private String companyUser;

	/**
	 * Constructor
	 */
	public DetialsSls() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the companyInvestor
	 */
	public String getCompanyInvestor() {
		return companyInvestor;
	}

	/**
	 * @param companyInvestor the companyInvestor to set
	 */
	public void setCompanyInvestor(String companyInvestor) {
		this.companyInvestor = companyInvestor;
	}

	/**
	 * @return the companyUser
	 */
	public String getCompanyUser() {
		return companyUser;
	}

	/**
	 * @param companyUser the companyUser to set
	 */
	public void setCompanyUser(String companyUser) {
		this.companyUser = companyUser;
	}
	
	
	
}
