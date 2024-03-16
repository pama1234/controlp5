package controlP5;

import processing.core.PGraphics;
import processing.core.PVector;
import java.util.ArrayList;

public class Keyboard extends Controller<Keyboard> {

    public static class Key{
        public int pitch;
        public PVector leftTop;
        public PVector rightBottom;

        public Key(int pitch, PVector leftTop, PVector rightBottom){
            this.pitch = pitch;
            this.leftTop = leftTop;
            this.rightBottom = rightBottom;
        }

    }

    public ArrayList<Key> keys = new ArrayList<>();


    public int hoveredNote = -1;

    public Keyboard(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        this.setView(new KeyboardView());
    }


    @Override
    public void draw(PGraphics theGraphics) {

        _myControllerView.display(theGraphics, this);

    }


    @Override
    protected void onPress() {

        float mouseX = getPointer().x();
        float mouseY = getPointer().y();

        int note = getNoteFromMousePosition(mouseX, mouseY);

        setValue(note);



    }

    @Override
    protected void onMove() {
        float mouseX = getPointer().x();
        float mouseY = getPointer().y();

        int note = getNoteFromMousePosition(mouseX, mouseY);
        if (note != hoveredNote) {
            hoveredNote = note;
        }

    }


    public int getNoteFromMousePosition(float mouseX, float mouseY) {
        for (int i = 0; i < keys.size(); i++) {
            Key key = keys.get(i);

            // Scale unitary coordinates to actual size
            float x = key.leftTop.x * getWidth();
            float y = key.leftTop.y * getHeight();
            float width = (key.rightBottom.x - key.leftTop.x) * getWidth();
            float height = (key.rightBottom.y - key.leftTop.y) * getHeight();

            if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                return key.pitch;
            }
        }
        return -1;

    }

    public Keyboard setRange(int minPitch, int maxPitch) {
        keys = buildKeys(minPitch, maxPitch);
        return this;
    }

    static ArrayList<Keyboard.Key> buildKeys(int minPitch, int maxPitch) {
        ArrayList<Keyboard.Key> keys = new ArrayList<>();
        float unitWidth = 1.0f / (maxPitch - minPitch + 1); // Unit width for each key

        for (int i = minPitch; i <= maxPitch; i++) {
            int noteIndex = i - minPitch;
            float xPosition = noteIndex * unitWidth;

            float keyHeight = 1f;
            keys.add(new Keyboard.Key(i, new PVector(xPosition, 0), new PVector(xPosition + unitWidth, keyHeight)));
        }
        return keys;
    }


}


class KeyboardView implements ControllerView<Keyboard> {





    @Override
    public void display(PGraphics theGraphics, Keyboard theController) {
        theGraphics.pushMatrix();
        theGraphics.pushStyle();

        float[] absolutePosition = theController.getAbsolutePosition();
        theGraphics.translate(absolutePosition[0], absolutePosition[1]);

        theGraphics.stroke(0);
        theGraphics.strokeWeight(0.25f);

        theGraphics.rect(0, 0, theController.getWidth(), theController.getHeight());

        for (int i = 0; i < theController.keys.size(); i++) {
            Keyboard.Key key = theController.keys.get(i);

            // Scale unitary coordinates to actual size
            float x = key.leftTop.x * theController.getWidth();
            float y = key.leftTop.y * theController.getHeight();
            float width = (key.rightBottom.x - key.leftTop.x) * theController.getWidth();
            float height = (key.rightBottom.y - key.leftTop.y) * theController.getHeight();

            if (key.pitch % 12 == 0 || key.pitch % 12 == 2 || key.pitch % 12 == 4 || key.pitch % 12 == 5 || key.pitch % 12 == 7 || key.pitch % 12 == 9 || key.pitch % 12 == 11) {
                theGraphics.fill(255); // White key
            } else {
                theGraphics.fill(0); // Black key
            }
            if (key.pitch == theController.hoveredNote) {
                theGraphics.fill(127); // Highlight hovered note
            }

            theGraphics.rect(x, y, width, height);
            theGraphics.fill(0);

            String noteName = pitchToNoteName(key.pitch);
            theGraphics.textSize(7);
            float textWidth = theGraphics.textWidth(noteName);

            theGraphics.text(noteName, x + width / 2 - textWidth / 2, y + height / 2);
        }

        theGraphics.popStyle();
        theGraphics.popMatrix();
    }

    private String pitchToNoteName(int pitch) {
        String[] noteNames = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A","A#", "B"};
        int octave = pitch / 12;
        int noteIndex = pitch % 12;
        return noteNames[noteIndex] + octave;
    }


}


