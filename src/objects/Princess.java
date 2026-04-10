package objects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Princess extends GameElement {
//// CONSTRUTOR ////
	public Princess(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Princess";
	}

	@Override
	public int getLayer() {
		return 1;
	}
	@Override
	public boolean hasFooting() {
		return true;
	}
//// POSITION ////
	@Override
	public boolean isAffectedByGravity() {
		return true;
	}

//// INTERACT ////
	@Override
	public void interactWithManel(Manel manel) {
		if(this.getPosition().equals(manel.getPosition())) {
			getRoom().setWinner();
		}

	}

	@Override
	public boolean canCollide() {
		// TODO Auto-generated method stub
		return true;
	}

}
