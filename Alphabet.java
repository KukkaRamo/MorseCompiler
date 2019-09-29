package compilemorse;
import java.util.Map;

/**
 * This alphabet is part of the internal morse alphabet (the whole for now)
 * 
 * @author Kukka
 */
public class Alphabet {
    
    /**
    * The name for the text alphabet
    */
    private String textAlphabetName = "English";
    
    /**
    * The name for the morse alphabet corresponding the text alphabet
    */
    private String morseAlphabetName = "Morse";
    
    /**
    * The prototype for the morse alphabet
    */
    final private MorseTokenPrototype myMorsePrototype;
    
    /**
    * Internal map for the alphabet.  A map from text token to morse token.  The AlphabetContainer class may either use this 
    * or build a new collection structure for translating text token to morse tooken, according to what type of collection is preferred.
    */
    public Map<String, String> alphabet; // Character or mnemonics, corresponding morse code

    /**
    * Getter for the text alphabet name
    * @return The text alphabet name
    */
    public String getTextAlphabetName() {
        return textAlphabetName;
    }

    /**
    * Getter for the morse alphabet name
    * @return The morse alphabet name
    */
    public String getMorseAlphabetName() {
        return morseAlphabetName;
    }

    /**
    * Getter for the prototype for the morse alphabet
    * @return The prototype for the morse alphabet
    */
    public MorseTokenPrototype getMyMorsePrototype() {
        return myMorsePrototype;
    }
    
     /**
    * Constructor for the alphabet
    * @param pTextAlphabetName Name for the text alphabet
    * @param pMorseAlphabetName Name for the morse alphabet
    * @param pInternalShort character for the short morse character inside the alphabet collection
    * @param pInternalLong character for the long morse character inside the alphabet collection
    * @param myMap The map to store the alphabet
    */
    Alphabet(String pTextAlphabetName, String pMorseAlphabetName, char pInternalShort, char pInternalLong, Map<String, String> myMap) {
        textAlphabetName = pTextAlphabetName; // Name of language
        morseAlphabetName = pMorseAlphabetName; // Name of the corresponding morse alphabet
        myMorsePrototype = new MorseTokenPrototype (pInternalShort, pInternalLong);
        alphabet = myMap;
    }

}
