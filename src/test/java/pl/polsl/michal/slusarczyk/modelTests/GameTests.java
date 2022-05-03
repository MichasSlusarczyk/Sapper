/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.michal.slusarczyk.modelTests;
import pl.polsl.michal.slusarczyk.model.Game;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.michal.slusarczyk.model.enums.Difficulty;
import pl.polsl.michal.slusarczyk.model.enums.FieldState;

/**
 * Class for testing public methods of class game.
 * @version 1.1
 * Update:
 * 
 *  * Update setUp method to updated model.
 * 
 * @author Michal_Slusarczyk
 */
public class GameTests 
{
     /**
     * private field for testing methods of this type object.
     */
    private Game game1;
    private Game game2;

     /**
     * Method which initialize the game field before each test.
     */
    @BeforeEach
    public void setUp() 
    {
        game1 = new Game();
        game1.setDifficulty(Difficulty.EASY);
        game2 = new Game();
        game2.setDifficulty(Difficulty.EASY);
        game2.startGame();
        game2.firstManipulationMap(3, 4);
    }

     /**
     * Testing situation when coordinates have negative values.
     * @param x Coordinate x
     * @param y Coordinate y
     */

    @ParameterizedTest
    @CsvSource({"-1,-1", "-5,-6", "-3,-1"})
    public void startGameNegativeInputTest(int x, int y) 
    {
        try {
            game1.startGame();
            game1.firstManipulationMap(3, 4);
            fail("An exception should be thrown when the inputs are non-positive.");
                    
        } 
        catch (Exception e)
        {
            assertTrue((x<0 || y<0), "The inputs are negative!");
        }
    }

     /**
     * Testing situation when coordinates are bigger than the map size.
     * @param x Coordinate x
     * @param y Coordinate y
     */
    @ParameterizedTest
    @CsvSource({"200,345", "1000,500", "3111,1222"})
    public void startGameOverflowInputTest(int x, int y) 
    {
        try {
            game1.startGame();
            game1.firstManipulationMap(3, 4);
            fail("An exception should be thrown when the inputs are too big.");
        } 
        catch (Exception e)
        {
            assertTrue((x>=10 || y>=10), "The input values are too big!");
        }
    }
    
     /**
     * Testing situation when coordinates are correct.
     * @param x Coordinate x
     * @param y Coordinate y
     */
    @ParameterizedTest
    @CsvSource({"1,1", "5,10", "3,2"})
    public void startGameCorrectInputTest(int x, int y) 
    {
        try 
        {
            game1.startGame();
            game1.firstManipulationMap(3, 4);
        } 
        catch (Exception e)
        {
            fail("An exception appears when input data are correct!");
        }
    }
    
     /**
     * Testing if the method works correctly.
     * @param x Coordinate x
     * @param y Coordinate y
     */
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,2"})
    public void startGameCorrectImplementationTest(int x, int y) 
    {
        game1.startGame();
        game1.firstManipulationMap(3, 4);
        assertNotEquals(game1.getMap().getFields(x, y).getState(),FieldState.UNKNOWN,"After startiong the game the start field state should be different than unknown.");
    }
    
     /**
     * Testing situation when coordinates have negative values. 
     * @param x Coordinate x
     * @param y Coordinate y 
     * @param choice Choice made by user about type of action to do
     */
    @ParameterizedTest
    @CsvSource({"-1,-1,D", "-23,-54,D", "-200,-34,F"})
    public void manipulateFieldNegativeInputTest(int x, int y, char choice) 
    {      
        try 
        {
            game2.manipulateField(x, y, choice);
            fail("An exception should be thrown when the inputs are non-positive.");
        } 
        catch (Exception e)
        {
            assertTrue((x<0 || y<0), "The inputs are negative!");
        }
    }
    
     /**
     * Testing situation when coordinates are bigger than the map size.
     * @param x Coordinate x
     * @param y Coordinate y 
     * @param choice Choice made by user about type of action to do
     */
    @ParameterizedTest
    @CsvSource({"1000,200,D", "1340,340,D", "12,34,F"})
    public void manipulateFieldOverflowInputTest(int x, int y, char choice) 
    {     
        try 
        {
            game2.manipulateField(x, y, choice);
            fail("An exception should be thrown when the inputs are too big.");
        } 
        catch (Exception e)
        {
            assertTrue((x>10 || y>10), "The input values are too big!");
        }
    }
    
     /**
     * Testing situation when user answer is incorrect. 
     * @param x Coordinate x
     * @param y Coordinate y 
     * @param choice Choice made by user about type of action to do
     */
    @ParameterizedTest
    @CsvSource({"3,3,E", "3,3,W", "3,3,W"})
    public void manipulateFieldIncorrectChoiceTest(int x, int y, char choice) 
    {    
        try 
        {
            game2.manipulateField(x, y, choice);
            assertFalse('F' == choice || 'D' == choice , "The choice input is not equal to 'D' or 'F'!");
        } 
        catch (Exception e)
        {
            fail("An exception shouldn't be thrown when choice value is not equal to 'D' or 'F'.");
        }
    }
    
     /**
     * Testing situation when coordinates are correct.
     * @param x Coordinate x
     * @param y Coordinate y 
     * @param choice Choice made by user about type of action to do
     */
    @ParameterizedTest
    @CsvSource({"3,3,F", "1,3,D", "10,1,D"})
    public void manipulateFieldCorrectInputTest(int x, int y, char choice) 
    { 
        try 
        {
            game2.manipulateField(x, y, choice);
        } 
        catch (Exception e)
        {
            fail("An exception appears when input data are correct!");
        }
    }
    
     /**
     * Testing if the method works correctly.
     * @param x Coordinate x
     * @param y Coordinate y 
     * @param choice Choice made by user about type of action to do
     */
    @ParameterizedTest
    @CsvSource({"3,3,F", "1,3,D", "10,1,D"})
    public void manipulateFieldCorrectImplementationTest(int x, int y, char choice) 
    {
        game2.manipulateField(x, y, choice);
        assertNotEquals(game2.getMap().getFields(x, y).getState(),FieldState.UNKNOWN,"After manipulation of the field his state should be different than unknown.");
    }

}
