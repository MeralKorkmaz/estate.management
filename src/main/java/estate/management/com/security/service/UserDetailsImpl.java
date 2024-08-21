//package estate.management.com.security.service;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class UserDetailsImpl implements UserDetails {
//
//    private Long id;
//    private String firstName;
//    private String email;
//    private Collection<? extends GrantedAuthority> authorities;
//
//
//    private  String passwordHash;
//
//    public UserDetailsImpl(Long id, String firstName, String email,  String passwordHash,
//                           String Role) {
//        this.id = id;
//        this.firstName = firstName;
//        this.email = email;
//        this.passwordHash = passwordHash;
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(Role));
//        this.authorities = grantedAuthorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return passwordHash;
//    }
//
//    @Override
//    public String getUsername() {
//        return null; //we do not have a field called userName
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return  true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public boolean equals(Object o){
//        if(this==o){
//            return true;
//        }
//        if (o==null || getClass()!=o.getClass()){
//            return false;
//
//        }
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id,user.getId());
//    }
//}
