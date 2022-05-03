package pl.polsl.michal.slusarczyk.controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.polsl.michal.slusarczyk.model.Model;
import pl.polsl.michal.slusarczyk.view.StartView;

/**
 * Class being controller for start view.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class StartController
{
    /**
    * Class field storing stage.
    */
    private final Stage stage;
    /**
    * Class field storing model.
    */
    private final Model model;
    /**
    * Class field storing view.
    */
    private final StartView startView;

    /**
    * Constructor of StartController class.
    */
    public StartController(final Stage stage) {
        model = new Model();      
        startView = new StartView(this);
        
        stage.setResizable(false);
        stage.setTitle("SAPPER");
        
        this.stage = stage;
    }
    
    /**
    * Constructor of StartController class.
    */
    public StartController(final Stage stage, Model model) {
        this.model = model;      
        startView = new StartView(this);
        
        this.stage = stage;
    }
    
    /**
     * Getter of field 'stage'.
     * @return Returning value of the field.
    */
    public Stage getStage() 
    {
        return stage;
    }

    /**
     * Getter of field 'startView'.
     * @return Returning value of the field.
    */
    public StartView getStartView() 
    {
        return startView;
    }
       
    /**
     * Getter of field 'model'.
     * @return Returning value of the field.
    */
    public Model getModel() {
        return model;
    }

    /**
     * Method for setting event handler to start button.
     * @param button Button to which the event is assigned.
    */   
    public void setStartButtonHandler(Button button) 
    {
        final EventHandler<MouseEvent> eventHandler = (final MouseEvent event) -> {
            model.getGame().setDifficulty(model.returnDifficulty(startView.getChooseDifficultyComboBox().getValue().toString()));
            model.getGame().startGame();
            final GameController gameController = new GameController(stage, model);
            final Scene scene = new Scene(gameController.getGameView());
            stage.setScene(scene);  
        };
        
        button.setOnMouseClicked(eventHandler);
    }
}