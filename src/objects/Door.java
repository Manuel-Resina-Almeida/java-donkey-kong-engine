package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {

//// ATRIBUTOS ////

	private boolean isDoorClosed = true;

//// CONSTRUTOR ////	

	public Door(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		if (isTheDoorOpen()) {
			return "DoorOpen";
		} else {
			return "DoorClosed";
		}
	}

	public int getLayer() {
		return 1;
	}

//// STATUS ////

	public boolean isTheDoorOpen() {
		if (this.isDoorClosed == true) {
			return false;
		}
		return true;
	}

	public void setDoorOpen() {
		this.isDoorClosed = false;
	}

	public void setDoorClosed() {
		this.isDoorClosed = true;
	}

	@Override
	public boolean hasFooting() {
		return true;
	}
	@Override
	public boolean canCollide() {
		return true;
	}

//// POSITION ////

	@Override
	public void move() {
		if (!this.isDoorClosed) {
			getRoom().setWinner();
		}
	}

//// INTERACT ////
	@Override
	public void interactWithManel(Manel manel) {
		if (this.getPosition().equals(manel.getPosition()) && !this.isTheDoorOpen()) {
			this.setDoorOpen();

		}

	}

}
