package compilemorse;

/*
* Builder class for the compiler.
*
* @author Kukka
*/
public class MorseCompilerBuilder {

    // I think that the prototypes are not heavyweight, so they may have default values here
    
    /**
    * The morse prototype for characters in the input morse file for the compiler
    */
    private MorseTokenPrototype inputPrototype=new MorseTokenPrototype ('.','-');
    
    /**
    * The morse prototype for characters in the output morse file for the compiler
    */
    private MorseTokenPrototype outputPrototype=new MorseTokenPrototype ('.','-');
    
    /**
    * The boolean variable textToUpperCase, which tells whether 
    * the input text shall be converted to uppercase before translation
    */
    boolean textToUpperCase = true;
    
    /**
    * The active method in the compiler
    */
    private String method="";
    
    /**
    * The alphabet for the compiler
    */
    private Alphabet alphabet = null;
    
    /**
    * Tells whether alphabet has been set in the builder
    */
    private boolean isAlphabetSet = false;
    
    /**
    * The alphabet collections for the compiler, must implement AlphabetCollections-interface
    */
    private AlphabetCollections alphabetCollections = null; // If this field is not initialized in builder,
        // default value is set in createMorseCompiler -method, so that there is no need to throw an exception here
    
    /**
    * Tells whether one has set the alphabet container when building morse compiler
    */
    private boolean isAlphabetCollectionsSet = false;

    /**
    * Constructor for morse compiler builder
    */
    public MorseCompilerBuilder() {
    }

    /**
    * Builder setter for the prototype for morse input to the compiler
    * @param pInputShort The string corresponding short morse character in input to the compiler
    * @param pInputLong The string corresponding long morse character in input to the compiler
    * @return this builder
    */
    public MorseCompilerBuilder setInputPrototype(char pInputShort, char pInputLong) {
        this.inputPrototype = new MorseTokenPrototype(pInputShort, pInputLong);
        return this;
    }
    
    /**
    * Builder setter for the prototype for morse output to the compiler
    * @param pOutputShort The string corresponding short morse character in input to the compiler
    * @param pOutputLong The string corresponding long morse character in input to the compiler
    * @return this builder
    */
    public MorseCompilerBuilder setOutputPrototype(char pOutputShort, char pOutputLong) {
        this.outputPrototype = new MorseTokenPrototype(pOutputShort, pOutputLong);
        return this;
    }
  
    /**
    * Builder setter for the boolean variable textToUpperCase, which tells whether 
    * the input text shall be converted to uppercase before translation
    * @param pTextToUpperCase The value for the variable
    * @return this builder
    */
    public MorseCompilerBuilder setTextToUpperCase(boolean pTextToUpperCase) {
        this.textToUpperCase = pTextToUpperCase;
        return this;
    }

    /**
    * Builder setter for the active method in the compiler
    * @param pMethod The active method
    * @return this builder
    */
    public MorseCompilerBuilder setMethod(String pMethod) {
        this.method = pMethod;
        return this;
    }
    
    /**
    * Builder setter for the alphabet for the compiler
    * This is useful only when the alphabet compiler has not been set yet,
    * otherwise, the alphabet is ignored
    * @param pAlphabet The alphabet, which is an instance of the Alphabet-class
    * @return this builder
    */
    public MorseCompilerBuilder setAlphabet(Alphabet pAlphabet) {
        if (isAlphabetCollectionsSet) {}
        // Should here be a warning that the new alphabet is ignored?
        this.alphabet = pAlphabet;
        isAlphabetSet = true;
        return this;
    }
    
    /**
    * Builder setter for the alphabet collections for the compiler
    * The previous alphabet of the builder is destroyed since the collections object has alphabet
    * @param pAlphabetCollections The alphabet container, which is an instance of a class that implements the AlphabetCollections interface
    * @return this builder
    */
    public MorseCompilerBuilder setAlphabetContainer(AlphabetCollections pAlphabetCollections) {
        this.alphabetCollections = pAlphabetCollections;
        isAlphabetCollectionsSet = true;
        isAlphabetSet = true;
        return this;
    }
    
    /**
    * Creates the morse compiler that has been built with the builder
    * @return new morse compiler
    * @throws IllegalArgumentException one tries to put duplicate token into alphabet container
    * @throws NoSuchMethodException thrown originally by morse compiler constructor that is called in this method
    */
    public MorseCompiler createMorseCompiler() throws IllegalArgumentException, NoSuchMethodException {
        if (!isAlphabetSet)
            alphabet = new AlphabetBuilder().createAlphabet(); // Default value
        // Alphabet may be chosen here for now, needs some UI for easier use
    /* For instance for methods KidsToOwnMorse and OwnMorseToKids: import HashMap and 
     alphabet = new AlphabetBuilder().setTextAlphabetName("Kids").setMorseAlphabetName("OwnMorse")
         .setMyMap(new HashMap<>()).addToken("HI","-").addToken("BYE","o").createAlphabet(); */
        // If you use AlphabetBuilder.addToken, take care of exception processing
        // In addition, the translation method (3rd input argument for main) is text alphabet name + "To" + morse alphabet name or vice verse
        if (!isAlphabetCollectionsSet)
            alphabetCollections = new AlphabetMaps(alphabet);
        return new MorseCompiler(inputPrototype, outputPrototype, textToUpperCase, method, alphabetCollections);
    }
    
}
