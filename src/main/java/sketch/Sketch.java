package sketch;


import controlP5.*;
import controlP5.layout.LayoutBuilder;
import processing.core.PApplet;


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
            builder.parseXML("<Window>" +
                    "<Group   width=100% height=33%   y=40px  background=rgb(0,223,0)>" + "<Textfield   ></Textfield>"+ "</Group>" +
                    "<Button></Button>" +
                    "<Group  hideBar width=100% height=33%  y=300px background=rgb(12,0,0)>" +"</Group>" +
                    "<Group  hideBar width=100% height=33%  y=600px background=rgb(12,0,223)>" +"</Group>" +
                    "<Textfield></Textfield>" +
                    "</Window>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }





    }

    public void radioButton(int index) {

    }

    public void draw() {

    }
}
