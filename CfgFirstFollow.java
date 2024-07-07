package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * Write your info here
 * 
 * @name Maram Hossam El-Deen Mohamed
 * @id 49-1891
 * @labNumber 18
 */

public class CfgFirstFollow {
	private ArrayList<String> variables = new ArrayList<String>();
	private ArrayList<String> terminals = new ArrayList<String>();
	private ArrayList<String> transitions = new ArrayList<String>();
	private HashMap<String, ArrayList<String>> firsts = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<String>> follows = new HashMap<String, ArrayList<String>>();


	public CfgFirstFollow(String cfg) {
		String[] temp = cfg.split("#"); 
		String[] vars = temp[0].split(";");
		
		for (int i = 0; i < vars.length; i++) {
			variables.add(vars[i]);
		}
		
		
		String[] terms = temp[1].split(";");
		
		for (int i = 0; i < terms.length; i++) {
			terminals.add(terms[i]);
		}
		
		
		String[] trans = temp[2].split(";");
		for (int i = 0; i < trans.length; i++) {
			transitions.add(trans[i]);
		}
		
	}
	
	
	public String first() {
		String finalOutput = "";
		assignArrays();
		assignTerminals();
		boolean change = true;
		
		while(change) {
			change = false;
			for (int i = 0; i < transitions.size(); i++) {
				   String[] temp = transitions.get(i).split("/");
				   String[] derivations = temp[1].split(",");
				   String v = temp[0];
				   				   
				   for (int k = 0; k < derivations.length; k++) {
					   String charToGetFirst =  String.valueOf(derivations[k].charAt(0));
					   boolean isEpsilon = false;
					   		 
					   if (variables.contains(charToGetFirst)) {
						   if(firsts.get(charToGetFirst).contains("e")) {
							   for (int z = 0; z < firsts.get(charToGetFirst).size(); z++) {
								   if (! (firsts.get(v).contains(firsts.get(charToGetFirst).get(z))) && ! (firsts.get(charToGetFirst).get(z).equals("e"))) {
									   firsts.get(v).add(firsts.get(charToGetFirst).get(z));
									   change = true;
								   }
								   isEpsilon = true;
							   }
							   
							   for (int p = 1; p < derivations[k].length(); p++) {
								   String strV = String.valueOf(derivations[k].charAt(p)); 
								   
								   if (variables.contains(strV)) {
									   if  (firsts.get(strV).contains("e")) {
										   
										   for (int x = 0; x < firsts.get(strV).size(); x++) {
											   if (! (firsts.get(strV).get(x).equals("e"))) {
												   if (! (firsts.get(v).contains(firsts.get(strV).get(x)))) {
													   firsts.get(v).add(firsts.get(strV).get(x));
													   change = true;
												   }
												   
											   }
											   
										   }
										   
										   isEpsilon = true;
									   }
									   
									   
									   else {
										   for (int x = 0; x < firsts.get(strV).size(); x++) {
												   if (! (firsts.get(v).contains(firsts.get(strV).get(x)))) {
													   firsts.get(v).add(firsts.get(strV).get(x));
													   change = true;
												   }
												   
											   
										   }
										   
										   isEpsilon = false;
										   break;
									   }
									   
									   
								   }
										   
								   
								   else if(terminals.contains(strV)) {
									   if(! (firsts.get(v).contains(strV))) {
										   firsts.get(v).add(strV);
										   change = true;
									   }
									   
									   isEpsilon = false;
									   break;
								   }
							   }
							   
							   
							   if(isEpsilon &&  ! (firsts.get(v).contains("e"))) {
								   firsts.get(v).add("e");
								   change = true;
							   }
							   
						   }
						   
						   
						   else {
							   for (int g = 0; g < firsts.get(charToGetFirst).size(); g++) {
								   if (! (firsts.get(v).contains(firsts.get(charToGetFirst).get(g)))) {
									   firsts.get(v).add(firsts.get(charToGetFirst).get(g));
									   change = true;
									   
								   }
							   }
						   }
					   }
					   
					   
					   

					   
					   if(terminals.contains(charToGetFirst)) {
						   if(! (firsts.get(v).contains(charToGetFirst))) {
							   firsts.get(v).add(charToGetFirst);
							   change = true;
						   }
					   }
					   
					   
					   
					   if (charToGetFirst.equals("e")) {
						   if (! (firsts.get(v).contains("e"))) {
							   firsts.get(v).add("e");
							   change = true;
						   }
					   }
					   
					   }
				   }
		}
		
		
        
        for ( int j = 0; j < variables.size(); j++) {
        	ArrayList<String> firstsOfKey = firsts.get(variables.get(j));
        	Collections.sort(firstsOfKey);
        	
        	
        	finalOutput = finalOutput + variables.get(j) + "/";
        	
        	for (int k = 0; k < firstsOfKey.size(); k++) {
        		finalOutput = finalOutput + firstsOfKey.get(k);
        	}
        	
        	finalOutput = finalOutput + ";";
        }

           
        
        finalOutput = finalOutput.substring(0, finalOutput.length() - 1);

		return finalOutput;
	}

	
	public String follow() {
		String finalOutput = "";
		first();
		boolean change = true;
		assignArraysFollow();
		follows.get(variables.get(0)).add("$");
		
		
		while(change) {
			change = false;
			
			for (int i = 0; i < transitions.size(); i++) {
				   String[] temp = transitions.get(i).split("/");
				   String[] derivations = temp[1].split(",");
				   String v = temp[0];
							   
				   for (int k = 0; k < derivations.length; k++) {
					   String startingChar =  String.valueOf(derivations[k].charAt(0));
					   
						   if (derivations[k].length() > 1) {
							   
							   for (int j = 0; j < derivations[k].length(); j++) {
								   String charToGetFollow = String.valueOf(derivations[k].charAt(j));
								   
								   if (variables.contains(charToGetFollow)) {
									   if (j < derivations[k].length()-1) {
										   String nextChar = String.valueOf(derivations[k].charAt(j+1));
										   if(terminals.contains(nextChar)) {
											   if(! (follows.get(charToGetFollow).contains(nextChar))) {
												   follows.get(charToGetFollow).add(nextChar);
												   change = true;
											   }
										   }
										   
										   
										   else if(variables.contains(nextChar)) {
											   for(int z = 0; z < firsts.get(nextChar).size(); z++) {
												   if (! (follows.get(charToGetFollow).contains(firsts.get(nextChar).get(z)))  && ! (firsts.get(nextChar).get(z).equals("e"))) {
													   follows.get(charToGetFollow).add(firsts.get(nextChar).get(z));
													   change = true;
												   }
											   }
											   
											   int epsilonChecker = j+1;
											   if(firsts.get(nextChar).contains("e") && (epsilonChecker == derivations[k].length()-1)) {
												   for(int r = 0; r < follows.get(v).size(); r++) {
													   if (! (follows.get(charToGetFollow).contains(follows.get(v).get(r)))) {
														   follows.get(charToGetFollow).add(follows.get(v).get(r));
														   change = true;
													   }
												   }
											   }
										   }
									   }
									   
									   
									   
									   else {
											if (variables.contains(charToGetFollow)) {
												for (int f = 0; f < follows.get(v).size(); f++) {
													if (! (follows.get(charToGetFollow).contains(follows.get(v).get(f)))) {
														follows.get(charToGetFollow).add(follows.get(v).get(f));
														change = true;
													}
												}
											}
									   }
								   }
									   
						
							   }
						   }
						   
					else {
						
						if (variables.contains(startingChar)) {
							for (int f = 0; f < follows.get(v).size(); f++) {
								if (! (follows.get(startingChar).contains(follows.get(v).get(f)))) {
									follows.get(startingChar).add(follows.get(v).get(f));
									change = true;
								}
							}
						}
						
					}
			}
			
		}
		
		
		
	
	}
        
        for ( int j = 0; j < variables.size(); j++) {
        	ArrayList<String> firstsOfKey = follows.get(variables.get(j));
        	Collections.sort(firstsOfKey);
        	
        	
        	finalOutput = finalOutput + variables.get(j) + "/";
        	
        	for (int k = 0; k < firstsOfKey.size(); k++) {
        		finalOutput = finalOutput + firstsOfKey.get(k);
        	}
        	
        	finalOutput = finalOutput + ";";
        }

           
        
        finalOutput = finalOutput.substring(0, finalOutput.length() - 1);			
		return finalOutput;
		}

	
	
	


public void assignArrays() {
	
	for (int i = 0; i < variables.size(); i++) {
		ArrayList<String> trans = new ArrayList<String>();
		firsts.put(variables.get(i),trans);
	}
}


public void assignTerminals() {
	for(int i = 0; i < transitions.size(); i++) {
		 String[] temp = transitions.get(i).split("/");
		   String[] derivations = temp[1].split(",");
		   String v = temp[0];
		   
		   for (int k = 0; k < derivations.length; k++) {
			   String firstChar = String.valueOf(derivations[k].charAt(0));
			   if (terminals.contains(firstChar)) {
				   if (! (firsts.get(v).contains(firstChar))) {
					   firsts.get(v).add(firstChar);
				   }
			   }
			   
		   }
	}
}




public void assignArraysFollow() {
	for (int i = 0; i < variables.size(); i++) {
		ArrayList<String> trans = new ArrayList<String>();
		follows.put(variables.get(i),trans);
	}
}


}
