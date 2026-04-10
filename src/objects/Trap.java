package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Trap extends GameElement {
//// ATRIBUTOS ////

private int damage = 30;
//// CONSTRUTOR ////
	public Trap(Point2D position, Room room) {
		super(position,room);
	}

	@Override
	public String getName() {
		return "Trap";
	}

	@Override
	public int getLayer() {
		return 2;
	}

////POSITION ////	

	@Override
	public boolean hasFooting() {
		return true;
	}

	@Override
	public boolean canCollide() {
		return false;
	}

//// DAMAGE ////


	public boolean isInRange(Manel manel) {
		Direction direction1 = Direction.UP;
		Vector2D vector1 = direction1.asVector();
		Point2D heroOnTop = this.getPosition().plus(vector1);

		Direction direction2 = Direction.DOWN;
		Vector2D vector2 = direction2.asVector();
		Point2D heroUnder = this.getPosition().plus(vector2);

		Direction direction3 = Direction.RIGHT;
		Vector2D vector3 = direction3.asVector();
		Point2D heroToTheRight = this.getPosition().plus(vector3);

		Direction direction4 = Direction.LEFT;
		Vector2D vector4 = direction4.asVector();
		Point2D heroToTheLeft = this.getPosition().plus(vector4);

		if (manel.getPosition().equals(heroOnTop) || manel.getPosition().equals(heroUnder)
				|| manel.getPosition().equals(heroToTheRight) || manel.getPosition().equals(heroToTheLeft)) {
			return true;
		}

		return false;
	}

//// INTERACT ////
@Override
	public void interactWithManel(Manel manel) {
		if (isInRange(manel)) {
			manel.addHealth(-this.damage);
			ImageGUI.getInstance().setStatusMessage("O Manel levou 30 de dano da Trap! " + manel.getHealth());
		}

	}

}
