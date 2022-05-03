package pl.polsl.michal.slusarczyk.model;

import pl.polsl.michal.slusarczyk.model.enums.Difficulty;
import pl.polsl.michal.slusarczyk.model.enums.FieldState;
import pl.polsl.michal.slusarczyk.model.enums.GameState;

/**
 * Class representing sapper game.
 * 
 * Updates:
 * Added methods for counting marked bombs and counting flags.
 * Added method to checks if game was won by marking all correct flags. 
 * Splitted methods for generating map and initialzing them.
 * 
 * @author Michał Ślusarczyk
 * @version 1.1
 */
public class Game {
    /**
     * Class field storing information about game state.
    */
    private GameState gameState;
    /**
     * Class field storing information about time that has passed from start of the game.
    */
    private long time;
    /**
     * Class field representing map of the game.
    */
    private Map map;
    /**
     * Class field storing information about game difficulty.
    */
    private Difficulty diff;
    
    /**
     * Getter of field 'gameState'.
     * @return Returning value of the field.
    */
    public GameState getGameState()
    {
        return gameState;
    }
    
    /**
     * Setter of field 'gameState'.
     * @param value New value of the class field.
    */
    public void setGameState(GameState value)
    {
        gameState = value;
    }

    /**
     * Getter of field 'time'.
     * @return Returning value of the field.
    */
    public long getTime()
    {
        return time;
    }

    /**
     * Setter of field 'time'.
     * @param value New value of the class field.
    */
    public void setTime(long value)
    {
        time = value;
    }

    /**
     * Getter of field 'map'.
     * @return Returning value of the field.
    */    
    public Map getMap()
    {
        return map;
    }

    /**
     * Setter of field 'map'.
     * @param value New value of the class field.
    */    
    public void setMap(Map value)
    {
        map = value;
    }   

    /**
     * Getter of field 'diff'.
     * @return Returning value of the field.
    */    
    public Difficulty getDifficulty()
    {
        return diff;
    }

    /**
     * Setter of field 'diff'.
     * @param value New value of the class field.
    */    
    public void setDifficulty(Difficulty value)
    {
        diff = value;
        map = new Map(diff);
    }      
    
    /**
     * Constructor of the game class.
    */ 
    public Game()
    {
        gameState = GameState.NONE;
        map = null;
        diff = Difficulty.NONE;
    }
    
    /**
     * Method that starting the game with generating map and checking first field on the map.
    */ 
    public void startGame()
    {
        //StartTime;
        gameState = GameState.RUNNING;
        
        time = System.currentTimeMillis();
        
        generateMap(diff);
    }
    
    /**
     * Method that generating map based on the difficulty level which hidden bombs.
     * @param diff Difficulty of the game.
     * @param x Horizontal coordinate of the first map field.
     * @param y Vertical coordinate of the first map field.
    */ 
    private void generateMap(Difficulty diff)
    {
        map = new Map(diff);
        map.setNumberOfMarkedFlags(map.getNumberOfBombs());
    }
    
    public void firstManipulationMap(int x, int y)
    {
        map.addBombs(x, y);
        clickField(x, y);
    }
      
    /**
     * Method that generating map based on the difficulty level which hidden bombs.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
     * @param choice Choice between two types of interaction which field that user can do.
    */ 
    public void manipulateField(int x, int y, char choice)
    {
        if(choice=='D')
        {
            //Check filed if there is a bomb hidden.
            clickField(x, y);
        }
        else if(choice=='F')
        {
            //Flag field.
            flagField(x, y);
        }
    }

    /**
     * Method that changing state of the filed based on number of bombs in surrounding.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
    */    
    private void clickField(int x, int y)
    {
        //If field state is unknown then interaction have sens.
        if(map.getFields(x,y).getState().equals(FieldState.UNKNOWN))
        {
            //If there was a bomb player lost and game stops.
            if(map.getFields(x,y).getBomb()==true)
            {
                map.getFields(x,y).setState(FieldState.EXPLODED);
                gameState = GameState.LOST;
            }
            //Else count number of boomb in surrounding and update the field state.
            else
            {  
                    int bombCounter = countBombs(x, y);

                    //If there was not any bomb in surrounding discover surface without bombs.
                    if(bombCounter == 0)
                    {
                        discover(x, y);
                    }
                    else
                    //Else upadate field state.
                    {
                        caseBombCounter(x,y,bombCounter);
                        map.setDiscoveredFields(map.getDiscoveredFields()+1);
                    }        


                //Check if user won.
                checkWin();
            }
        }
    }
    
    /**
     * Method for checking that if the user won.
    */     
    private void checkWin()
    {
        //If user checks all fields without boombs the he won.
        if(map.getDiscoveredFields()>=map.getNumberOfFileldWithoutBombs())
        {
            gameState = GameState.WON;
        }      
    }
    
