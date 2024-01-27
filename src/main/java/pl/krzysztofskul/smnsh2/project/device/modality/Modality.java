package pl.krzysztofskul.smnsh2.project.device.modality;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Modality {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	private String name;
	
	private String nameTrade;
	
	private Modality() {
		super();
	}

	public Modality(String code, String name, String nameTrade) {
		super();
		this.code = code;
		this.name = name;
		this.nameTrade = nameTrade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameTrade() {
		return nameTrade;
	}

	public void setNameTrade(String nameTrade) {
		this.nameTrade = nameTrade;
	}
	
}
