package com.hicola.service.impl;

import com.hicola.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/8/18
 * @
 */
@Service
public class RedisServiceImpl implements RedisService {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    // 如果只是单纯地set/get字符串，而不操作对象，可以使用StringRedisTemplate，以避免出现序列化导致的乱码\xAC\xED\x00\x05t\x00\x04
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * spring  boot  帮我们注入的  redisTemplate  类，泛型里面只能写<String, String>、<Object, Object>
     * 如下：
     */
//    private final RedisTemplate<String,String> redisTemplate;

//    private final RedisTemplate<Object, Object> redisTemplate;
    @Override
    public void getValue(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(value)) {
            logger.warn("No found any value in Redis with Key:{}", key);
            return;
        }
        logger.info("Get the value:{} from the Redis with key: {}", value, key);
    }
}
