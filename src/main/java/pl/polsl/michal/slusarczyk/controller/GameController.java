package pl.polsl.michal.slusarczyk.controller;

import pl.polsl.michal.slusarczyk.view.GameView;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.polsl.michal.slusarczyk.model.Model;
import pl.polsl.michal.slusarczyk.model.enums.Difficulty;
import pl.polsl.michal.slusarczyk.model.enums.GameState;

/**
 * Class being controller for game view.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class GameController
{
    /**
    * Class field storing stage.
    */
    private final Stage stage;
    /**
    * Class field storing model.
    */
    private Model model;
    /**
    * Class field storing game view.
    */
    private final GameView gameView;
    /**
    * Class field storing logic value to check that the first field on the map was checked.
    */
    boolean firstField = false;

    /**
    * Constructor of GameController class.
    */
    public GameController(final Stage stage) 
    {   
        gameView = null;       
        this.stage = stage;
    }
    
    /**
    * Constructor of GameController class.
    */
    public GameController(final Stage stage, Model model) 
    {
        this.model = model;      
        gameView = new GameView(this);
        
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
     * Getter of field 'gameView'.
     * @return Returning value of the field.
    */
    public GameView getGameView() 
    {
        return gameView;
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
            Difficulty temp = model.getGame().getDifficulty();
            model = new Model(); 
            model.getGame().setDifficulty(temp);
            model.getGame().startGame();
            firstField = false;    
            gameView.updateGameMapGridPane(model);
            gameView.stopTimer();
        };
        
        button.setOnMouseClicked(eventHandler);
    }
    
    /**
     * Method for setting event handler to field of the map.
     * @param button Button to which the event is assigned.
    */   
    public void setMapFieldButtonHandler(Button button) 
    {
        final EventHandler<MouseEvent> eventHandler = (final MouseEvent event) -> {
            Node node = (Node) event.getSource(); //button
            
            int rowIndex = GridPane.getRowIndex(node);
            int colIndex = GridPane.getColumnIndex(node); 
            
            if(firstField == false)
            {
                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    model.getGame().firstManipulationMap(rowIndex, colIndex);
                    gameView.updateTimer();
                    firstField = true;
                }
            }
            else
            {
                char click = 'D';
                
                if (event.getButton().equals(MouseButton.PRIMARY))
                {
                    click = 'D';
                }
                if (event.getButton().equals(MouseButton.SECONDARY))
                {
                    click = 'F';
                }
                model.getGame().manipulateField(rowIndex, colIndex, click);
            }
            
            gameView.updateGameMapGridPane(model);
            
            GameState gameState = model.getGame().getGameState();
            
            if(gameState == GameState.WON)
            {
                Stage stage = new Stage();
                final EndController endController = new EndController(stage, this.stage, model);
                endController.getEndView().setgameStatusLabelText("You won!");
                final Scene scene = new Scene(endController.getEndView());    
                gameView.stopTimer();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
            else if(gameState == GameState.LOST)
            {
                Stage stage = new Stage();
                final EndController endController = new EndController(stage, this.stage, model);
                endController.getEndView().setgameStatusLabelText("You lost!");
                final Scene scene = new Scene(endController.getEndView());
                gameView.stopTimer();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        };
        
        button.setOnMouseClicked(eventHandler);
    }
}