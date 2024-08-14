package estate.management.com.payload.messages;

public class ErrorMessages {


    private ErrorMessages(){

    }
    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You do not have any permission to do this operation";
    public static final String PASSWORD_SHOULD_NOT_MATCHED = "Your passwords are not matched" ;
    public static final String PASSWORD_LENGTH = "Your passwords must be 8 chracters at least" ;
    public static final String PASSWORD_DONT_CONSIST_DIGIT = "Your passwords must have a digit at least" ;
    public static final String PASSWORD_DONT_CONSIST_LETTER= "Your passwords must have a letter at least" ;
    public static final String PASSWORD_DONT_CONSIST_SPEACIAL_CHAR= "Your passwords must have a special char at least" ;
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error: User with email %s already registered" ;
    public static final String PASSWORD_NOT_MATCH = "Error:password doesnt match confirmpassword" ;
    public static final String ROLE_NOT_FOUND = "There is no role like that , check the database " ;


    public static final String CATEGORY_NOT_FOUND_MESSAGE = "Error: Category with id %s not found" ;
}
