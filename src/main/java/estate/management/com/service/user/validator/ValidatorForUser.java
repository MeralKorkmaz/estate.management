package estate.management.com.service.user.validator;

import estate.management.com.domain.user.User;
import estate.management.com.exception.BadRequestException;
import estate.management.com.payload.message.ErrorMessages;
import estate.management.com.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorForUser {
    private final UserRepository userRepository;

    public void validateForPassword(String password) {

        if (password.length() < 8) {
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_LENGTH);
        }
        boolean hasLetter = false;

        boolean hasDigit = false;

        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        if (!hasLetter) {
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_DONT_CONSIST_LETTER);
        }
        if (!hasDigit) {
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_DONT_CONSIST_DIGIT);
        }
        if (!hasSpecialChar) {
            throw new IllegalArgumentException(ErrorMessages.PASSWORD_DONT_CONSIST_SPEACIAL_CHAR);
        }

    }
public void uniqueEmail(String email){
        if(userRepository.existsByEmailEquals(email)){
            throw new IllegalArgumentException(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL);
        }
    }

public void confirmPasswordCheck(String password, String confirmpassword) {
    if (!password.equals(confirmpassword)) {
        throw new IllegalArgumentException(ErrorMessages.PASSWORD_NOT_MATCH);
    }

}

public void checkBuiltin(User user){
    if (Boolean.TRUE.equals(user.getBuilt_in())) {
        throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);

    }
}

}
