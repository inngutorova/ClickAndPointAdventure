import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Room {
    String name;
    ArrayList<ClickableObject> objects;
    ArrayList<NPC> npc;
    BufferedImage background;
    int height = 720;
    int width = 720;
    int playZone = 200;

    public Room(String name, BufferedImage background, ArrayList<ClickableObject> objects, ArrayList<NPC> npc) {
        this.name = name;
        this.background = background;
        this.objects = objects;
        this.npc = npc;

    }

    //отрисовка
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, width, height-40, null);
        g.setColor(Color.black);
        g.fillRect(0,720-40,720,40);

        for (int i = 0; i < objects.size(); ++i) {
            if (!objects.get(i).got) {
                objects.get(i).draw(g);
            }
            objects.get(i).drawMessage(g);
        }
    }
}
