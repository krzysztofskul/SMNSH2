package pl.krzysztofskul.sensit.smnsh.company.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nameFirst;
	
	private String nameLast;
	
	private String gender;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Person() {
	}
	
	/**
	 * @param nameFirst
	 * @param nameLast
	 * @param gender
	 */
	public Person(String nameFirst, String nameLast, String gender) {
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.gender = gender;
	}



	/*
	 * GETTERS AND SETTERS
	 */
	
	
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
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
