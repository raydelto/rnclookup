package org.raydelto.rncempresa.entities;

public class RncData {
	private String actividadPrincipal;
	private String direccionCalle;
	private String direccionNumero;
	private String direccionSector;
	private String estado;
	private String fechaConstitucion;
	private String nombreComercial;
	private String numeroRnc;
	private String razonSocial;
	private String regimenPago;
	private String telefono;
	public String getActividadPrincipal() {
		return actividadPrincipal;
	}
	public void setActividadPrincipal(String actividadPrincipal) {
		this.actividadPrincipal = actividadPrincipal;
	}
	public String getDireccionCalle() {
		return direccionCalle;
	}
	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}
	public String getDireccionNumero() {
		return direccionNumero;
	}
	public void setDireccionNumero(String direccionNumero) {
		this.direccionNumero = direccionNumero;
	}
	public String getDireccionSector() {
		return direccionSector;
	}
	public void setDireccionSector(String direccionSector) {
		this.direccionSector = direccionSector;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaConstitucion() {
		return fechaConstitucion;
	}
	public void setFechaConstitucion(String fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}
	public String getNombreComercial() {
		return nombreComercial;
	}
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	public String getNumeroRnc() {
		return numeroRnc;
	}
	public void setNumeroRnc(String numeroRnc) {
		this.numeroRnc = numeroRnc;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getRegimenPago() {
		return regimenPago;
	}
	public void setRegimenPago(String regimenPago) {
		this.regimenPago = regimenPago;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public RncData(String actividadPrincipal, String direccionCalle, String direccionNumero, String direccionSector, String estado, String fechaConstitucion,
			String nombreComercial, String numeroRnc, String razonSocial, String regimenPago, String telefono) {
		super();
		this.actividadPrincipal = actividadPrincipal;
		this.direccionCalle = direccionCalle;
		this.direccionNumero = direccionNumero;
		this.direccionSector = direccionSector;
		this.estado = estado;
		this.fechaConstitucion = fechaConstitucion;
		this.nombreComercial = nombreComercial;
		this.numeroRnc = numeroRnc;
		this.razonSocial = razonSocial;
		this.regimenPago = regimenPago;
		this.telefono = telefono;
	}
}
