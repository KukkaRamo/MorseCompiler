package compilemorse;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * The class for parsing input string.
 * The class is needed because input delimeter, too, may be a character in the alphabets
 * 
 * @author Kukka
 */
public class ParseUtilities {
    
    /**
    * Parses the input line.  Each character must be separated by delimeters, and there may be an optional delimeter in the beginning and/or in the end of line
    * Input delimeter, too, may be a character in the alphabets.  In that case, 
    * three delimeters are needed (two suffice in the beginning and end of line, or one is enough if there is nothing else in the line).
    * It is assumed that there are no two or more consequtive dots in the text.
    * @param inputString input string for the parser
    * @param delimeter the delimeter
    * @throws ParseException when the characters are not separated by delimeters in a correct way
    */
    static String[] ParseDelimeters(String inputString, String delimeter) throws ParseException {
        
        // If delimeter is a regex, it must be converted into non-regex
        
        String dels2 = delimeter + delimeter;
        String dels3 = dels2 + delimeter;
        String dels4 = dels3 + delimeter;
        
        int delLength = delimeter.length();
        
        // If input string contains four or more consequtive delimeters, there is an error
        if (inputString.contains(dels4)) throw new ParseException ("Parse error: input string contains at least one " + dels4, inputString.indexOf(dels4));
        
        // Empty input string, return an empty output array
        if (inputString.equals("")) {
            return new String[0];
        }
        
        // Input string contains nothing but 1-3 consequtive delimeters, so the input will be one character with the same value as delimeter
        if (inputString.equals(delimeter) || inputString.equals(dels2) || inputString.equals(dels3)) {
            String[] outputTable = new String[1];
            outputTable[0] = delimeter;
            return outputTable;
        }
        
        // Input string does not only consist of delimeters but also contains other characters
        
        // Parse the beginning
        
         // begin, 2-3 delimeters and then another character, convert the delemeter sequence to the delimeter 
        if (inputString.startsWith(dels3)) // 3 delimeters
            inputString = inputString.substring(delLength);
        if (inputString.startsWith(dels2)) // 2 delimeters (originally 2 or 3)
            inputString = inputString.substring(delLength);
        else if (inputString.startsWith(delimeter))
            // begin, delimeter and then another character, remove the delimeter
            inputString = inputString.substring(delLength);
        
        // Parse the end
        
         // another character, 2-3 delimeters and then end, convert the delemeter sequence to the delimeter
        if (inputString.endsWith(dels3)) // 3 delimeters
            inputString = inputString.substring(0, inputString.length()-delLength);
        if (inputString.endsWith(dels2)) // 2 delimeters (originally 2 or 3)
            inputString = inputString.substring(0, inputString.length()-delLength);
        else if (inputString.endsWith(delimeter))
            // another character, deimeter, and end, remove the delimeter
            inputString = inputString.substring(0, inputString.length()-delLength);
        
        if (inputString.contains(dels2)) {
            String testString = inputString.replace(dels3, "");
            if (testString.contains(dels2))
                throw new ParseException ("Parse error: two consequtive delemeters in input string at least once", inputString.indexOf(dels2));
        }
         
         // Finally parse the string into token array
         // At first convert three consequtive delimeters to two, so that split makes an empty item for each character that is the same as delimeter character
        String test = inputString.replace(dels3, dels2);
        String[] returnInput = test.split(Pattern.quote(delimeter), -1);
     //   String[] returnInput = inputString.replace(dels3, dels2).split(delimeter);
        for (int i=0; i<returnInput.length; ++i) // Replace each empty item with the character that correspondences the delimeter
            if (returnInput[i].equals("")) returnInput[i] = delimeter;
        return returnInput;
    }
    
}