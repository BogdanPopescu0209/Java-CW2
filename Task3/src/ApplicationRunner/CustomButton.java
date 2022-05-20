package ApplicationRunner;

import javafx.scene.control.Button;

public class CustomButton extends Button {

    public CustomButton(String label, double w, double h) {

        super(label);
        this.setMinWidth(w);
        this.setMinHeight(h);
        this.setMaxWidth(w);
        this.setMaxHeight(h);
        this.setPrefWidth(w);
        this.setPrefHeight(h);

    }

}
