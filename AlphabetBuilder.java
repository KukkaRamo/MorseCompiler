package compilemorse;
import java.util.Map;
import java.util.HashMap;

/*
* Builder class for the alphabet.
*
* @author Kukka
*/
public class AlphabetBuilder {

    /**
    * The name for the text alphabet
    */
    private String TextAlphabetName = "English";
    
    /**
    * The name for the morse alphabet
    */
    private String MorseAlphabetName = "Morse";
    
    /**
    * The character corresponding short morse character inside the alphabet collection
    */
    private char InternalShort = 'o';
    
    /**
    * The character corresponding long morse character inside the alphabet collection
    */
    private char InternalLong = '-';
    
    /**
    * Internal map for the alphabet
    */
    private Map<String, String> myMap = new HashMap<>();
    
    /**
    * Tells whether one has already set the alphabet map when building alphabet
    */
    private boolean alphabetMapTouched = false;
    
    /**
    * Constructor for alphabet builder
    */
    public AlphabetBuilder() {
    }

    /**
    * Builder setter for the name for the text alphabet
    * @param pTextAlphabetName the name for the text alphabet
    * @return this builder
    */
    public AlphabetBuilder setTextAlphabetName(String pTextAlphabetName) {
        this.TextAlphabetName = pTextAlphabetName;
        return this;
    }

    /**
    * Builder setter for the name for the morse alphabet
    * @param pMorseAlphabetName the name for the text alphabet
    * @return this builder
    */
    public AlphabetBuilder setMorseAlphabetName(String pMorseAlphabetName) {
        this.MorseAlphabetName = pMorseAlphabetName;
        return this;
    }

    /**
    * Builder setter for the string corresponding short morse character inside the alphabet collection
    * @param pInternalShort The string corresponding short morse character inside the alphabet collection
    * @return this builder
    */
    public AlphabetBuilder setInternalShort(char pInternalShort) {
        this.InternalShort = pInternalShort;
        return this;
    }

    /**
    * Builder setter for the string corresponding long morse character inside the alphabet collection
    * @param pInternalLong The string corresponding long morse character inside the alphabet collection
    * @return this builder
    */
    public AlphabetBuilder setInternalLong(char pInternalLong) {
        this.InternalLong = pInternalLong;
        return this;
    }

     /**
    * Sets the character set array
    * @param pAlphabetMap the new map (null is default map)
    * If pAlphabetMap is null, tokens can be added later to the default map (for example, scandinacian letters may be added to the standard map)
    * If the parameter is empty instead, the default map is not used as a basis (for example, kids may make short morse maps from scratch)
    * This method destroys any previous content set to the alphabet map
    * @return this, since this is a setter method in builder
    * */
    public AlphabetBuilder setMyMap(Map<String, String> pAlphabetMap) {
        if (alphabetMapTouched)
        {} // Should there be a warning about destroying the previous content?
        if (pAlphabetMap == null) {
            this.myMap = new HashMap<>();
            buildDefaultAlphabetMap();
        }
        else
            this.myMap = pAlphabetMap;
        alphabetMapTouched = true;
        return this;
    }
    
     /**
    * Creates the alphabet instance
    * and builds alphabet map if it has not been built yet
    * @return alphabet the new alphabet instance
    * */
    public Alphabet createAlphabet() {
        if (!alphabetMapTouched)
            buildDefaultAlphabetMap();
        alphabetMapTouched = true;
        return new Alphabet(TextAlphabetName, MorseAlphabetName, InternalShort, InternalLong, myMap);
    }
    
     /**
    * Adds a new text token and the corresponding morse token into the alphabet
    * For instance, scandinavian characters may be added
    * Note that the default map is used as a basis.  Please create an empty map by setMyMap before using this method if you want to start from scratch
    * @param textToken
    * @param morseToken
    * @return this alphabet builder
    * @throws IllegalArgumentException if the map already contains the text token
    */
    public AlphabetBuilder addToken(String textToken, String morseToken) throws IllegalArgumentException {
        if (!alphabetMapTouched)
            buildDefaultAlphabetMap();
        alphabetMapTouched = true;
        if (myMap.containsKey(textToken))
            throw new IllegalArgumentException ("Alphabet already contains text token " + textToken);
        myMap.put(textToken, morseToken);
        return this;
    }
    
    /**
    * Copies all default tokens to the alphabet.  Keeps the original map but destroys old content where there are duplicate keys (text tokens).
    * */
    private void buildDefaultAlphabetMap() {
        myMap.put("A", "o-");
        myMap.put("B", "-ooo");
        myMap.put("C", "-o-o");
        myMap.put("D", "-oo");
        myMap.put("E", "o");
        myMap.put("F", "oo-o");
        myMap.put("G", "--o");
        myMap.put("H", "oooo");
        myMap.put("I", "oo");
        myMap.put("J", "o---");
        myMap.put("K", "-o-");
        myMap.put("L", "o-oo");
        myMap.put("M", "--");
        myMap.put("N", "-o");
        myMap.put("O", "---");
        myMap.put("P", "o--o");
        myMap.put("Q", "--o-");
        myMap.put("R", "o-o");
        myMap.put("S", "ooo");
        myMap.put("T", "-");
        myMap.put("U", "oo-");
        myMap.put("V", "ooo-");
        myMap.put("W", "o--");
        myMap.put("X", "-oo-");
        myMap.put("Y", "-o--");
        myMap.put("Z", "--oo");
        myMap.put(".", "o-o-o-");
        myMap.put(",", "--oo--");
        myMap.put("?", "oo--oo");
        myMap.put("/", "-oo-o");
        myMap.put("@", "o--o-o");
        myMap.put("1", "o----");
        myMap.put("2", "oo---");
        myMap.put("3", "ooo--");
        myMap.put("4", "oooo-");
        myMap.put("5", "ooooo");
        myMap.put("6", "-oooo");
        myMap.put("7", "--ooo");
        myMap.put("8", "---oo");
        myMap.put("9", "----o");
        myMap.put("0", "-----");
    }
    
}
