package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Point2D;

public class BadGuy extends Enemy {

	public BadGuy(Point2D position, Room room, int initialDamage, int initialHealth) {
		super(position, room, initialDamage, initialHealth);
		System.out.println("GENERATED BAD GUY");
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getName() {
		return "BadGuy";
	}
}
