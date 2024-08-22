package estate.management.com.security.jwt;


import estate.management.com.security.service.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;


@Component
public class JwtUtils {
    private static final Logger LOGGER= LoggerFactory.getLogger(JwtUtils.class);

    @Value("${backendapi.app.jwtSecret}")
    private  String jwtSecret;
    @Value("${backendapi.app.jwtExpirationsMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        String token=generateJwtTokenFromEmail(userDetails.getEmail());
        return token;


    }

    public String generateJwtTokenFromEmail( String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String jwtToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Jwt token is expired : {}",e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Jwt token is unsupported : {}",e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Jwt token is invalid : {}",e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("Jwt token is invalid : {}",e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Jwt token is empty : {}",e.getMessage());
        }
        return false;
    }

    public String getUserEmailFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
