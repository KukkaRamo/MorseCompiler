package compilemorse;
import java.util.Map;
import java.util.HashMap;

/**
 * Map collections for the software to store alphabet for efficient translation.
 * This class also has methods for translation from alphabet token to morse token and vice verse.
 * 
 * @author Kukka
 */
public class AlphabetMaps implements AlphabetCollections {
    
    /**
     * The alphabet stored in these containers
     */
    Alphabet alphabet;

    @Override
    public MorseTokenPrototype getInternalPrototype() {
        return alphabet.getMyMorsePrototype();
    }
    
    @Override
    public String getTextAlphabetName() {
        return alphabet.getTextAlphabetName();
    }

    @Override
    public String getMorseAlphabetName() {
        return alphabet.getMorseAlphabetName();
    }
    
    /**
     * Tells whether there may be several text tokens for a morse token in the containers
     * Does nothing for now.
     */
    final boolean MayHaveDuplicateMorseCodes = false;
    
    // The map is a HashMap, but some other maps may be tried as well
    // If so, please modify the two lines below and the method initCharset
    
    /**
     * The subcontainer where the compiler can find a morse token corresponding a text token
     */
    Map<String, String> TextToMorseHash;
    
    /**
     * /**
     * The subcontainer where the compiler can find a text token corresponding a morse token
     */
    Map<String, String> MorseToTextHash = new HashMap<>();
    
    /**
    * Initializes the character set and calls the initialization method for
    * the text token container and the morse token container
    * The containers are used in translation
    * @param alphabet An instance of an Alphabet-class containing the text tokens and morse tokens 
    * @throws IllegalArgumentException re-thrown, occurs when a subroutine tries to add a text token that already exists
    */
    AlphabetMaps (Alphabet pAlphabet) throws IllegalArgumentException {
        alphabet = pAlphabet;
        initCharset();
    }
    
    /**
    * Initializes the text token container and the morse token container
    * The containers are used in translation
    * @throws IllegalArgumentException when the text token already exists, may be thrown by a subroutine
    */
    private void initCharset() throws IllegalArgumentException {
        if (alphabet.alphabet instanceof HashMap)
            TextToMorseHash = alphabet.alphabet;
        else {
            TextToMorseHash = new HashMap<>();
            alphabet.alphabet.entrySet().forEach( (entry) -> {
                addTextToken(entry.getKey(), entry.getValue());
            } );
        }
            
        alphabet.alphabet.entrySet().forEach( (entry) -> {
            addMorseToken(entry.getKey(), entry.getValue());
        } );
    }
    
    /**
    * Adds a token to the text token container
    * The containers are used in translation
    * @param textToken The text token to be added
    * @param morseToken The morse token to be added, corresponding the text token
    * @throws IOException when text token already exists
    */
    private void addTextToken(String textToken, String morseToken) throws IllegalArgumentException{
        if (TextToMorseHash.containsKey(textToken)) {
                throw new IllegalArgumentException("From AlhpabetHash: Duplicate character in text token array " + textToken);
        }
        TextToMorseHash.put(textToken, morseToken);
    }
    
    /**
    * Adds a token to the morse token container
    * The containers are used in translation
    * @param textToken The text token to be added
    * @param morseToken The morse token to be added, corresponding the text token
    */
    private void addMorseToken(String textToken, String morseToken) {
        if (MorseToTextHash.containsKey(morseToken))
        { // Decide here which character to choose in translation from morse
          // If there should not be duplicates, maybe throw an exception
          // Maybe write a warning message somewhere
        }
        MorseToTextHash.put(morseToken, textToken);
    }
    
    @Override
    public String morseTokenToTextToken(String morseToken) throws IllegalArgumentException {
        if (!MorseToTextHash.containsKey(morseToken))
            throw new IllegalArgumentException("No such morse code " + morseToken);
        return MorseToTextHash.get(morseToken);
    }
    
    @Override
    public String textTokenToMorseToken (String textToken) throws IllegalArgumentException {
        if (!TextToMorseHash.containsKey(textToken))
            throw new IllegalArgumentException("Character or mnemonics not found " + textToken);
        return TextToMorseHash.get(textToken);
    }
   
}
