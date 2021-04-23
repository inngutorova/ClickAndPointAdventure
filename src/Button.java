import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Button {
    int x;
    int y;
    public int width;
    public int height;
    public String text;
    public int xText;
    public BufferedImage image;
    public ClickListener clickListener;

    public Button(int x, int y, String text, int xText, ClickListener clickListener) throws IOException {
        image = ImageIO.read(new File("G:\\Java\\ClickAndPointAdventure\\src\\Pictures\\button.png"));
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 40;
        this.text = text;
        this.xText = xText;
        this.clickListener = clickListener;
    }

    public Button(int x, int y, int width, int height, String text, int xText, ClickListener clickListener) throws IOException {
        image = ImageIO.read(new File("G:\\Java\\ClickAndPointAdventure\\src\\Pictures\\button.png"));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.xText = xText;
        this.clickListener = clickListener;
    }

    //отрисовка
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(text, x + xText, y + 27);
    }

    //проверка клика
    public void checkClick(MouseEvent e) {
        int xx = e.getX();
        int yy = e.getY();
        boolean todo = true;
        if (xx < x || xx > x + width) {
            todo = false;
        }
        if (yy < y || yy > y + height) {
            todo = false;
        }
        if (todo) {
            this.clickListener.handleClick();
        }
    }
}