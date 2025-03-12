package az.security.bookstore.redis.impl;

import az.security.bookstore.dao.entity.BookEntity;
import az.security.bookstore.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, List<BookEntity>> redisTemplate;

    @Override
    public void saveToCache(String key, List<BookEntity> data, long timeMinutes) {
        redisTemplate.opsForValue().set(key,data,timeMinutes, TimeUnit.MINUTES);
    }
    public List<BookEntity> forGetCache(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
