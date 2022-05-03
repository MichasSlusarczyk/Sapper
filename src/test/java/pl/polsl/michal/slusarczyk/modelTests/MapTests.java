/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.michal.slusarczyk.modelTests;
import pl.polsl.michal.slusarczyk.model.Map;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Class for testing public methods of class map.
 * @version 1.1
 * Updates:
 * 
 * Update setUp method to updated model.
 * 
 * @author Michal_Slusarczyk
 */
public class MapTests 
{
    /**
     * private field for testing methods of this type object.
     */
    private Map map;

     /**
     * Method which initialize the map field before each test.
     */   
    @BeforeEach
    public void setUp() 
    {
        map = new Map();
    }
    
    /**
     * Testing situation when coordinates have negative values.
     * @param x Coordinate x
     * @param y Coordinate y 
     */
    @ParameterizedTest
    @CsvSource({"-1,-1", "-5,-6", "-3,-1"})
    public void addBombsNegativeInputTest(int x, int y) 
    {
        assertTrue((x<0 || y<0), "The inputs are negative!");
    }
    
    /**
     * Testing situation when coordinates are bigger than the map size.
     * @param x Coordinate x
     * @param y Coordinate y 
     */
    @ParameterizedTest
    @CsvSource({"11,11", "1000,500", "3111,1222"})
    public void addBombsOverflowInputTest(int x, int y) 
    {
        assertTrue((x>10 || y>10), "The input values are too big!");

    }
    
    /**
     * Testing situation when input data are correct.
     * @param x Coordinate x
     * @param y Coordinate y 
     */
    @ParameterizedTest
    @CsvSource({"1,1", "5,10", "3,2"})
    public void addBombsCorrectInputTest(int x, int y) 
    {
        try 
        {
            map.addBombs(x, y);
        } 
        catch (Exception e)
        {
            fail("An exception shouldn't appear when input data are correct!");
        }
    }

    /**
     * Testing correctness of addBomb method result.
     * @param x Coordinate x
     * @param y Coordinate y 
     */
    @ParameterizedTest
    @CsvSource({"1,1", "5,10", "3,2"})
    public void addBombsCorrectInplementationTest(int x, int y) 
    {
        map.addBombs(x, y);
        int numOfBombs = countBombsOnTheMap();
        assertEquals(map.getFields(x,y).getBomb(), false,"After adding bombs there shouldn't be any at starting field.");
        assertEquals(map.getNumberOfBombs(), numOfBombs,"After bomb addition, there should be exactly the same amount of bombs on the map that was expected.");
    } 
    
    /**
    * Private method for counting number of bombs to check bomb adding method.
    */   
    private int countBombsOnTheMap()
    {
        int counter = 0;
        for(int i = 0; i < map.getSizeW(); i++)
        {
            for(int j = 0; j < map.getSizeH(); j++)
            {
                if(map.getFields(i, j).getBomb()==true)
                {
                 counter = counter + 1;
                }
            }  
        }
        
        return counter;
    }
}
