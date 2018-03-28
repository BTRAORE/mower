/**
 * 
 */
package fr.mowitnow.mower;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Brehima
 *
 */
public class TestMower {
	@Test
	public void testEndToEnd() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("mowersInputs.txt").getFile());
		MowerRunner.run(file.getAbsolutePath());
		
	}
	@Test
	public void testMower() {
		Position expectedPosition = new Position(1, 3, Direction.N);
		Position position = new Position(1, 2, Direction.N);
    	Mower mower = new Mower(position);
    	List<Instruction>instructions = MowerRunner.buildInstructions("GAGAGAGAA");
    	Grass grass = new  Grass(5,5);
    	mower.mowIt(instructions, grass);
    	Assert.assertEquals("La position ne correspond pas a celle attendue",expectedPosition, mower.getCurrentPos());
    	
	}
	@Test(expected=IllegalArgumentException.class)
	public void corruptedInput() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("corruptedMowersInputs.txt").getFile());
		MowerRunner.run(file.getAbsolutePath());
		
	}
}
