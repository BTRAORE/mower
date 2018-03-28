/**
 * 
 */
package fr.mowitnow.mower;

import java.util.List;

/**
 * @author Brehima Cette classe represente la tondeuse
 */
public class Mower {

	/** La position courante de la tondeuse **/
	private Position currentPos;

	public Mower(Position currentPos) {
		this.currentPos = currentPos;
	}

	/**
	 * Execute les instructions sur le gazon
	 * 
	 * @param instructions
	 *            les instructions
	 * @param grass
	 *            le gazon
	 */
	public void mowIt(List<Instruction> instructions, Grass grass) {
		for (Instruction instruction : instructions) {
			if (Instruction.D.equals(instruction) || Instruction.G.equals(instruction)) {
				changeDirection(instruction);
			}
			if (Instruction.A.equals(instruction)) {
				if (isInGrassBorder(grass)) {
					move();
				}
			}
		}

	}

	/**
	 * Fait deplacer la tondeuse
	 */
	private void move() {

		switch (currentPos.getDirection()) {
		case N:
			int newY = currentPos.getY() + 1;
			currentPos.setY(newY);
			break;
		case S:
			newY = currentPos.getY() - 1;
			currentPos.setY(newY);
			break;
		case E:
			int newX = currentPos.getX() + 1;
			currentPos.setX(newX);
			break;
		case O:
			newX = currentPos.getX() - 1;
			currentPos.setX(newX);
			break;

		}
	}
	public Position getCurrentPos() {
		return currentPos;
	}

	/**
	 * Change la direction de la tondeuse
	 * @param instruction l'instruction recu
	 */
	private void changeDirection(Instruction instruction) {
		
		Direction curDirection = currentPos.getDirection();
		if (Instruction.G.equals(instruction)) {
			currentPos.setDirection(curDirection.left());
		}
		if (Instruction.D.equals(instruction)) {
			currentPos.setDirection(curDirection.right());
		}
	}
	/**
	 * Teste si on ne deborde pas du gazon
	 * @param grass le gazon
	 * @return true si on est toujours dans le gazon, false sinon
	 */
	private boolean isInGrassBorder(Grass grass) {
		int curX = currentPos.getX();
		int curY = currentPos.getY();
		Direction curDirection = currentPos.getDirection();

		if (curX == 0 && Direction.O.equals(curDirection)
				|| curX == grass.getYLength() && Direction.E.equals(curDirection)
				|| curY == 0 && Direction.S.equals(curDirection)
				|| curY == grass.getYLength() && Direction.N.equals(curDirection)) {
			return false;
		}
		return true;
	}
	/**
	 * Affiche la position actuelle de la tondeuse
	 */
	public void displayPosition() {
		System.out.println(currentPos);
	}
}
