package pl.polsl.michal.slusarczyk.model;
import java.util.ArrayList;
import java.util.stream.Stream;
import pl.polsl.michal.slusarczyk.model.enums.Difficulty;

/**
  * Class representing map used to play sapper game.
  * Updates:
  * Changed 2D-array to arraylist of arraylists of fields 
  * Added forEach and stream
  * 
  * Update the difficulty levels
  * 
  * @author Michał Ślusarczyk
  * @version 1.2
 */
public class Map {
    /**
     * Horizontal size of the map.
    */
    private int sizeW;
    /**
     * Vertical size of the map.
    */
    private int sizeH;
    /**
     * Number of the map fields on the map.
    */
    private int mapSize;
    /**
     * Number of bombs hidden on the map.
    */
    private int numberOfBombs;
    /**
     * Number of marked flags on the map.
    */
    private int numberOfMarkedFlags;
    /**
     * Number of map fields without bombs.
    */
    private int numberOfFileldWithoutBombs;
    /**
     * Number of fields checked by player.
    */   
    private int discoveredFields;    
    /**
     * Number of bombs minus flags.
    */   
    private int numberOfNotMarkedBombs;
    /**
     * 2-dimensional array of fields representing the map.
    */
    private ArrayList<ArrayList<Field>> fields;
  
    /**
     * Getter of field 'sizeW'.
     * @return Returning value of the field.
    */
    public int getSizeW()
    {
        return sizeW;
    }
    
    /**
     * Setter of field 'sizeW'.
     * @param value New value of the class field.
    */
    public void setSizeW(int value)
    {
        sizeW = value;
    }
 
    /**
     * Getter of field 'sizeH'.
     * @return Returning value of the field.
    */
    public int getSizeH()
    {
        return sizeH;
    }
    
    /**
     * Setter of field 'sizeH'.
     * @param value New value of the class field.
    */    
    public void setSizeH(int value)
    {
        sizeH = value;
    }
 
    /**
     * Getter of field 'mapSize'.
     * @return Returning value of the field.
    */   
    public int getMapSize()
    {
        return mapSize;
    }

    /**
     * Setter of field 'mapSize'.
     * @param value New value of the class field.
    */    
    public void setMapSize(int value)
    {
        mapSize = value;
    }

    /**
     * Getter of field 'numberOfBombs'.
     * @return Returning value of the field.
    */    
    public int getNumberOfBombs()
    {
        return numberOfBombs;
    }

    /**
     * Setter of field 'numberOfBombs'.
     * @param value New value of the class field.
    */    
    public void setNumberOfBombs(int value)
    {
        numberOfBombs = value;
    }
    
    /**
     * Getter of field 'numberOfMarkedFlags'.
     * @return Returning value of the field.
    */    
    public int getNumberOfMarkedFlags()
    {
        return numberOfMarkedFlags;
    }

    /**
     * Setter of field 'numberOfMarkedFlags'.
     * @param value New value of the class field.
    */    
    public void setNumberOfMarkedFlags(int value)
    {
        numberOfMarkedFlags = value;
    }
    
    /**
     * Getter of field 'numberOfBombs'.
     * @return Returning value of the field.
    */    
    public int getNumberOfNotMarkedBombs()
    {
        return numberOfNotMarkedBombs;
    }

    /**
     * Setter of field 'numberOfBombs'.
     * @param value New value of the class field.
    */    
    public void setNumberOfNotMarkedBombs(int value)
    {
        numberOfNotMarkedBombs = value;
    }

    /**
     * Getter of field 'numberOfFileldWithoutBombs'.
     * @return Returning value of the field.
    */    
    public int getNumberOfFileldWithoutBombs()
    {
        return numberOfFileldWithoutBombs;
    }

    /**
     * Setter of field 'numberOfFileldWithoutBombs'.
     * @param value New value of the class field.
    */    
    public void setNumberOfFileldWithoutBombs(int value)
    {
        numberOfFileldWithoutBombs = value;
    }

    /**
     * Getter of field 'discoveredFields'.
     * @return Returning value of the field.
    */    
    public int getDiscoveredFields()
    {
        return discoveredFields;
    }

    /**
     * Setter of field 'discoveredFields'.
     * @param value New value of the class field.
    */    
    public void setDiscoveredFields(int value)
    {
        discoveredFields = value;
    }

    /**
     * Getter of field 'fields'.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
     * @return Returning value of the field.
    */    
    public Field getFields(int x, int y)
    {
        return fields.get(x).get(y);
    }

    /**
     * Setter of field 'fields'.
     * @param x Horizontal coordinate of the map field.
     * @param y Vertical coordinate of the map field.
     * @param value New value of the class field.
    */    
    public void setFields(int x, int y, Field value)
    {
        fields.get(x).set(y, value);
    }
    
    /**
    * Constructor of the map class.
    */
    public Map()
    {
        initializeMap(Difficulty.MEDIUM);
    }
  
    /**
     * Constructor of the map class.
     * @param diff Difficulty chosen by player before starting the game to initialize map.
    */
    public Map(Difficulty diff)
    {
        initializeMap(diff);
    }
    
    /**
     * Method to initialize map class fields which values according to the difficulty level.
     * @param diff Difficulty chosen by player before starting the game to initialize map.
    */
    private void initializeMap(Difficulty diff)
    {
        if(diff != Difficulty.NONE)  
        {
            switch (diff) 
            {
                case EASY:
                    sizeW = 8;
                    sizeH = 8;
                    numberOfBombs = 10;
                    break;
                case MEDIUM:
                    sizeW = 16;
                    sizeH = 16;
                    numberOfBombs = 40;
                    break;
                case HARD:
                    sizeW = 16;
                    sizeH = 30;
                    numberOfBombs = 99;
                    break;        
                default:
                    break;
            }
                
            discoveredFields = 0;
            mapSize = sizeW*sizeH;
            numberOfFileldWithoutBombs = mapSize-numberOfBombs;
        }
        
        //Initializing main arrayList of fields
        fields = new ArrayList<>(sizeW);
        Stream<ArrayList<Field>> stream;
        
       for(int i = 0; i<sizeW; i++)
       {
            fields.add(new ArrayList<>(sizeH));
       }            
            
       for(int j = 0; j<sizeH; j++)
       {
           stream = fields.stream();
           stream.forEach(field -> field.add(new Field()));
       }
    }  
 
    /**
     * Method to randomly add bombs on the map without starting map field.
     * @param x Horizontal coordinate chosen by player.
     * @param y Vertical coordinate chosen by player.
    */
    public void addBombs(int x, int y)
    {      
        int counter = 0;
        while(counter < numberOfBombs)
        {
            //Generating coordinates.
            int coordinateW = (int)Math.floor(Math.random()*(sizeW));
            int coordinateH = (int)Math.floor(Math.random()*(sizeH));

            //Check if field has allready a bomb or it is starting field.
            if(getFields(coordinateW,coordinateH).getBomb()==false && coordinateW!=x && coordinateH!=y)
            {
                //Changing the state of the field.
                getFields(coordinateW,coordinateH).setBomb(true);
                counter++;
            }
        }
    }
}
