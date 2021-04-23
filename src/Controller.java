import java.awt.*;
import java.awt.event.KeyEvent;

public class Controller {
    KeyboardFocusManager keyboardManager;

    public Controller(PlayerCharacter playerCharacter) {
        keyboardManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardManager.addKeyEventDispatcher(new KeyEventDispatcher() {
            //изменение координат игрока в зависимости от нажатой клавиши
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    if ((e.getKeyCode() == KeyEvent.VK_UP) && (playerCharacter.y + playerCharacter.height > playerCharacter.room.height - playerCharacter.room.playZone)){
                        playerCharacter.y = playerCharacter.y - 2;
                    }
                    if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (playerCharacter.x > 0)){
                        playerCharacter.x = playerCharacter.x - 2;
                        playerCharacter.direction = -1;
                    }
                    if ((e.getKeyCode() == KeyEvent.VK_RIGHT) &&(playerCharacter.x + playerCharacter.width< playerCharacter.room.width)) {
                        playerCharacter.x = playerCharacter.x + 2;
                        playerCharacter.direction = 1;
                    }
                    if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (playerCharacter.y+ playerCharacter.height<playerCharacter.room.height-40)) {
                        playerCharacter.y = playerCharacter.y + 2;
                    }
                }
                return false;
            }
        });
    }
}