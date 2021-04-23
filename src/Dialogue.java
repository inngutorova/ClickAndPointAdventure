import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dialogue {
    int n = 0;
    int type;
    ArrayList<Phrase> phrases0;
    ArrayList<PhraseNPC> phrases1;
    boolean npcLine = true;
    int end = -1;
    boolean ended = false;
    boolean firstCheck = false;



    Dialogue(int type) {
        this.type = type;
        if (type==0) {
            phrases0 = new ArrayList<>();
        }
        if (type==1) {
            phrases1 = new ArrayList<>();
        }

    }

    //считывание диалога с файла txt и распределение реплик и ответов по соответствующим классам
    public void read(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        String s;
        int k = -1;
        while ((s = br.readLine()) != null) {
            k = k + 1;
            if (s.equals("")) {
                k = -1;
            } else {
                if (k == 0) {
                    n = Integer.parseInt(s);
                }
                if (type == 0) {
                    if (k == 1) {
                        phrases0.add(n, new Phrase(s));
                    }
                    if (k == 2) {
                        phrases0.get(n).response = new Response[Integer.parseInt(s)];
                        for (int i = 0; i < phrases0.get(n).response.length; ++i) {
                            phrases0.get(n).response[i] = new Response();
                            phrases0.get(n).response[i].height = 100 / phrases0.get(n).response.length;
                            phrases0.get(n).response[i].y = 720 - (phrases0.get(n).response.length - i) * phrases0.get(n).response[i].height-25;
                        }
                    }
                    if (k >= 3) {
                        for (int i = 0; i < phrases0.get(n).response.length; ++i) {
                            if (k == 3 + 2 * i) {
                                phrases0.get(n).response[i].nextId = Integer.parseInt(s);
                            }
                            if (k == 3 + 2 * i + 1) {
                                phrases0.get(n).response[i].str = s;
                            }
                        }
                    }
                }
                if (type == 1) {
                    if (k == 1) {
                        phrases1.add(n, new PhraseNPC(s));
                    }
                }
            }
        }
        end = n+1;
        n = 0;
    }

    //проверка клика для смены фразы / фразы на ответ и наоборот
    public void checkClick(MouseEvent e) {
        if(type == 0) {
            if (npcLine) {
                if (n == 0) {
                    if (firstCheck) {
                        npcLine = false;
                    }
                    firstCheck = true;
                } else npcLine = false;
            } else {
                int y = e.getY();
                for (int i = 0; i < phrases0.get(n).response.length; ++i) {
                    if (!npcLine && y > 720 - 40 - (i + 1) * phrases0.get(n).response[i].height && y < 720 - 40 - i * phrases0.get(n).response[i].height) {
                        n = phrases0.get(n).response[phrases0.get(n).response.length - i - 1].nextId;
                        System.out.println(n);
                        npcLine = true;
                    }
                }
            }
            if (phrases0.get(n).response.length == 0 && npcLine == false) {
                ended = true;
            }
        }
        if (type == 1) {

            if (n == 0) {
                if (firstCheck) {
                    n = n + 1;
                }
                firstCheck = true;
            } else n = n + 1;
            if (n == end) {
                ended = true;
            }
        }
    }

    //отрисовка
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 620 - 40, 720, 100);
        g.setColor(Color.black);
        if (type == 0) {
            if (npcLine) {
                phrases0.get(n).writeInRect(g, phrases0.get(n).text, 0, 720 - 165, 680, 100);
            } else {
                for (int i = 0; i < phrases0.get(n).response.length; ++i) {
                    phrases0.get(n).response[i].writeInRect(g);
                }
            }
        }
        if (type == 1) {
            phrases1.get(n).writeInRect(g, phrases1.get(n).text, 0, 720 - 165, 680, 160);
        }
    }

}
