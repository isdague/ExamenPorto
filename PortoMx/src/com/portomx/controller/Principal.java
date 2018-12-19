package com.portomx.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.porto.entity.EdoCuenta;
import com.portomx.dao.ILeerCsv;
import com.portomx.dao.ImplLeerCsv;


public class Principal {
	
	private  ILeerCsv ileerCsv;
	public int totalCargos = 0;
	public int totalAbonos = 0;	
	public int total = 0;	
	
	public Principal() {
		this.ileerCsv = new ImplLeerCsv();
	}

	public static void main(String [] args) throws ParseException {
		Principal principal = new Principal();
		
		int mes = Principal.leerMes();
		System.out.print("EL mes elegido es:"+ mes);
		
		principal.sumarValores(mes);
		
	}
	
	private void sumarValores(int mes) throws ParseException {
		Principal principal = new Principal();
		List<EdoCuenta> lscuenta = new ArrayList<EdoCuenta>();
		
		Map<EdoCuenta,Integer> map = new TreeMap<EdoCuenta,Integer>();
		
		// Mandamos llamar el dao que lee el csv
		lscuenta = principal.ileerCsv.leerCvs("Ejercicio.csv");
		
		Collections.sort(lscuenta);
		
		System.out.println("Inicia el objeto EduCuenta---------------------");
		int cuenta = 0;
		
		for(EdoCuenta valor : lscuenta) {
			if(cuenta == Integer.parseInt(valor.getCuenta())) {
			// Si la cuenta es igual entoncs sumar los valores.
				
				
			}else {
				//map.put(cuenta, 1);
			}
			
			// Obtenemos el total
			
			
			// Validamos los cargos y abonos.
			if(valor.getOperacion() == "1") {
				totalCargos += 1; 
			}else{
				totalAbonos += 1;
			}
			
			// Imprimimos lo cargos y abonos
			System.out.println("Total de cargos son:"+totalCargos);
			System.out.println("Total de abonos son:"+totalCargos);
			
			cuenta = Integer.parseInt(valor.getCuenta());
			System.out.println(valor.getCuenta() +","+valor.getMonto() + ","+valor.getOperacion() +","+ valor.getFecha());
		}
		
		
	}
	
	private void crearCSV() {
		String outputFile = ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        List<EdoCuenta> lscuenta = new ArrayList<EdoCuenta>();
         
        if(alreadyExists){
            File ArchivoEmpleados = new File(outputFile);
            ArchivoEmpleados.delete();
        }        
         
//        try {
//            for(EdoCuenta valor : lscuenta){
//            	//           
//            }
//             
//            //ArchivoEmpleados.close();
// 
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
	}
		
	private static int leerMes() {
		int mes=0;
		
		// Leemos el mes a consultar
		Scanner  leer = new Scanner(System.in);
		
		System.out.println("Favor de introducir el mes a consultas: ");
		mes = leer.nextInt();
		
		if(mes<=0 || mes>12) {
			System.out.println("El mes debe ser en un rango de 1 a 12");
			Principal.leerMes();
		}
		
		return mes;
	}
	
	
}
