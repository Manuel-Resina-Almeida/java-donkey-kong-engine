package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Banana extends Consumable {
//// ATRIBUTO ////
	private final int ticksToMove;
	private int bananaTick = 0;

//// CONSTRUTOR ////
	public Banana(Point2D position, Room room, int ticksToMove) {
		super(position, room, 0, -15);
		this.ticksToMove = ticksToMove;
	}

	@Override
	public String getName() {
		return "Banana";
	}

	@Override
	public int getLayer() {
		return 3;
	}

//// MOVE ////
	@Override
	public void move() {
		//System.out.println("BANANA MOVE");

		if (this.bananaTick == this.ticksToMove) {
			Point2D currentPosition = this.getPosition();
			Direction direction = Direction.DOWN;
			Vector2D vector = direction.asVector();
			Point2D under_position = currentPosition.plus(vector);
			this.setPosition(under_position);
			this.bananaTick = 0;
			if (!getRoom().testBoundaries(this.getPosition())) {
				unregister();
			}

		} else {
			this.bananaTick++;
		}
		//System.out.println("A POSICAO DA BANANA É :" + this.getPosition());

	}

}
