import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class proga {
	
	static LinkedList<LinkedList> ll = new LinkedList<LinkedList>(); //DEPENDENCIES
	//	 static String[] arrDep = new String[0];
	static String[] installedLib = new String[0];
	
	static int contIns=0;
	
	static void sDepend(String libs) {
		
		LinkedList<String> llDep = new LinkedList<String>();
		
		String[] lib = libs.split(" ");
		
		for(int i=0; i<lib.length; i++) {			
			llDep.add(lib[i]);
		}
		
		ll.add(llDep);
		
		llDep =null;

		System.out.println("DEPEND "+libs);
	}
	
	static void sInstall(String lib) {
		contIns++;
		String[] aux = installedLib;
		
		installedLib = new String[contIns];
		
		for (int i=0; i<aux.length; i++)
				installedLib[i]= aux[i];
	
		installedLib[installedLib.length-1] = lib;
		
		System.out.println("INSTALL "+lib);
		System.out.println("installing "+lib);
	}
	static void sRemove(String lib) {
		
		System.out.println("REMOVE "+lib);
		if(searchIstalledLibs(lib)) {
			
			Iterator<LinkedList> iLL = ll.iterator();
			 
			boolean isFirstLib = false;
			
			//check if is the first node
			while (iLL.hasNext() || !isFirstLib) {
				
				LinkedList<String> iLLDep = iLL.next();
				Object firstDep = iLLDep.get(0);
				
				if(firstDep.equals(lib)) isFirstLib = true;
				//System.out.println("firstDep"+ firstDep);			
				
			}			
			
			
			//chekc if is not linked in other linked list
			boolean isLinked= true;
			Iterator<LinkedList> iLL2 = ll.iterator();
			while (iLL2.hasNext() && isLinked) {
				
				Iterator<LinkedList> iLLDep = (Iterator<LinkedList>) iLL2.next();
				while(iLLDep.hasNext() && isLinked) {
						if (!iLLDep.next().getFirst().equals(lib) && !iLLDep.next().equals(lib) )
							isLinked= false;
					
				}
			}
			
			//delete is is first NOde and is not linked in other
			if(isFirstLib && !isLinked) {
				for(int i=0;i<installedLib.length; i++)
					if(lib.equals(installedLib[i]))
						installedLib[i] = "Removed "+installedLib[i];
				System.out.println("Removing "+lib);
			}
			else
				System.out.println(lib+ " is still needed");
			
		}
	}
	
	
	static void sList() {
		System.out.println("LIST ");
		for(int i=0;i<installedLib.length; i++) {
			if(!installedLib[i].contains("Removed"))
				System.out.println(installedLib[i]);
		}
		
	}
	
	static boolean searchIstalledLibs(String lib) {
		boolean isInstalled= false;
		
		for(int i=0; i<installedLib.length; i++ ) {
			if(lib.equals(installedLib[i]))
				isInstalled= true;
		}
		
		return isInstalled;
	}

	public static void main (String[] args){
		String text = "";
		String sampleI = "";
		
		String[] commandsI = {"DEPEND", "INSTALL", "REMOVE", "LIST"};
		
		
		
		do {
			text =JOptionPane.showInputDialog("Type the Sample Input");
			
			for(int i=0; i<commandsI.length; i++) {
					sampleI= text.toUpperCase();
				if(sampleI.contains(commandsI[i])) {
					
					String libs = sampleI.replace(commandsI[i]+ " ", "");
					
//					System.out.println("=====================");			
//					System.out.println(libs);
//					System.out.println("=====================");
					
					switch (i){
					case 0: sDepend(libs);break;
					case 1: sInstall(libs);break;
					case 2: sRemove(libs);break;			
					case 3: sList();break;
					default: break;	
						
					}
				}
			}
			
		}while (!(sampleI.equals("END")));
		
		System.out.println("END");
		System.gc();
	}
}
