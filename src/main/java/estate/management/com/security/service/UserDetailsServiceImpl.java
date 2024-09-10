package estate.management.com.security.service;


import estate.management.com.domain.user.User;
import estate.management.com.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmailEquals(email);
        if (user != null) {
            return new UserDetailsImpl(user.getId(), user.getFirstName(), user.getEmail(), user.getPasswordHash(), user.getUserRole().getRoleType().name());
        }
        throw new UsernameNotFoundException("User has this email " + email + " not found");

    }
}
