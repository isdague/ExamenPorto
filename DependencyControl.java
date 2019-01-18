import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class DependencyControl {

	// listado de constantes para mejorar legibilidad de código
	private static final String DEPEND = "DEPEND";
	private static final String INSTALL = "INSTALL";
	private static final String REMOVE = "REMOVE";
	private static final String END = "END";
	private static final String LIST = "LIST";

	private static HashSet<String> installed = new HashSet<String>();
	private static HashMap<String, HashSet<String>> dependencies = new  HashMap<String,HashSet<String>>();
	
	
	public static void main(String args []) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Bienvenido al programa de dependencias ingresa un comando");
		

		ArrayList<String> comms = new ArrayList<String>();
		
		do{
			String line = scanner.nextLine();
			
			String tokens[]= line.split(" ");
			
			comms = new ArrayList<String>();
			
			for(String s:tokens) {
				comms.add(s);
			}


						
			
			if(!isValidArgs(comms)) {
				System.out.println("Command Syntax Error!");
				System.out.println("Example:");
				System.out.println("Command Syntax                  Interpetation/Response");
				System.out.println("----------------------------------------------------------------------------------------");
				System.out.println("DEPEND item1 item2 [item3...]   item1 depends on item2 (and item3...)");
				System.out.println("INSTALL item1                   install item1 and those on which it depends");
				System.out.println("REMOVE item1                    remove item1, and those on which it depends, if possible");
				System.out.println("LIST                            list the names of all currently-installed components");
			}else {
				
				switch(comms.get(0)) {
					case DEPEND: 	System.out.print(comms.get(0)+" "+comms.get(1));
									addDependency(comms);
									break;
					case INSTALL: 	System.out.println("INSTALL "+comms.get(1));
									installItem(comms.get(1));
									break;
					case REMOVE:	System.out.println("REMOVE "+comms.get(1));
									removeItem(comms.get(1));
									break;
					case LIST:		listItems();
									break;
				}	
			}
		}while(!comms.get(0).equals(END));
		System.out.println(END);
		scanner.close();
		return;
	}
	
	private static boolean isValidArgs(ArrayList<String> comms) {
		switch(comms.size()) {
			case 0 :	// NO RECIBE ARGUMENTOS ES UN ERROR
						return false;
						
			case 1 :	if(comms.get(0).equals(LIST)||comms.get(0).equals(END)) //Cuando es un solo argumento debe ser del tipo LIST o END
							return true;
						
			case 2:		if(comms.get(0).equals(INSTALL)||comms.get(0).equals(REMOVE)) // Cuando son dos argumentos el primero debe ser un INSTALL o REMOVE
							return true;
				
			default:	if(comms.get(0).equals(DEPEND) && comms.size()>2)// Cuando son mas de dos argumentos debe ser un DEPEND de lo contrario es falso
							return true;
						else
							return false;
		}
	}
	
	private static void addDependency(ArrayList<String> comms) {
		
		HashSet<String> items = new HashSet<String>();
		
		for(int x = 2; x < comms.size();x++) {
			System.out.print(" "+comms.get(x));
			items.add(comms.get(x));
		}
		System.out.println("");
		dependencies.put(comms.get(1), items);
		

//System.out.println(dependencies.size() +" "+ dependencies);		
	}
	
	private static void installItem(String item) {
		
		if(installed.contains(item)) {
			System.out.println("     "+item +"is already installed.");
		}else {
			checkDependenciesToInstall(item);
			installed.add(item);
			System.out.println("     Installing "+item);
		}
		
	}
	
	private static void removeItem(String item) {
		
		if(!installed.contains(item)) {
			System.out.println("     "+item +" is not installed.");
		}else {
			if(isNeeded(item)) {
				return;
			}else {
				//INTENTA BORRAR DEPENDENCIAS
				HashSet<String> items = new HashSet<String>();
				items = dependencies.get(item);
				if(items!= null)
					for(String s:items) {
						removeItem(s);
					}
			installed.remove(item);
			dependencies.remove(item);
			System.out.println("     Removing "+item);
			}
		}
	}
	
	private static void listItems() {
		for(String item:installed) {
			System.out.println("     "+item);
		}
	}
	
	private static void checkDependenciesToInstall(String item) {
		HashSet<String> items = new HashSet<String>();
		items = dependencies.get(item);
		
//System.out.println(items);		
		if(items!= null)
			for(String s:items) {
				checkDependenciesToInstall(s);
				if(!installed.contains(s)) {
					System.out.println("     Installing "+s);
				}
				installed.add(s);
			}
	}
	
	private static boolean isNeeded(String item) {
		for(HashSet<String> items:dependencies.values()) {
			if(items.contains(item)) {
				System.out.println("     "+item+" is still needed.");
				return true;
			}
		}
		return false;

	}
	
}	
