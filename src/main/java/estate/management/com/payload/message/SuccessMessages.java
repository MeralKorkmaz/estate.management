package estate.management.com.payload.message;

public class SuccessMessages {

    private SuccessMessages() {
    }

    /*
    Format for %s used success messages should be:
    String.format(SuccessMessages.NAME_OF_THE_SUCCESS, id/username/title etc.));
     */

    // GENERAL SUCCESS MESSAGES:
    private static final String GENERAL_SUCCESS = "Operation completed successfully!";
    private static final String GENERAL_FORMAT_SUCCESS = "Format is valid!";
    private static final String GENERAL_DATE_FORMAT_SUCCESS = "Date format is valid!";
    private static final String GENERAL_TIME_SUCCESS = "Time is valid and available!";
    private static final String GENERAL_CREATION_SUCCESS = "Successfully created!";
    private static final String GENERAL_UPDATE_SUCCESS = "Successfully updated!";
    private static final String GENERAL_DELETION_SUCCESS = "Successfully deleted!";
    private static final String GENERAL_FOUND_SUCCESS = "Found successfully!";

    // SUCCESS MESSAGES FOR ADVERTS:
    public static final String ADVERT_CREATED_SUCCESS = "Advert created successfully!";
    public static final String ADVERT_UPDATED_SUCCESS = "Advert updated successfully!";
    public static final String ADVERT_DELETED_SUCCESS = "Advert deleted successfully!";
    public static final String ADVERT_FOUND_SUCCESS = "Advert with %s id found successfully!";
    public static final String ADVERT_CATEGORY_FOUND_SUCCESS = "Advert with %s category found successfully!";
    public static final String ADVERT_TYPE_FOUND_SUCCESS = "Advert with %s advert type found successfully!";
    public static final String ADVERT_CITY_FOUND_SUCCESS = "Advert in %s city found successfully!";
    public static final String ADVERT_LOCATION_FOUND_SUCCESS = "Advert in %s location found successfully!";
    public static final String ADVERT_STATUS_FOUND_SUCCESS = "Advert in %s status found successfully!";
    public static final String ADVERT_SLUG_FOUND_SUCCESS = "Advert with %s slug found successfully!";
    public static final String ADVERT_USER_FOUND_SUCCESS = "Advert with %s user id found successfully!";
    public static final String ADVERT_PRICE_RANGE_FOUND_SUCCESS = "Adverts found in the specified price range!";

    // SUCCESS MESSAGES FOR USERS:
    public static final String USER_REGISTERED_SUCCESS = "User registered successfully!";
    public static final String USER_UPDATED_SUCCESS = "User updated successfully!";
    public static final String USER_DELETED_SUCCESS = "User deleted successfully!";
    public static final String USER_FOUND_SUCCESS = "User with id %s found successfully!";
    public static final String USER_LOGIN_SUCCESS = "User logged in successfully!";
    public static final String USER_ROLE_ASSIGNED_SUCCESS = "User role %s assigned successfully!";
    public static final String USER_ROLE_FOUND_SUCCESS = "User found with user-role %s successfully!";
    public static final String USER_PERMITTED_ACTION_SUCCESS = "User is permitted to perform this operation!";
    public static final String USER_PASSWORD_MATCH_SUCCESS = "Passwords matched successfully!";

    // SUCCESS MESSAGES FOR IMAGES:
    public static final String IMAGE_UPLOADED_SUCCESS = "Image uploaded successfully!";
    public static final String IMAGE_DELETED_SUCCESS = "Image deleted successfully!";
    public static final String IMAGE_FOUND_SUCCESS = "Image with %s id found successfully!";
    public static final String IMAGE_ADVERT_FOUND_SUCCESS = "Image for advert with %s id found successfully!";

    // SUCCESS MESSAGES FOR CATEGORIES:
    public static final String CATEGORY_CREATED_SUCCESS = "Category created successfully!";
    public static final String CATEGORY_UPDATED_SUCCESS = "Category updated successfully!";
    public static final String CATEGORY_DELETED_SUCCESS = "Category deleted successfully!";
    public static final String CATEGORY_FOUND_SUCCESS = "Category with %s id found successfully!";
    public static final String CATEGORY_PROPERTY_FOUND_SUCCESS = "Category Property Key with %s id found successfully!";
    public static final String CATEGORY_SLUG_FOUND_SUCCESS = "Category with %s slug found successfully!";

    // SUCCESS MESSAGES FOR ADVERT TYPES:
    public static final String ADVERT_TYPE_CREATED_SUCCESS = "Advert type created successfully!";
    public static final String ADVERT_TYPE_UPDATED_SUCCESS = "Advert type updated successfully!";
    public static final String ADVERT_TYPE_DELETED_SUCCESS = "Advert type deleted successfully!";
    public static final String ADVERT_TYPE_TITLE_FOUND_SUCCESS = "Advert type with %s title found successfully!";

    // SUCCESS MESSAGES FOR TOUR REQUESTS:
    public static final String TOUR_REQUEST_CREATED_SUCCESS = "Tour request created successfully!";
    public static final String TOUR_REQUEST_UPDATED_SUCCESS = "Tour request updated successfully!";
    public static final String TOUR_REQUEST_DELETED_SUCCESS = "Tour request deleted successfully!";
    public static final String TOUR_REQUEST_FOUND_SUCCESS = "Tour request with %s id found successfully!";
    public static final String TOUR_REQUEST_TIME_AVAILABLE_SUCCESS = "Tour request time is available!";

    // SUCCESS MESSAGES FOR REPORTS:
    public static final String REPORT_CREATED_SUCCESS = "Report created successfully!";
    public static final String REPORT_UPDATED_SUCCESS = "Report updated successfully!";
    public static final String REPORT_DELETED_SUCCESS = "Report deleted successfully!";
    public static final String REPORT_FOUND_SUCCESS = "Report with %s id found successfully!";

    // SUCCESS MESSAGES FOR CONTACT MESSAGES:
    public static final String CONTACT_MESSAGE_SENT_SUCCESS = "Contact message sent successfully!";
    public static final String CONTACT_MESSAGE_DELETED_SUCCESS = "Contact message deleted successfully!";
    public static final String CONTACT_MESSAGE_FOUND_SUCCESS = "Contact message with %s id found successfully!";

    // SUCCESS MESSAGES FOR FAVORITES:
    public static final String FAVORITE_ADDED_SUCCESS = "Favorite added successfully!";
    public static final String FAVORITE_REMOVED_SUCCESS = "Favorite removed successfully!";
    public static final String FAVORITE_FOUND_SUCCESS = "Favorite with %s id found successfully!";
}
