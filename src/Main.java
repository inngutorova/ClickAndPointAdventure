
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String path = new String();
        path = "G:\\Java\\ClickAndPointAdventure\\src\\";  // + in Inventory

        JFrame frame = new JFrame();
        frame.setTitle("Click and Point Adventure");
        frame.setSize(720 + 15, 720 + 40);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BufferedImage paper = ImageIO.read(new File(path + "Pictures\\paper.png"));

        ArrayList<ClickableObject> objects0 = new ArrayList<>();
        ArrayList<ClickableObject> objects1 = new ArrayList<>();
        ArrayList<ClickableObject> objects2 = new ArrayList<>();
        ArrayList<ClickableObject> objects3 = new ArrayList<>();


        objects1.add(0, new ClickableObject(300, 270, 15, 15,
                ImageIO.read(new File(path + "Pictures\\key1.png")),
                paper, "Ключ, которым была заперта изнутри комната мистера Ли. Отпечатков на нем не обнаружили, но на конце ключа есть мелкие царапины.", new ClickListener() {
            @Override
            public void handleClick() {
            }
        }));

        objects1.add(1, new ClickableObject(348, 479, 341, 177,
                ImageIO.read(new File(path + "Pictures\\Trash.png")),
                paper, "Явные следы борьбы", new ClickListener() {
            @Override
            public void handleClick() {

            }
        }));

        ArrayList<Dialogue> dInspector = new ArrayList<>();
        dInspector.add(new Dialogue(1));
        dInspector.get(0).read(path + "(1)Диалог Сагдена и Джонсона.txt");
        dInspector.add(new Dialogue(0));
        dInspector.get(1).read(path + "(2) Диалог гг и Сагдена.txt");
        dInspector.add(new Dialogue(0));
        dInspector.get(2).read(path + "(4)Диалог о силе старика.txt");
        dInspector.add(new Dialogue(1));
        dInspector.get(3).read(path + "(9) Замечание Сагдена о Гарри.txt");
        dInspector.add(new Dialogue(1));
        dInspector.get(4).read(path + "(11) Разговор после допроса Магдалины.txt");
        dInspector.add(new Dialogue(0));
        dInspector.get(5).read(path+ "Колышек и резинка.txt");
        NPC inspectorSugden = new NPC(400, 250, 220, "Инспектор Сагден", 60, ImageIO.read(new File(path + "Pictures\\Sugden.png")), dInspector);
        inspectorSugden.currentDialogue = 0;

        ArrayList<Dialogue> dJohnson = new ArrayList<>();
        dJohnson.add(new Dialogue(0));
        dJohnson.get(0).read(path + "(3) Разговор после нахождения ключа.txt");
        dJohnson.add(new Dialogue(1));
        dJohnson.get(1).read(path + "(5) Знакомство с Альфредом и Лидией.txt");
        NPC johnson = new NPC(50, 250, 105, "Полковник Джонсон", -10, ImageIO.read(new File(path + "Pictures\\Johnson.png")), dJohnson);

        ArrayList<Dialogue> dAlfred = new ArrayList<>();
        dAlfred.add(new Dialogue(1));
        dAlfred.add(new Dialogue(0));
        dAlfred.get(0).read(path + "Не знакомы.txt");
        dAlfred.get(1).read(path + "(6) Допрос Альфреда.txt");
        NPC alfred = new NPC(200, 250, 220, "Альфред Ли", 60, ImageIO.read(new File(path + "Pictures\\Alfred.png")), dAlfred);
        alfred.currentDialogue = 0;

        ArrayList<Dialogue> dLydia = new ArrayList<>();
        dLydia.add(new Dialogue(1));
        dLydia.get(0).read(path + "Не знакомы.txt");
        dLydia.add(new Dialogue(1));
        dLydia.get(1).read(path + "Лидия.txt");
        NPC lydia = new NPC(300, 250, 220, "Лидия Ли", 70, ImageIO.read(new File(path + "Pictures\\Lydia.png")), dLydia);
        lydia.currentDialogue = 0;

        ArrayList<Dialogue> dGeorge = new ArrayList<>();
        dGeorge.add(new Dialogue(1));
        dGeorge.get(0).read(path + "Не знакомы.txt");
        dGeorge.add(new Dialogue(0));
        dGeorge.get(1).read(path + "(7) Допрос Джорджа.txt");
        NPC george = new NPC(250, 250, 220, "Джордж Ли", 60, ImageIO.read(new File(path + "Pictures\\George.png")), dGeorge);
        george.currentDialogue = 0;

        ArrayList<Dialogue> dHarry = new ArrayList<>();
        dHarry.add(new Dialogue(1));
        dGeorge.get(0).read(path + "Не знакомы.txt");
        dHarry.add(new Dialogue(0));
        dHarry.get(1).read(path + "(8) Допрос Гарри.txt");
        NPC harry = new NPC(500, 250, 220, "Гарри Ли", 60, ImageIO.read(new File(path + "Pictures\\Harry.png")), dHarry);
        harry.currentDialogue = 0;

        ArrayList<Dialogue> dMagdalene = new ArrayList<>();
        dMagdalene.add(new Dialogue(1));
        dMagdalene.get(0).read(path + "Не знакомы.txt");
        dMagdalene.add(new Dialogue(0));
        dMagdalene.get(1).read(path + "(10) Допрос Магдалины.txt");
        NPC magdalene = new NPC(470,250,90, "Магдалина Ли", -10, ImageIO.read(new File(path + "Pictures\\Magdalene.png")), dMagdalene);
        magdalene.currentDialogue = 0;