    /**
     * Method for checking that if the user won by marking all necessary flags.
    */     
    private void checkWinByFlags()
    {
        int numOfMarkedBombs = 0;
        
        countMarkedBombs();
        
        if(numOfMarkedBombs == map.getNumberOfBombs())
        {
            gameState = GameState.WON;            
        }
    }
    
    /**
     * Method for counting all bombs in nearest surrounding.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
     * @return Returning number of bombs in surrounding.
    */     
    private int countBombs(int x, int y)
    {      
        int bombCounter = 0;
        
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                int maskX = x-1+i;
                int maskY = y-1+j;
                
                if(maskX >= 0 && maskX < map.getSizeW() && maskY >= 0 && maskY < map.getSizeH())
                {
                    //If on specific field in surrounding state of bomb field is true then add to counter.
                    if(map.getFields(maskX,maskY).getBomb()==true)
                    {
                        if(i!=1 || j!=1)
                        {
                            bombCounter = bombCounter + 1;
                        }
                    }
                }
            }     
        }  
        
        return bombCounter;
    }
    
    /**
     * Method for counting all marked flags.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
    */     
    private void countFlags()
    {      
        int flagCounter = 0;
        
        for(int i=0; i<map.getSizeW(); i++)
        {
            for(int j=0; j<map.getSizeH(); j++)
            {

                    if(map.getFields(i,j).getState().equals(FieldState.FLAG))
                    {
                       flagCounter = flagCounter + 1;
                    }
            }
        }     
        
        map.setNumberOfMarkedFlags(map.getNumberOfBombs()-flagCounter);       
    }
    
            /**
     * Method for counting all bombs with are flagged.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
    */     
    private void countMarkedBombs()
    {      
        int markedBombCounter = 0;
        
        for(int i=0; i<map.getSizeW(); i++)
        {
            for(int j=0; j<map.getSizeH(); j++)
            {

                    if(map.getFields(i,j).getState().equals(FieldState.FLAG) && map.getFields(i,j).getBomb())
                    {
                        markedBombCounter = markedBombCounter + 1;
                    }
            }
        }     
         
        map.setNumberOfNotMarkedBombs(markedBombCounter);       
    }
   
    /**
     * Method used when in the nearest surrounding of the chosen field there is no bomb then a larger area is unfolding.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
    */  
    private void discover(int x, int y)
    {        
        int bombCounter = countBombs(x,y);
        //If there is no bomb in surrounding change field state to empty and invoke method for neighboring fields.
        if(bombCounter == 0)
        {
            map.getFields(x,y).setState(FieldState.EMPTY);
            map.setDiscoveredFields(map.getDiscoveredFields()+1);
            
            for(int i=0; i<3; i++)
            {
                for(int j=0; j<3; j++)
                {
                    int maskX = x-1+i;
                    int maskY = y-1+j;

                    if(maskX >= 0 && maskX < map.getSizeW() && maskY >= 0 && maskY < map.getSizeH())
                    {
                        if((i!=1 || j!=1) && map.getFields(maskX,maskY).getState().equals(FieldState.UNKNOWN))
                        {
                            discover(maskX, maskY);
                        }
                    }
                }     
            }  
        }
        //Or else change the state of the field based on number of counted bombs.
        else
        {
            caseBombCounter(x, y, bombCounter);
            map.setDiscoveredFields(map.getDiscoveredFields()+1);
        }
    }
  
    /**
     * Method used to flag field by user. After that he can not check it accidentally.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
    */  
    private void flagField(int x, int y)
    {     
        Field fields = map.getFields(x,y);
        
        if(fields.getState().equals(FieldState.UNKNOWN))
        {
            fields.setState(FieldState.FLAG);
            countFlags();
        }
        else if(fields.getState().equals(FieldState.FLAG))
        {
            fields.setState(FieldState.UNKNOWN);
           countFlags();
        }   
        
        checkWinByFlags();
    }
    
    /**
     * Method used to change state of the field based on number of bombs in surrounding.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
     * @param bombCounter Number of bombs in surrounding.
    */  
    private void caseBombCounter(int x, int y, int bombCounter)
    {     
        
        Field fields = map.getFields(x,y);
        
        switch (bombCounter) 
        {
        case 1:
            fields.setState(FieldState.ONE);
            break;
        case 2:
            fields.setState(FieldState.TWO);
            break;
        case 3:
            fields.setState(FieldState.THREE);
            break;
        case 4:
            fields.setState(FieldState.FOUR);
            break;
        case 5:
            fields.setState(FieldState.FIVE);
            break;
        case 6:
            fields.setState(FieldState.SIX);
            break;
        case 7:
            fields.setState(FieldState.SEVEN);
            break;
        case 8:
            fields.setState(FieldState.EIGHT);
            break;     
        default:
            break;
        }
    }
}
