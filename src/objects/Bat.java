package objects;

import java.util.Random;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends Enemy {
//// ATRIBUTOS ////

//// CONSTRUTOR ////	
	public Bat(Point2D position, Room room, int initialDamage, int initialHealth) {
		super(position, room, initialDamage, initialHealth);
	}

	@Override
	public String getName() {
		return "Bat";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean canCollide() {
		return true;
	}
//// INTERACT ////
	@Override
	public void interactWithManel(Manel manel) {
		if (this.getPosition().equals(manel.getPosition())) {
			manel.addHealth(-this.getDamage());
			this.addHealth(-manel.getDamage());
			System.out.println(" -----------------VIDA DO MORCEGO: " + this.getHealth());
			if(this.getHealth()<= 0) {
				unregister();
			}
			ImageGUI.getInstance()
					.setStatusMessage("Manel - " + "VIDA: " + manel.getHealth() + ", " + "DANO: " + manel.getDamage());
			//getRoom().elementToBeRemoved(this);
		}
	}

//// MOVE ////
	@Override
	public void move() {

		Point2D currentPosition = this.getPosition();
		if (this.canClimbDown()) {
			System.out.println("------------------------MORCEGO DIZ QUE PODE DESCER");
			Direction direction = Direction.DOWN;

			Vector2D vector = direction.asVector();
			Point2D newPosition = currentPosition.plus(vector);

			if (!getRoom().testBoundaries(newPosition)) {
				// System.out.println("Morcego tentou sair dos limites!");
				return;
			}

			if (!canMove(newPosition)) {
				System.out.println("Morcego não pode se mover porque há obstáculos!");
				return;
			}

			this.setPosition(newPosition);
		} else {
			Random random = new Random();
			int randomInt = random.nextInt(3);
			Direction direction = null;

			switch (randomInt) {
			case 0:
				direction = Direction.LEFT;
				break;
			case 1:
				return;
			case 2:
				direction = Direction.RIGHT;
				break;

			}
			Vector2D vector = direction.asVector();
			Point2D newPosition = currentPosition.plus(vector);
			if (!getRoom().testBoundaries(newPosition)) {
				// System.out.println("O Gorila está a tentar sair dos limites!");
				return;
			}

			if (!canMove(newPosition)) {
				System.out.println("O morcego não anda");
				return;
			}
			this.setPosition(newPosition);
		}
	}

}
