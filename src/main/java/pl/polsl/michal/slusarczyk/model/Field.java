package pl.polsl.michal.slusarczyk.model;

import pl.polsl.michal.slusarczyk.model.enums.FieldState;

/**
 * Class representing single field on the map.
 * 
 * @author Michał Ślusarczyk
 * @version 1.0
 */
public class Field {
    /**
    * Class field storing information about that if map field store bomb.
    */
    private boolean bomb;
    /**
    * Class field storing information about map field state.
    */
    private FieldState state; 
    
    /**
    * Constructor of field class.
    */
    public Field()
    {
        bomb = false;
        state = FieldState.UNKNOWN;
    }
  
    /**
    * Getter of field 'bomb'.
     * @return Returning value of the field.
    */
    public boolean getBomb()
    {
        return bomb;
    }
   
    /**
    * Setter of field 'bomb'.
    * @param value New value of the class field.
    */
    public void setBomb(boolean value)
    {
        bomb = value;
    }
 
     /**
    * Getter of field 'state'.
     * @return Returning value of the field.
    */   
    public FieldState getState()
    {
        return state;
    }
    
    /**
     * Setter of field 'state'.
     * @param value New value of the class field.
    */
    public void setState(FieldState value)
    {
        state = value;
    }
}
