package pl.krzysztofskul.smnsh2.company.ContactDetails;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pl.krzysztofskul.smnsh2.company.Company;
import pl.krzysztofskul.smnsh2.company.ContactDetails.Address.Address;

@Entity
@Table(name = "contactdetails")
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	private String phoneNumberMain;
	
    @ElementCollection
    @CollectionTable(name = "contactdetails_phonename_phonenumber", 
      joinColumns = {@JoinColumn(name = "contactDetails_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "phone_name")
    @Column(name = "phone_number")
	private Map<String, String> phoneNumbers;

    private String emailMain;
    
    @ElementCollection
    @CollectionTable(name = "contactdetails_emailname_emailaddress", 
      joinColumns = {@JoinColumn(name = "contactDetails_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "email_name")
    @Column(name = "email_address")
	private Map<String, String> emailAdresses;
	
	private String website;
	
	@OneToOne(mappedBy = "contactDetails")
	private Company company;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public ContactDetails() {}
	
	public ContactDetails(Long id, 
			Address address, 
			String phoneNumberMain,
			Map<String, String> phoneNumbers,
			String emailMain,
			Map<String, String> emailAdresses,
			String website) {
		super();
		this.id = id;
		this.address = address;
		this.phoneNumberMain = phoneNumberMain;
		this.phoneNumbers = phoneNumbers;
		this.emailMain = emailMain;
		this.emailAdresses = emailAdresses;
		this.website = website;
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumberMain() {
		return phoneNumberMain;
	}

	public void setPhoneNumberMain(String phoneNumberMain) {
		this.phoneNumberMain = phoneNumberMain;
	}

	/**
	 * @return the phoneNumbers
	 */
	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers the phoneNumbers to set
	 */
	public void setPhoneNumbers(Map<String, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmailMain() {
		return emailMain;
	}

	public void setEmailMain(String emailMain) {
		this.emailMain = emailMain;
	}

	/**
	 * @return the emailAdresses
	 */
	public Map<String, String> getEmailAdresses() {
		return emailAdresses;
	}

	/**
	 * @param emailAdresses the emailAdresses to set
	 */
	public void setEmailAdresses(Map<String, String> emailAdresses) {
		this.emailAdresses = emailAdresses;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}	
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
}
