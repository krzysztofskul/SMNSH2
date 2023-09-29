package pl.krzysztofskul.sensit.smnsh.project.attachment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import pl.krzysztofskul.sensit.smnsh.filestorage.File;
import pl.krzysztofskul.sensit.smnsh.project.Project;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="attachmentcategory_id", referencedColumnName = "id")
    private AttachmentCategory attachmentCategory;
    
    @ManyToOne
    private Project project;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="file_id", referencedColumnName = "id")
    private File file;

	/**
	 * CONSTRUCTOR
	 */
	public Attachment() {
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
	 * @return the attachmentCategory
	 */
	public AttachmentCategory getAttachmentCategory() {
		return attachmentCategory;
	}

	/**
	 * @param attachmentCategory the attachmentCategory to set
	 */
	public void setAttachmentCategory(AttachmentCategory attachmentCategory) {
		this.attachmentCategory = attachmentCategory;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	
    
}