//        ArrayList<Dialogue> dDavid = new ArrayList<>();
//        dDavid.add(new Dialogue(1));
//        dDavid.get(0).read(path + "Не знакомы.txt");
//        dDavid.add(new Dialogue(0));
//        dDavid.get(1).read(path + "(12) Допрос Дэвида.txt");
//        NPC david = new NPC(450,240,250, "Дэвид Ли", 60, ImageIO.read(new File(path + "Pictures\\bc21448253860e54346553ab36f74392-man-jacket-tie-trousers-flat-by-vexels.png")), dDavid);
//        david.currentDialogue = 0;
//
//        ArrayList<Dialogue> dHilda = new ArrayList<>();
//        dHilda.add(new Dialogue(1));
//        dHilda.get(0).read(path + "Не знакомы.txt");
//        dHilda.add(new Dialogue(0));
//        dHilda.get(1).read(path + "(13) Допрос Хильды.txt");
//        NPC hilda = new NPC(450,240,250, "Хильда Ли", 60, ImageIO.read(new File(path + "Pictures\\bc21448253860e54346553ab36f74392-man-jacket-tie-trousers-flat-by-vexels.png")), dDavid);
//        hilda.currentDialogue = 0;
//
        ArrayList<Dialogue> dPilar = new ArrayList<>();
        dPilar.add(new Dialogue(1));
        dPilar.get(0).read(path + "Не знакомы.txt");
        dPilar.add(new Dialogue(0));
        dPilar.get(1).read(path + "(12) Допрос Пилар.txt");
        NPC pilar = new NPC(450,240,250, "Пилар Эстравадос", 60, ImageIO.read(new File(path + "Pictures\\bc21448253860e54346553ab36f74392-man-jacket-tie-trousers-flat-by-vexels.png")), dPilar);
        pilar.currentDialogue = 0;

        ArrayList<NPC> npc0 = new ArrayList<>();
        npc0.add(inspectorSugden);
        npc0.add(johnson);

        ArrayList<NPC> npc1 = new ArrayList<>();

        ArrayList<NPC> npc2 = new ArrayList<>();
        npc2.add(alfred);
        npc2.add(lydia);
        npc2.add(george);
        npc2.add(harry);
        npc2.add(magdalene);
