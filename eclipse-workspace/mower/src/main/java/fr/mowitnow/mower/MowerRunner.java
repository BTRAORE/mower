/**
 * 
 */
package fr.mowitnow.mower;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brehima
 *
 */
public class MowerRunner {
	
public static final String SPACE = "\\s";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		run(args[0]);
	}
	public static void run(String inputFile) {
		
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
		    String line1 = br.readLine();
		    if(line1 == null) {
		    	throw new IllegalArgumentException("ERREUR Aucune ligne dans le fichier");
		    }
		    String [] grassSize = line1.split(SPACE);
		    if(grassSize.length != 2) {
		    	throw new IllegalArgumentException("ERREUR : La ligne pour les coordonnées du coin superieur du gazon est mal formatee");
		    }
		    Grass grass = new  Grass(Integer.parseInt(grassSize[0]), Integer.parseInt(grassSize[1]));
		    while ((line1 = br.readLine()) != null) {
		    	String [] initPos = line1.split(SPACE);
		    	if(initPos.length != 3) {
			    	throw new IllegalArgumentException("ERREUR : La ligne de la position initial est mal formate");
			    }
		    	Position position = new Position(Integer.parseInt(initPos[0]), Integer.parseInt(initPos[1]), toDirection(initPos[2]));
		    	Mower mower = new Mower(position);
		    	String instLine = br.readLine();
		    	if(instLine == null) {
		    		throw new IllegalArgumentException("WARNING : Aucune ligne pour les instructions");
		    	}
		    	if(!instLine.matches("[AGD]*")) {
		    		throw new IllegalArgumentException("WARNING : Un caractere inconnu dans liste des instructions, les caracteres valable sont : A, G et D");
		    	}
		    	mower.mowIt(buildInstructions(instLine), grass);
		    	mower.displayPosition();
		       
		    }
		} catch (FileNotFoundException e) {
			System.out.println("ERREUR : Le fichier est introuvable : "+e);
		} catch (IOException e) {
			System.out.println("ERREUR : Problème lors de la lecture du fichier");
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException("ERREUR : impossible de convertir en nombre."+e);
		}catch(Exception e) {
			System.out.println("ERREUR : Une erreur inattendue : "+e);
		}
	}
	/**
	 * Transforme une chaine (A, G, D) en direction.
	 * @param direction la direction
	 * @return la direction
	 */
	public static Direction toDirection(String direction) {
		return Direction.valueOf(direction);
	}
	/**
	 * Construit les instructions
	 * @param instLine la chaine des instructions
	 * @return la liste des instructions
	 */
	public static List<Instruction> buildInstructions(String instLine) {
		List<Instruction>instructions = new ArrayList<Instruction>();
		for(char inst : instLine.toCharArray()) {
			instructions.add(Instruction.valueOf(String.valueOf(inst)));
    	}
		return instructions;
	}
}
