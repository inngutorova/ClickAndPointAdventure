
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Inventory {
    ArrayList<ClickableObject> objects;
    BufferedImage backImage;
    boolean opened = false;
    Button button = new Button(720-150, 720 - 40,150,40, "Инвентарь", 22, new ClickListener() {
        @Override
        public void handleClick() {
            opened = true;
        }
    });

    Button close = new Button(120+70, 450, "Закрыть", 10, new ClickListener() {
        @Override
        public void handleClick() {
            opened = false;
        }
    });

    public Inventory(ArrayList<ClickableObject> objects) throws IOException {
        this.objects = objects;
        backImage = ImageIO.read(new File("G:\\Java\\ClickAndPointAdventure\\src\\Pictures\\IventoryBack.png"));
    }

    public void draw(Graphics g) {
        if (opened) {
            g.drawImage(backImage, 60, 120, 600, 480, null);
            close.draw(g);

            if (objects.size() != 0) {
                for (int i = 0; i < objects.size(); ++i) {
                    objects.get(i).draw(g);
                }
                for (int i = 0; i < objects.size(); ++i) {
                    objects.get(i).drawMessage(g);
                }
            }
        }
    }

    public void update() {
        for (int i = 0; i<objects.size(); ++i) {
            objects.get(i).width = 60;
            objects.get(i).height = 60;
            if (i % 2 == 0) {
                objects.get(i).x0 = 120;
                objects.get(i).y0 = 180 + 120*i/2 ;
            } else {
                objects.get(i).x0 = 240;
                objects.get(i).y0 = 180 + 120*(i-1)/2;
            }
        }
    }
}