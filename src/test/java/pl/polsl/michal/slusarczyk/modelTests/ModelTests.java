/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.michal.slusarczyk.modelTests;
import pl.polsl.michal.slusarczyk.model.Model;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.michal.slusarczyk.model.enums.Difficulty;

/**
 * Class for testing public methods of class Model.
 * @version 1.1
 * Updates:
 * 
 * Deleted tests for validation of validating methods.
 * Update setUp method to updated model.
 * 
 * @author Michal_Slusarczyk
 */
public class ModelTests {
    
    /**
     * private field for testing methods of this type object.
     */
    private Model model;
    
     /**
     * Method which initialize the Model object before each test.
     */  
    @BeforeEach
    public void setUp() 
    {
        model = new Model();
        model.getGame().setDifficulty(Difficulty.EASY);
        model.getGame().startGame();
        model.getGame().firstManipulationMap(3, 4);
    }
    
     /**
     * Method testing what will happen when tested method input is incorrect.
     * @param input Input string to be parsed
     */ 
    @ParameterizedTest
    @ValueSource(strings = {"test", "D"}) // add "" 
    public void returnClickCorrectInputTest(String input) 
    {
        try 
        {
            model.returnClick(input);
        } 
        catch (Exception e)
        {
            fail("An exception shouldn't be thrown when the input is string.");
        }
    }

     /**
     * Method testing what will happen when tested method input is incorrect.
     * @param input Input string to be parsed
     */ 
    @ParameterizedTest
    @ValueSource(strings = {"test", "D"}) // add "" 
    public void returnCoordinateIncorrectInputTest(String input) 
    {
        try 
        {
            model.returnCoordinate(input);
            fail("An exception should be thrown when the input is non-number.");
        } 
        catch (Exception e)
        {
        }
    }
   
    /**
     * Method testing what will happen tested method when input is correct.
     * @param input Input string to be parsed
     */ 
    @ParameterizedTest
    @ValueSource(strings = {"12", "3"}) // add "" 
    public void returnCoordinateCorrectInputTest(String input) 
    {
        try 
        {
            model.returnCoordinate(input);
        } 
        catch (Exception e)
        {
            fail("An exception shouldn't be thrown when the input is number.");
        }
    }

    /**
     * Method testing what will happen when tested method input is incorrect.
     * @param input Input string to be parsed
     */ 
    @ParameterizedTest
    @ValueSource(strings = {"test", "DDDDD"}) // add "" 
    public void returnDifficultyIncorrectInputTest(String input) 
    {
        assertEquals(Difficulty.NONE, model.returnDifficulty(input), "In situation when input is diffrent than posibble typres of difficulties method should return NONE difficulty.");
    }
    
    /**
     * Method testing what will happen when tested method input is correct.
     * @param input Input string to be parsed
     */ 
    @ParameterizedTest
    @ValueSource(strings = {"very easy", "easy", "hard", "medium"}) // add "" 
    public void returnDifficultyCorrectInputTest(String input) 
    {
        assertNotEquals(Difficulty.NONE, model.returnDifficulty(input), "In situation when input doesn't match any posiible game difficulty.");
    }
}
