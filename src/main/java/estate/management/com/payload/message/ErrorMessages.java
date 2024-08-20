package estate.management.com.payload.message;

public class ErrorMessages {


    private ErrorMessages() {
    }

    /*
    Format for %s used errors should be:
    String.format(ErrorMessages.NAME_OF_THE_ERROR, id/username/title etc.));
     */

    // GENERAL ERRORS:
    private static final String GENERAL_NOT_FOUND_ERROR="Error: Not found!";
    private static final String GENERAL_FORMAT_ERROR="Invalid format! Please enter a valid format.";
    private static final String GENERAL_DATE_FORMAT_ERROR="Invalid date format! Please enter a valid date format such as yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String GENERAL_TIME_CONFLICT_ERROR="There is a time conflict. Please enter a valid time!";

    // ADVERTS:
    public static final String ADVERT_GENERAL_NOT_FOUND_ERROR="Advert cannot found!";
    public static final String ADVERT_ID_NOT_FOUND_ERROR="Advert with %s id cannot found!";
    public static final String ADVERT_CATEGORY_NOT_FOUND_ERROR="Advert with %s category cannot found!";
    public static final String ADVERT_CATEGORY_ID_NOT_FOUND_ERROR="Advert with %s category id cannot found!";
    public static final String ADVERT_ADVERT_TYPE_NOT_FOUND_ERROR="Advert with %s advert type cannot found!";
    public static final String ADVERT_ADVERT_TYPE_ID_NOT_FOUND_ERROR="Advert with %s advert type id cannot found!";
    public static final String ADVERT_CITY_NOT_FOUND_ERROR="Advert in %s city cannot found!";
    public static final String ADVERT_CITY_ID_NOT_FOUND_ERROR="Advert with %s city id cannot found!";
    private static final String ADVERT_PRICE_RANGE_NOT_FOUND_ERROR="There is no advert found in specified price range";
    private static final String ADVERT_LOCATION_NOT_FOUND_ERROR="Advert in %s location cannot found!";
    private static final String ADVERT_LOCATION_ID_NOT_FOUND_ERROR="Advert with %s location id cannot found!";
    private static final String ADVERT_STATUS_NOT_FOUND_ERROR="Advert in %s status cannot found!";
    private static final String ADVERT_STATUS_ID_NOT_FOUND_ERROR="Advert with %s status id cannot found!";
    private static final String ADVERT_SLUG_NOT_FOUND_ERROR="Advert with %s slug cannot found!";
    private static final String ADVERT_SLUG_ID_NOT_FOUND_ERROR="Advert with %s slug id cannot found!";
    private static final String ADVERT_USER_ID_NOT_FOUND_ERROR="Advert with %s user id cannot found!";

    // USER:
    private static final String USER_GENERAL_NOT_FOUND_ERROR="User cannot found!";
    public static final String USER_NOT_FOUND_USER_MESSAGE = "User with id %s cannot found!";
    private static final String USER_LOGIN_DENIED_ERROR="Invalid credentials!";
    public static final String USER_ALREADY_REGISTER_MESSAGE_PHONE_NUMBER = "User with phone number %s is already registered";
    public static final String USER_ALREADY_REGISTER_MESSAGE_EMAIL = "User with email %s is already registered";
    public static final String USER_NOT_HAVE_EXPECTED_ROLE_USER = "User does not have expected role";
    public static final String USER_NOT_PERMITTED_METHOD_MESSAGE = "User do not have any permission to do this operation";
    public static final String USER_PASSWORD_SHOULD_NOT_MATCHED = "Passwords are not matched";

    // USER ROLE
    public static final String USER_ROLE_NOT_FOUND = "Please enter valid user role!";
    public static final String USER_ROLE_USER_NOT_FOUND = "User not found with user-role %s";

    //  IMAGE:

    public static final String IMAGE_GENERAL_NOT_FOUND_ERROR="Image cannot found!";
    public static final String IMAGE_ID_NOT_FOUND_ERROR="Image with %s id cannot found!";
    public static final String IMAGE_ADVERT_ID_NOT_FOUND_ERROR="Image with %s advert id cannot found!";

