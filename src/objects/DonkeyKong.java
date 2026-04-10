package objects;

import java.util.Random;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class DonkeyKong extends Enemy {

//// ATRIBUTOS ////
	private final int ticksToThrow;
	private int kongTicks = 0;
	private int banana_ticks;

//// CONSTRUTOR ////

	public DonkeyKong(Point2D position, Room room, int initialDamage, int initialHealth, int ticksToThrow,
			int banana_ticks) {
		super(position, room, initialDamage, initialHealth);
		this.ticksToThrow = ticksToThrow;
		this.banana_ticks = banana_ticks;
	}

	@Override
	public String getName() {
		return "DonkeyKong";
	}

	@Override
	public int getLayer() {
		return 2;
	}


	@Override
	public void move() {
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
		Point2D currentPosition = this.getPosition();
		Vector2D vector = direction.asVector();
		Point2D newPosition = currentPosition.plus(vector);
		if (!getRoom().testBoundaries(newPosition)) {
			// System.out.println("O Gorila está a tentar sair dos limites!");
			return;
		}

		if (!canMove(newPosition)) {
			// System.out.println("O Gorila não pode andar porque tem obstáculo!");
			return;
		}
		this.setPosition(newPosition);

		this.kongTicks++;
		if (this.kongTicks == this.ticksToThrow) {
			Banana b = new Banana(new Point2D(newPosition.getX(), newPosition.getY() + 1), getRoom(),
					this.banana_ticks);
			this.kongTicks = 0;
		}

	}
	

}
