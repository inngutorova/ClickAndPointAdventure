import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerCharacter {
    int x  = 100;
    int y;
    int height = 400;
    int width = 200;
    BufferedImage imageRight;
    BufferedImage imageLeft;
    BufferedImage currentImage;
    Room room;
    int direction = 1;
    Controller controller = new Controller(this);

    public PlayerCharacter (Room room) throws IOException {
        this.room = room;
        y = room.height - room.playZone - height+100;
        imageRight = ImageIO.read(new File("G:\\Java\\ClickAndPointAdventure\\src\\Pictures\\PlayerRight.png"));
        imageLeft = ImageIO.read(new File("G:\\Java\\ClickAndPointAdventure\\src\\Pictures\\PlayerLeft.png"));
        currentImage = imageRight;
    }

    //отрисовка
    public void draw(Graphics g) {
        g.drawImage(currentImage, x, y, width, height, null);
    }

    //изменение картинки в зависимости от направления движения
    public void update() throws IOException {
        if (direction == 1) {
            currentImage = imageRight;
        }
        if (direction == -1) {
            currentImage = imageLeft;
        }
    }
}
