import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NPC {
    int x;
    int y;
    int height = 400;
    int width;
    String name;
    int xName;
    BufferedImage image;
    ArrayList<Dialogue> dialogues;
    int currentDialogue;
    boolean talking = false;
    boolean clicked = false;

    public NPC(int x, int y, int width,  String name, int xname, BufferedImage image, ArrayList<Dialogue> dialogues) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.name = name;
        this.xName = xname;
        this.image = image;
        this.dialogues = dialogues;
        currentDialogue = -1;


    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        g.drawString(name,x+xName,y-5);

        if (currentDialogue != -1 && dialogues.size() != 0 && dialogues.get(currentDialogue).ended) {
            talking = false;
            clicked = false;
        }
    }

    public void checkClick(MouseEvent e) {
        int xx = e.getX();
        int yy = e.getY();

        if (xx > x && xx < x + width && yy > y && yy < y + height) {
            System.out.println("There???");
            clicked = true;
        }
    }

}