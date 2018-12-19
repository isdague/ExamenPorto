package com.portomx.dao;

import java.text.ParseException;
import java.util.List;

import com.porto.entity.EdoCuenta;

public interface ILeerCsv {
	// Interface para obtener los resultados del csv
	public List<EdoCuenta> leerCvs(String ruta) throws ParseException;
	
	
}
