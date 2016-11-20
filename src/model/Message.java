package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idMessage;

	private String decodedMessage;

	private String message;

	//bi-directional many-to-one association to Acquisition
	@ManyToOne
	@JoinColumn(name="acquisitionID")
	private Acquisition acquisition;

	public Message() {
	}

	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getDecodedMessage() {
		return this.decodedMessage;
	}

	public void setDecodedMessage(String decodedMessage) {
		this.decodedMessage = decodedMessage;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Acquisition getAcquisition() {
		return this.acquisition;
	}

	public void setAcquisition(Acquisition acquisition) {
		this.acquisition = acquisition;
	}

}