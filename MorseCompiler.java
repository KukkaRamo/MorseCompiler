package compilemorse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Takes care of translation
 * Compiles a message.  Takes an input array and compiles it character by character into an output array.
 * 
 * @author Kukka
 */
public class MorseCompiler {
    
    /**
     * The The prototype for morse input for the compiler
     */
    final private MorseTokenPrototype InputPrototype;
    
    /**
     * The The prototype for morse output for the compiler
     */
    final private MorseTokenPrototype OutputPrototype;
    
    /**
     * Tells whether input text message will be converted to uppercaes before compiling it to morse message
     */
    final private boolean textToUpperCase;
    
    /**
     * Active alphabet container (default is HashMap)
     */
    final private AlphabetCollections myAlphabetMap;
    
    /**
     * Alphabet container method to be called (morseTokenToTextToken or textTokenToMorseToken)
     */
    private Method activeMethod;
    
    /**
     * Tells whether we are compiling from text to morse or morse to text
     */
    private boolean toMorse = false;
    
     /**
     * The constructor that takes everythong but the flag as a parameter.  The parameters can be built with a builder class MorseCompilerBuilder
     * @param inputPrototype The prototype for morse input for the compiler
     * @param outputPrototype The prototype for morse output for the compiler
     * @param pTextToUpperCase Tells whether lowercase text tokens should be translated to upper case before compiling them to morse tokens
     * @param method The method to be used in compilation
     * @param alphabetContainer The alphabet container, may use for example hashtables and/or radix trees (both the container and its alhpabet can be chosen in the builder)
     * @throws IllegalArgumentException thrown by subroutine if the user has chosen a non-existing translation map
     * @throws NoSuchMethodException thrown by subroutine if the compiler does not find the chosen method (internal error)
     */
    MorseCompiler(MorseTokenPrototype inputPrototype, MorseTokenPrototype outputPrototype, boolean pTextToUpperCase, String method,
        AlphabetCollections alphabetContainer) throws NoSuchMethodException, IllegalArgumentException {
        InputPrototype = inputPrototype;
        OutputPrototype = outputPrototype;
        textToUpperCase = pTextToUpperCase;
        myAlphabetMap = alphabetContainer;
        setActiveMethod(method);
    }
    
     /**
     * Checks whether the desired method exists and if so, sets it as the active compiling method
     * Method should be chosen based on active alphabet and translation direction
     * Also sets the internal flag about the translation direction (to or from morse)
     * @param method the desired active method
     * @throws IllegalArgumentException if the user has chosen a non-existing translation map
     * @throws NoSuchMethodException if the compiler does not find the chosen method (internal error)
     */
    final public void setActiveMethod (String method) throws NoSuchMethodException, IllegalArgumentException {
        String methodSeparator = "To";
        String prefix = myAlphabetMap.getTextAlphabetName();
        String postfix = myAlphabetMap.getMorseAlphabetName();
        Class[] cArg = new Class[1];
        cArg[0] = String.class;
        if ((prefix + methodSeparator + postfix).equals(postfix + methodSeparator + prefix)) {
            throw new IllegalArgumentException("Active method (argument 3 for main) is not unique " + method);
        }
        if (method.equals(prefix + methodSeparator + postfix))
        {
            activeMethod = AlphabetCollections.class.getMethod("textTokenToMorseToken", cArg);
            toMorse = true;
        }
        else if (method.equals(postfix + methodSeparator + prefix))
        {
            activeMethod = AlphabetCollections.class.getMethod("morseTokenToTextToken", cArg);
            toMorse = false; // Also the default value
        }
        else throw new IllegalArgumentException ("No translation map found for translation method (argument 3 for main) " + method);
    }
    
     /**
     * Compiles a message from text to morse or vice verse
     * @param data A string array of input tokens (text or morse)
     * @return the translated output array
     * @throws IllegalAccessException if the chosen method has too strict an access specifier to be accessed here (internal error)
     * @throws InvocationTargetException if the compiler does not find the chosen method (internal error)
     * @throws IllegalArgumentException if the active method called or pre-process or post-process method returns IllegalArgumentException
     */
    String[] compile(String[] data) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        String[] returnData = new String[data.length];
        for (int i=0; i<data.length; ++i) {
            String currentData = data[i];
            currentData = preProcess(currentData);
            currentData = (String) activeMethod.invoke(myAlphabetMap, currentData);
            returnData[i] = postProcess(currentData);
        }
        return returnData;
    }
    
    /**
     * Prepares the input string for translation
     * If the input is text token, converts text token to lower case if desired
     * If the input is morse token, converts it from input format to the translation alphabet format
     * @param b the input string to be translated
     * @return the input string in an appropriate format for compilation
     * @throws IllegalArgumentException (re-throwns from convertToken)
     */
    String preProcess (String b) throws IllegalArgumentException {
        if (toMorse) {
            if (textToUpperCase)
                b = b.toUpperCase();
        }
        else {
            b = this.myAlphabetMap.getInternalPrototype().convertToken(b, InputPrototype);
        }
        return b;
    }
    
    /**
     * Post-processes the translated output string
     * If the output is morse token, converts it from the translation alphabet format to output format
     * @param c the translated output string
     * @return the output string in an appropriate format for output
     * @throws IllegalArgumentException (re-throws from convertToken)
     */
    String postProcess (String c) throws IllegalArgumentException {
        if (toMorse) {
            c =  this.OutputPrototype.convertToken(c, this.myAlphabetMap.getInternalPrototype());
        }
        return c;
    }
    
    
}