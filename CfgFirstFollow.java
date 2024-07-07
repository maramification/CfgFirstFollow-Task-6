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
		String[] temp = cfg.split("#"); //4 0-->3
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
		
		//first();
		//follow();
	}
	
	
	public String first() {
		String finalOutput = "";
		assignArrays();
		assignTerminals();
		boolean change = true;
		
		while(change) {
//			System.out.println("IN WHILE...");
			change = false;
			for (int i = 0; i < transitions.size(); i++) {
				   String[] temp = transitions.get(i).split("/");
				   String[] derivations = temp[1].split(",");
				   String v = temp[0];
				   
//				   System.out.println("My V is..." + v);
				   
				   for (int k = 0; k < derivations.length; k++) {
					   String charToGetFirst =  String.valueOf(derivations[k].charAt(0));
					   boolean isEpsilon = false;
//					   System.out.println("My derivation is..." + derivations[k]);
//					   System.out.println(charToGetFirst + " firsts are..." + firsts.get(charToGetFirst));
					   
					 
					   //if it is a variable 
					   if (variables.contains(charToGetFirst)) {
						   //its First contains Epsilon and is a variable
						   if(firsts.get(charToGetFirst).contains("e")) {
//							   System.out.println("VARIABLE..." + charToGetFirst);
							   //add its firsts minus the epsilon
//							   System.out.println("FIRSTS BEFORE: " + firsts.get(v));
							   for (int z = 0; z < firsts.get(charToGetFirst).size(); z++) {
								   if (! (firsts.get(v).contains(firsts.get(charToGetFirst).get(z))) && ! (firsts.get(charToGetFirst).get(z).equals("e"))) {
									   firsts.get(v).add(firsts.get(charToGetFirst).get(z));
									   change = true;
								   }
//								   System.out.println("FIRSTS AFTER: " + firsts.get(v));
								   isEpsilon = true;
							   }
							   
							   //continuing the rest of the sentential form (derivations[k]) is the entire sentential form
							   for (int p = 1; p < derivations[k].length(); p++) {
								   String strV = String.valueOf(derivations[k].charAt(p)); 
								   
								   //assuming the rest of the string begins with a variable
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
										   
								   
								   //reaching a terminal
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
							   //add its firsts we khalas
							   for (int g = 0; g < firsts.get(charToGetFirst).size(); g++) {
								   if (! (firsts.get(v).contains(firsts.get(charToGetFirst).get(g)))) {
									   firsts.get(v).add(firsts.get(charToGetFirst).get(g));
									   change = true;
									   
								   }
							   }
						   }
					   }
					   
					   
					   

					   
					   //its a terminal 
					   if(terminals.contains(charToGetFirst)) {
//						   System.out.println("TERMINAL..." + charToGetFirst);
						   
						   if(! (firsts.get(v).contains(charToGetFirst))) {
							   firsts.get(v).add(charToGetFirst);
							   change = true;
						   }
					   }
					   
					   
					   
					   //if it is "e"
					   if (charToGetFirst.equals("e")) {
						   //add e to its firsts and add it to epsilon vars
						   //check to see if it has any variables that start with itself
//						   System.out.println("E...");
						   if (! (firsts.get(v).contains("e"))) {
							   firsts.get(v).add("e");
							   change = true;
						   }
					   }
					   
					   }
				   }
			
		}
		
		
		//string formation
        
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
        
//        System.out.println("finalOutput: " + finalOutput);
        
//		System.out.println("Elements in the HashMap:");
//        for (Entry<String, ArrayList<String>> entry : firsts.entrySet()) {
////            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
		return finalOutput;
	}

	
	public String follow() {
		String finalOutput = "";
		//execute first
		first();
		boolean change = true;
		
		//initialize follow arrays
		assignArraysFollow();
		
		
		//add $ to follows of the starting variable 
		follows.get(variables.get(0)).add("$");
		
		
		while(change) {
			change = false;
			
			for (int i = 0; i < transitions.size(); i++) {
				   String[] temp = transitions.get(i).split("/");
				   String[] derivations = temp[1].split(",");
				   String v = temp[0];
				   
//				   System.out.println("My V is..." + v);
				   
				   for (int k = 0; k < derivations.length; k++) {
					   String startingChar =  String.valueOf(derivations[k].charAt(0));
//					   System.out.println("My startingChar is..." + startingChar);
					   
						   if (derivations[k].length() > 1) {
//							   System.out.println("My derivation is longer than 1..." + derivations[k]);
							   
							   for (int j = 0; j < derivations[k].length(); j++) {
								   String charToGetFollow = String.valueOf(derivations[k].charAt(j));
//								   System.out.println("My charToGetFollow is..." + charToGetFollow);
								   
								   
								   if (variables.contains(charToGetFollow)) {
									   //im not at the last char of my sentential form
									   if (j < derivations[k].length()-1) {
										   String nextChar = String.valueOf(derivations[k].charAt(j+1));
//										   System.out.println("My nextChar is..." + nextChar);
										   
										   //if terminal add it to the follow of my charToGetFollow and break
										   if(terminals.contains(nextChar)) {
//											   System.out.println("TERMINAL..." + nextChar);
//											   System.out.println("Follow of (" + charToGetFollow + ") BEFORE..." + follows.get(charToGetFollow));
											   if(! (follows.get(charToGetFollow).contains(nextChar))) {
												   follows.get(charToGetFollow).add(nextChar);
												   change = true;
											   }
//											   System.out.println("Follow of (" + charToGetFollow + ") AFTER..." + follows.get(charToGetFollow));
										   }
										   
										   
										   // if variable, add the firsts of that variable minus the epsilon if there is one
										   else if(variables.contains(nextChar)) {
//											   System.out.println("VARIABLE..." + nextChar);
//											   System.out.println("Follow of (" + charToGetFollow + ") BEFORE..." + follows.get(charToGetFollow));
											   for(int z = 0; z < firsts.get(nextChar).size(); z++) {
												   if (! (follows.get(charToGetFollow).contains(firsts.get(nextChar).get(z)))  && ! (firsts.get(nextChar).get(z).equals("e"))) {
													   follows.get(charToGetFollow).add(firsts.get(nextChar).get(z));
													   change = true;
												   }
											   }
//											   System.out.println("Follow of (" + charToGetFollow + ") AFTER..." + follows.get(charToGetFollow));
											   
											   //check if epsilon is in the firsts of your nextChar, then add Follow(v) to the Follow(charToGetFollow)
											   int epsilonChecker = j+1;
											   if(firsts.get(nextChar).contains("e") && (epsilonChecker == derivations[k].length()-1)) {
//												   System.out.println("CONTAINS EPSILON..." + nextChar);
//												   System.out.println("Follow of (" + charToGetFollow + ") BEFORE..." + follows.get(charToGetFollow));
												   for(int r = 0; r < follows.get(v).size(); r++) {
													   if (! (follows.get(charToGetFollow).contains(follows.get(v).get(r)))) {
														   follows.get(charToGetFollow).add(follows.get(v).get(r));
														   change = true;
													   }
												   }
//												   System.out.println("Follow of (" + charToGetFollow + ") AFTER..." + follows.get(charToGetFollow));
											   }
										   }
									   }
									   
									   
									   
									   else {
											if (variables.contains(charToGetFollow)) {
//												System.out.println("Derivation length is has reached max...");
//												System.out.println("Follow of (" + charToGetFollow + ") BEFORE..." + follows.get(charToGetFollow));
												for (int f = 0; f < follows.get(v).size(); f++) {
													if (! (follows.get(charToGetFollow).contains(follows.get(v).get(f)))) {
														follows.get(charToGetFollow).add(follows.get(v).get(f));
														change = true;
													}
												}
//												System.out.println("Follow of (" + charToGetFollow + ") AFTER..." + follows.get(charToGetFollow));
											}
									   }
								   }
									   
						
							   }
						   }
						   
					//my derivation's length is 1
					else {
						
						if (variables.contains(startingChar)) {
//							System.out.println("Derivation length is 1..." + derivations[k]);
//							System.out.println("Follow of (" + startingChar + ") BEFORE..." + follows.get(startingChar));
							for (int f = 0; f < follows.get(v).size(); f++) {
								if (! (follows.get(startingChar).contains(follows.get(v).get(f)))) {
									follows.get(startingChar).add(follows.get(v).get(f));
									change = true;
								}
							}
//							System.out.println("Follow of (" + startingChar + ") AFTER..." + follows.get(startingChar));
						}
						
					}
			}
			
		}
		
		
		
	
	}
//string formation
        
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
//        System.out.println("finalOutput: " + finalOutput);
		
//		System.out.println("Elements in the HashMap:");
//      for (Entry<String, ArrayList<String>> entry : follows.entrySet()) {
//          System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//      }
		
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
	
	

	
//	public static void main(String[] args) { 
//		String test = "S;Z;Q;F;C;B;I#a;g;m;s;v;y#S/gQCFC,Bm;Z/IBS,C,s,B,S;Q/QyCy,mSC,vBg,IZ,e;F/gIa,C,e,F;C/gIF,CQFC,C;B/ZS,ZF,F,S;I/y,Cv,gZmCy,BFIQI,BIIS,sZg";
//		CfgFirstFollow cfgff = new CfgFirstFollow(test);
//		
//		
//	}

}
