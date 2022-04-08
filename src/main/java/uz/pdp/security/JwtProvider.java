package uz.pdp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.entity.User;
import uz.pdp.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {
    @Autowired
    UserRepository userRepository;
    @Value("${app.jwt.secret}")
    String secretKey ;
    @Value("${app.jwt.expireTime}")
    long expire;

    public String generateToken(String username) {

        Optional<User> byUserName = userRepository.findByUserName(username);
        if (byUserName.isPresent()){
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS512,secretKey)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expire))
                    .compact();
        }
        return "Bunday username li foydalanuvchi yo'q";

    }

   public String getUserNameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
   }

    public boolean expireToken(String token) {
        try {

            Date expiration = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
