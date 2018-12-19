package com.porto.entity;

import java.util.Date;

public class EdoCuenta implements Comparable<EdoCuenta> {
	
	private String 		Cuenta;
	private String 		Monto;
	private String 		Operacion;
	private String		Fecha;
	
	
	public String getCuenta() {
		return Cuenta;
	}
	public void setCuenta(String cuenta) {
		Cuenta = cuenta;
	}
	public String getMonto() {
		return Monto;
	}
	public void setMonto(String monto) {
		Monto = monto;
	}
	public String getOperacion() {
		return Operacion;
	}
	public void setOperacion(String operacion) {
		Operacion = operacion;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
	@Override
	public int compareTo(EdoCuenta c) {
		return  this.Cuenta.compareTo(c.getCuenta());
	
	}
	

}
