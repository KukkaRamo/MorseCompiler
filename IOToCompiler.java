package compilemorse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Takes input, perpares it, and delegates it to the compiler and takes output from the compiler, prepares it, and forwards it
 * 
 * @author Kukka
 */
public class IOToCompiler {
    
    /**
    * Default value for field separator in input file (used when reading input file)
    */
    final private String inputFieldSeparator;
    
    /**
    * Default value for field separator in output file (used when writing output file)
    */
    final private String outputFieldSeparator;
    
    /**
    * Default value for short morse character in input file (used when building morse compiler)
    */
    final private char shortMorseInInputFile; 
    
    /**
    * Default value for long morse character in input file (used when building morse compiler)
    */
    final private char longMorseInInputFile;
    
    /**
    * Default value for short morse character in output file (used when building morse compiler)
    */
    final private char shortMorseInOutputFile; 
    
    /**
    * Default value for long morse character in output file (used when building morse compiler)
    */
    final private char longMorseInOutputFile;
    
     /**
    * Default value for whether input shall be translated to upper case before compilint (used when building morse compiler)
    */
    final private boolean textToUpperCase;
    
    /**
    * The compiler to which this class delegates the compilation
    */
    MorseCompiler myCompiler = null;
    
    /**
    * Constructor from builder
    * @param pInputFieldSeparator Field separator in input file
    * @param pOutnputFieldSeparator Field separator in output file
    * @param pShortMorseInInputFile Default value for short morse character in input file
    * @param pLongMorseInInputFile Default value for long morse character in input file
    * @param pShortMorseInOutputFile Default value for short morse character in output file
    * @param pLongMorseInOutputFile Default value for long morse character in output file
    * @param pTextToUpperCase Default value for whether we want to convert text to upper case in the compiler
    * @param pMethod Default value for whether we want to convert text to upper case in the compiler
    * @throws IllegalArgumentException re-thrown
    * @throws NoSuchMethodException re-thrown
    */
    IOToCompiler (String pInputFieldSeparator, String pOutputFieldSeparator, 
      char pShortMorseInInputFile, char pLongMorseInInputFile,
      char pShortMorseInOutputFile, char pLongMorseInOutputFile, boolean pTextToUpperCase, String pMethod)
            throws IllegalArgumentException, NoSuchMethodException {
        this.inputFieldSeparator = pInputFieldSeparator;
        this.outputFieldSeparator = pOutputFieldSeparator;
        this.shortMorseInInputFile = pShortMorseInInputFile;
        this.longMorseInInputFile = pLongMorseInInputFile;
        this.shortMorseInOutputFile = pShortMorseInOutputFile;
        this.longMorseInOutputFile = pLongMorseInOutputFile;
        this.textToUpperCase = pTextToUpperCase;
        setMyCompiler(pMethod);
    }
    
     /**
    * Setter for the compiler to which this class delegates the compilation
    * This setter uses default alphabet and default alphabet collections
    * @param method Active method for the compiler
    * @throws IllegalArgumentException re-thrown
    * @throws NoSuchMethodException re-thrown
    */
    private void setMyCompiler(String method) throws IllegalArgumentException, NoSuchMethodException {
        this.myCompiler = new MorseCompilerBuilder().setInputPrototype(shortMorseInInputFile, longMorseInInputFile)
        .setOutputPrototype(shortMorseInOutputFile, longMorseInOutputFile).setTextToUpperCase(textToUpperCase)
                .setMethod(method).createMorseCompiler();
    }
    
    /**
     * Reads text input, unpacks each line into token array, delegates the compilation, joins the result array into output, and writes text output
     * @param inputFile input file name
     * @param outputFile output file name
     * @throws IllegalArgumentException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    void compileTextIO (String inputFile, String outputFile) 
      throws IllegalArgumentException, FileNotFoundException, 
      ParseException , IOException, IllegalAccessException, InvocationTargetException {
        if ((new File(outputFile)).exists()) {
            // What should be done?
        }
        try (BufferedReader fInputStream = new BufferedReader(new FileReader(inputFile));
          BufferedWriter fOutputStream = new BufferedWriter(new FileWriter(outputFile)) ) {
            String inputLine, outputLine;
            while ( (inputLine = fInputStream.readLine()) != null )  { // Read line from text file
                // inputLine is one line of the message to be translated
                // spilt input line to an array of text or morse tokens to be translated
                // call the compiler that translates the token item by item
                // and join the result array items to create the outputLine
                outputLine = String.join(outputFieldSeparator, 
                    myCompiler.compile(ParseUtilities.ParseDelimeters(inputLine,inputFieldSeparator)));
                // The inputLine has been compiled to outputLine
                fOutputStream.write(outputLine);
            }
        }
        catch (IllegalArgumentException | FileNotFoundException | ParseException // For now, let Main do everything
            | IllegalAccessException | InvocationTargetException e) { throw e;}
        catch (IOException e) {throw e;}
    }
    
}