    //  CATEGORY:
    public static final String CATEGORY_GENERAL_NOT_FOUND_ERROR="Category cannot found!";
    public static final String CATEGORY_ID_NOT_FOUND_ERROR="Category with %s id cannot found!";
    public static final String CATEGORY_CATEGORY_PROPERTY_KEY_ID_NOT_FOUND_ERROR="Category with %s category property key id cannot found!";
    public static final String CATEGORY_SLUG_NOT_FOUND_ERROR="Category with %s slug cannot found!";

    // ADVERT TYPE:
    public static final String ADVERT_TYPE_GENERAL_NOT_FOUND_ERROR="Advert type cannot be found!";
    public static final String ADVERT_TYPE_ID_NOT_FOUND_ERROR="Advert type with the id %s cannot be found!";
    public static final String ADVERT_TYPE_TITLE_NOT_FOUND_ERROR="Advert type with the title %s cannot be found!";
    public static final String ADVERT_TYPE_HAS_ASSOCIATED_ADVERTS_ERROR = "Cannot delete Advert Type as it has associated adverts.";

    //  TOUR REQUEST:
    public static final String TOUR_REQUEST_GENERAL_NOT_FOUND_ERROR="Tour request cannot found!";
    public static final String TOUR_REQUEST_ID_NOT_FOUND_ERROR="Tour request with %s id cannot found!";
    public static final String TOUR_REQUEST_TIME_CONFLICT_ERROR="Tour request cannot be taken due to time conflict!";

    // REPORT:
    public static final String REPORT_GENERAL_NOT_FOUND_ERROR="Report cannot found!";
    public static final String REPORT_ID_NOT_FOUND_ERROR="Report with %s id cannot found!";

    // CONTACT MESSAGE:
    public static final String CONTACT_MESSAGE_GENERAL_NOT_FOUND_ERROR="Contact message cannot found!";
    public static final String CONTACT_MESSAGE_ID_NOT_FOUND_ERROR="Contact message with %s id cannot found!";

    // FAVORITE:
    public static final String FAVORITE_GENERAL_NOT_FOUND_ERROR="Favorite cannot found!";
    public static final String FAVORITE_ID_NOT_FOUND_ERROR="Favorite with %s id cannot found!";


    // PRIVATE/FOREIGN KEY - ID MISMATCH ERRORS:
    public static final String ADVERT_TYPE_ID_MISMATCH_ERROR="Advert type with %s id cannot found!";
    public static final String COUNTRY_ID_MISMATCH_ERROR="Country with %s id cannot found!";
    public static final String CITY_ID_MISMATCH_ERROR="City with %s id cannot found!";
    public static final String DISTRICT_ID_MISMATCH_ERROR="District with %s id cannot found!";
    public static final String USER_ID_MISMATCH_ERROR="User with %s id cannot found!";
    public static final String CATEGORY_ID_MISMATCH_ERROR="Category with %s id cannot found!";
    private static final String CATEGORY_PROPERTY_KEY_ID_MISMATCH_ERROR="Category Property Key with %s id cannot found!";
    private static final String ADVERT_ID_MISMATCH_ERROR="Advert with %s id cannot found!";
    private static final String ROLE_ID_MISMATCH_ERROR="Role with %s id cannot found!";
    private static final String OWNER_USER_ID_MISMATCH_ERROR="Owner user with %s id cannot found!";
    private static final String GUEST_USER_ID_MISMATCH_ERROR="Guest user with %s id cannot found!";

    // FORMAT ERRORS:
    public static final String SLUG_FORMAT_ERROR = "Slug must be URL encoded and in a valid format!";
    public static final String LOCATION_FORMAT_ERROR = "Location must be a valid embed code!";
    public static final String EMAIL_FORMAT_ERROR = "E-mail must contain @ and end with '.com'. Please enter a valid format!";

    // OTHER ERRORS:
    public static final String SLUG_DUPLICATE_ERROR = "Slug with %s id is already in use!";
    public static final String BUILT_IN_IMMUTABLE_ERROR = "Built-in adverts cannot be deleted or updated!";
    public static final String GENERIC_ERROR = "Something went wrong!";



}
