package compilemorse;

/**
 * The presentation prototype for a morse token. 
 * The prototype contains character presentation for sort, character presentation for long, 
 * and a conversion method from another presentation prototype
 * 
 * @author Kukka
 */
public class MorseTokenPrototype {
    
    /**
    * The character corresponding short morse character
    */
    final private char shortCharacter;
    
    /**
    * The character corresponding long morse character
    */
    final private char longCharacter;
    
    /**
    * The getter for the character corresponding short morse character
    * @return The character corresponding short morse character
    */
    public char getShortCharacter() {
        return shortCharacter;
    }

    /**
    * The getter for the character corresponding long morse character
    * @return The character corresponding long morse character
    */
    public char getLongCharacter() {
        return longCharacter;
    }
    
    /**
    * Constructor for the morse character prototype
    * @param pShortChacacter The character corresponding short morse character
    * @param pLongChacacter The character corresponding long morse character
    */
    MorseTokenPrototype (char pShortCharacter, char pLongCharacter) {
        this.shortCharacter = pShortCharacter;
        this.longCharacter = pLongCharacter;
    }
    
     /**
    * String for changing the format of the morse character
    * @param oldTokenString The token string to be converted
    * @param oldTokenPrototype The character corresponding long morse character
    * @return the new string
    * @throws IllegalArgumentException if the morse character is not a valid sequence of short and long tokens
    */
    public String convertToken (String oldTokenString, MorseTokenPrototype oldTokenPrototype) throws IllegalArgumentException {
        // regex cannot be used since there may be regex characters in the string
        char myShort = oldTokenPrototype.getShortCharacter();
        char myLong = oldTokenPrototype.getLongCharacter();
        char c;
        
        char[] tokenCharacters = oldTokenString.toCharArray();
        for (int i=0; i<tokenCharacters.length; ++i) {
            c = tokenCharacters[i];
            if (c == myShort) {
               tokenCharacters[i] = shortCharacter;
            }
            else if ( c == myLong) { // If myShort is equal to myLong, translate everything to myShort, maybe warning?
                tokenCharacters[i] = longCharacter;
            }
            else {
                throw new IllegalArgumentException("Morse token contains other characters than dashes or dots " + c);
            }
        }            
        return String.valueOf(tokenCharacters);
    }
    
}
