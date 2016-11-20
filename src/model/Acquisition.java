package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the acquisition database table.
 * 
 */
@Entity
@NamedQuery(name="Acquisition.findAll", query="SELECT a FROM Acquisition a")
public class Acquisition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAcquisition;

	@Lob
	private String description;

	private String fileInputSettings;

	private String filename;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectID")
	private Project project;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="acquisition")
	private List<Message> messages;

	public Acquisition() {
	}

	public int getIdAcquisition() {
		return this.idAcquisition;
	}

	public void setIdAcquisition(int idAcquisition) {
		this.idAcquisition = idAcquisition;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileInputSettings() {
		return this.fileInputSettings;
	}

	public void setFileInputSettings(String fileInputSettings) {
		this.fileInputSettings = fileInputSettings;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setAcquisition(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setAcquisition(null);

		return message;
	}

}