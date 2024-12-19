package net.chiheb.eventmanagment.Service;

import net.chiheb.eventmanagment.Security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenBlackListService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Lazy
    private JwtUtils jwtService;

    public void addToBlacklist(String token) {
        Date expiry = jwtService.extractExpiration(token);
        // Calculate the remaining time to expiration
        long expiration = expiry.getTime() - System.currentTimeMillis();
        System.out.println("exepiration " + expiration);
        redisTemplate.opsForValue().set(token, "blacklisted", expiration, TimeUnit.MILLISECONDS);
    }

    public void isBlacklisted(String token) throws Exception {
        if(Boolean.TRUE.equals(redisTemplate.hasKey(token))){
            throw new Exception("this token is used for logout");
        };
    }
}
