package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.gui.ImageGUI;
import pt.iscte.poo.utils.Point2D;

public class Potion extends Consumable {

    public Potion(Point2D position, Room room) {
        super(position, room, 0, 500);
        
    }

    @Override
    public String getName() {
        return "Bomb";
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public boolean canCollide() {
        return true; // Permite o Manel apanhar a poção
    }

    @Override
    public void interactWithManel(Manel manel) {
        manel.addToInventory(this); // Adiciona ao inventário do Manel
        System.out.println("----------INTERAGIU");
        unregister(); // Remove do mapa
    }
    @Override
    public void use(Manel manel) {
        manel.addHealth(this.getHealthBonus());
        ImageGUI.getInstance().setStatusMessage("Usou uma poção! HP: " + manel.getHealth());
    }
}
