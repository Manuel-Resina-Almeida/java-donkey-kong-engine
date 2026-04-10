package objects;

import java.util.ArrayList;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import java.util.ArrayList;
import java.util.List;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Manel extends GameElement {

//// ATRIBUTOS ////	
	private final int initialHealth;
	private final int initialDamage;
	private int numberOfLives;
	private Point2D initialPosition;
	private int health;
	private int damage_manel;

//// CONSTRUTOR ////

	public Manel(Point2D initialPosition, Room room) {
		this(initialPosition, room, 1000, 10, 3);
	}

	public Manel(Point2D position, Room room, int initialHealth, int initialDamage, int numberOfLives) {
		super(position, room);
		this.initialHealth = initialHealth;
		this.initialDamage = initialDamage;
		this.health = initialHealth;
		this.damage_manel = initialDamage;
		this.numberOfLives = numberOfLives;
		this.initialPosition = position;
	}

	public void reset() {
		this.health = this.initialHealth;
		this.damage_manel = this.initialDamage;
		this.numberOfLives--;
		this.setPosition(initialPosition);
		ImageGUI.getInstance().showMessage("O Herói morreu!!!", "Vidas restantes: " + this.numberOfLives);
		ImageGUI.getInstance().showMessage("", "HP restantes: " + this.health);
	}

	@Override
	public String getName() {
		return "JumpMan";
	}

	@Override
	public int getLayer() {
		return 2;
	}

//// POSITION ////

//// HEALTH ////
	@Override

	public boolean canCollide() {
		return false;
	}

	public int getHealth() {
		return this.health;
	}

	public int addHealth(int HealthBonus) {
		return this.health += HealthBonus;
	}

	public boolean checkHeroDeath() {
		if (this.getHealth() <= 0) {
			return true;
		}
		return false;
	}

//// DAMAGE ////

	public int getDamage() {
		return damage_manel;
	}

	public void addDamage(int damage) {
		this.damage_manel += damage;
	}

//// NUMBER OF LIVES ////

	public int getNumberOfLives() {
		return this.numberOfLives;
	}

	public void setNumberOfLives(int numberOfLives) {
		this.numberOfLives = numberOfLives;
	}

//// MOVES ////	

	@Override
	public void move() {

		Point2D currentPosition = this.getPosition();
		int k = ImageGUI.getInstance().keyPressed();
		System.out.println("TECLA IMPIMIDA: " + k);
		if (ImageGUI.getInstance().wasKeyPressed()) {
			if (k == 'b' || k == 'B' || k == 98 || k == 66) { // Trata minúscula, maiúscula e ASCII
			    useItem();
			    System.out.println("ATIROU BOMBA");
			    return;
			}
			try {
				Direction direction = Direction.directionFor(k);

				Vector2D vector = direction.asVector();
				Point2D newPosition = currentPosition.plus(vector);

				if (!getRoom().testBoundaries(newPosition)) {
					ImageGUI.getInstance().setStatusMessage("OUT OF BOUNDS");
					// System.out.println("O Herói não pode andar, porque sai dos boundaries");
					return;
				}

				if (!canMove(newPosition)) {
					ImageGUI.getInstance().setStatusMessage("OBSTACLE IN THE WAY");
					// System.out.println("O Herói não pode andar, porque tem obstáculos");
					return;
				}
				if (direction.equals(Direction.UP) && !canClimbUp()) {
					ImageGUI.getInstance().setStatusMessage("NO STAIRS TO CLIMB");
					// System.out.println("O Herói não pode subir, porque não tem escadas");
					return;

				}
				if (direction.equals(Direction.DOWN) && !canClimbDown()) {
					ImageGUI.getInstance().setStatusMessage("NO STAIRS CANT CLIMB DOWN");
					// System.out.println("O Herói não pode descer, porque não tem escadas");
					return;
				}

				this.setPosition(newPosition);

			} catch (IllegalArgumentException e) {
				System.out.println("MOVIMETNOS INVALIDO: " + e.getMessage());
				return;

			}

		}
	}

	@Override
	public boolean isAffectedByGravity() {
		return true;
	}

	@Override
	public void interact() {
	    for (GameElement e : getRoom().getGameElements()) {
	        if (this.getPosition().equals(e.getPosition())) { // Interage apenas com objetos na mesma posição
	            e.interactWithManel(this);
	        }
	    }
	}


	private List<GameElement> inventory = new ArrayList<>(); // Inventário

	// Outros atributos e construtores...

	public void addToInventory(GameElement item) {
		inventory.add(item);
		ImageGUI.getInstance().setStatusMessage("Apanhou: " + item.getName());
	}

	public void useItem() {
	    if (!inventory.isEmpty()) {
	        GameElement item = inventory.remove(0); // Remove o primeiro item
	        item.use(this); // Chama o método polimórfico
	    } else {
	        ImageGUI.getInstance().setStatusMessage("Inventário vazio!");
	    }
	}

}
