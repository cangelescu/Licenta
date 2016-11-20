package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the zonesofinterest database table.
 * 
 */
@Entity
@NamedQuery(name="Zonesofinterest.findAll", query="SELECT z FROM Zonesofinterest z")
public class Zonesofinterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idZonesOfInterest;

	private int coordinates;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projectID")
	private Project project;

	public Zonesofinterest() {
	}

	public int getIdZonesOfInterest() {
		return this.idZonesOfInterest;
	}

	public void setIdZonesOfInterest(int idZonesOfInterest) {
		this.idZonesOfInterest = idZonesOfInterest;
	}

	public int getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(int coordinates) {
		this.coordinates = coordinates;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}