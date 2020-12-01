package aed.hibernate.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Ligas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	
@Id
@Column (columnDefinition="char(5)")
private String codLiga;
@Column (columnDefinition="varchar(50)")
private String nomLiga;

@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="liga")
private List<Equipos> equipos= new ArrayList<Equipos>();



public String getCodLiga() {
	return codLiga;
}
public void setCodLiga(String codLiga) {
	this.codLiga = codLiga;
}
public String getNomLiga() {
	return nomLiga;
}
public void setNomLiga(String nomLiga) {
	this.nomLiga = nomLiga;
}
	
	
}
