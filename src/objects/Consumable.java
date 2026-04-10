package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public abstract class Consumable extends GameElement {


	private int damage;
	private int health;

	public Consumable(Point2D position, Room room, int initialDamage, int initialHealth) {
		super(position, room);
		this.damage = initialDamage;
		this.health = initialHealth;
	}

	public int getHealthBonus() {
		return this.health;
	}
	public void setHealth(int newHealth) {
		this.health = newHealth;
	}
	public void addHealth(int healthAdder) {
		this.health += healthAdder;
	}

	@Override
	public boolean canCollide() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean hasFooting() {
		return false;
	}
	public void interactWithManel(Manel manel) {
		if (this.getPosition().equals(manel.getPosition())) {
			manel.addHealth(this.health);
			manel.addDamage(this.damage);
			ImageGUI.getInstance()
					.setStatusMessage("Manel - " + "VIDA: " + manel.getHealth() + ", " + "DANO: " + manel.getDamage());
			unregister();
		}
	}
}
