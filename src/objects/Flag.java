package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Flag extends Consumable {
	private int ticksVisibility = 10;
	private int currentTicks = 0;
	private boolean timeToChange = false;

	public Flag(Point2D position, Room room) {
		super(position, room, 0, 0);
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean canCollide() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		if (this.timeToChange) {
			return "Floor";
		}
		return "Flag";
	}

	public void appear() {
		register();
	}

	public void disappear() {
		this.timeToChange = true;
		unregister();
	}

	@Override
	public void move() {
		System.out.println("------------TICKS: " + currentTicks);
		int Cycle = 10;
		if (this.currentTicks == 10) {
			this.timeToChange = true;
			System.out.println("VALOR DE TIMES TO CHANGE: " + this.timeToChange);
		} else {
			if (this.currentTicks == (Cycle += 10)) {
				this.timeToChange = false;
				System.out.println("ENTROU AQUIIIIIIIIIIIIII");
			}
		}
		this.currentTicks++;

	}
	
	@Override
	public void interactWithManel(Manel manel) {
		if(this.getPosition().equals(manel.getPosition()) && !this.timeToChange) {
			manel.setPosition(getRoom().getHeroStartingPosition());
		}
	}

}
