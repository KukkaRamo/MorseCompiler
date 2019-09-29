package compilemorse;

/**
 * Interface for the containers where text tokens and the corresponding morse tokens are stored
 * This may or may not use the map where alphabet is stored
 * 
 * @author Kukka
 */
public interface AlphabetCollections {
    
    /**
    * @return the prototype of the internal lphabet
    */
    public MorseTokenPrototype getInternalPrototype();
    
    /**
    * @return the name of the text alphabet
    */
    public String getTextAlphabetName();
    
    /**
    * @return the name of the morse alphabet
    */
    public String getMorseAlphabetName();
    
    /**
    * Translates the morse token into the corresponding text token
    * @param morseToken
    * @return textToken
    * @throws IllegalArgumentException when the morse token is not in the collection
    */
    public String morseTokenToTextToken(String morseToken) throws IllegalArgumentException;
    
    /**
    * Translates the text token into the corresponding morse token
    * @param textToken
    * @return morseToken
    * @throws IllegalArgumentException when the text token is not in the collection
    */
    public String textTokenToMorseToken (String textToken) throws IllegalArgumentException;
}
