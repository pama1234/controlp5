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
        size(1280, 820);
    }
    public void setup() {
        cp5 = new ControlP5(this);
        LayoutBuilder builder = new LayoutBuilder(this, cp5);

        try {
            Path xmlPath = Paths.get("src/main/resources/test.xxml");
            String xmlContent = new String(Files.readAllBytes(xmlPath));
            builder.parseXML(xmlContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }


    public void draw() {

    }
}
