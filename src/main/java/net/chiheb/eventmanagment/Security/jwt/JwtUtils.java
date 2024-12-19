package net.chiheb.eventmanagment.Security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.chiheb.eventmanagment.Entity.User;
import net.chiheb.eventmanagment.Service.TokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import net.chiheb.eventmanagment.Security.CustomerUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Component
@ConfigurationProperties(prefix = "mehdi.app")
public class JwtUtils {
    @Autowired
    private TokenBlackListService tokenBlackListService;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //@Value("${mehdi.app.jwtSecret}")
    private final String jwtSecret = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";

    @Value("${mehdi.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getEmail())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build()
                .parseSignedClaims(token).getBody();
    }


    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }



    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSignInKey()).build().parse(authToken);
            tokenBlackListService.isBlacklisted(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("JWT token is Dirty {}", e.getMessage());
        }

        return false;
    }
}
