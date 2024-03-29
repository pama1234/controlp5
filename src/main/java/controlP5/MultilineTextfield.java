package controlP5;

import processing.event.KeyEvent;

public class MultilineTextfield extends Textfield {

    public MultilineTextfield(ControlP5 theControlP5, String theName) {
        super(theControlP5, theName);
        _myValueLabel.setMultiline(true);
        TEXTALIGN = 0;
    }


}
