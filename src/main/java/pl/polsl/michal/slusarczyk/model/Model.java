package pl.polsl.michal.slusarczyk.model;

import pl.polsl.michal.slusarczyk.model.enums.Difficulty;
import pl.polsl.michal.slusarczyk.model.enums.FieldState;

/**
 * Class representing model.
 * Updates:
 * Moved part about view to controller.
 * 
 * Update method about Difficulty to new types of it
 * Deleted validate methods
 * 
 * @author Michał Ślusarczyk
 * @version 1.1
 */
public class Model {
    /**
     * Class field of the game field.
    */
    private Game game;
    
    /**
     * Getter of field 'game'.
     * @return Returning value of the field.
    */
    public Game getGame()
    {
        return game;
    }
    
    /**
     * Setter of field 'game'.
     * @param value New value of the class field.
    */
    public void setGame(Game value)
    {
        game = value;
    }
    
    /**
     * Constructor of the model class.
    */   
    public Model()
    {
        game = new Game();
    }
    
    /**
     * Method returning corrected format of the input.
     * @param text Text needed to by convert on correct information.
     * @return Single letter informing about type of interaction player want to do.
    */ 
    public char returnClick(String text)
    {
        return text.charAt(0);
    }
    
    /**
     * Method returning corrected format of the input.
     * @param text Text needed to by convert on correct information
     * @return Vertical coordinate to identify specific map field.
    */ 
    public int returnCoordinate(String text)
    {
        return (Integer.valueOf(text)-1);
    }
    
    /**
     * Method returning corrected format of the information about difficulty level.
     * @param text Text representing level of difficulty.
     * @return Enumerated type of difficulty level.
    */ 
    public Difficulty returnDifficulty(String text)
    {
        Difficulty diff;
        
         switch (text) 
                {
                case "Easy":
                    diff = Difficulty.EASY;
                    break;
                case "Medium":
                    diff = Difficulty.MEDIUM;
                    break;
                case "Hard":
                    diff = Difficulty.HARD;
                    break;
                default:
                    diff = Difficulty.NONE;
                    break;
                }
        
        return diff;
    }
    
    public String returnGameStateText(FieldState fieldState)
    {

        String result = "X";
        
        switch (fieldState) 
        {
        case EMPTY:
            result = (" ");
            break;
        case EXPLODED:
            result = ("B");
            break;
        case FLAG:
            result = ("F");
            break;
        case UNKNOWN:
            result = (" ");
            break;
        case ONE:
            result = ("1");
            break;
        case TWO:
            result = ("2");
            break;
        case THREE:
            result = ("3");
            break;
        case FOUR:
            result = ("4");
            break;
        case FIVE:
            result = ("5");
            break;
        case SIX:
            result = ("6");
            break;
        case SEVEN:
            result = ("7");
            break;
        case EIGHT:
            result = ("8");
            break;
        default:
            break;
        }
        
        return result;
    }
}
