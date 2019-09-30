package compilemorse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * The main class takes care about the UI for now.
 * Main class checks arguments except that it delegates method name check to the compiler.
 * The class takes care about reading from and writing into a text file.
 * Each line in the text file is processed as one message.
 * Error processing is performed here as well.
 * 
 * @author Kukka
 */
public class CompileMorseOriginal {
    
    
    /**
     * Checks arguments (delegates the third argument check), does the necessary building, forwards control to a controller, and does error processing
     * @param args the command line arguments:
     * String input file name
     * String output file name
     * String method name in format [character language]To[morse version] or [morse version]To[Character language]
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void main2(String[] args) throws IllegalArgumentException, NoSuchMethodException, FileNotFoundException, 
            ParseException , IOException, IllegalAccessException, InvocationTargetException  {
        
        
        IOToCompiler myController;
        
        if (args.length != 3){ // Four if there are other alphabets
            String errorMessage = "Usage: CompileMorse infile outfile translation_method";
            System.out.println(errorMessage);
            System.out.println("For now, the translation method must be either EnglishToMorse or MorseToEnglish.");
            throw new IllegalArgumentException (errorMessage);
        }
        
        try {
            myController = new IOToCompilerBuilder().setMethodName(args[2]).createIOToCompiler();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        catch (NoSuchMethodException e) {
            System.out.println("Problems in finding method "+ args[2]);
            System.out.println(e.getMessage());
            throw e;
        }
        
        try {
            myController.compileTextIO(args[0], args[1]);
        } 
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
            throw e;
        }
        catch (ParseException e) {
            System.out.println("Erroneous input line in input file.");
            System.out.println(e.getMessage());
            System.out.println("first index in position " + e.getErrorOffset());
            throw e;
        }
        catch (IOException e) {
            System.out.println("IO exception");
            System.out.println(e.getMessage());
            throw e;
        }
        catch (IllegalAccessException e) {
            System.out.println("Problems with access to the method choesn");
            System.out.println(e.getMessage());
            throw e;
        }
        catch (InvocationTargetException e) {
            System.out.println("Problems with invocation of the method choesn");
            System.out.println(e.getMessage());
            throw e;
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    
}
