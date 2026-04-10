package objects;


import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Stairs extends GameElement{
	public Stairs(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Stairs";
	}
	
	@Override
	public int getLayer() {
		return 1;
	}
	
	@Override
	public boolean canClimb() {
		return true;
	}
	@Override
	public boolean hasFooting() {
		return true;
		}

	@Override 
	public boolean canCollide() {
		return true;
	}
}
