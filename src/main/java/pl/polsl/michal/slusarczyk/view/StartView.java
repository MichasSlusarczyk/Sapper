package pl.polsl.michal.slusarczyk.view;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import pl.polsl.michal.slusarczyk.controller.StartController;

/**
 * Class being view for first part of the game.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class StartView extends GridPane 
{
    
    /**
    * Class field being control for choose difficulty label.
    */
    private final Label chooseDifficultyLabel = new Label();
    /**
    * Class field being control for choose difficulty combobox.
    */
    final ComboBox chooseDifficultyComboBox = new ComboBox();
    /**
    * Class field being control for start button.
    */   
    private final Button startButton = new Button();

    /**
    * Constructor of StartView class.
    */
    public StartView(final StartController controller) 
    {
        this.setMinWidth(300);
        this.setMinHeight(200);
        this.setAlignment(Pos.CENTER);
  
        chooseDifficultyLabel.setText("Choose difficulty:");
        chooseDifficultyLabel.setAlignment(Pos.CENTER);    


        chooseDifficultyComboBox.getItems().addAll(
            "Easy",
            "Medium",
            "Hard"
        );   
        chooseDifficultyComboBox.setValue("Easy");

        startButton.setAlignment(Pos.CENTER);
        startButton.setText("Start!");
        controller.setStartButtonHandler(startButton);

        GridPane.setRowIndex(chooseDifficultyLabel, 0);
        GridPane.setRowIndex(chooseDifficultyComboBox, 1);
        GridPane.setRowIndex(startButton, 2);

        this.getChildren().add(0,chooseDifficultyLabel);       
        this.getChildren().add(1,chooseDifficultyComboBox);
        this.getChildren().add(2,startButton);


        GridPane.setHalignment(this.getChildren().get(0), HPos.CENTER);
        GridPane.setValignment(this.getChildren().get(0), VPos.CENTER);

        GridPane.setHalignment(this.getChildren().get(1), HPos.CENTER);
        GridPane.setValignment(this.getChildren().get(1), VPos.CENTER);

        GridPane.setHalignment(this.getChildren().get(2), HPos.CENTER);
        GridPane.setValignment(this.getChildren().get(2), VPos.CENTER);
    }

    /**
     * Getter of field 'startButton'.
     * @return Returning value of the field.
    */
    public Button getStartButton() 
    {
        return startButton;
    }

    /**
     * Getter of field 'chooseDifficultyComboBox'.
     * @return Returning value of the field.
    */
    public ComboBox getChooseDifficultyComboBox() {
        return chooseDifficultyComboBox;
    }
}