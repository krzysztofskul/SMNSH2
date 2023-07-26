package pl.krzysztofskul.sensit.smnsh.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.project.installation.Installation;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameFirst = "";

    private String nameLast = "";

    @OneToMany(mappedBy = "salesRep")
    private List<Project> projectListAsSalesRep = new ArrayList<Project>();

    @OneToMany(mappedBy = "projectManager")
    private List<Project> projectListAsProjectManager = new ArrayList<Project>();
    
    @OneToMany(mappedBy = "projectManagerAdd")
    private List<Project> projectListAsProjectManagerAdd = new ArrayList<Project>();
    
    /**
     * TODO
     * The list of project installations where user is set as additional project manager
     */
    @ManyToMany
    @JoinTable(name = "projectmanagers_installations", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "inastallation_id")})
    private List<Installation> projectManagersAdd= new ArrayList<Installation>();
    
    private UserBusinessPosition businessPosition;

    private String email;
    
    private String phoneNoMobile;

    private String password;

	/**
	 * CONSTRUCTOR
	 */
	public User() {
	}
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param nameFirst
	 * @param nameLast
	 * @param businessPosition
	 * @param email
	 * @param password
	 */
	public User(String nameFirst, String nameLast, UserBusinessPosition businessPosition, String email,
			String password) {
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.businessPosition = businessPosition;
		this.email = email;
		this.password = password;
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
	 * @return the nameFirst
	 */
	public String getNameFirst() {
		return nameFirst;
	}

	/**
	 * @param nameFirst the nameFirst to set
	 */
	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	/**
	 * @return the nameLast
	 */
	public String getNameLast() {
		return nameLast;
	}

	/**
	 * @param nameLast the nameLast to set
	 */
	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}
	
	/**
	 * @return the projectListAsSalesRep
	 */
	public List<Project> getProjectListAsSalesRep() {
		return projectListAsSalesRep;
	}

	/**
	 * @param projectListAsSalesRep the projectListAsSalesRep to set
	 */
	public void setProjectListAsSalesRep(List<Project> projectListAsSalesRep) {
		this.projectListAsSalesRep = projectListAsSalesRep;
	}
	
	/**
	 * @return the projectListAsProjectManager
	 */
	public List<Project> getProjectListAsProjectManager() {
		return projectListAsProjectManager;
	}

	/**
	 * @param projectListAsProjectManager the projectListAsProjectManager to set
	 */
	public void setProjectListAsProjectManager(List<Project> projectListAsProjectManager) {
		this.projectListAsProjectManager = projectListAsProjectManager;
	}

	/**
	 * @return the projectListAsProjectManagerAdd
	 */
	public List<Project> getProjectListAsProjectManagerAdd() {
		return projectListAsProjectManagerAdd;
	}

	/**
	 * @param projectListAsProjectManagerAdd the projectListAsProjectManagerAdd to set
	 */
	public void setProjectListAsProjectManagerAdd(List<Project> projectListAsProjectManagerAdd) {
		this.projectListAsProjectManagerAdd = projectListAsProjectManagerAdd;
	}

	/**
	 * @return the projectManagersAdd
	 */
	public List<Installation> getProjectManagersAdd() {
		return projectManagersAdd;
	}

	/**
	 * @param projectManagersAdd the projectManagersAdd to set
	 */
	public void setProjectManagersAdd(List<Installation> projectManagersAdd) {
		this.projectManagersAdd = projectManagersAdd;
	}

	/**
	 * @return the businessPosition
	 */
	public UserBusinessPosition getBusinessPosition() {
		return businessPosition;
	}

	/**
	 * @param businessPosition the businessPosition to set
	 */
	public void setBusinessPosition(UserBusinessPosition businessPosition) {
		this.businessPosition = businessPosition;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNoMobile
	 */
	public String getPhoneNoMobile() {
		return phoneNoMobile;
	}

	/**
	 * @param phoneNoMobile the phoneNoMobile to set
	 */
	public void setPhoneNoMobile(String phoneNoMobile) {
		this.phoneNoMobile = phoneNoMobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}