package sketch;


import controlP5.*;
import controlP5.layout.LayoutBuilder;
import processing.core.PApplet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Sketch extends PApplet {

    static public void main(String[] args) {

        PApplet.main("sketch.Sketch");
    }

    ControlP5 cp5;

    public void settings() {
        pixelDensity(displayDensity());
        size(1280, 820);
    }
    public void setup() {
        cp5 = new ControlP5(this);
        cp5.enableShortcuts();

        LayoutBuilder builder = new LayoutBuilder(this, cp5);

        try {
            Path xmlPath = Paths.get("src/main/resources/test.xxml");
            String xmlContent = new String(Files.readAllBytes(xmlPath));
            builder.parseXML(xmlContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        Keyboard keyboard2 = new Keyboard(cp5, "keyboard2");
        keyboard2.setPosition(0, 0);
        keyboard2.setSize(100, 40);
        keyboard2.setRange(20, 32);


        Textfield textfield1 = new Textfield(cp5, "textfield1");
        textfield1.setPosition(0, 50);
        textfield1.setSize(200, 40);


        MultilineTextfield multilineTextfield1 = new MultilineTextfield(cp5, "multilineTextfield1");
        multilineTextfield1.setPosition(250, 250);
        multilineTextfield1.setSize(200, 200);



    }


    public void draw() {



    }
}
