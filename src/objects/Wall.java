package objects;


import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement{
	
	public Wall(Point2D position, Room room) {
		super(position, room);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	
	@Override
	public boolean hasFooting() {
		return true;
		}

	@Override
	public boolean canCollide() {
		// TODO Auto-generated method stub
		return false;
	}
	}
