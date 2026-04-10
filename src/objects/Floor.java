package objects;


import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Floor extends GameElement{
	
	public Floor(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Floor";
	}

	@Override
	public int getLayer() {
		return 1;
	}
	@Override
	public boolean canCollide() {
		return true;
	}
	@Override
	public boolean hasFooting() {
		return false;
	}

}
