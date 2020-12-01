package aed.hibernate.table;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class EquiposObservaciones implements Serializable{


	private static final long serialVersionUID = 1L;

@Id
@GenericGenerator(name="myForeign",strategy="foreign",
parameters= {@org.hibernate.annotations.Parameter(name="property",value="equipo")})
private int codEquipo;

@Column (columnDefinition="varchar(200)")
private String observaciones;


@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
@PrimaryKeyJoinColumn
private Equipos equipo;











public int getCodEquipo() {
	return codEquipo;
}

public void setCodEquipo(int codEquipo) {
	this.codEquipo = codEquipo;
}

public String getObservaciones() {
	return observaciones;
}

public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}
	




}
