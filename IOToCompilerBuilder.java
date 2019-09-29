package compilemorse;

/**
 * Builder class for the IOToCompiler controller
 * 
 * @author Kukka
 */
public class IOToCompilerBuilder {
    
    /**
    * Default value for field separator in input file (used when reading input file)
    */
    String inputFieldSeparator = ".";
    
    /**
    * Default value for field separator in output file (used when writing output file)
    */
    String outputFieldSeparator = ".";
    
    /**
    * Default value for short morse character in input file (used when building morse compiler)
    */
    char shortMorseInInputFile = 'o'; 
    
    /**
    * Default value for long morse character in input file (used when building morse compiler)
    */
    char longMorseInInputFile = '-';
    
    /**
    * Default value for short morse character in output file (used when building morse compiler)
    */
    char shortMorseInOutputFile = 'o'; 
    
    /**
    * Default value for long morse character in output file (used when building morse compiler)
    */
    char longMorseInOutputFile = '-';
    
     /**
    * Default value for whether input shall be translated to upper case before compilint (used when building morse compiler)
    */
    boolean textToUpperCase = true;
    
    /**
    * Default value for the active method name
    */
    String methodName;
    
    
    /**
    * Builder setter for the input file field separatpr
    * @param pInputFieldSeparator The field separator in the input file
    * @return this builder
    */
    public IOToCompilerBuilder setInputFieldSeparator(String pInputFieldSeparator) {
        this.inputFieldSeparator = pInputFieldSeparator;
        return this;
    }
    
    /**
    * Builder setter for the output file field separatpr
    * @param pOutputFieldSeparator The field separator in the output file
    * @return this builder
    */
    public IOToCompilerBuilder setOutputFieldSeparator(String pOutputFieldSeparator) {
        this.outputFieldSeparator = pOutputFieldSeparator;
        return this;
    }
    
    /**
    * Builder setter for the short morse character in input file
    * @param pShortMorseInInputFile The short morse character in the input file
    * @return this builder
    */
    public IOToCompilerBuilder setShortMorseInInputFile (char pShortMorseInInputFile) {
        this.shortMorseInInputFile = pShortMorseInInputFile;
        return this;
    }
    
    /**
    * Builder setter for the long morse character in input file
    * @param pLongMorseInInputFile The long morse character in the input file
    * @return this builder
    */
    public IOToCompilerBuilder setLongMorseInInputFile (char pLongMorseInInputFile) {
        this.longMorseInInputFile = pLongMorseInInputFile;
        return this;
    }
    
    /**
    * Builder setter for the short morse character in output file
    * @param pShortMorseInOutputFile The short morse character in the output file
    * @return this builder
    */
    public IOToCompilerBuilder setShortMorseInOutputFile (char pShortMorseInOutputFile) {
        this.shortMorseInOutputFile = pShortMorseInOutputFile;
        return this;
    }
    
    /**
    * Builder setter for the long morse character in output file
    * @param pLongMorseInOutputFile The long morse character in the output file
    * @return this builder
    */
    public IOToCompilerBuilder setLongMorseInOutputFile (char pLongMorseInOutputFile) {
        this.longMorseInOutputFile = pLongMorseInOutputFile;
        return this;
    }
    
    /**
    * Builder setter for the decision of whether text characters shall be converted to uppercase before translating them
    * @param pTextToUpperCase The decision of whether text characters shall be converted to uppercase before translating them
    * @return this builder
    */
    public IOToCompilerBuilder setTextToUpperCase (boolean pTextToUpperCase) {
        this.textToUpperCase = pTextToUpperCase;
        return this;
    }
    
    /**
    * Builder setter for the name of the active method
    * @param pMethodName The name of the active method
    * @return this builder
    */
    public IOToCompilerBuilder setMethodName (String pMethodName) {
        this.methodName = pMethodName;
        return this;
    }
    
    /**
    * Creates the IOToCompiler without the compiler being set
    * @return New instance of IOToCompiler class
    * @throws IllegalArgumentException re-thrown
    * @throws NoSuchMethodException re-thrown
    */
    public IOToCompiler createIOToCompiler() throws IllegalArgumentException, NoSuchMethodException {
        return  new IOToCompiler (inputFieldSeparator, outputFieldSeparator, 
        shortMorseInInputFile, longMorseInInputFile,
        shortMorseInOutputFile, longMorseInOutputFile, textToUpperCase, methodName);
    }
}
