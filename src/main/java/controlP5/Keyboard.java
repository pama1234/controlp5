package controlP5;

import processing.core.PGraphics;
import processing.core.PVector;

public class Keyboard extends Controller<Keyboard> {

    public int hoveredNote = -1;

    public Keyboard(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        this.setView(new KeyboardView());
    }


    @Override
    public void draw(PGraphics theGraphics) {
        theGraphics.pushMatrix();
        theGraphics.translate(x(position), y(position));
        _myControllerView.display(theGraphics, this);
        theGraphics.popMatrix();
    }


    @Override
    protected void onPress() {

        float mouseX = getPointer().x();
        float mouseY = getPointer().y();

        int note = getNoteFromMousePosition(mouseX, mouseY);
        if (note != hoveredNote) {
            hoveredNote = note;
            System.out.println(note);
        }

    }

    @Override
    protected void onMove() {
        float mouseX = getPointer().x();
        float mouseY = getPointer().y();

        int note = getNoteFromMousePosition(mouseX, mouseY);
        if (note != hoveredNote) {
            hoveredNote = note;
            System.out.println(note);
        }

    }


    public int getNoteFromMousePosition(float mouseX, float mouseY) {

        for (int i = KeyboardView.keyPositions.length - 1; i >= 0; i--) {
            PVector[] positions = KeyboardView.keyPositions[i];
            PVector upperLeft = positions[0];
            PVector lowerRight = positions[1];

            if (mouseX >= upperLeft.x * getWidth() && mouseX <= lowerRight.x * getWidth() && mouseY >= upperLeft.y * getHeight() && mouseY <= lowerRight.y * getHeight()) {
                if (i == 0) {
                    return 0;
                } else if (i == 1) {
                    return(2);
                } else if (i == 2) {
                    return(4);
                } else if (i == 3) {
                    return(5);
                } else if (i == 4) {
                    return(7);
                } else if (i == 5) {
                    return(9);
                } else if (i == 6) {
                    return(11);
                } else if (i == 7) {
                    return(1);
                } else if (i == 8) {
                    return(3);
                } else if (i == 9) {
                    return(6);
                } else if (i == 10) {
                    return(8);
                } else if (i == 11) {
                    return(10);
                }
                break;
            }
        }
        return -1;
    }

    static public int indexMapping(int note) {
        switch (note) {
            case 0: return 0;
            case 2: return 1;
            case 4: return 2;
            case 5: return 3;
            case 7: return 4;
            case 9: return 5;
            case 11: return 6;
            case 1: return 7;
            case 3: return 8;
            case 6: return 9;
            case 8: return 10;
            case 10: return 11;

            default: return -1;
        }
    }
}


class KeyboardView implements ControllerView<Keyboard> {

    static public PVector[][] keyPositions = new PVector[][]{
            //C key
            new PVector[]{
                    new PVector(0, 0), new PVector(1 / 7f, 1)
            },
            //D key
            new PVector[]{
                    new PVector(1 / 7f, 0), new PVector(2 / 7f, 1)
            },
            //E key
            new PVector[]{
                    new PVector(2 / 7f, 0), new PVector(3 / 7f, 1)
            },
            //F key
            new PVector[]{
                    new PVector(3 / 7f, 0), new PVector(4 / 7f, 1)
            },
            //G key
            new PVector[]{
                    new PVector(4 / 7f, 0), new PVector(5 / 7f, 1)
            },
            //A key
            new PVector[]{
                    new PVector(5 / 7f, 0), new PVector(6 / 7f, 1)
            },
            //B key
            new PVector[]{
                    new PVector(6 / 7f, 0), new PVector(1, 1)
            },
            //C# key
            new PVector[]{
                    new PVector((0.5f) / 7f, 0), new PVector((0.5f) / 7f + 1 / 7f, 0.5f)
            },
            //D# key
            new PVector[]{
                    new PVector((1.5f) / 7f, 0), new PVector((1.5f) / 7f + 1 / 7f, 0.5f)
            },
            //F# key
            new PVector[]{
                    new PVector((3.5f) / 7f, 0), new PVector((3.5f) / 7f + 1 / 7f, 0.5f)
            },
            //G# key
            new PVector[]{
                    new PVector((4.5f) / 7f, 0), new PVector((4.5f) / 7f + 1 / 7f, 0.5f)
            },
            //A# key
            new PVector[]{
                    new PVector((5.5f) / 7f, 0), new PVector((5.5f) / 7f + 1 / 7f, 0.5f)
            },

    };

    @Override
    public void display(PGraphics theGraphics, Keyboard theController) {
        theGraphics.pushMatrix();
        float[] absolutePosition = theController.getAbsolutePosition();
        theGraphics.translate(absolutePosition[0], absolutePosition[1]);

        theGraphics.stroke(0);
        theGraphics.strokeWeight(0.25f);

        for (int i = 0; i < keyPositions.length; i++) {
            PVector[] positions = keyPositions[i];
            PVector upperLeft = positions[0];
            PVector lowerRight = positions[1];


            if (i == theController.indexMapping(theController.hoveredNote)) {
                theGraphics.fill(100);
            } else {
                if (i >= 7) {
                    theGraphics.fill(0);
                    theGraphics.stroke(255);
                } else {
                    theGraphics.fill(255);
                    theGraphics.stroke(0);
                }
            }

            theGraphics.rect(upperLeft.x * theController.getWidth(), upperLeft.y * theController.getHeight(), (lowerRight.x - upperLeft.x) * theController.getWidth(), (lowerRight.y - upperLeft.y) * theController.getHeight());
        }

        theGraphics.popMatrix();
    }


}