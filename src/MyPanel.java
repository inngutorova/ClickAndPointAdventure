import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MyPanel extends JPanel implements MouseListener {
    Map map;
    Room room;
    PlayerCharacter playerCharacter;
    Inventory inventory;
    Button ending;
    boolean start = true;
    boolean gettingDarker = false;
    boolean gettingLighter = false;
    int visibility = 0;
    String start1 = "24 декабря. Вы в гостях у своего друга и коллеги - полковника Джонсона, начальника полиции Миддлшира. Вы обсуждаете старые дела, но все же радуетесь подаренным Рождеством праздникам. Ведь казалось бы, какие могут быть преступления в Рождество? В комнату заходит камердинер полковника и докладывает о звонке от инспектора Сагдена. Извинившись, начальник полиции вышел, а когда вернулся, рассказал о произошедшем убийстве.";
    String start2 = "«Жертва - старый Симеон Ли. Один из самых богатых людей в наших краях. Сколотил себе состояние еще в Южной Африке. На золоте… Нет, на алмазах, по-моему. Заработал огромные деньги, придумав какую-то штуку в горнодобывающем оборудовании. По-моему, сам изобрел. Во всяком случае, получил за это кучу денег. Говорят, у него не один миллион. Он был довольно странным человеком. Последние годы болел. Я знаю о нем очень мало. Но, конечно, он был видной фигурой в нашем графстве. Мне нужно немедленно отправляться в Лонгдейл».";
    String start3 = "Становится очевидно, что полковник хочет, чтобы вы тоже поехали. Пусть формально дело ведет инспектор Сагден, но вы можете выступать в качестве неофициального консультанта.";
    boolean end = false;
    boolean right = false;
    boolean wrong = false;
    boolean firstEnd = false;


    public MyPanel(PlayerCharacter playerCharacter, Map map, Inventory inventory) throws IOException {
        this.map = map;
        this.playerCharacter = playerCharacter;
        this.inventory = inventory;
        this.room = playerCharacter.room;
        ending = new Button(100, 720 - 40, 470, 40, "Сделать выбор", 160, new ClickListener() {
            @Override
            public void handleClick() {
                end = true;
            }
        });
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            playerCharacter.update();    //обновление игрока
        } catch (IOException e) {
            e.printStackTrace();
        }

        room.draw(g);        //отрисовка комнаты

        //отрисовка игрока и npc, порядок зависит от координат
        for (int i = 0; i < room.npc.size(); ++i) {
            if (room.npc.get(i).y + room.npc.get(i).height < playerCharacter.y + playerCharacter.height) {
                room.npc.get(i).draw(g);
            }
        }

        playerCharacter.draw(g);

        for (int i = 0; i < room.npc.size(); ++i) {
            if (room.npc.get(i).y + room.npc.get(i).height >= playerCharacter.y + playerCharacter.height) {
                room.npc.get(i).draw(g);
            }
        }

        //отрисовка сообщения при нахождении предмета
        for (int i = 0; i < room.objects.size(); ++i) {
            room.objects.get(i).drawMessage(g);
        }

        //отрисовка всех нижних кнопок
        map.button.draw(g);
        inventory.button.draw(g);
        ending.draw(g);

        //отрисовка раскрытых карты и инвентаря
        if (map.opened) {
            map.draw(g);
        }
        inventory.update();
        inventory.draw(g);

        //отрисовка диалогов
        for (int i = 0; i < room.npc.size(); ++i) {
            if (room.npc.get(i).talking && room.npc.get(i).clicked && room.npc.get(i).currentDialogue != -1) {
                room.npc.get(i).dialogues.get(room.npc.get(i).currentDialogue).draw(g);
            }
        }

        //стартовое окно с сюжетным введением
        if (start) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 720, 720);
            g.setColor(Color.white);
            map.rooms[1].objects.get(0).writeInRect(g, start1, 30, 50, 520, 100);
            map.rooms[1].objects.get(0).writeInRect(g, start2, 30, 200, 530, 100);
            map.rooms[1].objects.get(0).writeInRect(g, start3, 30, 390, 530, 100);
        }

        //отрисовка затемнения
        if (gettingDarker) {
                visibility = visibility + 1;
            Color dark = new Color(0, 0, 0, visibility);
            g.setColor(dark);
            g.fillRect(0, 0, 720, 720);
        }

        if (gettingLighter) {
            visibility = visibility - 1;
            Color dark = new Color(0, 0, 0, visibility);
            g.setColor(dark);
            g.fillRect(0, 0, 720, 720);
        }

        //отрисовка выбора
        if(end) {
            g.setColor(Color.black);
            g.fillRect(0,0,720,720);
            g.setColor(Color.white);
            g.drawString("Кто же убийца?", 300, 300);
            g.drawString("Альфред Ли?", 300, 340);
            g.drawString("Джордж Ли?", 300, 360);
            g.drawString("Лидия Ли?", 300, 380);
            g.drawString("Магдалина Ли?", 300, 400);
            g.drawString("Гарри Ли?", 300, 420);
            g.drawString("Пилар Эстравадос?", 300, 440);
            g.drawString("Инспектор Сагден?", 300, 460);

        }

        if(right) {
            g.setColor(Color.black);
            g.fillRect(0,0,720,720);
            g.setColor(Color.white);
            g.drawString("Вы угадали!", 300, 320);
        }

        if(wrong) {
            g.setColor(Color.black);
            g.fillRect(0,0,720,720);
            g.setColor(Color.white);
            g.drawString("Вы не правы!", 300, 320);
        }


    }


    @Override
    public void mouseClicked(MouseEvent e) {

        if (start) {
            start = false;
        }

        //проверка клика по предметам и по кнопке ок при открытом сообщении, добавление собранных предметов в инвентарь
        for (int i = 0; i < room.objects.size(); ++i) {
            if (!room.objects.get(i).got) {
                room.objects.get(i).checkClick(e);
                if (room.objects.get(i).openedMessage) {
                    room.objects.get(i).ok.checkClick(e);
                }
            }
            if (room.objects.get(i).got && !room.objects.get(i).inInvent) {
                inventory.objects.add(room.objects.get(i));
                room.objects.get(i).inInvent = true;
            }
        }

        //проверка клика по предметам в инвентаре и по кнопке ок при открытом сообщении
        if (inventory.objects.size() != 0 && inventory.opened) {
            for (int i = 0; i < inventory.objects.size(); ++i) {
                inventory.objects.get(i).checkClick(e);
                if (inventory.objects.get(i).openedMessage) {
                    inventory.objects.get(i).ok.checkClick(e);
                }
            }
        }

        //проверка кнопок нижней панели
        map.button.checkClick(e);
        inventory.button.checkClick(e);
        ending.checkClick(e);

        //проверка кнопки "закрыть инвентарь"
        if (inventory.opened) {
            inventory.close.checkClick(e);
        }

        //смена комнаты
        if (map.opened) {
            map.changeRoom(e);
            room = map.rooms[map.currentRoom];
        }

        //проверка для начала диалога
        for (int i = 0; i < room.npc.size(); ++i) {
            if (room.npc.get(i).x < playerCharacter.x + 200 && room.npc.get(i).x > playerCharacter.x - 200 && !map.opened) {
                room.npc.get(i).checkClick(e);
                room.npc.get(i).talking = true;
            }
        }

        //проверка для переключения фраз в диалогах
        for (int i = 0; i < room.npc.size(); ++i) {
            if (room.npc.get(i).talking && room.npc.get(i).clicked && room.npc.get(i).currentDialogue != -1 && !map.opened) {
                room.npc.get(i).dialogues.get(room.npc.get(i).currentDialogue).checkClick(e);
            }
        }

        //проверка итогового выбора
        if (end && !right && !wrong) {
            if(firstEnd) {
                if (e.getY() > 450 && e.getY() < 470) {
                    right = true;
                } else wrong = true;
            }
            firstEnd = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
