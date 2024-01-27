package pl.krzysztofskul.smnsh2.project.remark;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import pl.krzysztofskul.smnsh2.project.Project;
import pl.krzysztofskul.smnsh2.user.User;

@Entity
public class Remark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User author;
	
	private LocalDateTime dateTimeOfCreation;
	
	@Column(length = 1024)
	private String text;
	
	@ManyToOne
	private Project project;

	
	
	/**
	 * CONSTRUCTOR
	 */
	public Remark() {
	}

	/**
	 * CONSTRUCTOR
	 */
	public Remark(User author, LocalDateTime dateTimeOfCreation, String text) {
		this.author = author;
		this.dateTimeOfCreation = dateTimeOfCreation;
		this.text = text;
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
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the dateTimeOfCreation
	 */
	public LocalDateTime getDateTimeOfCreation() {
		return dateTimeOfCreation;
	}

	/**
	 * @param dateTimeOfCreation the dateTimeOfCreation to set
	 */
	public void setDateTimeOfCreation(LocalDateTime dateTimeOfCreation) {
		this.dateTimeOfCreation = dateTimeOfCreation;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	
}
