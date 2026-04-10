package pt.iscte.poo.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objects.BadGuy;
import objects.Banana;
import objects.Bat;
import objects.Beef;
import objects.DonkeyKong;
import objects.Door;
import objects.Flag;
import objects.Floor;
import objects.GameElement;
import objects.HiddenTrap;
import objects.Manel;
import objects.Potion;
import objects.Princess;
import objects.Stairs;
import objects.Sword;
import objects.Trap;
import objects.Wall;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Room {

//// ATRIBUTOS & LISTAS ////
	private int room_height;
	private int room_width;
	private int alive_timer;
	private Manel manel;
	private Point2D heroStartingPosition;
	private ArrayList<GameElement> elements = new ArrayList<>();
	private ArrayList<GameElement> elementsToBeAdded = new ArrayList<>();
	private ArrayList<GameElement> elementsToBeRemoved = new ArrayList<>();
//	private ArrayList<Wall> walls = new ArrayList<>();
//	private ArrayList<Stairs> stairs = new ArrayList<>();
//	private ArrayList<Trap> traps = new ArrayList<>();
	private boolean isThereWinner = false;

//// CONSTRUTORES ////
	public Room(String fileName) {
		readFile(fileName);
		Manel manel = new Manel(heroStartingPosition, this);
		this.manel = manel;
		addGameElements();
		ImageGUI.getInstance().setStatusMessage(
				"Vida restante: " + manel.getHealth() + ", o Manel dá: " + manel.getDamage() + "de dano!");

	}

	public Room(String fileName, Manel manelRecebido) {
		readFile(fileName);
		this.manel = new Manel(heroStartingPosition, this, manelRecebido.getHealth(), manelRecebido.getDamage(),
				manelRecebido.getNumberOfLives());
		addGameElements();
		ImageGUI.getInstance().setStatusMessage(
				"Vida restante: " + manel.getHealth() + ", o Manel dá: " + manel.getDamage() + "de dano!");

	}

//// GETTERS ////

	public Manel getManel() {
		return this.manel;
	}
public Point2D getHeroStartingPosition() {
	return this.heroStartingPosition;
}
	public void resetManel() {
		this.manel.reset();
		this.manel.setPosition(heroStartingPosition);
	}

//// TIMER ////

	public int getAlive_timer() {
		return alive_timer++;
	}

//// TESTERS ////

	public void update() {
		System.out.println("ROOM UPDATE");
		for (GameElement e : this.elements) {
			e.move();
		}
		removeGameElements();
		addGameElements();
		for (GameElement e : this.elements) {
			e.interact();
		}
		removeGameElements();
		addGameElements();
	}

	public boolean testBoundaries(Point2D position) {
		if ((position.getX() >= this.room_width || position.getX() < 0)
				|| (position.getY() >= this.room_height || position.getY() < 0)) {
			// System.out.println("Posição inválida: " + position);

			return false;
		} else {
			return true;
		}

	}

	public void applyGravityToElement(GameElement e1) {
		if (e1.isAffectedByGravity()) {
			Point2D currentPosition = e1.getPosition();

			Direction direction = Direction.DOWN;

			Vector2D vector = direction.asVector();
			Point2D under_position = currentPosition.plus(vector);
			boolean hasFooting = false;

			for (GameElement e : this.elements) {
				Point2D position_e = e.getPosition();
				if (position_e.equals(under_position))
					if (e.hasFooting()) {
						hasFooting = true;
						return;
					}

			}

			if (hasFooting == false) {
				System.out.println("Em queda!!!");
				this.manel.setPosition(under_position);
			}

		}
	}
	public void applyGravity() {
//		for(GameElement e : this.elements) {
//			this.applyGravityToElement(e);
//		}
		this.applyGravityToElement(this.manel);
	}

//// MOVES ////

	public void moveManel() {
		try {
			if (ImageGUI.getInstance().wasKeyPressed()) {
				this.manel.move();
			}
		} catch (IllegalArgumentException e) {
			ImageGUI.getInstance().setStatusMessage("Tecla inválida!");
		}

	}

	public void elementToBeAdded(GameElement e) {
		this.elementsToBeAdded.add(e);
	}

	public void elementToBeRemoved(GameElement e) {
		this.elementsToBeRemoved.add(e);
	}

	private void addGameElements() {
		this.elements.addAll(elementsToBeAdded);
		for (GameElement e : this.elementsToBeAdded) {
			ImageGUI.getInstance().addImage(e);
		}
		this.elementsToBeAdded = new ArrayList<>();
	}

	private void removeGameElements() {
		this.elements.removeAll(elementsToBeRemoved);
		for (GameElement e : this.elementsToBeRemoved) {
			ImageGUI.getInstance().removeImage(e);
		}
		this.elementsToBeRemoved = new ArrayList<>();
	}

	public ArrayList<GameElement> getGameElements() {
		return this.elements;
	}
//// CHECKERS ////

	public boolean checkForDeath() {
		if (this.manel.checkHeroDeath()) {
			ImageGUI.getInstance().setStatusMessage("YOU DIED !");
			return true;
		}
		return false;
	}

	public boolean checkForDefeat() {
		if (this.manel.getNumberOfLives() == 0) {
			return true;
		}
		return false;
	}

	public boolean isThereWinner() {
		if (isThereWinner) {
			return true;
		}
		return false;
	}

	public void setWinner() {
		this.isThereWinner = true;
	}

//// UPDATES ////
//// UTILITIES ////
	private <T> void removeImages(List<T> to_remove) {
		for (T o : to_remove) {
			ImageTile t = (ImageTile) o;
			ImageGUI.getInstance().removeImage(t);
		}
	}

	public void readFile(String nameFile) {
		int count_y = -1;

		try {
			Scanner sc = new Scanner(new File(nameFile));

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				count_y++;

				for (int count_x = 0; count_x < line.length(); count_x++) {
					char ch = line.charAt(count_x);

					new Floor(new Point2D(count_x, count_y), this);

					switch (ch) {
					case 'W':
						Wall wall = new Wall(new Point2D(count_x, count_y), this);

						// elementToBeAdded(wall);

						break;

					case 'S':
						Stairs stair = new Stairs(new Point2D(count_x, count_y), this);

						// elementToBeAdded(stair);

						break;
//
					case 'P':
						Princess princess = new Princess(new Point2D(count_x, count_y), this);
						break;

					case 't':
						Trap t = new Trap(new Point2D(count_x, count_y), this);
						// elementToBeAdded(t);
						break;
//
					case 'G':
						DonkeyKong kong = new DonkeyKong(new Point2D(count_x, count_y), this, 10, 100, 2, 2);
						// elementToBeAdded(kong);
						break;

					// BATS
					case 'w':
						Bat bat = new Bat(new Point2D(count_x, count_y), this, 10, 10);
						break;
//
					case 's':
						Sword s = new Sword(new Point2D(count_x, count_y), this);
						// elementToBeAdded(s);
						break;

					case 'H':
						this.heroStartingPosition = new Point2D(count_x, count_y);
						// E QUANDO PASSA DE NIVEL?????

						break;
//
					case 'm':
						Beef b = new Beef(new Point2D(count_x, count_y), this, 10);
						// elementToBeAdded(b);
						break;
//
					case '0':
						Door d = new Door(new Point2D(count_x, count_y), this);
						// elementToBeAdded(d);
						break;
					case 'z':
						HiddenTrap trap = new HiddenTrap(new Point2D(count_x, count_y), this);
						// elementToBeAdded(d);
						break;
						
					case 'k': 
						BadGuy badGuy = new BadGuy(new Point2D(count_x, count_y),this, 10, 100);
						break;
					
					case 'p': // Representa uma poção no mapa
					    Potion potion = new Potion(new Point2D(count_x, count_y), this);
					    //elementToBeAdded(potion);
					    break;
					    
					case 'f':
						Flag flag = new Flag(new Point2D(count_x, count_y), this);
						break;
					}
				}
				this.room_width = line.length();

			}
			this.room_height = count_y + 1;
			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não existe: " + ImageGUI.getInstance().askUser("What is the next level? "));
		}

	}
}
