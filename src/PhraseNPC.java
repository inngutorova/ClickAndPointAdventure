import java.awt.*;
import java.util.ArrayList;

public class PhraseNPC {
    String text;


    public PhraseNPC(String text) {
        this.text = text;
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

                if (stringWidth > width - 100) {
                    text = text.substring(strings.get(n).length() + 1);
                    n = n + 1;
                    strings.add("");
                }
            }
        }
        strings.set(n, text);
        for (int i = 0; i <= strings.size() - 1; i++) {
            g.drawString(strings.get(i), x + 10, y + 40 + i * 20);
        }
    }
}
