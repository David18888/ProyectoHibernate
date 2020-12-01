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
public class Futbolistas implements Serializable{

	private static final long serialVersionUID = 1L;
@Id	
@Column (columnDefinition="char(9)")
private String codDNIoNIE;
@Column (columnDefinition="varchar(50)")
private String nombre;
@Column (columnDefinition="varchar(40)")
private String nacionalidad;



@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="futbolista")
private List<Contratos> contratos= new ArrayList<Contratos>();



public String getCodDNIoNIE() {
	return codDNIoNIE;
}

public void setCodDNIoNIE(String codDNIoNIE) {
	this.codDNIoNIE = codDNIoNIE;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getNacionalidad() {
	return nacionalidad;
}
public void setNacionalidad(String nacionalidad) {
	this.nacionalidad = nacionalidad;
}
	




	
	
}
