package fix.parser.rules;

/**
 * Created by arisharbab on 13/9/15.
 */
public class FIXValidations {

    public static boolean isValid(String tag){
        String [] tagValuePair = tag.split("=");
        if(tagValuePair.length>1 && (tagValuePair[0]!=null && isInteger(tagValuePair[0])) && tagValuePair[1]!=null)
        return true;
        return false;
    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
