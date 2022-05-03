package pl.polsl.michal.slusarczyk.controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.polsl.michal.slusarczyk.model.Model;
import pl.polsl.michal.slusarczyk.view.EndView;

/**
 * Class being controller for end view.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class EndController
{
    /**
    * Class field storing stage.
    */
    private final Stage stage;
    /**
    * Class field storing game stage to close.
    */
    private final Stage gameStage;
    /**
    * Class field storing model.
    */
    private Model model;
    /**
    * Class field storing view.
    */
    private final EndView endView;
    /**
    * Class field storing logic value to check that the first field on the map was checked.
    */
    boolean firstField = false;

    /**
    * Constructor of EndController class.
    */
    public EndController(final Stage stage, final Stage gameStage) {  
        endView = new EndView(this);
        
        this.stage = stage;
        this.gameStage = gameStage;
    }
    
    /**
    * Constructor of EndController class.
    */
    public EndController(final Stage stage, final Stage gameStage, Model model) {
        this.model = model;      
        endView = new EndView(this);
        
        this.stage = stage;
        this.gameStage = gameStage;
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
     * Getter of field 'endView'.
     * @return Returning value of the field.
    */   
    public EndView getEndView() 
    {
        return endView;
    }
  
    /**
     * Getter of field 'model'.
     * @return Returning value of the field.
    */   
    public Model getModel() {
        return model;
    }
   
    
    /**
     * Method for setting event handler to restart button.
     * @param button Button to which the event is assigned.
    */   
    public void setRestartButtonHandler(Button button) 
    {
        final EventHandler<MouseEvent> eventHandler = (final MouseEvent event) -> {
            model = new Model();
            firstField = false;           
            final StartController startController = new StartController(stage);
            final Scene scene = new Scene(startController.getStartView());
            stage.setScene(scene);  
            gameStage.close();
        };
        
        button.setOnMouseClicked(eventHandler);
    }
}