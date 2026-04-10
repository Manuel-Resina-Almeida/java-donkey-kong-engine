package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class HiddenTrap extends Trap {
	private boolean inRange = false;

	public HiddenTrap(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {

		if (!this.inRange) {
			return "Wall";
		} else {
			return super.getName();
		}
	}
	@Override
	public void interactWithManel(Manel manel) {
		if(isInRange(manel)) {
			System.out.println("O MANEL TOCOU ME");
			inRange = true;
			super.interactWithManel(manel);
		}
		
	}
}

