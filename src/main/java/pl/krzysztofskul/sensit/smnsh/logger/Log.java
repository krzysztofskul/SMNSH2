package pl.krzysztofskul.sensit.smnsh.logger;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import pl.krzysztofskul.sensit.smnsh.project.Project;
import pl.krzysztofskul.sensit.smnsh.user.User;

@Entity
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User subject;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Project project;
	
	private LogTypeEnum action;
	
	private LocalDateTime time ;

	public Log() {}

	public Log(User subject, Project project, LogTypeEnum action, LocalDateTime time) {
		this.subject = subject;
		this.project = project;
		this.action = action;
		this.time = time;
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
	 * @return the subject
	 */
	public User getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(User subject) {
		this.subject = subject;
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
	 * @return the action
	 */
	public LogTypeEnum getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(LogTypeEnum action) {
		this.action = action;
	}

	/**
	 * @return the time
	 */
	public LocalDateTime getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
