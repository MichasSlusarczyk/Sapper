package pl.polsl.michal.slusarczyk.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pl.polsl.michal.slusarczyk.controller.EndController;

/**
 * Class being view for last part of the game.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class EndView extends GridPane {
       
    /**
    * Class field being control for game status label.
    */
    private final Label gameStatusLabel = new Label();
        /**
    * Class field being control for restart button.
    */
    private final Button restartButton = new Button();

    /**
    * Constructor of EndView class.
    */
    public EndView(final EndController controller) 
    {
        
        this.setMinWidth(300);
        this.setMinHeight(200);
        this.setAlignment(Pos.CENTER);

        gameStatusLabel.setText("");
        gameStatusLabel.setAlignment(Pos.CENTER);   

        restartButton.setText("Restart!");
        restartButton.setAlignment(Pos.CENTER);
        controller.setRestartButtonHandler(restartButton);
        
        GridPane.setRowIndex(gameStatusLabel, 0);
        GridPane.setRowIndex(restartButton, 1);

        this.getChildren().add(0,gameStatusLabel);       
        this.getChildren().add(1,restartButton);
    }

    /**
     * Getter of field 'restartButton'.
     * @return Returning value of the field.
    */
    public Button getRestartButton() {
        return restartButton;
    }

    /**
     * Setter of field 'gameStatusLabel'.
     * @param value New value of the class field.
    */    
    public void setgameStatusLabelText(String text) {
        gameStatusLabel.setText(text);
    }
}