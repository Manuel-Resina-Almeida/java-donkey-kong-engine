package pt.iscte.poo.game;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import objects.Manel;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;

public class GameEngine implements Observer {
	private Room currentRoom;
	private boolean isGameFinito = false;
	private int lastTickProcessed = 0;
	private int level_number;
	private int maxLevels = 3;

	public GameEngine() {

		// ROOM 0
		level_number = 0;
		Room room = new Room("rooms/room" + level_number + ".txt");
		currentRoom = room;

		// UPDATE
		ImageGUI.getInstance().update();

	}

	@Override
	public void update(Observed source) {
		if (isGameFinito)
			return;
		if (currentRoom.isThereWinner()) {
			updateRoom();
			return;

		} else if (currentRoom.checkForDeath()) {
			handleDeath();
			if (currentRoom.checkForDefeat()) {
				ImageGUI.getInstance().showMessage("Game Over", "Você perdeu!");
				handleDefeat();
				return;
			}
		}

		// currentRoom.moveManel();
		currentRoom.update();
		System.out.println("TIME : " + this.lastTickProcessed);
		
		int t = ImageGUI.getInstance().getTicks();
		while (lastTickProcessed < t) {
			processTick();
			currentRoom.applyGravity();

		}
		ImageGUI.getInstance().update();

	}

	private void handleDeath() {
		currentRoom.resetManel();
	}

	private void handleDefeat() {
		isGameFinito = true;
		ImageGUI.getInstance().clearImages();
		ImageGUI.getInstance().dispose();
		currentRoom = null;

	}

	public void updateRoom() {
		this.level_number++;
		if (this.level_number <this.maxLevels) {
			ImageGUI.getInstance().clearImages();
			Manel manel = this.currentRoom.getManel();
			this.currentRoom = new Room("rooms/room" + level_number + ".txt", manel);
		} else {
			handleVictory();
		}
	}

	private void handleVictory() {
		isGameFinito = true;
		ImageGUI.getInstance().showMessage("PARABÉNS!", "ÉS O VENCEDOR");

		try {
			String playerName = ImageGUI.getInstance().askUser("Qual é o teu nome?");

			PrintWriter p = new PrintWriter(new FileWriter("rooms/Scoreboard", true));
			p.println("Player: " + playerName + " | Time: " + this.lastTickProcessed);
			p.close();

			ImageGUI.getInstance().clearImages();
			ImageGUI.getInstance().dispose();
			ImageGUI.getInstance().showMessage("TABELA DE HIGHSCORES", null);
			return;
		} catch (IOException e) {
			ImageGUI.getInstance().showMessage("Não encontramos o ficheiro", null);
		}
		currentRoom = null;
	}

	private void processTick() {
		System.out.println("Tic Tac : " + lastTickProcessed);
		lastTickProcessed++;
	}

}
