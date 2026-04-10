package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public abstract class Enemy extends GameElement {

	public Enemy(Point2D position, Room room, int initialDamage, int initialHealth) {
		super(position, room);
		this.damage = initialDamage;
		this.health = initialHealth;

	}

	private int damage;
	private int health;

	public void setHealth(int newHealth) {
		this.health = newHealth;
	}

	public void addHealth(int healthAdder) {
		this.health += healthAdder;
	}

	public int getHealth() {
		return this.health;
	}

	public void setDamage(int newDamage) {
		this.damage = newDamage;
	}

	public int getDamage() {
		return this.damage;
	}

	public boolean hasFooting() {
		return false;
	}

	@Override
	public boolean isAffectedByGravity() {
		return true;
	}

	@Override
	public boolean canCollide() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void interactWithManel(Manel manel) {
		if (this.getPosition().equals(manel.getPosition())) {
			manel.addHealth(-this.getDamage());
			this.addHealth(-manel.getDamage());
			if (this.getHealth() <= 0) {
				unregister();
			}
			ImageGUI.getInstance()
					.setStatusMessage("Manel - " + "VIDA: " + manel.getHealth() + ", " + "DANO: " + manel.getDamage());
		}
	}

}
