package com.portomx.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.porto.entity.EdoCuenta;

public class ImplLeerCsv implements ILeerCsv{
	
	// Metodo para leer y devolver un objeto tipo EdoCuenta
	@Override
	public List<EdoCuenta> leerCvs(String ruta) throws ParseException {
		
		String csvFile = ruta;
		BufferedReader br = null;
		String line = "";
		String cvsSplit = ",";
		// List de tipo EdoCuenta.
		List<EdoCuenta> lscuenta = new ArrayList<EdoCuenta>(); 
		EdoCuenta cuenta;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		
		try {
		    br = new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) { 
		    	cuenta = new EdoCuenta(); 
		    	String[] datos = line.split(cvsSplit);
		        // Asigamos los valores al objeto EdoCuenta
//		    	cuenta.setCuenta(Integer.parseInt(datos[0])); 
//		        cuenta.setMonto(Double.parseDouble(datos[1]));
//		        cuenta.setOperacion(Integer.parseInt(datos[2]));
//		        cuenta.setFecha(formatter.parse(datos[3]));
		    	
		    	cuenta.setCuenta(datos[0]); 
		        cuenta.setMonto(datos[1]);
		        cuenta.setOperacion(datos[2]);
		        cuenta.setFecha(datos[3]);
		        
		        
		        //Agregamos a la lista
		        lscuenta.add(cuenta);
		        
		        //Imprime datos.
		       System.out.println(datos[0] + ", " + datos[1] + ", " + datos[2] + ", " + datos[3]);
		    }
		    
		} catch (FileNotFoundException e ) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	    		
		return lscuenta;
	}

}
