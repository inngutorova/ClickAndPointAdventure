import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class ClickableObject {

    public int x0;
    public int y0;
    public int width;
    public int height;
    public BufferedImage image;
    public BufferedImage paper;
    public String text;
    public boolean openedMessage = false;
    public Button ok;
    public boolean got = false;
    public boolean inInvent = false;
    public ClickListener clickListener;

    public ClickableObject(int x0, int y0, int width, int height, BufferedImage image, BufferedImage paper, String text, ClickListener clickListener) throws IOException {
        this.x0 = x0;
        this.y0 = y0;
        this.width = width;
        this.height = height;
        this.image = image;
        this.paper = paper;
        this.text = text;
        this.clickListener = clickListener;
        ok = new Button(360 - 100 / 2, 450, "Понятно", 10, new ClickListener() {
            @Override
            public void handleClick() {
                openedMessage = false;
                got = true;
            }
        });
    }

    //отрисовка самого объекта
    public void draw(Graphics g) {
        g.drawImage(image, x0, y0, width, height, null);
    }

    //отрисовка сообщения, появляющегося при клике
    public void drawMessage(Graphics g) {
        if (openedMessage) {
            g.drawImage(paper, 180, 180, 360, 360, null);
            g.setColor(Color.black);
            this.writeInRect(g, text, 200, 200, 320, 320);
            ok.draw(g);
        }
    }


    //проверка клика
    public void checkClick(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        boolean todo = true;
        if (x < x0 || x > x0 + width) {
            todo = false;
        }
        if (y < y0 || y > y0 + height) {
            todo = false;
        }
        if (todo) {
            //this.clickListener.handleClick();
            openedMessage = true;
        }
    }

    //метод, который позволет вписать текст в определенную область, то есть переносит слова на следующую строчку, когда они не помещаются
    public void writeInRect(Graphics g, String text, int x, int y, int width, int height) {
        Font f = new Font("TimesRoman", Font.PLAIN, 15);
        g.setFont(f);
        ArrayList<String> strings = new ArrayList<>();
        strings.add(0, "");
        int n = 0;

        for (int i = 0; i <= text.length() - 1; i++) {
            if (text.charAt(i) == ' ') {
                String textpart = text.substring(0, i);
                FontMetrics fm = g.getFontMetrics();
                int stringWidth = fm.stringWidth(textpart);
                strings.set(n, textpart);

                if (stringWidth > width - 120) {
                    text = text.substring(strings.get(n).length() + 1);
                    n = n + 1;
                    strings.add("");
                }
            }
        }
        strings.set(n, text);
        for (int i = 0; i <= strings.size() - 1; i++) {
            g.drawString(strings.get(i), x + 30, y + 40 + i * 20);
        }
    }
}