//      npc2.add(david);
//      npc2.add(hilda);

        ArrayList<NPC> npcDefault = new ArrayList<>();


        Room[] rooms = new Room[4];
        rooms[0] = new Room("Холл", ImageIO.read(new File(path + "Pictures\\Hall.png")), objects0, npc0);
        rooms[1] = new Room("Комната Симеона Ли", ImageIO.read(new File(path + "Pictures\\Li Bedroom.png")), objects1, npc1);
        rooms[2] = new Room("Гостинная", ImageIO.read(new File(path + "Pictures\\Living Room.png")), objects2, npc2);
        rooms[3] = new Room("Кабинет", ImageIO.read(new File(path + "Pictures\\Office.png")), objects3, npcDefault);
        Map map = new Map(rooms);
        PlayerCharacter playerCharacter = new PlayerCharacter(map.rooms[map.currentRoom]);
        Inventory inventory = new Inventory(new ArrayList<>());

        MyPanel panel = new MyPanel(playerCharacter, map, inventory);
        frame.add(panel);
        frame.setVisible(true);


        boolean theyLeftTheHall = false;
        boolean dialogueAboutKeyHappend = false;
        boolean sawAMess = false;
        boolean meetingInTheOffice = false;
        boolean gotDarker1 = false;
        boolean gotLighter1 = false;
        boolean gotDarker2 = false;
        boolean gotLighter2 = false;
        boolean gotDarker3 = false;
        boolean gotLighter3 = false;
        boolean gotDarker4 = false;
        boolean gotLighter4 = false;
        boolean gotDarker5 = false;
        boolean gotLighter5 = false;
        boolean gotDarker6 = false;
        boolean gotLighter6 = false;
        boolean gotDarker7 = false;
        boolean gotLighter7 = false;
        boolean gotDarker8 = false;
        boolean gotLighter8 = false;

        boolean sugdenLeftOffice = false;
        boolean sugdenNewD = false;
        boolean addedDiamonds = false;



        while (true) {
            Thread.sleep(1000 / 400);
            frame.repaint();

            //System.out.println(inspectorSugden.dialogues[1].ended);

            if (inspectorSugden.dialogues.get(0).ended && inspectorSugden.currentDialogue == 0) {
                inspectorSugden.currentDialogue = 1;
            }

            if (inspectorSugden.dialogues.get(1).ended && map.currentRoom != 0 && !theyLeftTheHall) {
                theyLeftTheHall = true;
                System.out.println("!!");
                map.rooms[0].npc.remove(inspectorSugden);
                map.rooms[0].npc.remove(johnson);
                map.rooms[1].npc.add(johnson);
                map.rooms[1].npc.add(inspectorSugden);

            }

            if (theyLeftTheHall && map.rooms[1].objects.get(0).got && !dialogueAboutKeyHappend) {
                dialogueAboutKeyHappend = true;
                johnson.currentDialogue = 0;
                System.out.println("??");
            }

            if (theyLeftTheHall && map.rooms[1].objects.get(1).got && !sawAMess) {       
                System.out.println("?!");
                sawAMess = true;
                inspectorSugden.currentDialogue = 2;
            }


            if (!meetingInTheOffice && dialogueAboutKeyHappend && sawAMess && map.currentRoom != 1) {
                map.rooms[1].npc.remove(johnson);
                map.rooms[1].npc.remove(inspectorSugden);
                map.rooms[2].npc.remove(alfred);
                map.rooms[2].npc.remove(lydia);
                map.rooms[3].npc.add(johnson);
                map.rooms[3].npc.add(inspectorSugden);
                map.rooms[3].npc.add(alfred);
                map.rooms[3].npc.add(lydia);
                johnson.currentDialogue = 1;
                meetingInTheOffice = true;
            }

            if (johnson.dialogues.get(1).ended) {
                lydia.dialogues.get(0).ended = true;
                alfred.currentDialogue = 1;
            }

            if (alfred.dialogues.get(1).ended && !gotDarker1) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker1 = true;
                    panel.gettingDarker = false;
                    map.rooms[3].npc.remove(alfred);
                    map.rooms[3].npc.remove(lydia);
                    map.rooms[2].npc.remove(george);
                    map.rooms[3].npc.add(george);
                    george.currentDialogue = 1;
                }
            }

            if (gotDarker1 && !gotLighter1) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter1 = true;
                    panel.gettingLighter = false;
                }
            }

            if (george.dialogues.get(1).ended && !gotDarker2) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker2 = true;
                    panel.gettingDarker = false;
                    map.rooms[3].npc.remove(george);
                    map.rooms[2].npc.remove(harry);
                    map.rooms[3].npc.add(harry);
                    harry.x = 300;
                    harry.currentDialogue = 1;
                }
            }

            if (gotDarker2 && !gotLighter2) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter2 = true;
                    panel.gettingLighter = false;
                }
            }

            if (harry.dialogues.get(1).ended && !gotDarker3) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker3 = true;
                    panel.gettingDarker = false;
                    map.rooms[3].npc.remove(harry);
                    inspectorSugden.currentDialogue = 3;
                }
            }

            if (gotDarker3 && !gotLighter3) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter3 = true;
                    panel.gettingLighter = false;
                }
            }

            if (inspectorSugden.dialogues.get(3).ended && !gotDarker4) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker4 = true;
                    panel.gettingDarker = false;
                    map.rooms[2].npc.remove(magdalene);
                    map.rooms[3].npc.add(magdalene);
                    magdalene.x = 300;
                    magdalene.currentDialogue = 1;
                }
            }

            if (gotDarker4 && !gotLighter4) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter4 = true;
                    panel.gettingLighter = false;
                }
            }

            if (magdalene.dialogues.get(1).ended && !gotDarker5) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker5 = true;
                    panel.gettingDarker = false;
                    map.rooms[3].npc.remove(magdalene);
                    map.rooms[3].npc.add(pilar);
                    pilar.currentDialogue = 1;
                }
            }

            if (gotDarker5 && !gotLighter5) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter5 = true;
                    panel.gettingLighter = false;
                }
            }

            if (pilar.dialogues.get(1).ended && !gotDarker6) {
                panel.gettingDarker = true;
                if (panel.visibility == 254) {
                    gotDarker6 = true;
                    panel.gettingDarker = false;
                    map.rooms[3].npc.remove(pilar);
                    inspectorSugden.currentDialogue = 4;
                    ClickableObject stick = new ClickableObject(100,550,30,30,
                            ImageIO.read(new File(path + "Pictures\\Stick.png")),
                            paper, "Деревянный колышек", new ClickListener() {
                        @Override
                        public void handleClick() {
                        }});

                    ClickableObject rubber = new ClickableObject(250, 550,30,30,
                            ImageIO.read(new File(path + "Pictures\\Rubber.png")),
                            paper, "Кусочек какой-то резины", new ClickListener() {
                        @Override
                        public void handleClick() {
                        }});
                    map.rooms[1].objects.add(stick);
                    map.rooms[1].objects.add(rubber);


                }
            }

            if (gotDarker6 && !gotLighter6) {
                panel.gettingLighter = true;
                if (panel.visibility == 1) {
                    gotLighter6 = true;
                    panel.gettingLighter = false;
                }
            }

            if(map.currentRoom != 3 && gotLighter6 && !sugdenLeftOffice) {
                map.rooms[3].npc.remove(inspectorSugden);
                map.rooms[1].npc.add(inspectorSugden);

                sugdenLeftOffice = true;
            }

            if(gotDarker6 && map.rooms[1].objects.get(2).got && map.rooms[1].objects.get(2).got && !sugdenNewD) {
                inspectorSugden.currentDialogue = 5;
                sugdenNewD = true;
            }

            if(inspectorSugden.currentDialogue == 5 && !addedDiamonds) {
                map.rooms[3].npc.remove(johnson);
                map.rooms[3].npc.add(lydia);
                ClickableObject garden = new ClickableObject(550,260,70,70,
                        ImageIO.read(new File(path + "Pictures\\Garden.png")),
                        paper, "Сад в миниатюре Лидии Ли", new ClickListener() {
                    @Override
                    public void handleClick() {
                    }});
                map.rooms[3].objects.add(garden);
                ClickableObject diamonds = new ClickableObject(560,270,30,30,
                        ImageIO.read(new File(path + "Pictures\\Diamonds.png")),
                        paper, "Пропавшие брилианты найдены в миниатюрном саде Лидии Ли", new ClickListener() {
                    @Override
                    public void handleClick() {
                    }});
                map.rooms[3].objects.add(diamonds);
                addedDiamonds = true;
            }

            if(addedDiamonds && map.rooms[3].objects.get(1).got && lydia.currentDialogue==0) {
                lydia.currentDialogue = 1;
            }




