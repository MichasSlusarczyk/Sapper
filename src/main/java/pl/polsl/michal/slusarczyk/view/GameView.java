package pl.polsl.michal.slusarczyk.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import pl.polsl.michal.slusarczyk.controller.GameController;
import pl.polsl.michal.slusarczyk.model.Model;
import pl.polsl.michal.slusarczyk.model.enums.FieldState;

/**
 * Class being view for main part of the game.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class GameView extends GridPane 
{
    /**
    * Class field being control for declarated bomb counter label.
    */
    private final Label declaratedBombCounterLabel = new Label();
    /**
    * Class field being control for instant restart button.
    */
    private final Button instantRestartButton = new Button();
    /**
    * Class field being control for timer counter label.
    */
    private final Label timeCounterLabel = new Label();
    /**
    * Class field being control for game map gridpane.
    */
    private final GridPane gameMapGridPane = new GridPane();
    
    /**
    * Class field for start time of the timer.
    */
    private static final int STARTTIME = 0;
    /**
    * Class field for timeline.
    */
    private Timeline timeline;
    /**
    * Class field for past time in seconds.
    */
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    
     /**
    * Constructor of GameView class.
    */   
    public GameView(final GameController controller) {
        
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100/3);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(100/3);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(100/3);
        
        this.getColumnConstraints().addAll(col1, col2, col3);
        

        GridPane.setRowIndex(declaratedBombCounterLabel, 0);
        GridPane.setColumnIndex(declaratedBombCounterLabel, 0);   
        GridPane.setHalignment(declaratedBombCounterLabel, HPos.CENTER);
        GridPane.setHgrow(declaratedBombCounterLabel, Priority.ALWAYS); 
        
        GridPane.setRowIndex(instantRestartButton, 0);
        GridPane.setColumnIndex(instantRestartButton, 1);
        GridPane.setHalignment(instantRestartButton, HPos.CENTER);
        GridPane.setHgrow(instantRestartButton, Priority.ALWAYS);

        GridPane.setRowIndex(timeCounterLabel, 0);
        GridPane.setColumnIndex(timeCounterLabel, 2);       
        GridPane.setHalignment(timeCounterLabel, HPos.CENTER);
        GridPane.setHgrow(timeCounterLabel, Priority.ALWAYS); 
        
        GridPane.setRowIndex(gameMapGridPane, 1);
        GridPane.setColumnIndex(gameMapGridPane, 0); 
        GridPane.setColumnSpan(gameMapGridPane,3);
        GridPane.setHalignment(gameMapGridPane, HPos.CENTER);
        GridPane.setHgrow(gameMapGridPane, Priority.ALWAYS);  

        declaratedBombCounterLabel.setText(((Integer)controller.getModel().getGame().getMap().getNumberOfBombs()).toString());

        instantRestartButton.setText("Restart");
        instantRestartButton.setAlignment(Pos.CENTER);
        controller.setRestartButtonHandler(instantRestartButton);
        
        timeCounterLabel.setText("0");
        
        gameMapGridPane.setHgap(1);
        gameMapGridPane.setVgap(1);
        
        for (int r = 0; r < controller.getModel().getGame().getMap().getSizeW(); r++) {
            for (int c = 0; c < controller.getModel().getGame().getMap().getSizeH(); c++) 
            {
                Button button = new Button();
                button.setId("MapField");
                button.setMinHeight(25);
                button.setMinWidth(25);
                button.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #eeeeee; ");
                controller.setMapFieldButtonHandler(button);
                gameMapGridPane.add(button, c, r);         
            }
        }     
        
        this.add(declaratedBombCounterLabel, 0, 0);
        this.add(instantRestartButton, 1, 0);
        this.add(timeCounterLabel, 2, 0);
        this.add(gameMapGridPane, 0, 1);  
    }

    /**
     * Getter of field 'gameMapGridPane'.
     * @return Returning value of the field.
    */
    public GridPane getGameMapGridPane() {
        return gameMapGridPane;
    } 
    
    /**
     * Method for updating each of the buttons being map fields.
     * @param model Model of the program.
    */
    public void updateGameMapGridPane (Model model) 
    {
        ObservableList<Node> childrens = gameMapGridPane.getChildren();
       
        childrens.forEach(node -> {
            int rowIndex = GridPane.getRowIndex(node);
            int colIndex = GridPane.getColumnIndex(node);
            
            Button b = (Button) node;
            FieldState fieldState = model.getGame().getMap().getFields(rowIndex, colIndex).getState();

            
            if(null == fieldState)
            {
                b.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #999999; -fx-text-fill: #ffffff ");
            }
            else switch (fieldState) {
                case UNKNOWN:
                case FLAG:
                    b.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #eeeeee; ");
                    break;
                case EMPTY:
                    b.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #555555; ");
                    break;
                default:
                    b.setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #999999; -fx-text-fill: #ffffff ");
                    break;
            }

            b.setText(model.returnGameStateText(fieldState));
            
            
            declaratedBombCounterLabel.setText(((Integer)model.getGame().getMap().getNumberOfMarkedFlags()).toString());
        }); 
    }
    
    /**
     * Method for incrementing time.
    */
    private void updateTime() 
    {
    int seconds = timeSeconds.get();
    timeSeconds.set(seconds+1);
    }

    /**
     * Method for starting timer.
    */
    public void updateTimer()  
    {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> updateTime())); 
        timeline.setCycleCount(Animation.INDEFINITE);
        timeSeconds.set(STARTTIME);
        timeline.play();
        timeCounterLabel.textProperty().bind(timeSeconds.asString());
    }
    
    /**
     * Method for stop timer.
    */    
    public void stopTimer()
    {
        timeline.stop();
    }
}