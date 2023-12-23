package core.others;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Zoom {

    public static void zoomOut() {
        try {
            Robot robot = new Robot();
            for (int i = 0; i < 4; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }
}
