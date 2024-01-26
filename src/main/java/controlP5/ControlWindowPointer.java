package controlP5;

/**
 * A pointer by default is linked to the mouse and
 * stores the x and y position as well as the pressed
 * and released state. The pointer can be accessed by
 * its getter method {@link ControlWindow#getPointer()}.
 * Then use
 * {@link ControlWindow#set(int, int)} to
 * alter its position or invoke {
 * {@link ControlWindow#pressed()} or
 * {@link ControlWindow#released()} to change
 * its state. To disable the mouse and enable the
 * Pointer use {@link ControlWindow#enable()}
 * and {@link ControlWindow#disable()} to
 * default back to the mouse as input parameter.
 */
// TODO offset against pgx and pgy
public class ControlWindowPointer {

    private final ControlWindow controlWindow;

    public ControlWindowPointer(ControlWindow controlWindow) {
        this.controlWindow = controlWindow;
    }

    public ControlWindowPointer setX(int theX) {
        controlWindow.mouseX = theX;
        return this;
    }

    public ControlWindowPointer setY(int theY) {
        controlWindow.mouseY = theY;
        return this;
    }

    public int getY() {
        return controlWindow.mouseY;
    }

    public int getX() {
        return controlWindow.mouseX;
    }

    public int getPreviousX() {
        return controlWindow.pmouseX;
    }

    public int getPreviousY() {
        return controlWindow.pmouseY;
    }

    public ControlWindowPointer set(int theX, int theY) {
        setX(theX);
        setY(theY);
        return this;
    }

    // TODO mousePressed/mouseReleased are handled wrongly, released is called when moved, for now do not use, instead use set(x,y), pressed(), released()
    public ControlWindowPointer set(int theX, int theY, boolean pressed) {
        setX(theX);
        setY(theY);
        if (pressed) {
            if (!controlWindow.mousePressed) {
                pressed();
            }
        } else {
            if (controlWindow.mousePressed) {
                released();
            }
        }
        return this;
    }

    public ControlWindowPointer pressed() {
        controlWindow.mousePressedEvent();
        return this;
    }

    public ControlWindowPointer released() {
        controlWindow.mouseReleasedEvent();
        return this;
    }

    public void enable() {
        controlWindow.isMouse = false;
    }

    public void disable() {
        controlWindow.isMouse = true;
    }

    public boolean isEnabled() {
        return !controlWindow.isMouse;
    }
}