//            if (inspectorSugden.dialogues.get(4).ended && !gotDarker6) {
//                panel.gettingDarker = true;
//                if (panel.visibility == 254) {
//                    gotDarker6 = true;
//                    panel.gettingDarker = false;
//                    map.rooms[2].npc.remove(david);
//                    map.rooms[3].npc.add(david);
//                    david.currentDialogue = 1;
//                }
//            }
//
//            if (gotDarker6 && !gotLighter6) {
//                panel.gettingLighter = true;
//                if (panel.visibility == 1) {
//                    gotLighter6 = true;
//                    panel.gettingLighter = false;
//                }
//            }
//
//            if (david.dialogues.get(1).ended && !gotDarker7) {
//                panel.gettingDarker = true;
//                if (panel.visibility == 254) {
//                    gotDarker7 = true;
//                    panel.gettingDarker = false;
//                    map.rooms[3].npc.remove(david);
//                    map.rooms[3].npc.add(hilda);
//                    hilda.currentDialogue = 1;
//                }
//            }
//
//            if (gotDarker7 && !gotLighter7) {
//                panel.gettingLighter = true;
//                if (panel.visibility == 1) {
//                    gotLighter7 = true;
//                    panel.gettingLighter = false;
//                }
//            }
//
//            if (hilda.dialogues.get(1).ended && !gotDarker8) {
//                panel.gettingDarker = true;
//                if (panel.visibility == 254) {
//                    gotDarker8 = true;
//                    panel.gettingDarker = false;
//                    map.rooms[3].npc.remove(hilda);
//                    map.rooms[3].npc.add(pilar);
//                    pilar.currentDialogue = 1;
//                }
//            }
//
//            if (gotDarker8 && !gotLighter8) {
//                panel.gettingLighter = true;
//                if (panel.visibility == 1) {
//                    gotLighter8 = true;
//                    panel.gettingLighter = false;
//                }
//            }


        }
    }
}
