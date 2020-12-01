package aed.hibernate.table;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contratos implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int codContrato;
@Column(columnDefinition="date")
private String fechaInicio;
@Column (columnDefinition="date")
private String  fechaFin;
private int precioAnual;
private int precioRescision;

@ManyToOne
@JoinColumn(name="codDNIoNIE")
private Futbolistas futbolista=new Futbolistas();

@ManyToOne
@JoinColumn(name="codEquipo")
private Equipos equipo= new Equipos();



public String getCodDNIoNIE() {
	return this.futbolista.getCodDNIoNIE();
}

public void setCodDNIoNIE(String futbolista) {
	this.futbolista.setCodDNIoNIE(futbolista);
}


public int getCodEquipo() {
	return this.equipo.getCodEquipo();
}

public void setCodEquipo(int codEquipo) {
	this.equipo.setCodEquipo(codEquipo);
}



public String getFechaInicio() {
	return fechaInicio.toString();
}
public void setFechaInicio(String fechaInicio) {
	this.fechaInicio=fechaInicio;


}
public String getFechaFin() {
	return fechaFin;
}
public void setFechaFin(String fechaFin) {
	this.fechaFin=fechaFin;
}
public int getCodContrato() {
	return codContrato;
}
public void setCodContrato(int codContrato) {
	this.codContrato = codContrato;
}


public int getPrecioAnual() {
	return precioAnual;
}
public void setPrecioAnual(int precioAnual) {
	this.precioAnual = precioAnual;
}
public int getPrecioRescision() {
	return precioRescision;
}
public void setPrecioRescision(int precioRescision) {
	this.precioRescision = precioRescision;
}




	
	
	

}
