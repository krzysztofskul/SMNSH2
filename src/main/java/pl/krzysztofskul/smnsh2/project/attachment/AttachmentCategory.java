package pl.krzysztofskul.smnsh2.project.attachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AttachmentCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String attCategoryCode;
	
	private String attCategoryNamePL;
	
	private String attCategoryNameEn;
	
	/*
	 * CONSTR.
	 */
	
	 public AttachmentCategory() {
	
	}
	
	public AttachmentCategory(String attCategoryCode, String attCategoryNamePL, String attCategoryNameEn) {
		super();
		this.attCategoryCode = attCategoryCode;
		this.attCategoryNamePL = attCategoryNamePL;
		this.attCategoryNameEn = attCategoryNameEn;
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

	public String getAttCategoryCode() {
		return attCategoryCode;
	}

	public void setAttCategoryCode(String attCategoryCode) {
		this.attCategoryCode = attCategoryCode;
	}

	public String getAttCategoryNamePL() {
		return attCategoryNamePL;
	}

	public void setAttCategoryNamePL(String attCategoryNamePL) {
		this.attCategoryNamePL = attCategoryNamePL;
	}

	public String getAttCategoryNameEn() {
		return attCategoryNameEn;
	}

	public void setAttCategoryNameEn(String attCategoryNameEn) {
		this.attCategoryNameEn = attCategoryNameEn;
	}
	
	
}
