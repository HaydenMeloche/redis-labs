package com.redislabs.university.RU102J.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.time.ZonedDateTime;
import com.redislabs.university.RU102J.core.KeyHelper;

public class RateLimiterSlidingDaoRedisImpl implements RateLimiter {

    private final JedisPool jedisPool;
    private final long windowSizeMS;
    private final long maxHits;

    public RateLimiterSlidingDaoRedisImpl(JedisPool pool, long windowSizeMS,
                                          long maxHits) {
        this.jedisPool = pool;
        this.windowSizeMS = windowSizeMS;
        this.maxHits = maxHits;
    }

    // Challenge #7
    @Override
    public void hit(String name) throws RateLimitExceededException {
        // START CHALLENGE #7
        try (Jedis jedis = jedisPool.getResource()) {
            String key = KeyHelper.getKey("limiter:" + windowSizeMS + ":" + name + ":" + maxHits);
            long now = ZonedDateTime.now().toInstant().toEpochMilli();

            Transaction transaction = jedis.multi();
            String member = now + "-" + Math.random();
            transaction.zadd(key, now, member);
            transaction.zremrangeByScore(key, 0, now - windowSizeMS);
            Response<Long> hits = transaction.zcard(key);
            transaction.exec();

            if (hits.get() > maxHits) {
                throw new RateLimitExceededException();
            }
        }
        // END CHALLENGE #7
    }
}
