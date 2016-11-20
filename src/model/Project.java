package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProject;

	@Lob
	private String description;

	//bi-directional many-to-one association to Acquisition
	@OneToMany(mappedBy="project")
	private List<Acquisition> acquisitions;

	//bi-directional many-to-one association to Zonesofinterest
	@OneToMany(mappedBy="project")
	private List<Zonesofinterest> zonesofinterests;

	public Project() {
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Acquisition> getAcquisitions() {
		return this.acquisitions;
	}

	public void setAcquisitions(List<Acquisition> acquisitions) {
		this.acquisitions = acquisitions;
	}

	public Acquisition addAcquisition(Acquisition acquisition) {
		getAcquisitions().add(acquisition);
		acquisition.setProject(this);

		return acquisition;
	}

	public Acquisition removeAcquisition(Acquisition acquisition) {
		getAcquisitions().remove(acquisition);
		acquisition.setProject(null);

		return acquisition;
	}

	public List<Zonesofinterest> getZonesofinterests() {
		return this.zonesofinterests;
	}

	public void setZonesofinterests(List<Zonesofinterest> zonesofinterests) {
		this.zonesofinterests = zonesofinterests;
	}

	public Zonesofinterest addZonesofinterest(Zonesofinterest zonesofinterest) {
		getZonesofinterests().add(zonesofinterest);
		zonesofinterest.setProject(this);

		return zonesofinterest;
	}

	public Zonesofinterest removeZonesofinterest(Zonesofinterest zonesofinterest) {
		getZonesofinterests().remove(zonesofinterest);
		zonesofinterest.setProject(null);

		return zonesofinterest;
	}

}