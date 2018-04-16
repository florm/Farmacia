package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Farmacia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String telefono;
	private String diaDeTurno;
	@OneToOne
	private Punto geoLocalizacion;
	@OneToOne
	private Direccion direccion;
	

	//Constructores
	public Farmacia(String nombre, String telefono, String diaDeTurno) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.diaDeTurno = diaDeTurno;
	}
	
	public Farmacia() {
		
	}
	
	//Getters y Setters
	public Punto getGeoLocalizacion() {
		return geoLocalizacion;
	}
	public void setGeoLocalizacion(Punto geoLocalizacion) {
		this.geoLocalizacion = geoLocalizacion;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDiaDeTurno() {
		return diaDeTurno;
	}
	public void setDiaDeTurno(String diaDeTurno) {
		this.diaDeTurno = diaDeTurno;
	}
}
