package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Beef extends Consumable {

//// ATRIBUTOS ////

	private boolean isExpired = false;
	private int currentTicks = 0;
	private int ticksToExpire;

//// CONSTRUTOR ////

	public Beef(Point2D position, Room room, int ticksToExpire) {
		super(position,room,0, 20);
		this.ticksToExpire = ticksToExpire;
		
	}

	@Override
	public String getName() {

		if (this.isExpired) {
			return "BadMeat";
		} else {
			return "GoodMeat";
		}
	}

	@Override
	public int getLayer() {
		return 1;
	}

//// EXPIRY ////

	private void setExpired() {
		this.isExpired = true;
		this.setHealth(-20);
	}

//// CONSUMES ////
	@Override
	public void move() {
		this.currentTicks++;
		if (this.currentTicks > this.ticksToExpire) {
			setExpired();
		}
	}


}
