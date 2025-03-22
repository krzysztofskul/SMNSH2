package pl.krzysztofskul.smnsh2.project.device3rd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.smnsh2.project.Project;

@Entity
public class Device3rd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    
    private String categoryNamePl;
    private String categoryNameEn;
    
    private String manufacturer;
    
    private String modelName;
    
    private String serialNo;
    
    private int warranty;

	/**
	 * Constructor
	 */
	public Device3rd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * @param project
	 */
	public Device3rd(Project project) {
		super();
		this.project = project;
	}
	
	/**
	 * Constructor
	 * @param category
	 * @param manufacturer
	 * @param modelName
	 * @param serialNo
	 * @param warranty
	 */
	public Device3rd(String categoryNamePl, String categoryNameEn, String manufacturer, String modelName, String serialNo, int warranty) {
		super();
		this.categoryNamePl = categoryNamePl;
		this.categoryNameEn = categoryNameEn;
		this.manufacturer = manufacturer;
		this.modelName = modelName;
		this.serialNo = serialNo;
		this.warranty = warranty;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getCategoryNamePl() {
		return categoryNamePl;
	}

	public void setCategoryNamePl(String categoryNamePl) {
		this.categoryNamePl = categoryNamePl;
	}

	public String getCategoryNameEn() {
		return categoryNameEn;
	}

	public void setCategoryNameEn(String categoryNameEn) {
		this.categoryNameEn = categoryNameEn;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
    
}
