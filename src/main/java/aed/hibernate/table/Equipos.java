package aed.hibernate.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Equipos  implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int codEquipo;
@Column (columnDefinition="varchar(40)")
private String nomEquipo;
@Column(columnDefinition="varchar(60)")
private String localidad;
private int internacional;


@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="equipo")
private List<Contratos> contratos= new ArrayList<Contratos>();


@ManyToOne
@JoinColumn(name="codLiga")
private Ligas liga=new Ligas();

@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE},fetch=FetchType.LAZY)
@PrimaryKeyJoinColumn
private EquiposObservaciones equipo;





public EquiposObservaciones getObservaciones() {
	return equipo;
	
}

public String getCodLiga() {
	return this.liga.getCodLiga();
}

public void setCodLiga(String liga) {
	this.liga.setCodLiga(liga);
}

public int getCodEquipo() {
	return codEquipo;
}
public void setCodEquipo(int codEquipo) {
	this.codEquipo = codEquipo;
}
public String getNomEquipo() {
	return nomEquipo;
}
public void setNomEquipo(String nomEquipo) {
	this.nomEquipo = nomEquipo;
}

public String getLocalidad() {
	return localidad;
}
public void setLocalidad(String localidad) {
	this.localidad = localidad;
}
public int getInternacional() {
	return internacional;
}
public void setInternacional(int internacional) {
	this.internacional = internacional;
}





}
