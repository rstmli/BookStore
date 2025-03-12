package az.security.bookstore.redis;

import az.security.bookstore.dao.entity.BookEntity;

import java.util.List;

public interface RedisService {
    void saveToCache(String key, List<BookEntity> data, long timeMinutes);


}
