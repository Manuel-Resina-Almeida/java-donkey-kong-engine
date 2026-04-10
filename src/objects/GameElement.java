package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameElement implements ImageTile {
//// ATRIBUTO ////
	private Point2D position;
	private Room room;

//// CONSTRUTOR ////
	public GameElement(Point2D position, Room room) {
		this.position = position;
		this.room = room;
		register();

	}

//// POSITION ////
	public Point2D getPosition() {
		return this.position;
	}

//// MÉTODOS ////
	protected void register() {
		this.room.elementToBeAdded(this);
	}

	public boolean isAffectedByGravity() {
		return false;
	}

	protected void unregister() {
		System.out.println("ELEMENTO DESRESGISTADO");
		this.room.elementToBeRemoved(this);
	}

	public abstract boolean canCollide();

	public boolean canMove(Point2D newPosition) {

		for (GameElement e : this.room.getGameElements()) {
			Point2D position_e = e.getPosition();
			if (e != this && position_e.equals(newPosition) && !e.canCollide()) {
				return false;

			}
		}

		return true;
	}

	public boolean canClimb() {
		return false;
	}

	public boolean hasFooting() {
		return true;
	}

	public String getName() {
		return this.getName();
	}

	public void move() {

	}

	public void setPosition(Point2D newPosition) {
		this.position = newPosition;
	}

	protected Room getRoom() {
		return this.room;
	}

	public void interact() {

	}

	public boolean canClimbUp() {

		for (GameElement e : room.getGameElements()) {
			Point2D position_e = e.getPosition();
			if (position_e.equals(this.getPosition()) && e.canClimb()) {
				return true;
			}

		}
		return false;
	}

	public boolean canClimbDown() {

		Direction direction = Direction.DOWN;

		Vector2D vector = direction.asVector();
		Point2D underCurrentPosition = this.getPosition().plus(vector);

		for (GameElement e : this.room.getGameElements()) {
			Point2D position_e = e.getPosition();
			if (position_e.equals(underCurrentPosition) && e.canClimb()) {
				return true;
			}
		}
		return false;
	}

	public void use(Manel manel) {
		// Implementação padrão: não faz nada
		ImageGUI.getInstance().setStatusMessage("Item não utilizável!");
	}

	public void interactWithManel(Manel manel) {

	}
}
