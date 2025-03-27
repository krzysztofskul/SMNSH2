package pl.krzysztofskul.smnsh2.kpds;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import pl.krzysztofskul.smnsh2.project.Project;

@Entity
public class Kpds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//test
	private String testDescription;
	
	@OneToOne
    @JoinColumn(name = "project_id")
	private Project project;
	
	private int warranty;
	
	private LocalDateTime dateTimeGenerated;

	/*
	 * constr.
	 */
	
	public Kpds() {
		super();
		this.dateTimeGenerated = LocalDateTime.now();
	}

	public Kpds(Project project) {
		super();
		this.dateTimeGenerated = LocalDateTime.now();
		this.project = project;
		
	}
	
	public Kpds(Project project, int warranty) {
		super();
		this.dateTimeGenerated = LocalDateTime.now();
		this.project = project;
		this.warranty = warranty;
		
	}

	/*
	 * getters and setters
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public LocalDateTime getDateTimeGenerated() {
		return dateTimeGenerated;
	}

	public void setDateTimeGenerated(LocalDateTime dateTimeGenerated) {
		this.dateTimeGenerated = dateTimeGenerated;
	}

	
}
