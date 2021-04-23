import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Map {
    Room[] rooms;
    int currentRoom;
    Button button;

    boolean opened = false;

    public Map(Room[] rooms) throws IOException {
        this.rooms = rooms;
        button = new Button(0, 720 - 40, "Карта", 22, new ClickListener() {
            @Override
            public void handleClick() {
                opened = true;
            }
        });
        currentRoom = 0;
    }

    //отрисовка
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(200, 200, 320, 320);
        g.setColor(Color.black);
        g.drawRect(240, 240, 240, 60);
        g.drawRect(240, 300, 240, 60);
        g.drawRect(240, 360, 240, 60);
        g.drawRect(240, 420, 240, 60);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString(rooms[0].name, 360-25, 280);
        g.drawString(rooms[1].name, 360-105, 340);
        g.drawString(rooms[2].name, 360-50, 400);
        g.drawString(rooms[3].name, 360-40, 460);
    }

    //смена комнат в зависимости от координат клика
    public void changeRoom(MouseEvent e) {
        if (e.getX() > 240 && e.getX() < 480 && e.getY() > 240 && e.getY() < 300) {
            currentRoom = 0;
            opened = false;
        }
        if (e.getX() > 240 && e.getX() < 480 && e.getY() > 300 && e.getY() < 360) {
            currentRoom = 1;
            opened = false;
        }
        if (e.getX() > 240 && e.getX() < 480 && e.getY() > 360 && e.getY() < 420) {
            currentRoom = 2;
            opened = false;
        }
        if (e.getX() > 240 && e.getX() < 480 && e.getY() > 420 && e.getY() < 480) {
            currentRoom = 3;
            opened = false;
        }
    }
}
