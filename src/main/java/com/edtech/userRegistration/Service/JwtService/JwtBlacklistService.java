package com.edtech.userRegistration.Service.JwtService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class JwtBlacklistService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String BLACKLIST_PREFIX = "blacklist:";
    private static final String USER_REVOKED_PREFIX = "user:";

    public void blacklistToken(String token, long expirationMillis) {
        redisTemplate.opsForValue().set(
                BLACKLIST_PREFIX + token,
                "blackListed",
                expirationMillis,
                TimeUnit.MILLISECONDS
        );
    }

    public void revokeAllTokensForUser(String username) {
        long now = System.currentTimeMillis();
        redisTemplate.opsForValue().set(
                USER_REVOKED_PREFIX + username,
                now,
                48,
                TimeUnit.HOURS
        );
    }

}
